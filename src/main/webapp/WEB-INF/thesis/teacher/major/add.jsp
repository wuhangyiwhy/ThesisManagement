<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
 <link rel="stylesheet"  href="${pageContext.request.contextPath}/layui/css/layui.css"/>
<title>增加专业</title>
</head>
<body>
  <div class="layui-container fly-marginTop fly-user-main" >
  <div class="fly-panel fly-panel-user"  pad20="">
		<div class="layui-tab layui-tab-brief" lay-filter="user">
			<ul class="layui-tab-title" id="LAY_mine">
				<li class="layui-this" lay-id="info">增加学院</li>
			</ul>
			<div class="layui-tab-content" style="padding: 20px 0;">
				<form class="layui-form layui-form-pane" action="">
					<div class="layui-form-item">
					  <label class="layui-form-label">学院名称</label>
					  <div class="layui-input-block">
					    <input type="text" name="xy" autocomplete="off" placeholder="请输入学院名称" 
					    lay-verify="xy" class="layui-input" >
					  </div>
					</div>
					<div class="layui-form-item">
					  <label class="layui-form-label">专业名称</label>
					  <div class="layui-input-block">
					    <input type="text" name="zy"  placeholder="请输入专业名称" autocomplete="off" lay-verify="zy" 
					     class="layui-input" >
					  </div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
						    <button class="layui-btn" lay-submit="" lay-filter="zjzy">立即提交</button>
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
	 	 //提交信息表单
		 form.on('submit(zjzy)', function(data){
			  $.ajax({   
	                url:'${pageContext.request.contextPath}/teacher/major/zenjiamajor',       
	                method:'post',       
	                data:data.field,        
	                dataType:'JSON',     
					beforeSend: function () {
						index = layer.msg('信息提交中',{
				    		icon: 0
				    		,shade: [0.1,'#fff'] //0.1透明度的白色背景
				    		,time: 400000 
						})
					},    
	                success:function(res){ 
						 var index = parent.layer.getFrameIndex(window.name);
		                 if(res.code=='0'){ 
		      				layer.alert('增加成功',{
							skin:'layui-layer-molv'
							,closeBtn:0
							,anim:4
						  },function(){
							parent.layer.close(index);	
							//刷新父页面的值
							parent.layui.table.reload('thesismanage');
					})
	                    }else            
	                      layer.alert('增加失败', {icon: 2});  
	                    },               
	                error:function (data) { }           
		         }); 
		  			return false;
		});
	})
</script> 	 

</body>
</html>