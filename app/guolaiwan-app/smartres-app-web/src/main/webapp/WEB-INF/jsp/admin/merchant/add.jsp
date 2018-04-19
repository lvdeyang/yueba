<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>过来玩餐厅管理平台</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
	<div class="x-body" style="height: 1800px;">
		
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopName" autocomplete="off" class="layui-input">
						<!-- <input type="text" name="shopName" required
						lay-verify="required" autocomplete="off" class="layui-input"> -->
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginName" id="shopLoginName"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录密码 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginPwd"  autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 头像图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
				<img alt="" src="" id="shopHeadingPic" style=" height:100px;width:100px ">
					<input type="hidden" name="shopHeading" id="shopHeading"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-input-inline">
				
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopHeading&img=shopHeadingPic','600','400')" class="layui-btn" >图片素材</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 企业资质图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
				<img alt="" src="" id="shopQualificationsPic" style="height: 100px; width: 100px"/>
					<input type="hidden" name="shopQualifications" id="shopQualifications"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopQualifications&img=shopQualificationsPic','600','400')" class="layui-btn" >图片素材</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> QQ </label>
				<div class="layui-input-block">
					<input type="text" name="shopQQ" 
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系电话 </label>
				<div class="layui-input-block">
					<input type="text" name="shopTel" 
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户地址 </label>
				<div class="layui-input-block">
					<input type="text" name="shopAddress"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 银行卡号 </label>
				<div class="layui-input-block">
					<input type="text" name="shopBankId"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 开户行 </label>
				<div class="layui-input-block">
					<input type="text" name="shopOpenBank"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系人 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLinkperson"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 显示图片 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
				
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn" >图片素材</a>
				</div>
			</div>
		     <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 轮播图 </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td align="center">
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl1">
					<input type="hidden" id="img1" name="imgs" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img1&img=imgurl1','600','400')" class="layui-btn" >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl2">
					<input type="hidden" id="img2" name="imgs" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img2&img=imgurl2','600','400')" class="layui-btn" >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl3">
					<input type="hidden" id="img3" name="imgs" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img3&img=imgurl3','600','400')" class="layui-btn" >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl4">
					<input type="hidden" id="img4" name="imgs" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img4&img=imgurl4','600','400')" class="layui-btn" >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl5">
					<input type="hidden" id="img5" name="imgs" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img5&img=imgurl5','600','400')" class="layui-btn" >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl6">
					<input type="hidden" id="img6" name="imgs" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img6&img=imgurl6','600','400')" class="layui-btn" >图片素材</a>
					</td>
					</tr>
				</table>
				
					<input type="hidden" name="shopMpic"  autocomplete="off" class="layui-input" >
		        </div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户简介 </label>
				<div class="layui-input-block">
					<textarea  id="shopIntroduction" name="shopIntroduction" style="height: 300px"
						autocomplete="off" class="layui-input">
						</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 坐标 </label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text"  name="shopLongitude" id="shopLongitude"  autocomplete="off" class="layui-input">
						
				</div>
				<div class="layui-form-mid">-</div>
      		<div class="layui-input-inline" style="width: 100px;">
				<input type="text" name="shopLatitude" id="shopLatitude"  autocomplete="off" class="layui-input">
				</div>
				  <div class="layui-input-inline" style="width: 100px;">
				  <a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >打开地图</a>
				 
				</div>
			</div>
			<input type="hidden" name="shopAuditstates" value="" class="layui-input">
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add1" lay-submit >
						提交审核
                    </button>
                    <button class="layui-btn layui-btn-danger" lay-filter="add2" lay-submit >
						存为草稿
                    </button>
                </div>
		</form>
	</div>
	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8">
        </script>


	<script>
	  //实例化编辑器
    var um = UM.getEditor('shopIntroduction');
    
           
            
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;


              //监听提交
              form.on('submit(add1)', function(data){
          	
              data.field.shopAuditstates = "D";
              var message = "提交待审核成功！";  
              add(data,message);
              return false;
              });
              //监听提交
              form.on('submit(add2)', function(data){
              
               data.field.shopAuditstates = "C";
               var message = "保存草稿成功！";  
               add(data,message);
               return false;
              });
              
              
            
            });
            
            function add(data,message) {
            	
				
            	 var imgs="";
              $("input[name='imgs']").each(function(){
               console.log($(this).val());
              	if($(this).val()!=""){
	              	if(imgs==""){
	              		imgs=$(this).val();
	              	}else{
	              		imgs+=","+$(this).val();
	              	}
              	}
              })
             
              $("input[name='shopMpic']").val(imgs);
              
              console.log(data.field);
                 $.ajax({
                	  type:"post",
           			  url:"add.do",
                      data:data.field,
                      success:function(msg){
                      	if(msg=="has"){
                      		layer.alert("登录名的已经注册过了");
                      	}
                        if(msg=="success"){
                          layer.alert(message, {icon: 6},function () {
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.window.location.reload();
                               parent.layer.close(index);
                             }catch(exception)
                             {
                               window.location.reload();
                             }
                           });
                        }
                       }
                })
            }
            
            // 验证商户登录名称是否重复
            $("#shopLoginName").blur(function() {
            	var url = "verifyLoginName.do";
            	var param = {"shopLoginName":$(this).val()};
            	$.post(url,param,function(result) {
	            	if (result=="has") {
	            		layer.alert("登录名称已存在，请更换！", {icon: 6});
	            	}
            	});
            	
            	console.log(param);
            });
            
            //打开地图
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
            //打开分类
            function openClass (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
            function showClass() {
           		alert("111");
            }
        </script>

</body>

</html>