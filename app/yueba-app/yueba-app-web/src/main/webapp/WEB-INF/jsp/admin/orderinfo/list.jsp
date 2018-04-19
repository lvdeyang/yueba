<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            订单中心
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>订单中心</cite></a>
              <a><cite>订单信息</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
       <form class="layui-form">
		<div class="layui-form-item">
			<label for="modularCode" class="layui-form-label">选择餐桌：</label>
			<div class="layui-input-inline">
				<select name="table"  id="tableCode" lay-filter="filter">
        			<option value="0"></option>
        			<c:forEach items="${tables}" var="table">  
                		<option  value="${table.id}">${table.num}</option>  
               		 </c:forEach> 
      			</select>
			</div>
			
			
			<div class="layui-inline">
      		<label class="layui-form-label" style= "width:90px">查询时间</label>
      		<div class="layui-input-inline">
        		<input type="text" class="layui-input" id="timeframe" placeholder=" - " style= "width:300px">
      		</div>
    	</div>
			
			
		</div>
		
		
		    </form>
		<div align="right">
			<button  type="reset"  class="layui-btn layui-btn-primary">重置</button>
			<button class="layui-btn layui-btn-normal" id="getorder">查询</button>
		</div>
        
            
            <xblock><button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button><button class="layui-btn" onclick="modular_add('添加模块','addv','900','500')"><i class="layui-icon">&#xe608;</i>添加订单</button><span class="x-right" style="line-height:40px">共有数据：${allcount} 条</span></xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" name="" value="">
                        </th>
                        
                        <th>
                            订单号
                        </th>
                    
                        <th>
                            下单时间
                        </th>
                    
      
                     
                        <th>
                           产品名称
                        </th>
                        <th>
                            产品数量
                        </th>
                        <th>
                            产品单价
                        </th>
                      
                        <th>
                            会员信息
                        </th>
                     

                        <th>
                            订单总金额
                        </th>
                        
          
                        <th>
                            订单状态
                        </th>
                   
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
                            {{ item.id	 }}
                        </td>
                       
                    
                        <td>
                            {{ item.createDate }}
                        </td>

                       
 <td>
                            {{item.productName}}
                         </td>
 <td>
                            {{item.productNum}}
                         </td>
 <td>
                            {{item.productPrice}}
                         </td>


                        <td>
                            {{ item.userInfo }}
                        </td>

                    

<td>
                            {{item.orderAllMoney}}
                         </td>

 <td>
                            {{item.orderState}}
                         </td>
                        
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  没有相关信息
{{#  } }} 
</script>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        
        
           
            layui.use(['element','laypage','layer','laytpl','form','laydate'], function(){
              $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              var form=layui.form;
              laydate = layui.laydate;
              
              
             //日期时间范围
		  		laydate.render({
		    		elem: '#timeframe'
		    		,type: 'datetime'
		    		,range: true
		  		});
		              
              
              
              
              //以上模块根据需要引入
               getlist(1,$('#tableCode').val(),$('#timeframe').val());
               //以上模块根据需要引入
              laypage.render({
                elem: 'page'
                ,count:${allcount}
                ,first: false
                ,last: false
                ,limit: 5
                ,jump: function(obj){
                	getlist(obj.curr,$('#tableCode').val(),$('#timeframe').val());
          
                   }
              });
              
               $('#getorder').on('click',function(){
                    var data={};
			 		data.tableId=$('#tableCode').val();
		 			data.date=$('#timeframe').val();
		
		 			$.ajax({
						type:"post",
						url:"orderCount",
			            data:data,
						success:function(msg){
							 var allcount = msg.getcount;
							 $("#count").html(allcount);
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
					                	getlist(obj.curr,$('#tableCode').val(),$('#timeframe').val());
					                }
				              });
							}
					});
 				});
              
              
              
               
            });
	         
		
            //异步加载列表
			function getlist(c,tableId,dateStr){
			
				$.ajax({
				 type:"post",
				 url:"list.do",
				 data:{"pagecurr":c,"tableId":tableId,"date":dateStr},
				 success:function(msg)
				 {
				 console.log(msg);
				 var data = {
                               "list":msg.alist
                         }
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
            function modular_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function modular_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            /*删除*/
            function modular_del(obj,id){
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