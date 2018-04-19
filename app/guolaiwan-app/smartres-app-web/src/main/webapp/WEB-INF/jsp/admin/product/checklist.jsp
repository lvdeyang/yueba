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
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>产品管理</cite></a>
			<a><cite>产品列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>	
	<div class="x-body" id="prosel" >
	<form class="layui-form" >
		<div class="layui-form-item">
			<label for="cityCode" class="layui-form-label">城市名称：</label>
			<div class="layui-input-inline">
				<select name="cityCode" id="cityCode">
					<option value=""></option>
					<c:forEach items="${cityList}" var="city">
						<option value="${city.cityCode}">${city.cityName}</option>
					</c:forEach>
				</select>
			</div>
			
			<label for="modularCode" class="layui-form-label">模块名称：</label>
			<div class="layui-input-inline">
				<select name="modularCode"  id="modularCode" lay-filter="filter">
        			<option value=""></option>
        			<c:forEach items="${modulars}" var="modular">  
                		<option  value="${modular.modularCode}" >${modular.modularName}</option>  
               		 </c:forEach> 
      			</select>
			</div>
			
			<label for="classCode" class="layui-form-label">分类名称：</label>
			<div class="layui-input-inline">
				<select name="classCode"  id="classCode">
        			<option value=""></option>
        			<c:forEach items="${Classes}" var="Class">  
                		<option  value="${Class.classCode}" >${Class.className}</option>  
               	 	</c:forEach> 
      			</select>
			</div>
			
			<div class="layui-inline">
			<label for="productName" class="layui-form-label">产品名称：</label>
			<div class="layui-input-inline">
				<input class="layui-input" name="productName" >
			</div>
			</div>
			<div class="layui-inline">
			<label for="shopName" class="layui-form-label">商家名称：</label>
			<div class="layui-input-inline">
				<input class="layui-input" name="shopName" >
			</div>
			</div>
		</div>
		<div align="right">
			<button  type="reset"  class="layui-btn layui-btn-primary">重置</button>
			<button  class="layui-btn layui-btn-normal" lay-filter="getPro" lay-submit>查询</button>
		</div>
	</form>
	</div>
	
	<div class="x-body">
		<xblock><div align="right">
		<span  style="line-height:40px">共有数据：<span id="count">${acount}</span>条</span>
		
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" id="chooseAll" onclick="chooseAll()"></th>
					<th>商品名称</th>
					<th>副标题</th>
					<th>城市</th>
					<th>商家</th>
					<th>板块</th>
					<th>原价</th>
					<th>现价</th>
					<th>上传时间</th>
					<th>审核状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="view">

			</tbody>
		</table>

		<div id="page"></div>

		<script id="alist" type="text/html">
{{#  layui.each(d.list, function(index, item){ }}
                        <tr>
                        <td>
                            <input type="checkbox" value="{{ item.uuid }}" name="selected">
                        </td>
						<td>
							<a title="详情" href="javascript:;" onclick="product_show('产品详情','infov','{{ item.uuid }}','','510')" 
                           	style="text-decoration:none">

                            {{ item.productName }}
							</a>	
                        </td>
						<td>
                            {{ item.productSubtitle }}
                        </td>
						<td>
                            {{ item.productCityName }}
                        </td>
						<td>
                            {{ item.productMerchantName }}
                        </td>
						<td>
                            {{ item.productModularCodeName }}
                        </td>
						<td>
                            {{ item.productOldPrice }}
                        </td>
						<td>
                            {{ item.productPrice }}
                        </td>
						<td>
                            {{ item.updateTime }}
                        </td>
                        <td>
                            <span id="introduce" style="color: #01AAED">{{ item.productAuditstatus }}</span>
                        </td>
						<td>
							<a title="审核" href="javascript:;" onclick="product_show('产品审核','check','{{item.uuid}}','','510')" 
                           	class="ml-5" style="text-decoration:none">
                            	<i class="layui-icon">审核</i>
                        	</a>
                        	<a title="删除" href="javascript:;" onclick="pic_del(this,'{{ item.uuid }}')" 
                           	style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
                        	</a>
						</td>
                        
                    </tr>
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  没有相关信息
{{#  } }} 
</script>
	</div>

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		
	var ipage ;	
	var ilimit;
	var doneCount = 0;
	layui.use(['element','layer','laytpl','upload','laydate','laypage','form'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        laypage = layui.laypage;//分页
        upload = layui.upload;
        laydate = layui.laydate;
     	var form = layui.form;
  
 		//分页
 		laypage.render({
                elem: 'page'
                ,count: parseInt($("#count").text())
                ,first: '首页'
    			,last: '尾页'
    			,limit: 10
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
    			,layout: ['page', 'skip']
                ,jump: function(obj){
                	getlist(obj.curr);
                   }
              });
 		/* $(function(){
	 		$("#view tr").mouseover(function(){
       			$(this).css("background-color","#009688").siblings().css("background-color", "#FFFFFF");
    		});
	 	}) */
	 	//模块
 		form.on('select(filter)',function(data){
 			$.ajax({
			type:"post",
			url:"getProBymc.do",
			async:false,
            data:{"modularCode":data.value},
			success:function(msg){
				var sbHtml="<option value=''></option>";
				 classn = msg.classn;
        		console.log(classn);
        		for(i=0;i<classn.length;i++){
        			classn[i].classCode;
        			classn[i].className;
        			sbHtml=sbHtml+"<option value='"+classn[i].classCode+"'>"+classn[i].className+"</option>"
        		}
        		//清空下拉框
        		$("#classCode").html(sbHtml);
        		 form.render();
				}
			})
 		});
	 	
	 	//查询按钮
	 	form.on('submit(getPro)',function(data){
	 		data.field.productAuditstates="D";
 			var allcount;
 			console.log(data.field);
 			$.ajax({
			type:"post",
			url:"getProductByC.do",
			async:false,
            data:data.field,
			success:function(msg){
				 allcount = msg.getcount;
				 $("#count").html(allcount);
        		console.log(allcount);
				}
			})
 			console.log("2");
 			laypage.render({
                elem: 'page'
                ,count: allcount
                ,first: '首页'
    			,last: '尾页'
    			,limit: 10
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
    			,layout: ['page', 'skip']
                ,jump: function(obj){
                	data.field.pagecurr=obj.curr;
                	console.log(data.field);
                	getProductBy(data);
                   }
              });
              return false;
 		});
 		
 		
 		
});
	
	
	//加载列表
	function getlist(pagecurr){
		$.ajax({
			type:"post",
			url:"checklist.do",
			async:false,
            data:{'pagecurr':pagecurr},
			success:function(msg){
				console.log("jin");
				var data = {
           		"list":msg.list
       	 		}
       	 		console.log(data);
				//操作模板引擎
				var getTpl = alist.innerHTML;
				laytpl(getTpl).render(data, function(html){
            		view.innerHTML = html;
        		});
			}
		})
	}
	
	//查询列表
	function getProductBy(data){
 				$.ajax({
					type:"post",
					url:"getProductBy.do",
					async:false,
            		data:data.field,
					success:function(msg){
					var data = {
           				"list":msg.getlist
       	 			}
       	 			console.log(data);
					//操作模板引擎
					var getTpl = alist.innerHTML;
					laytpl(getTpl).render(data, function(html){
            			view.innerHTML = html;
        			});
        		
					}
				})
 			}
	
 		
 	//弹页面
    function product_show(title,url,id,w,h) {
    	x_admin_show(title,url+"?uuid="+id,w,h); 
    }
    
    
    
</script>

</body>
</html>