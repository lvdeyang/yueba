<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>商家管理</cite></a>
			<a><cite>审核通过商家</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<!-- b1 -->
	<br />
	<div>
		<from class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">

				<label for="cityName" class="layui-form-label">城市名称：</label>
				<div class="layui-input-inline">
					<select name="cityCode" id="cityCode">
						<option value="-1"></option>
						<c:forEach items="${cityList}" var="city">
							<option value="${city.cityCode}">${city.cityName}</option>
						</c:forEach>
					</select>


				</div>
				<div style="float:right">
					<button id=selCity class="layui-btn layui-btn-normal"
						lay-filter="getMer" lay-submit>查询</button>
				</div>
			</div>
		</div>

		</from>
	</div>
	<!-- e1 -->
	<div class="x-body">

		<xblock>
		
		<span class="x-right" style="line-height:40px">共有数据：${checkcount}
			条</span></xblock>
	<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" name="" value=""></th>
					<th>商户名称</th>
					<th>登录名称</th>
					<th>联系电话</th>
					<th>商户地址</th>
					<th>联系人</th>
					<th>审核状态</th>
					<th>审核意见</th>
				
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
                            <input type="checkbox" value="1" name="">
                        </td>
                        
                        <td>
                            {{ item.shopName }}
                        </td>
                       
                        <td>
                            {{ item.shopLoginName }}
                        </td>

						<td>
                            {{ item.shopTel }}
                        </td>

						<td>
                            {{ item.shopAddress }}
                        </td>

						<td>
                            {{ item.shopLinkperson }}
                        </td>
						
						<td>
                             {{ item.shopAuditState }}
                        </td>

						<td>
                             {{ item.shopAuditopinion }}
                        </td>
						
					
                       
                        <td class="td-manage">
                            <a title="编辑" href="javascript:;" onclick="merchant_submit('编辑','check','{{ item.uuid }}','','510')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon">提交</i>
                            </a>
                            <a title="删除" href="javascript:;" onclick="merchant_del(this,'{{ item.uuid }}')" 
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
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
           
            layui.use(['element','laypage', 'layer','laytpl', 'form'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              var form = layui.form;
              //以上模块根据需要引入
              
               laypage.render({
                elem: 'page'
                ,count:${checkcount}
                ,first: false
                ,last: false
                ,limit: 5
                ,prev: '<em><</em>'
                ,next: '<em>></em>'
                ,jump: function(obj){
                getpages(obj.curr);
          
                   }
              });

    //查询按钮
    form.on('submit(getMer)',function(data){
    data.field.shopAuditState="T";
    var allcount;

    $.ajax({
    type:"post",
    url:"getCountByCity.do",
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
    getMerByCity(data);
    }
    });
    });
              
            });
	         
	         
	         //加载数据
              function getpages(pagecurr)
              {
              	$.ajax({
              	   type:"post",
              	   url:"passlist.do",
              	   async:false,
              	   data:{'pagecurr':pagecurr},
              	   success:function(msg){
              	       var data = {
                               "list":msg.list
                         }
                         console.log(data);
               			    var getTpl = alist.innerHTML;
                			laytpl(getTpl).render(data, function(html){
                              view.innerHTML = html;
                			 });
              	   }
              	})
              }

    //查询列表
    function getMerByCity(data){
    $.ajax({
    type:"post",
    url:"getMerByCity.do",
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

			
            //批量删除提交
            function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function merchant_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function merchant_submit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            
            /*删除*/
            function merchant_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"uuid":id},
                    success:function(msg){
                    	if(msg=="success"){
                    		$(obj).parents("tr").remove();
                    		layer.msg('已删除!',{icon:1,time:1000});
                    	}
                    }
                    })
                    
                });
            }
            </script>

</body>
</html>