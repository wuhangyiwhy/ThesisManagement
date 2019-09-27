<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core.js"></script>
<title>Insert title here</title>
	<SCRIPT type="text/javascript">
		<!--
		var setting = { 
				data: { 
					simpleData: {
						enable: true
					}
				} , 
				view: {
					fontCss: getFont,
					nameIsHTML: true
				} 
			};  
		var zNodes =[ 
			<c:forEach items="${lsitem}" var="lsitem" varStatus="stat">  
		 		{ id:${lsitem.id},pId:${lsitem.pid}, name:"${lsitem.name}",font:{'font-size':100},url:"${lsitem.url}",target:"${lsitem.target}"
		 		 , icon:"${pageContext.request.contextPath}/ztree/css/zTreeStyle/img/diy/1_open.png"} 
		 		<c:if test="${!stat.last}" >,</c:if>
	 	 	</c:forEach>   
		];   
		
		function getFont(treeId, node) {
			return node.font ? node.font : {};
		}
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//-->
	</SCRIPT> 
</head>
<body>
<ul id="treeDemo" class="ztree"></ul>
</body>
</html>