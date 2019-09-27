<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>
 <head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>学院管理</title>
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/layui/css/layui.css"/>
  <link rel="stylesheet"   href="${pageContext.request.contextPath}/css/global.css" charset="utf-8" />
 </head>
 <body>
  <div class="fly-header ">  
   <div class="layui-container"> 
	<!-- <a class="fly-logo" href="/">  </a>  -->
    <ul class="layui-nav fly-nav-user"> 
     <li class="layui-nav-item">
	      <a class="fly-nav-avatar" href="${pageContext.request.contextPath}/student/index" id="LAY_header_avatar"> 
		      <cite class="layui-hide-xs">${teacher.teacherName } </cite>
	      </a> 
	      <dl class="layui-nav-child layui-anim layui-anim-upbit"> 
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
	      </dl> 
      </li> 
    </ul> 
   </div>
  </div>
  <div class="fly-header "> 
  	<div class="layui-container"> 
	    <img src="${pageContext.request.contextPath}/images/logo.jpg" /> 
	</div>
  </div>
  <div class="layui-container fly-marginTop fly-user-main" >
  <%@ include file="../inc_menu.jsp" %> 
  <div class="fly-panel fly-panel-user"  pad20="" >
		<div class="layui-tab layui-tab-brief" lay-filter="user">
			<ul class="layui-tab-title" id="LAY_mine">
				<li class="layui-this" lay-id="info">学院管理</li>
			</ul>
			<div  style="padding-top: 10px;">
				  <button type="button" class="layui-btn" id="import_data"><i class="layui-icon"></i>导入数据</button>
				  <a  class="layui-btn" id="export_data" href="${pageContext.request.contextPath}/teacher/major/export"><i class="layui-icon">&#xe601;</i>导出数据</a>  &nbsp; 
				  <button type="button" class="layui-btn" id="zjzy" onclick="zjzy()"><i class="layui-icon"></i>增加专业</button
			</div> 
			<table id="demo" lay-filter="test"></table> 
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
	var index
	layui.use([ 'upload', 'form', 'element', 'table' ],function() {
		var form = layui.form, upload = layui.upload, element = layui.element, table = layui.table;
			table.render({
				elem : '#demo',
					height : 500,
					url : '${pageContext.request.contextPath}/teacher/major/querymajor' //数据接口
					,page : true //开启分页
					,toolbar : '#toolbarDemo',
					id : 'thesismanage',
					cols : [ [ //表头
						{field : 'xy',title : '学院',sort : true,fixed : 'left',edit: 'text'}
					   ,{field : 'zy',title : '专业',sort : true,edit: 'text'} 
					   ,{fixed : 'right',title : '操作',toolbar : '#barDemo',width : 210} 
				   	]]
				});
			
			//监听行工具事件
			  table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			    var data = obj.data //获得当前行数据
			    ,layEvent = obj.event; //获得 lay-event 对应的值
				 if(layEvent === 'del'){
			      layer.confirm('真的删除行么', function(index){
			        obj.del(); //删除对应行（tr）的DOM结构
			   	 $.ajax({ 
		 		     type : "POST", //提交方式 
		 		     url :"${pageContext.request.contextPath}/teacher/major/delmajor",//路径 
		 		     data:
		 		     { 
		 		    	"id":data.id
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
			    } else if(layEvent === 'edit'){
		    	  layer.open({
		    		  type: 2,
		    		  area: ['600px', '300px'], 
		    		  fixed: false, //不固定
		    		  maxmin: true,
		    		  content: '${pageContext.request.contextPath}/teacher/major/editmajor?id='+data.id 
		    		});
			  	}
			  });
			
		})
		function zjzy(){
			layer.open({
	    		  type: 2,
	    		  area: ['600px', '300px'], 
	    		  fixed: false, //不固定
	    		  maxmin: true,
	    		  content: '${pageContext.request.contextPath}/teacher/major/addmajor'
	    	});
			}
	</script> 	 
 
 </body>
</html>