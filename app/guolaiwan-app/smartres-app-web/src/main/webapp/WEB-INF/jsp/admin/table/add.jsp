<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="../layui/css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             号码
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="num" name="num" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             人数
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="count" name="count" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             预定价格
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="reversePrice" name="reversePrice" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" lay-submit>
                        保存
                    </button>
                </div>
            </form>
        </div>
        <script src="../layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="../layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer','jquery'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
			
			
			
         
              //监听提交
              form.on('submit(add)', function(data){
              
          
          
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"add",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("增加成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        } else {
                        	//layer.alert("");
                        }
                       }
                }) 
                //发异步，把数据提交给php

                return false;
              });
              
            });
            
               
          
        </script>
        
    </body>

</html>