<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>过来玩餐厅管理平台</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>餐桌管理</cite></a>
			<a><cite>餐桌图形列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">
            <c:forEach items="${tables}" var="table">
            <c:if test="${table.status=='未使用'}">
              <div style="width:100px;height:100px;float:left;margin-left:10px;margin-top:10px;background:#7CAE23;text-align:center;line-height:100px" onclick="showOrder(${table.id})">${table.num}</div>
            
            </c:if>
            <c:if test="${table.status=='使用中'}">
              <div style="width:100px;height:100px;float:left;margin-left:10px;margin-top:10px;background:#ff8444;text-align:center;line-height:100px" onclick="showOrder(${table.id})">${table.num}</div>
            
            </c:if>
            <c:if test="${table.status=='已预定'}">
              <div style="width:100px;height:100px;float:left;margin-left:10px;margin-top:10px;background:#ff8444;text-align:center;line-height:100px" onclick="showOrder(${table.id})">${table.num}</div>
            
            </c:if>
           
	             
            </c:forEach> 


		

	</div>
	<script src="../layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="../layui/js/x-layui.js" charset="utf-8"></script>
	<script>
            layui.use(['laydate','element','laypage','layer','laytpl'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
             
         
              
            }); 
              

           function showOrder(tableId){
           
            var data={};
            data.tableId=tableId;
           $.ajax({
						type:"post",
						url:"orderList",
			            data:data,
						success:function(msg){
						
						    var orders=msg.orders;
						    var msgs=[];
						    msgs.push("============================");
						    for(var i=0;i<orders.length;i++){
						       msgs.push(orders[i].productName);
						    }
						   
						    layer.alert('点菜列表<br>'+msgs.join('<br>'),function(index){
						       layer.close(index);
						     
						       });

						}
				});
           
           
          
           
           }
            
            </script>

</body>
</html>