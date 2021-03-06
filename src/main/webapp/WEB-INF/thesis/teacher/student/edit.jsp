<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>
 <head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>学生信息</title>
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/layui/css/layui.css"/>
 </head>
 <body>
  <div class="layui-container fly-marginTop fly-user-main" >
  <div class="fly-panel fly-panel-user"  pad20="">
		<div class="layui-tab layui-tab-brief" lay-filter="user">
			<ul class="layui-tab-title" id="LAY_mine">
				<li class="layui-this" lay-id="info">学生信息</li>
			</ul>
			<div class="layui-tab-content" style="padding: 20px 0;">
				<form class="layui-form layui-form-pane" action="">
					<div class="layui-form-item">
					  <label class="layui-form-label">身份证号</label>
					  <div class="layui-input-block">
					    <input type="text" name="sfzh" autocomplete="off" placeholder="请输入身份证号" 
					    lay-verify="sfzh" class="layui-input" value="${student.sfzh }">
					  </div>
					</div>
					<div class="layui-form-item">
					  <label class="layui-form-label">学院名称</label>
					  <div class="layui-input-block">
					  	<select lay-filter="changezy" id="changexy" name="major.xy">  
							<c:forEach items="${lsmajor}" var="student" >
					  			<option>${student.xy }</option>
					  		 </c:forEach>
				  		</select>
					  </div>
					</div>
					<div class="layui-form-item">
					  <label class="layui-form-label">专业名称</label>
					  <div class="layui-input-block">
					  	<select  id="selectId" name="major_id"> 
				  			<option></option>
				  		</select>
					  </div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<input type="hidden" name="xh" id ="xh" value="${student.xh}">
						    <button class="layui-btn" lay-submit="" lay-filter="zytj">立即提交</button>
						    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
  	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
  	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
 <script>
 	var index
	layui.use(['upload', 'form','element'],function(){
		 var form = layui.form
		 ,upload = layui.upload
		 ,element = layui.element;
		 //前台判断用户输入的字符
		 form.verify({
			    sfzh: [ /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,'请填入正确的身份证号'] 
		  });
	 	 //提交学院信息
	 	 form.on('submit(zytj)', function(data){
			  $.ajax({   
                url:'${pageContext.request.contextPath}/teacher/stu/save',       
                method:'post',       
                data:data.field,        
                dataType:'JSON',     
				beforeSend: function () {
					index = layer.msg('信息修改中',{
		 	    		icon: 0
		 	    		,shade: [0.1,'#fff'] //0.1透明度的白色背景
		 	    		,time: 400000 
					})
				},    
                success:function(res){ 
	 				var index = parent.layer.getFrameIndex(window.name);
                    if(res.code=='0'){ 
	        			layer.alert('修改成功',{
		  					skin:'layui-layer-molv'
		  					,closeBtn:0
		  					,anim:4
	  					  },function(){
	  						parent.layer.close(index);	
	  						//刷新父页面的值
	  						parent.layui.table.reload('testReload');
	  					})
	  					
                    }else            
                      layer.alert('修改失败', {icon: 2});  
                    },               
                error:function (data) { }           
         }); 
		    return false;
		  }); 
	 	form.on('select(changezy)', function(data){
	 		$("#selectId").empty();
	   		console.log(data.value); //得到被选中的值
	   	 	$.ajax({   
                url:'${pageContext.request.contextPath}/teacher/major/findzy',       
                method:'post',       
                data:{xy:data.value},        
                dataType:'JSON',     
                success:function(res){ 
                    if(res.code=='0'){ 
	        			for(var j=0;j<res.data.length;j++)
         				 $("#selectId").append("<option value='"+res.data[j].id+"'>"+res.data[j].zy+"</option>");
	 					 form.render('select');
                    }else            
                      layer.alert('提交失败', {icon: 2});  
                    },               
                error:function (data) { }           
         	});
		
	 	});
	})
</script> 	 
 
 </body>
</html>