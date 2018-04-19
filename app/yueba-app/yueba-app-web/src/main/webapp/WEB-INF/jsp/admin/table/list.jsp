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
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>餐桌管理</cite></a>
			<a><cite>餐桌列表</cite></a>
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
			onclick="admin_add('添加用户','initadd','600','500')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height:40px">共有数据：${allcount}
			条</span></xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" name="" value=""></th>
					<th>ID</th>
					<th>号码</th>
					<th>人数</th>
					<th>用户</th>
					<th>状态</th>
					<th>订单总额</th>
					<th>待打印订单</th>
					<th>待支付订单</th>
					<th>待支付打印订单</th>
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
                             {{ item.id }}
                        </td>
                        <td>
                            {{ item.num }}
                        </td>
                       
                        <td >
                            {{ item.count }}
                        </td>
                        <td>
                            {{ item.userName }}
                        </td>
                        <td>
                            {{ item.status }}
                        </td>
                        <td>
                            {{ item.total }}
                        </td>
                        <td>
                            {{ item.waitingForPrint }}
                        </td>
                        <td>
                            {{ item.waitingForPay }}
                        </td>
                        <td>
                            {{ item.waitingForPayPrint }}
                        </td>
                       
                        <td class="td-manage">
                            <a title="删除" href="javascript:;" onclick="admin_del(this,'{{ item.id }}')" 
                            style="text-decoration:none">
                                <i class="layui-icon">&#xe640;</i>
                            </a>

                            <a title="详情" href="javascript:;" onclick="order_info('订单详情','orderInfo','{{item.id}}','','510')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon">&#xe62d;</i>
							</a>

                          <a title="设置可用" href="javascript:;" onclick="set_nouse('{{item.id}}')" 
                           		style="text-decoration:none">
                            	<i class="layui-icon"></i>
							</a>

                        </td>
                    </tr>
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  无数据
{{#  } }} 
</script>

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
             
              //以上模块根据需要引入
              laypage.render({
                elem: 'page'
                ,count:${allcount}
                ,first: false
                ,last: false
                ,limit: 5
                ,jump: function(obj){
                getpages('liuliqiang',obj.curr);
          
                   }
              }); 
              var ndata;
              //加载数据
              function getpages(username,pagecurr)
              {
              	$.ajax({
              	   type:"post",
              	   url:"list.do",
              	   async:false,
              	   data:{'pagecurr':pagecurr,'username':'${username}'},
              	   success:function(msg){
              	   if(msg.list!=null){
              	       var data = {
                               "list":msg.list
                         }
               			    var getTpl = alist.innerHTML;
                			laytpl(getTpl).render(data, function(html){
                              view.innerHTML = html;
                			 });
              	   }else{
              	     layer.msg('您没有权限或者没有数据!',{icon:1,time:5000});
              	   }
              	   }
              	})
              }
             
    
          
              
            }); 
              
            //批量删除提交
             function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function admin_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

           
           
            /*删除*/
            function admin_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                     $.ajax({
                	  type:"post",
           			  url:"del",
                      data:{"id":id},
                      success:function(msg){
                      console.log(msg);
                        if(msg=="success"){
                           $(obj).parents("tr").remove();
                           layer.msg('已删除!',{icon:1,time:1000});
                        }else{
                        layer.msg('您没有权限!',{icon:1,time:1000});
                        }
                       }
                    }) 
                   
                });
            }
            
             function order_info(title,url,id,w,h) {
		    	x_admin_show(title,url+"?tableId="+id,w,h); 
		    }
            
            function set_nouse(id){
            var data={};
            data.tableId=id;
            $.ajax({
              	   type:"post",
              	   url:"setnouse",
              	   async:false,
              	   data:data,
              	   success:function(msg){
              	        location.href=location.href;
              	   }
              	});
            }
            </script>

</body>
</html>