<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>
 <head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>论文管理</title>
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
  <div class="fly-panel fly-panel-user"  pad20=""  style="padding-top: 20px;">
		<div class="layui-tab layui-tab-brief" lay-filter="user">
			<ul class="layui-tab-title" id="LAY_mine">
				<li class="layui-this" lay-id="info">论文管理</li>
			</ul> 
			<div class="demoTable" style="float: right;">
			  <div class="layui-inline"> 
			    <input class="layui-input" name="keyword" id="demoReload" autocomplete="off" placeholder="请输入学号或学院关键字"> 
			  </div>
			  <button class="layui-btn" data-type="reload" id="search"><i class="layui-icon layui-icon-search"></i></button>
			</div>
			<div class="layui-tab-content" style="padding: 28px  0;"> 
				<table id="demo" lay-filter="test"></table>
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
		<a class="layui-btn layui-btn-xs" lay-event="downloadlw"  >导出论文</a>
		<a class="layui-btn layui-btn-xs" lay-event="downloadfj"  >导出附件</a>
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
						url : '${pageContext.request.contextPath}/teacher/thesis/listpage' //数据接口
						,page : true //开启分页
						,toolbar : '#toolbarDemo',
						id : 'thesismanage',
						cols : [ [ //表头
							{field : 'xh',title : '学号',sort : true,fixed : 'left'}
						   ,{field : 'tm',title : '题目',sort : true}
						   ,{title : '姓名',sort : true,templet:'<div>{{createrFormat(d.student.xm)}}</div>'}
						   ,{title : '年级',sort : true,templet:'<div>{{createrFormat(d.student.nj)}}</div>'}
						   ,{field:'xy',title : '学院',sort : true,templet:'<div>{{createrFormat(d.collegesAndMajors.xy)}}</div>'}
						   ,{field:'zy',title : '专业',sort : true,templet:'<div>{{createrFormat(d.collegesAndMajors.zy)}}</div>'}
						   ,{field : 'lwcclj',title : '论文',sort : true}
						   ,{field : 'fjcclj',title : '附件',sort : true}
						   ,{fixed : 'right',title : '操作',toolbar : '#barDemo',width : 210} 
					   	]]
					});
				//表格重载 通过学号条件查询
				var $ = layui.$, active = {
					reload : function() { 
						var demoReload = $('#demoReload');
						//执行重载
						table.reload('thesismanage',{
							page : {
								curr : 1	//重新从第 1 页开始
							}
							,page:true
							,where : {
								key:{
									keyword:demoReload.val()
								}
							} 
						});
					}
				};
				$('.demoTable .layui-btn').on('click', function(data) {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
			 

				//删除论文及单独下载论文
				 table.on('tool(test)', function(obj){
					   var data = obj.data,
					   layEvent=obj.event;
					   //console.log(obj) 
					   if(obj.event === 'del'){
					     layer.confirm('真的删除行么', function(index){
				      		 $.ajax({ 
				 		     type : "POST", //提交方式 
				 		     url :"${pageContext.request.contextPath}/teacher/delThesisInfo",//路径 
				 		     data:
				 		     { 
				 		    	xh:data.xh 
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
					});
				}else if ( layEvent=== 'downloadlw') {
					if(data.lwcclj==""){
						layer.msg('论文文件不存在');
					}else{
						window.location.href="${pageContext.request.contextPath}/thesis/download?filename="+ data.lwcclj;
					}
				}else if ( layEvent=== 'downloadfj') {
					layer.confirm('附件可能有点大，确定下载吗？',function(index){
						layer.close(index);
						if(data.fjcclj==""){
							layer.msg('附件文件不存在');
						}else{
							window.location.href="${pageContext.request.contextPath}/thesis/download?filename="+ data.fjcclj;
						}
					})
				}
			}) 
			
		})
	</script> 	 
 
 </body>
</html>