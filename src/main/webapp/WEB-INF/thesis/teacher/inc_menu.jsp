<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	.layout-blogger-typemenuli
	{
		background-color: #399;
	}
</style>
<body>
	<ul class="layui-nav layui-nav-tree layui-inline lay-filter="user" "  > 
		<li class="layui-nav-item "> <a href="${pageContext.request.contextPath}/teacher/stu/list" id="menu"> <i class="layui-icon">&#xe613</i>  学生管理 </a> </li> 
		<li class="layui-nav-item"> <a href="${pageContext.request.contextPath}/teacher/thesis/list"><i class="layui-icon">&#xe655;</i> 论文管理</a> </li> 
		<li class="layui-nav-item"> <a href="${pageContext.request.contextPath}/teacher/major/majorlist"><i class="layui-icon">&#xe655;</i> 学院管理</a> </li> 
		<li class="layui-nav-item"> <a onclick="login_out()"> <i class="layui-icon layui-icon-close-fill"></i> 退出系统</a> </li>
	</ul>
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript">
		function login_out(){
			layer.confirm('您确定退出吗？', { 
				btn: ['确定','取消'] //按钮
				}, function(){
				  window.location.href="${pageContext.request.contextPath}/teacher/loginOut";
				}, function(){
				 
				});
		}
		
	</script>
</body>
</html>