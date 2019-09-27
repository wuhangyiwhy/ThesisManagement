<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"content="width=device-width, initial-scale=1, maximum-scale=1" />
<title>登录</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/layui.css"/>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/global.css" charset="utf-8" />
</head>
<body>
	<div class="fly-header "> 
	    <div class="layui-container"> 
	    	<img src="${pageContext.request.contextPath}/images/logo.jpg" /> 
	    </div>
	</div>
	<div class="layui-container fly-marginTop" >
		<div class="fly-panel fly-panel-user" pad20="" style="background:url(${pageContext.request.contextPath}/images/backimage.jpg); background-size:100%;">
			<div class="layui-tab layui-tab-brief" lay-filter="user" >
				<ul class="layui-tab-title" id="LAY_mine">
					<li class="layui-this" lay-id="info">登入</li> 
				</ul> 
				<div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 30px 0;" >
					<div class="layui-tab-item layui-show"  >
						<div class="layui-form layui-form-pane" >
							<form method="post" style="text-algin:center;">
								<div class="layui-form-item" > 
									<label for="L_xh" class="layui-form-label" >编号</label>
									<div class="layui-input-inline">
										<input type="text" id="L_teacherNo" name="teacherNo"  required=""
											lay-verify="teacherNo" autocomplete="off" class="layui-input" />
									</div>
								</div>
								<div class="layui-form-item">
									<label for="L_xm" class="layui-form-label">姓名</label>
									<div class="layui-input-inline">
										<input type="text" id="L_teacherName" name="teacherName"
											required=""  lay-verify="teacherName" autocomplete="off"
											class="layui-input" />
									</div>
								</div>
								<div class="layui-form-item">
									<label for="L_vercode" class="layui-form-label">验证码</label>
									<div class="layui-input-inline">
										<input type="text" id="L_imagecode" name="imagecode"
											required="" lay-verify="required" autocomplete="off"
											class="layui-input" />
									</div>
									<div class="layui-form-mid" >
										<img id="imgVerify" src="${pageContext.request.contextPath}/getVerify"  onclick="getVerify()" class="fly-imagecode"  />
									</div>
								</div>
								<div class="layui-form-item">
									<button class="layui-btn" lay-submit="" lay-filter="xsd">立即提交</button>
      								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</span>
								</div>
							</form>
						</div>
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
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<script>
		//点击获取验证码
		function getVerify(){
		     var src1=document.getElementById('imgVerify')
		     src1.src = "${pageContext.request.contextPath}/getVerify?"+Math.random();
		}
		
		layui.use(['form', 'layedit', 'laydate'], function(){
			  var form = layui.form
			  ,layer = layui.layer
			  ,layedit = layui.layedit
			  ,laydate = layui.laydate;			  
			 
			  //自定义验证规则
			/*   form.verify({
			    xh: function(value){
			      if(value.length < 5){
			        return '标题至少得5个字符啊';
			      }
			    }
			  }); */
			  
			  //监听提交
			  form.on('submit(xsd)', function(data){
				  $.ajax({   
	                  url:'${pageContext.request.contextPath}/teacher/dologin',       
	                  method:'post',       
	                  data:data.field,        
	                  dataType:'JSON',         
	                  success:function(res){ 
	                      if(res.code=='0'){ 
		        			layer.msg(res.msg);
		  					window.location.href="${pageContext.request.contextPath}/teacher/stu/list";
	                      }else            
	                        layer.msg(res.msg);   
	                      },               
	                  error:function (data) { }           
	           }); 
			    return false;
			  });
			});
	</script>
</body>
</html>