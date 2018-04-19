<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>订单管理</cite></a>
			<a><cite>订单列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<button class="layui-btn" id="printorder"
			>
			<i class="layui-icon"></i>打印到厨房
		</button>
		<button class="layui-btn" id="payprintorder"
			>
			<i class="layui-icon"></i>结账打印
		</button>
		<xblock>
		
		<span class="x-right" style="line-height:40px">共有数据：${count}&nbsp;合计金额：${total}</span></xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>图片</th>
					<th>名称</th>
					<th>价格</th>
					<th>用户</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="view">
	        <c:forEach items="${orders}" var="order">  
	        <tr>
                	<td>${order.id}</td>
					<td> <img  src="${order.productPic}" width="50" height="50">	</td>
					<td>${order.productName}</td>
					<td>${order.productPrice}</td>
					<td>${order.userInfo}</td>	
					<td>	<a title="删除" href="javascript:;" onclick="delOrder(${order.id})" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
								</a></td>	
					</tr>
            </c:forEach> 

			</tbody>
		</table>

		

		

	</div>
	<script src="../layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="../layui/js/x-layui.js" charset="utf-8"></script>
    <script type="text/javascript">
     layui.use(['laydate','element','laypage','layer','laytpl'], function(){
             $ = layui.jquery;//jquery
           lement = layui.element;//面包导航
           laypage = layui.laypage;//分页
           layer = layui.layer;//弹出层
           laytpl = layui.laytpl;//模板引擎
          
          
          
         
          
          
          function printorder(){
          
               var data={};
               data.tableId=${tableId};
               $.ajax({
						type:"post",
						url:"../admin/orderinfo/print",
			            data:data,
						success:function(msg){
						layer.msg("打印完成");
						}
			   });
          
          }
          
          function payprintorder(){
          
               var data={};
               data.tableId=${tableId};
               $.ajax({
						type:"post",
						url:"../admin/orderinfo/payprint",
			            data:data,
						success:function(msg){
						layer.msg("打印完成");
						}
			   });
          
          }
          
           $('#printorder').on('click',function(){
               printorder();
           
           });
           
            $('#payprintorder').on('click',function(){
               payprintorder();
           
           });
			     
           
           
         }); 
    
    
     function delOrder(orderId){
          
               var data={};
               data.orderId=orderId;
               $.ajax({
						type:"post",
						url:"../admin/orderinfo/delOrder",
			            data:data,
						success:function(msg){
						  location.href=location.href;
						}
			   });
          
          }
    
    </script>
            
</body>
</html>