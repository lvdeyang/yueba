<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/css/bootstrap.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/js/bootstrap.min.js" media="all">
    </head>
    <body>
    <style type="text/css">
	legend { display: block; width:100px; border-bottom:0px; font-family: "Microsoft YaHei","Helvetica Neue";}
	legend a{ color:#666;} legend a:hover{ text-decoration:none;}
	.layui-table{ font-family: "Microsoft YaHei","Helvetica Neue";}
	</style>
	<body>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
                页面超时，请退出重新登录或者重新扫码<span class="f-14"></span>
            </blockquote>

        </div>
    
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/index.js"></script>
        <script src="<%=path %>/layui/js/echarts.min.js"></script>
        <script src="<%=path %>/layui/js/echart.js"></script>

    </body>
</html>