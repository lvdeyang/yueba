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
                                                        积分转换率
                    </label>
                    <div class="layui-input-block">
                        <input type="text" value="${config.changeRate}" id="changeRate" name="changeRate" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             支付转换率
                    </label>
                    <div class="layui-input-block">
                        <input type="text" value="${config.orderChangeRate}" id="payChangeRate" name="payChangeRate" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             小票广告位
                    </label>
                    <div class="layui-input-block">
                        <input type="text" value="${config.cardDes}" id="cardDes" name="cardDes" required lay-verify="title"
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
              
          
                $.ajax({
                	  type:"post",
           			  url:"save",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("保存成功", {icon: 6},function () {
                              location.reload();
                           });
                        } else {
 
                        }
                       }
                }) 


                return false;
              });
              
            });
            
               
          
        </script>
        
    </body>

</html>