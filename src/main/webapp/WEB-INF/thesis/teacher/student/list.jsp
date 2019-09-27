<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html> 
 <head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>学生管理</title>
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/layui/css/layui.css"/>
  <link rel="stylesheet"   href="${pageContext.request.contextPath}/css/global.css" charset="utf-8" />
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
 </head>
 <body>
  <div class="fly-header "> 
   <div class="layui-container"> 
    <a class="fly-logo" href="/">  </a> 
    <ul class="layui-nav fly-nav-user"> 
     <li class="layui-nav-item"> <a class="fly-nav-avatar" href="/user/" id="LAY_header_avatar"> 
     <cite class="layui-hide-xs">${teacher.teacherName } </cite>
     </a> 
      <dl class="layui-nav-child">  
       <dd>
        <a href="${pageContext.request.contextPath}/student/index">我的主页</a>
       </dd> 
       <dd>
        <a href="${pageContext.request.contextPath}/thesis/addthesis">论文上传</a>
       </dd> 
       <hr style="margin: 5px 0;" /> 
       <dd>
        <a href="${pageContext.request.contextPath}/teacher/loginOut" style="text-align: center;">退出</a>
       </dd> 
      </dl> </li> 
    </ul> 
   </div>
  </div>
  <div class="fly-header "> 
  	<div class="layui-container"> 
	    <img src="${pageContext.request.contextPath}/images/logo.jpg" /> 
	</div>
  </div>
  <div class="layui-container fly-marginTop fly-user-main"> 
    <%@ include file="../inc_menu.jsp" %> 
    <div class="fly-panel fly-panel-user"  pad20=""  style="padding-top: 20px;">
   
    	<div class="layui-tab layui-tab-brief " lay-filter="user">
     		<ul class="layui-tab-title " id="LAY_mine">
				<li class="layui-this" lay-id="info">学生管理</li>
			</ul>
			<div class="demoTable" style="float: right;">
			  <div class="layui-inline">
			    <input class="layui-input" name="xh" id="demoReload" autocomplete="off" placeholder="请输入学号">
			  </div>
			  <button class="layui-btn" data-type="reload" id="search"><i class="layui-icon layui-icon-search"></i></button>
			</div>
			<div style="float:right">
				  <button type="button" class="layui-btn" id="import_data"><i class="layui-icon"></i>导入数据</button>
				  <a  class="layui-btn" id="export_data" href="${pageContext.request.contextPath}/teacher/stu/export"><i class="layui-icon">&#xe601;</i>导出数据</a>
				  &nbsp; 
			</div>  
			
	   	<div class="layui-tab-content" style="padding: 28px 0;"> 
			<div class="layui-form layui-form-pane layui-tab-item layui-show"> 
				<table id="demo" lay-filter="test" ></table>
			
			</div>  
	    </div>
 	</div>    
 </div>				
</div>
<div class="fly-footer"> 
 	<p>
	<a href="http://www.gzmdrw.cn">贵州民族大学人文科技学院</a> 2019 &copy; <a href="http://www.gzmdrw.cn/">www.gzmdrw.cn</a>
</p>
 </div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
