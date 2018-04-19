<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>商户管理</cite></a>
			<a><cite>商户列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<button class="layui-btn" 
			onclick="merchant_add('添加权限','addv','900','750')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height:40px">共有数据：${allcount}
			条</span></xblock>
	<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" name="" value=""></th>
					<th>商家名称</th>
					<th>登录名称</th>
					<th>城市</th>
					<th>商户地址</th>
					<th>联系人</th>
					<th>联系电话</th>
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
                            <input type="checkbox" value="{{ item.uuid }}" name="selected" >
                        </td>
                        
                        <td>
                            {{ item.shopName }}
                        </td>
                       
                        <td>
                            {{ item.shopLoginName }}
                        </td>

						<td>
                             {{ item.cityName }}
                        </td>

						<td>
                             {{ item.shopAddress }}
                        </td>
						<td>
                            {{ item.shopLinkperson }}
                        </td>
						
						<td>
                            {{ item.shopTel }}
                        </td>

						

						

						<td>
							{{#  if(item.shopAuditState === '草稿'||item.shopAuditState === '未通过'){ }}
    							<span id="introduce" style="color: #F581B1;">{{ item.shopAuditState }}</span>
			  				{{#  }else if(item.shopAuditState === '待审核'){ }}
    							<span id="introduce" style="color: #01AAED;">{{ item.shopAuditState }}</span>
							{{#  } else { }}
    							{{ item.shopAuditState }}
  							{{#  } }}
                        </td>

						<td>
                             {{ item.shopAuditopinion }}
                        </td>
					
               
                        <td class="td-manage">
                       
							{{#  if(item.shopAuditState === '草稿'||item.shopAuditState === '未通过'){ }}
								<a title="修改" href="javascript:;" onclick="merchant_edit('修改','updatev','{{ item.uuid }}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe642;</i>
								</a>
								<a title="删除" href="javascript:;" onclick="merchant_del(this,'{{ item.uuid }}')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
								</a>
							{{#  } else { }}
								<a title="详情" href="javascript:;" onclick="merchant_info('商家详情','info','{{item.uuid}}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe62d;</i>
								</a>
    							<a title="删除" href="javascript:;" onclick="merchant_del(this,'{{ item.uuid }}')" 
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
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
           
            layui.use(['element','laypage', 'layer','laytpl'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              //以上模块根据需要引入
              
               laypage.render({
                elem: 'page'
                ,count:${allcount}
                ,first: false
                ,last: false
                ,limit: 5
                ,prev: '<em><</em>'
                ,next: '<em>></em>'
                ,jump: function(obj){
                getpages(obj.curr);
          
                   }
              }); 
              
              
            });
	         
	         
	         //加载数据
              function getpages(pagecurr)
              {
              	$.ajax({
              	   type:"post",
              	   url:"list.do",
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
            function merchant_edit (title,url,id,w,h) {
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
            
            //产品详情
		   function merchant_info(title,url,id,w,h) {
		   	x_admin_show(title,url+"?uuid="+id,w,h); 
		   }
            </script>

</body>
</html>