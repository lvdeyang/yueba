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
			<input type="hidden" name="uuid" value="${merchant.uuid}">
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopName" readonly="readonly"
						lay-verify="title" value="${merchant.shopName}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录名称 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginName" readonly="readonly"
						lay-verify="title" value="${merchant.shopLoginName}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 登录密码 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLoginPwd" readonly="readonly"
						lay-verify="title" value="${merchant.shopLoginPwd}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 头像图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${merchant.shopHeading}" id="shopHeadingPic" style=" height:100px;width:100px ">
					<input type="hidden" name="shopHeading" id="shopHeading"
						value="${merchant.shopHeading}" autocomplete="off"
						class="layui-input">
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 企业资质图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
				<img alt="" src="${merchant.shopQualifications}" id="shopQualificationsPic" style="height: 100px; width: 100px"/>
					<input type="hidden" name="shopQualifications" id="shopQualifications"
						lay-verify="title" value="${merchant.shopQualifications}" autocomplete="off"
						class="layui-input">
				</div>
				
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> QQ </label>
				<div class="layui-input-block">
					<input type="text" name="shopQQ" readonly="readonly"
						lay-verify="title" value="${merchant.shopQQ}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系电话 </label>
				<div class="layui-input-block">
					<input type="text" name="shopTel" readonly="readonly"
						lay-verify="title" value="${merchant.shopTel}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户地址 </label>
				<div class="layui-input-block">
					<input type="text" name="shopAddress" readonly="readonly"
						lay-verify="title" value="${merchant.shopAddress}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 银行卡号 </label>
				<div class="layui-input-block">
					<input type="text" name="shopBankId" readonly="readonly"
						lay-verify="title" value="${merchant.shopBankId}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 开户行 </label>
				<div class="layui-input-block">
					<input type="text" name="shopOpenBank" readonly="readonly"
						lay-verify="title" value="${merchant.shopOpenBank}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 联系人 </label>
				<div class="layui-input-block">
					<input type="text" name="shopLinkperson" readonly="readonly"
						lay-verify="title" value="${merchant.shopLinkperson}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 显示图片 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${merchant.shopPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="L_title" name="shopPic" 
						lay-verify="title" value="${merchant.shopPic}" autocomplete="off"
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
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl2">
					<input type="hidden" id="img2" name="imgs" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl3">
					<input type="hidden" id="img3" name="imgs" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl4">
					<input type="hidden" id="img4" name="imgs" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl5">
					<input type="hidden" id="img5" name="imgs" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl6">
					<input type="hidden" id="img6" name="imgs" >
					</td>
					</tr>
				</table>
					<input type="hidden" name="shopMpic" 
						 value="${merchant.shopMpic}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商户简介 </label>
				<div class="layui-input-block">
					<textarea  id="shopIntroduction" name="shopIntroduction" style="height: 300px" readonly="readonly"
						 autocomplete="off" class="layui-input" >
						${merchant.shopIntroduction}</textarea>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 坐标 </label>
				<div class="layui-input-inline">
					<input type="text" id="shopLongitude" name="shopLongitude" readonly="readonly"
						lay-verify="title" value="${merchant.shopLongitude}" autocomplete="off"
						class="layui-input">
				</div>

				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline">
					<input type="text" id="shopLatitude" name="shopLatitude" readonly="readonly"
						lay-verify="title" value="${merchant.shopLatitude}" autocomplete="off"
						class="layui-input">
				</div>
				<div class="layui-input-inline" style="width: 100px;">
				  <a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >打开地图</a>
				 
				</div>
			</div>
			

			
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
	<script>
			
             
             
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

