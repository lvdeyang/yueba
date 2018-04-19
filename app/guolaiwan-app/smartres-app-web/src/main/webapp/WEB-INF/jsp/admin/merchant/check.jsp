<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
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
			<input type="hidden" name="uuid" value="${list.uuid}">
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopName" disabled="true"
						lay-verify="title" value="${list.shopName}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginName" disabled="true"
						lay-verify="title" value="${list.shopLoginName}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录密码 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginPwd" disabled="true"
						lay-verify="title" value="${list.shopLoginPwd}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 头像图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${list.shopHeading}" id="shopHeadingPic" style=" height:100px;width:100px ">
					<input type="hidden" name="shopHeading" id="shopHeading" disabled="true"
						value="${list.shopHeading}" autocomplete="off"
						class="layui-input">
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 企业资质图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
				<img alt="" src="${list.shopQualifications}" id="shopQualificationsPic" style="height: 100px; width: 100px"/>
					<input type="hidden" name="shopQualifications" id="shopQualifications" disabled="true"
						lay-verify="title" value="${list.shopQualifications}" autocomplete="off"
						class="layui-input">
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> QQ </label>
				<div class="layui-input-block">
					<input type="text" name="shopQQ" 
						lay-verify="title" value="${list.shopQQ}" autocomplete="off" disabled="true"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系电话 </label>
				<div class="layui-input-block">
					<input type="text" name="shopTel" 
						lay-verify="title" value="${list.shopTel}" autocomplete="off" disabled="true"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户地址 </label>
				<div class="layui-input-block">
					<input type="text" name="shopAddress" 
						lay-verify="title" value="${list.shopAddress}" autocomplete="off" disabled="true"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 银行卡号 </label>
				<div class="layui-input-block">
					<input type="text" name="shopBankId" 
						lay-verify="title" value="${list.shopBankId}" autocomplete="off" disabled="true"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 开户行 </label>
				<div class="layui-input-block">
					<input type="text" name="shopOpenBank" 
						lay-verify="title" value="${list.shopOpenBank}" autocomplete="off" disabled="true"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系人 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLinkperson" 
						lay-verify="title" value="${list.shopLinkperson}" autocomplete="off" disabled="true"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 显示图片 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${list.shopPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="L_title" name="shopPic" 
						lay-verify="title" value="${list.shopPic}" autocomplete="off" disabled="true"
						class="layui-input">
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
	
					</tr>
				</table>
					<input type="hidden" name="shopMpic" disabled="true"
						 value="${list.shopMpic}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户简介 </label>
				<div class="layui-input-block">
					<textarea  id="shopIntroduction" name="shopIntroduction" style="height: 300px" disabled="true"
						 autocomplete="off" class="layui-input" >
						${list.shopIntroduction}</textarea>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 坐标 </label>
				<div class="layui-input-inline">
					<input type="text" id="shopLongitude" name="shopLongitude" disabled="true"
						lay-verify="title" value="${list.shopLongitude}" autocomplete="off"
						class="layui-input">
				</div>

				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline">
					<input type="text" id="shopLatitude" name="shopLatitude" disabled="true"
						lay-verify="title" value="${list.shopLatitude}" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-input-inline" style="width: 100px;">
				  <a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >打开地图</a>
				 
				</div>
			</div>
			
			
			 <div class="layui-form-item">
    			<label class="layui-form-label">审核状态</label>
    			<div class="layui-input-block">
     				<input type="radio" name="shopAuditState" value="T" title="审核通过" checked="">
      				<input type="radio" name="shopAuditState" value="N" title="未通过">
      				<input type="radio" name="shopAuditState" value="D" title="待审核" disabled="">
    			</div>
  			</div>
  			
  			<div class="layui-form-item">
    			<label class="layui-form-label">审核意见</label>
    			<div class="layui-input-block">
     				<input type="text" name="shopAuditopinion" autocomplete="off" class="layui-input">
    			</div>
  			</div>
  			
			<div class="layui-form-item">
				<button class="layui-btn" lay-filter="add" lay-submit>保存</button>
			</div>
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
	<script>
			//实例化编辑器
		    var um = UM.getEditor('shopIntroduction');
    
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
               
              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"checkResult.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
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
                
               
                return false;
              });
            });
             
             
           //打开地图
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }  
            
            //打开分类
            function openClass (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
            
             
        </script>

</body>

</html>
