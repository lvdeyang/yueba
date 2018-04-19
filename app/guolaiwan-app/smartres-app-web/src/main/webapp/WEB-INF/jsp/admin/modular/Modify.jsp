<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            过来玩餐厅管理平台
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form layui-form-pane">
            <input type="hidden" name="uuid" value="${list.uuid}">
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             标识
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="modularCode" required lay-verify="title" value="${list.modularCode}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             板块名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="modularName" required lay-verify="title"value="${list.modularName}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            是否显示
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" id="L_title" name="modularIsv" required lay-verify="title" value="${list.modularIsv}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 
				图片
				 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="${list.modularPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="modularPic" 
						lay-verify="title" autocomplete="off"  class="layui-input" value="${list.modularPic}">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn " >更换图片</a>
				</div>
			</div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                     
                    <button class="layui-btn" lay-filter="add" lay-submit>
                        保存
                    </button>
                </div>
            </form>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
               var form = layui.form
              ,layer = layui.layer;
               
              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"update.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.layer.close(index);
                           });
                        }
                       }
                }) 
                //发异步，把数据提交给php
               
                return false;
              });
            });
             //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
             
        </script>
        
    </body>

</html>