function createrFormat(o){
	return o;
} 
layui.use(['upload', 'form','element','laypage','table','layer'],function(){
	var index;
	var form = layui.form,
	element = layui.element
	,upload = layui.upload
	,table = layui.table
	,laypage = layui.laypage;
	var index;
	 upload.render({
		    elem: '#import_data' 
		    ,url: '${pageContext.request.contextPath}/teacher/stu/import/'
		    ,accept: 'file' //普通文件
	    	,before: function(obj){
	    	      //显示上传中
	 	    	index = layer.msg('导入中',{
	 	    		icon: 0
	 	    		,shade: [0.1,'#fff'] //0.1透明度的白色背景
	 	    		,time: 400000 //（如果不配置，默认是3秒） 
				})
	 	    }
		    ,done: function(res){
		    	layer.close(index);
		    	layer.msg('导入成功', {icon: 1});
		    	setTimeout('window.location.reload()',1000);
		    }
		  });
	 
	 //表格重载 通过学号条件查询
	  var $ = layui.$, active = {
	    reload: function(){
	    var demoReload = $('#demoReload');
	      //执行重载
	      table.reload('testReload', {
	        page: {
	          curr: 1 //重新从第 1 页开始
	        }
	      	,url: '${pageContext.request.contextPath}/teacher/stu/queryStudentInfo' //数据接口
	        ,where: {
        	   xh : demoReload.val()
	        }
	      });
	    }
	  };
	  $('#search').on('click', function(data){
	    	var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
	 		});
	      
	table.render({
	   elem: '#demo'
	   ,height: 500
	   ,url: '${pageContext.request.contextPath}/teacher/stu/listpage' //数据接口
	   ,page: true //开启分页
	   ,id: 'testReload'
	   ,toolbar: '#toolbarDemo'
	   ,cols: [[ //表头
	    {field: 'xh', title: '学号',  sort: true,edit: 'text', fixed: 'left',templet: '#nameTpl'}
	    ,{field: 'xm', title: '姓名', edit: 'text',sort: true,}
	    ,{field: 'sfzh', title: '身份证号',edit: 'text',  sort: true,templet: '#nameTpl'} 
	    ,{field: 'nj', title: '年级', edit: 'text',sort: true}
	    ,{field:'xy',title : '学院',sort : true,templet:'<div>{{createrFormat(d.major.xy)}}</div>',templet: '#nameTpl'}
		,{field:'zy',title : '专业',sort : true,templet:'<div>{{createrFormat(d.major.zy)}}</div>'}
	    ,{field: 'bj', title: '班级',  edit: 'text',sort: true,templet: '#nameTpl'}
	    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
	   ]]
	 });
	
	  //监听删除
	table.on('tool(test)', function(obj){
	   var data = obj.data;
	   //console.log(obj)
	   if(obj.event === 'del'){
	     layer.confirm('真的删除行么', function(index){
      		 $.ajax({ 
 		     type : "POST", //提交方式 
 		     url :"${pageContext.request.contextPath}/teacher/stu/delStudentInfo",//路径 
 		     data:
 		     { 
 		    	"xh":data.xh
 		     },//数据，这里使用的是Json格式进行传输 
 		     success: function(result)
 		     {//返回数据根据结果进行相应的处理 
 			     if (result.code==0) 
 			     { 
 				     layer.msg('删除成功', {icon: 1});
 				     setTimeout('window.location.reload()',1000); 
 			     } 
 		     } 
 		});
	       layer.close(index);
	     });
	   } else if(obj.event === 'edit'){
		   layer.open({
    		  type: 2,
    		  area: ['600px', '400px'], 
    		  fixed: false, //不固定
    		  maxmin: true,
    		  content: '${pageContext.request.contextPath}/teacher/stu/edit?xh='+data.xh 
    		});
		   
       }
	 })
	  

	//修改学生数据
	table.on('edit(test)', function(obj,xh){
     var value = obj.value //得到修改后的值
     ,data = obj.data //得到所在行所有键值
     ,field = obj.field; //得到字段
     $.ajax({ 
     type : "POST", //提交方式 
     url :"${pageContext.request.contextPath}/teacher/stu/updateStudentInfo",//路径 
     data:
     { 
    	   xh:data.xh ,
           field:field,
           fieldvalue:value 
     },//数据，这里使用的是Json格式进行传输 
     success: function(result)
	     {//返回数据根据结果进行相应的处理 
		     if (result.code==0) 
		     { 
			     layer.msg('修改成功', {icon: 1});
			     //setTimeout('window.location.reload()',1000);
			     //console.log(data)
		     } 
	     } 
     , error:function(){
    	 layer.alert('请求错误！请根据规则修改', {
    		  icon: 2,
    		  skin: 'layer-ext-moon' 
    		})
      }
	});
     //layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
   });

	  		
})
</script>
<script type="text/html" id="nameTpl">
	 <a href="/?table-demo-id={{d.id}}" class="layui-table-link" target="_blank">{{ d.xm }}</a>
</script>
</body>
</html>