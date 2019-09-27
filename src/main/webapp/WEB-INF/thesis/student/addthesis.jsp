<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>
 <head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>论文上传</title>
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/layui/css/layui.css"/>
  <link rel="stylesheet"   href="${pageContext.request.contextPath}/css/global.css" charset="utf-8" />
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"> </script>
  <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
  <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
  <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<style>
#mythesislink,#mythesislink2,#downloadthesis{
	display:none;
}
</style>
 </head>
 <body>
  <div class="fly-header layui-bg-black">  
   <div class="layui-container"> 
	<!-- <a class="fly-logo" href="/">  </a>  -->
    <ul class="layui-nav fly-nav-user"> 
     <li class="layui-nav-item">
	      <a class="fly-nav-avatar" href="${pageContext.request.contextPath}/student/index" id="LAY_header_avatar"> 
		      <cite class="layui-hide-xs">${loginfilter.xm } </cite>
		      <img id="images" />
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
	        <a href="${pageContext.request.contextPath}/student/removeSession" style="text-align: center;">退出</a>
	       </dd>
	      </dl> 
      </li> 
    </ul> 
   </div>
  </div>
  <div class="layui-container fly-marginTop fly-user-main" >
  <%@ include file="inc_menu.jsp" %> 
  <div class="fly-panel fly-panel-user"  pad20=""  style="padding-top: 20px;">
		<div class="layui-tab layui-tab-brief" lay-filter="user">
			<ul class="layui-tab-title" id="LAY_mine">
				<li class="layui-this" lay-id="info">上传论文</li>
			</ul>
			<div class="layui-tab-content" style="padding: 20px 0;">
				<form class="layui-form layui-form-pane" action="">
					<div class="layui-form-item">
					  <label class="layui-form-label">题目</label>
					  <div class="layui-input-block">
					    <input type="text" name="tm" autocomplete="off" placeholder="请输入标题" 
					    lay-verify="tm" class="layui-input" value="${thesis.tm }">
					  </div>
					</div>
					<div class="layui-form-item">
					  <label class="layui-form-label">关键字</label>
					  <div class="layui-input-block">
					    <input type="text" name="gjz"  placeholder="请输入" autocomplete="off" lay-verify="gjz" 
					     class="layui-input" value="${thesis.gjz }">
					  </div>
					</div>
					<div class="layui-form-item layui-form-text">
					   <label class="layui-form-label"> 摘要</label>
					   <div class="layui-input-block">
					     <textarea placeholder="请输入内容" class="layui-textarea" name="zy"  lay-verify="zy">${thesis.zy}</textarea>
					   </div>
					</div>
					<div class="layui-form-item layui-form-text">
					   <label class="layui-form-label">论文正文</label>
					   <div class="layui-input-block">
					    <script id="editor" type="text/plain" name="zw"  style="width:915px;height:300px;">${thesis.zw }</script>
					   </div>
					</div>
					<div class="layui-form-item ">
					   <label class="layui-form-label">上传论文</label>
					   <div class="layui-input-block">
					     <button type="button" class="layui-btn layui-btn-primary" id="test3">
							<i class="layui-icon"></i>上传论文
						 </button>
						 <input type="hidden" name="lwcclj" id="lwcclj" value="${thesis.lwcclj }" />
					   </div>
					</div>
					<div class="layui-form-item ">
					   <label class="layui-form-label">上传附件</label>
					   <div class="layui-input-block">
					     <button type="button" class="layui-btn layui-btn-primary"  id="test4">
							<i class="layui-icon"></i>上传附件
						 </button>
						 <input type="hidden" name="fjcclj" id="fjcclj"  value="${thesis.fjcclj }" />
					   </div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<input type="hidden" name="xh" id="xh" value="${thesis.xh }"/>
						    <button class="layui-btn" lay-submit="" lay-filter="lwtj">立即提交</button>
						    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					</div>
				</form>
			</div>
			<fieldset class="layui-elem-field" id="downloadthesis">
				<legend>下载</legend>
				<div class="layui-field-box">
				 <div>
					<span ><a href=""  id="mythesislink">下载我的论文</a></span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span ><a href=""  id="mythesislink2">下载我的附件</a></span>
				</div>
			</fieldset>
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
 	var ue = UE.getEditor('editor');
 	var index
	layui.use(['upload', 'form','element'],function(){
		 var form = layui.form
		 ,upload = layui.upload
		 ,element = layui.element;
		 //前台判断用户输入的字符
		 form.verify({
			    tm: function(value){
			      if(value.length >10){
			        return '题目不能超过10个字符啊';
			      }
			    },
			    gjz:function(value){
			    	if(value.length >20)
			    	{
					  return '关键字不能超过20个字符啊';
					}
			    },
			    zy:function(value){
			       if(value.length >100)
			    	 {
					    return '摘要不能超过100个字符啊';
					 }
			    }
			  });
		 //判断是否上传了论文和附件
		if("${thesis.lwcclj }"!=""){
			$("#mythesislink").css("display","inline-block");
	    	$("#downloadthesis").css("display","block");
	    	$('#mythesislink ').attr('href',"${pageContext.request.contextPath}/thesis/download?filename=${thesis.lwcclj }"); 
	    	$("#mythesislink ").text('下载我的论文');
		}
		if ("${thesis.fjcclj }"!=""){
			$("#downloadthesis").css("display","block");
			$("#mythesislink2").css("display","inline-block");
	    	$('#mythesislink2 ').attr('href',"${pageContext.request.contextPath}/thesis/download?filename=${thesis.fjcclj }"); 
	    	$("#mythesislink2 ").text('下载我的附件');
		}
		//接收后台数据上传论文
	 	 upload.render({
	 	    elem: '#test3'
	 	    ,url: '${pageContext.request.contextPath}/thesis/douploadthesis'
	 	    ,accept: 'file'
 	    	//,exts: 'zip|rar|7z|' //只允许上传压缩文件
 	    	,size: 5120000 //限制文件大小，单位 KB
	 	    ,before: function(obj){
	    	      //显示上传中
	 	    	index = layer.msg('文件上传中',{
	 	    		icon: 0
	 	    		,shade: [0.1,'#fff'] //0.1透明度的白色背景
	 	    		,time: 400000 //（如果不配置，默认是3秒） 
				})
	 	    }
	 	    ,done: function(res){
	 	    	//关闭上传中
 	    		layer.close(index);
	 	    	if(res.code==0){
	 	    		layer.alert("论文上传成功");
	 	    		$("#mythesislink").css("display","inline-block");
	 		    	$("#downloadthesis").css("display","block");
	 		    	$('#mythesislink ').attr('href',"${pageContext.request.contextPath}/thesis/download?filename="+res.msg); 
	 		    	$("#mythesislink ").text('下载论文');
	 	    		$("#lwcclj").val(res.msg);
	 	    	}else{
	 	    		layer.alert(res.msg);
	 	    	}
	 	    }
	 	  });
	 	 //接收后台数据上传附件
	 	upload.render({
	 	    elem: '#test4'
	 	    ,url: '${pageContext.request.contextPath}/thesis/douploadthesis'
	 	    ,accept: 'file'
 	    	//,exts: 'zip|rar|7z|' //只允许上传压缩文件
 	    	,size: 5120000 //限制文件大小，单位 KB
	 	    ,before: function(obj){
	    	      //显示上传中
	 	    	index = layer.msg('文件上传中',{
	 	    		icon: 0,
	 	    		time: 400000 //（如果不配置，默认是3秒） 
				})
	 	    }
	 	    ,done: function(res){
	 	    	//关闭上传中
 	    		layer.close(index);
	 	    	if(res.code==0){
	 	    		layer.alert("附件上传成功");
	 	    		$("#downloadthesis").css("display","block");
	 				$("#mythesislink2").css("display","inline-block");
	 		    	$('#mythesislink2 ').attr('href',"${pageContext.request.contextPath}/thesis/download?filename="+res.msg); 
	 		    	$("#mythesislink2 ").text('下载附件');
	 	    		$("#fjcclj").val(res.msg);
	 	    	}else{
	 	    		layer.alert(res.msg);
	 	    	}
	 	    }
	 	  });
	 	 //提交论文表单
	 	 form.on('submit(lwtj)', function(data){
			  $.ajax({   
                url:'${pageContext.request.contextPath}/thesis/save',       
                method:'post',       
                data:data.field,        
                dataType:'JSON',     
				beforeSend: function () {
					index = layer.msg('论文提交中',{
		 	    		icon: 0
		 	    		,shade: [0.1,'#fff'] //0.1透明度的白色背景
		 	    		,time: 400000 
					})
				},    
                success:function(res){ 
				layer.close(index)
                    if(res.code=='0'){ 
	        			layer.alert('上传成功', {icon: 1});
                    }else            
                      layer.alert('上传失败', {icon: 2});  
                    },               
                error:function (data) { }           
         }); 
		    return false;
		  }); 
	})
</script> 	 
 
 </body>
</html>