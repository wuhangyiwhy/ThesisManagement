<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
 <head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>个人信息</title>
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/layui/css/layui.css"/>
  <link rel="stylesheet"   href="${pageContext.request.contextPath}/css/global.css" charset="utf-8" />
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <style>
#images{
	display:none;
}
</style>
 </head>
 <body>
  <div class="fly-header layui-bg-black"> 
   <div class="layui-container"> 
    <a class="fly-logo" href="/">  </a> 
    <ul class="layui-nav fly-nav-user"> 
     <li class="layui-nav-item"> <a class="fly-nav-avatar" href="/user/" id="LAY_header_avatar"> 
     <cite class="layui-hide-xs">${loginfilter.xm } </cite>
     <img  id="images" /> </a> 
      <dl class="layui-nav-child">  
       <dd>
        <a href="${pageContext.request.contextPath}/student/myinfo">我的主页</a>
       </dd> 
       <dd>
        <a href="${pageContext.request.contextPath}/thesis/addthesis">论文上传</a>
       </dd> 
       <hr style="margin: 5px 0;" /> 
       <dd>
        <a href="${pageContext.request.contextPath}/student/removeSession" style="text-align: center;">退出</a>
       </dd> 
      </dl> </li> 
    </ul> 
   </div>
  </div>
  <div class="layui-container fly-marginTop fly-user-main"> 
    <%@ include file="inc_menu.jsp" %> 
    <div class="fly-panel fly-panel-user"  pad20=""  style="padding-top: 20px;">
    	<div class="layui-tab layui-tab-brief " lay-filter="user"> 
     		<ul class="layui-tab-title " id="LAY_mine">
				<li class="layui-this" lay-id="info">个人信息</li>
				<li class="" lay-id="photo">上传头像</li> 
			</ul>
   <div class="layui-tab-content" style="padding: 20px 0;"> 
      <div class="layui-form layui-form-pane layui-tab-item layui-show"> 
		<form class="layui-form layui-form-pane layui-tab-item layui-show"" action="">
			<div class="layui-form-item">
			  <label class="layui-form-label">姓名</label>
			  <div class="layui-input-inline">
			    <input type="text" name="xm" autocomplete="off" placeholder="请输入" class="layui-input" id="xm" value="${student.xm}">
			  </div>
			</div>
			<div class="layui-form-item">
			  <label class="layui-form-label">身份证号</label>
			  <div class="layui-input-inline">
			    <input type="text" id="sfzh" name="sfzh" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input"
			     value="${student.sfzh}">
			  </div>
			</div>
			<div class="layui-form-item"> 
			  <label class="layui-form-label">学号</label>
			  <div class="layui-input-inline">
			    <input type="text" id="xh" name="xh" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input"
			     value="${student.xh}"> 
			  </div>
			</div>
			<%-- <div class="layui-form-item">
			  <label class="layui-form-label">学院</label>
			  <div class="layui-input-inline">
			    <input type="text" id="xy" name="xy" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input"
			     value="${student.major.xy}">
			  </div>
			</div> --%>
			<div class="layui-form-item">
			  <label class="layui-form-label">年级</label>
			  <div class="layui-input-inline">
			    <input type="text" id="nj" name="nj" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input"
			     value="${student.nj}">
			  </div>
			</div>   
			<div class="layui-form-item">
				<div class="layui-input-block">
				  <input type="hidden" name="xh" id="xh" value="${student.xh }"/>
				  <button class="layui-btn" lay-submit="" lay-filter="updateMyInfo"  id="updateMyInfo" >修改</button>
				  <button type= "reset" class="layui-btn layui-btn-primary"">取消</button>
				</div>
			</div>
		</form>
      </div>  
      <div class="layui-form layui-form-pane layui-tab-item"> 
	       <div class="layui-form-item">
		       <form class="layui-form layui-form-pane" action=""> 
			       <div class="avatar-add">
					  <div class="layui-upload-list">
					  	<button type="button" class="layui-btn upload-img " id="uploadphoto"> <i class="layui-icon" ></i>上传头像</button>
					    <img class="layui-up load-img" id="demo1" src="${pageContext.request.contextPath}/student/download?filename=${student.image}">
					    <p id="demoText" style="writing-mode:lr-tb;"></p>
					    <p> <button class="layui-btn " lay-submit="" lay-filter="tjzp"><i class="layui-icon" ></i>立即上传</button></p>
					    <p>建议尺寸168*168  支持jpg、png、gif，最大不能超过50KB</p>
					  </div>
					  <input type="hidden" name="xh" id="xh" value="${student.xh }"/>
					  <input type="hidden" name="image" id="image" value=""/>
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
  	<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
  	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>
	if("${student.image}"!=""){ 
		$('#images ').attr('src',"${pageContext.request.contextPath}/student/download?filename=${student.image}");
		$('#images ').css("display","inline-block");
	}else{
		$('#images ').css('display',"none");
	}
layui.use(['upload', 'form','element','laypage'],function(){
	var index;
	var form = layui.form,
	element = layui.element
	,upload = layui.upload;
	
	//照片上传
	   upload.render({
	    elem: '#uploadphoto'
	    ,url: '${pageContext.request.contextPath}/student/douploadphoto'
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo1').attr('src', result); //图片链接（base64）
	      })
	    } 
	    ,done: function(res,obj){
	      if(res.code==0){
	         $("#image").val(res.msg);
	      }else{
	    	  layer.msg('上传失败');
	      }
	    }
	    ,error: function(){
	      //演示失败状态，并实现重传
	      var demoText = $('#demoText');
	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	      demoText.find('.demo-reload').on('click', function(){
	        uploadInst.upload();
	      }); 
	    }
	  });
	
	//照片提交到数据库
	 form.on('submit(tjzp)', function(data){
		  $.ajax({   
                url:'${pageContext.request.contextPath}/student/addphoto',       
                method:'post',       
                data:data.field,        
                dataType:'JSON',     
				beforeSend: function () {
				index = layer.msg('照片提交中',{
	 	    		icon: 0
	 	    		,shade: [0.1,'#fff'] //0.1透明度的白色背景
	 	    		,time: 400000 
				})
			},    
                success:function(res){ 
					layer.close(index)
                    if(res.code=='0'){ 
       					layer.alert('上传成功', {icon: 1});
						setTimeout(function(){window.location.reload();},5000);
                    }else            
                      layer.alert('上传失败', {icon: 2});   
                    },               
                error:function (data) { }           
         }); 
	    return false;
	  });
	
	//修改信息
	form.on('submit(updateMyInfo)', function(data){
		if(layer.confirm('确定修改吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				 $.ajax({   
		           url:'${pageContext.request.contextPath}/student/updateMyInfo ',       
		           method:'post',       
		           data:data.field,        
		           dataType:'JSON',     
		           success:function(res){ 
		               if(res.code==0){ 
		       		       layer.msg(res.msg);
		 				   setTimeout(function(){window.location.reload();},1000);
		                     }else            
		                       layer.msg(res.msg);   
		                     },               
                   error:function (data) { }           
		          })
				},function(){
					window.location.reload();
				}
			)
 		)
			return false;
 	})
})
</script>
</body>
</html>