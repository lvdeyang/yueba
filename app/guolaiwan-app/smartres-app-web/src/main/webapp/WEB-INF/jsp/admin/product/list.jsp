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
<div  id="prosel" class="x-body">
	<form class="layui-form">
		<div class="layui-form-item">
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
			<div class="layui-inline">
			<label for="productAuditstates" class="layui-form-label">审核状态：</label>
			<div class="layui-input-inline">
				<select name="productAuditstates"  id="productAuditstates">
        			<option value=""></option>
        			<option value="C">草稿</option>
        			<option value="D">待审核</option>
        			<option value="N">未通过</option>
        			<option value="T">审核通过</option>
      			</select>
			</div>
			</div>
		</div>
		<div align="right">
			<button  type="reset"  class="layui-btn layui-btn-primary">重置</button>
			<button class="layui-btn layui-btn-normal" lay-filter="getPro" lay-submit>查询</button>
		</div>
	</form>
	</div>
	<div class="x-body">
		<xblock>
		<button class="layui-btn"
			onclick="product_add('添加商品','addv','500','600')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<span class="x-right" style="line-height:40px">共有数据：<span id="count">${count}</span>
			条</span>
			</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" id="chooseAll" onclick="chooseAll()" ></th>
					<th>商品名称</th>
					<th>副标题</th>
					<th>城市</th>
					<th>商家</th>
					<th>分类</th>
					<th>原价</th>
					<th>现价</th>
					<th>上传时间</th>
					<th>状态</th>
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
                            <input type="checkbox" value="{{ item.uuid }}" name="selected" >
                        </td>
						<td>
                            <a href="javascript:;" onclick="full_show('子产品列表','child/list','{{ item.id }}','400','600')">{{ item.productName }}</a>
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
							{{#  if(item.productAuditstatus === '草稿'||item.productAuditstatus === '未通过'){ }}
    							<span id="introduce" style="color: #F581B1;">{{ item.productAuditstatus }}</span>
			  				{{#  }else if(item.productAuditstatus === '待审核'){ }}
    							<span id="introduce" style="color: #01AAED;">{{ item.productAuditstatus }}</span>
							{{#  } else { }}
    							{{ item.productAuditstatus }}
  							{{#  } }}
                        </td>
						<td>
							{{#  if(item.productAuditstatus === '草稿'){ }}
								<a title="修改" href="javascript:;" onclick="product_info('产品修改','infov','{{ item.uuid }}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe642;</i>
								</a>
								<a title="删除" href="javascript:;" onclick="pro_del(this,'{{ item.uuid }}')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
								</a>
							{{#  } else if(item.productAuditstatus === '未通过'){ }}
								<a title="修改" href="javascript:;" onclick="product_info('产品修改','infov','{{ item.uuid }}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe642;</i>
								</a>
								<a title="删除" href="javascript:;" onclick="pro_del(this,'{{ item.uuid }}')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
								</a>
								<a onclick="advice_show('{{item.productAuditAdvice}}')" 
                            	style="text-decoration:none">
                            	<i class="layui-icon">意见</i>
								</a>
							{{#  } else if(item.productAuditstatus === '审核通过'){ }}
								<a title="详情" href="javascript:;" onclick="product_info('产品详情','info','{{item.uuid}}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe62d;</i>
								</a>
    							<a title="删除" href="javascript:;" onclick="pro_del(this,'{{ item.uuid }}')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
								</a>
								<a onclick="advice_show('{{item.productAuditAdvice}}')" 
                            	style="text-decoration:none">
                            	<i class="layui-icon">意见</i>
								</a>	
							{{#  } else { }}
								<a title="详情" href="javascript:;" onclick="product_info('产品详情','info','{{item.uuid}}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe62d;</i>
								</a>
    							<a title="删除" href="javascript:;" onclick="pro_del(this,'{{ item.uuid }}')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
								</a>
  							{{#  } }}
                        	
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
	layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        laypage = layui.laypage;//分页
        upload = layui.upload;
        laydate = layui.laydate;
     	var form = layui.form;
     	var table = layui.table;
     	
  		
 		//以上模块根据需要引入
 		fenye();
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
	 	
	 	
	 	
	 	form.on('submit(getPro)',function(data){
	 		data.field.cityCode="${cityCode}";
	 		console.log("${cityCode}");
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
	 
	
	
	function product_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
	
	//异步加载列表
	function getlist(pagecurr){
		console.log("llak");
		$.ajax({
			type:"post",
			url:"proList.do",
			async:false,
            data:{'pagecurr':pagecurr},
			success:function(msg){
			
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
	
	
	//异步删除列表 
	function pro_del(obj,id){
    	layer.confirm('确认要删除吗？',function(index){
    	console.log(id);
   		//发异步删除数据
    		$.ajax({
        		type:"post",
        		url:"prodel.do",
        		data:{"uuid":id},
       			success:function(msg){
       				console.log(msg);
        			if(msg=="success"){
            			getlist(ipage);
            			$("#count").html(parseInt($("#count").text()) - 1);
            			layer.msg('已删除!',{icon:1,time:1000});
            		}
            	}
       		}) 
   		});
	}
	
	
	//全选和全不选（**）
	function chooseAll(){
		var list = $("input[name='selected']")
		if($("#chooseAll").prop("checked")){
			$("input[name='selected']").prop("checked", "checked");
		}else{
			$("input[name='selected']").removeAttr("checked");			
		}
	}
	
	function delAll(){
		var list =$("input[name='selected']:checked");
		var uuids = new Array(list.length);
		for(var i=0;i<list.length;i++){
			uuids[i] = $(list[i]).val();
		}
		if(uuids.length == 0){
			layer.msg("请选择删除项！");
		}else{
		layer.confirm('确认要删除吗？',function(){
   		
    		$.ajax({
        		type:"post",
        		url:"delAll.do",
        		data:{'uuids':uuids},
       			success:function(msg){
        			if(msg=="success"){
            			/* $("input[name='selected']:checked").parents("tr").remove();  */
            			getlist(ipage,ilimit);
            			if($("#chooseAll").prop("checked")){
            				$("#chooseAll").removeAttr("checked");
            			} 
            			$("#count").html($("#count").text() - list.length);
            			layer.msg('已删除'+list.length+'条数据!',{icon:1,time:1000});
            			
            		}
            		
            	}
       		})
       		 
   		});
   		}
	}
	
	function fenye(){
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
                	ipage=obj.curr;
                   }
              });
 		}
 		
 	//产品详情
    function product_info(title,url,id,w,h) {
    	x_admin_show(title,url+"?uuid="+id,w,h); 
    }
     function full_show(title,url,id,w,h) {
    	var index = layer.open({
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose: true,
			shade:0.4,
			title: title,
			content: url+"?uuid="+id
		}); 
		layer.full(index);
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
 			
    
    //审核意见
    function advice_show(advice){
    	  layer.open({
        	type: 1
        	,title: false //不显示标题栏
        	,closeBtn: false
        	,area: '300px;'
        	,shade: 0.8
        	,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        	,btn: ['知道啦']
        	,btnAlign: 'c'
        	,moveType: 1 //拖拽模式，0或者1
        	,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">审核意见：<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+advice+'</div>'
        	
      });
    }
	
</script>

</body>
</html>