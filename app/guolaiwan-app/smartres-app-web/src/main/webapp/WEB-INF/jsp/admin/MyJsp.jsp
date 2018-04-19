<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>过来玩系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/x-admin.css"
	media="all">
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header header header-demo">
			<div class="layui-main">

				<div class="admin-logo-box" id="testP">
					<a class="layui-logo">过来玩餐厅管理平台</a>
					<div class="larry-side-menu">
						<i class="fa fa-th-large" aria-hidden="true"></i>
					</div>
				</div>
				<ul class="layui-nav layui-layout-left layui-ygyd-menu"
					style="position:absolute; left:250px;">

				</ul>

				<ul class="layui-nav" lay-filter="">
					<!-- <li class="layui-nav-item">
                        <a href="javascript:void(0)" title="消息" onclick="">
                            <i class="layui-icon" style="top: 1px;">&#xe63a;</i>
                        </a>
                        </li>  -->
					<li class="layui-nav-item"><a href="javascript:;">${loginName}</a>
						<dl class="layui-nav-child">
							<!-- 二级菜单 -->
							<dd>
								<a href="<%=request.getContextPath()%>/do/logout">退出</a>
							</dd>
						</dl></li>

				</ul>
			</div>
		</div>
		<div class="layui-side layui-bg-black x-side" style="left:-200px;">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
					${menulist}
				</ul>
			</div>
		</div>
		<div class="layui-tab layui-tab-card site-demo-title x-main"
			lay-filter="x-tab" lay-allowclose="true"
			style="left: 0px;border-left: solid 2px #2299ee;">
			<ul class="layui-tab-title">
				<li class="layui-this">我的桌面 <i
					class="layui-icon layui-unselect layui-tab-close"></i>
				</li>
			</ul>
			<div class="layui-tab-content site-demo site-demo-body">
				<div class="layui-tab-item layui-show">
					<iframe frameborder="0" src="../welcome/init" class="x-iframe"></iframe>
				</div>
			</div>
		</div>
		<div class="site-mobile-shade"></div>
	</div>
	<div id="printContent"></div>
	<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"
		charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/layui/js/x-admin.js"></script>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jQuery.print.js"></script>
	
	<script type="text/javascript">
	

		Date.prototype.Format = function (fmt) { // author: meizz 
		    var date=new Date();
			var o = {   
			    "M+" : date.getMonth()+1,                 //月份   
			    "d+" : date.getDate(),                    //日   
			    "h+" : date.getHours(),                   //小时   
			    "m+" : date.getMinutes(),                 //分   
			    "s+" : date.getSeconds(),                 //秒   
			    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
			    "S"  : date.getMilliseconds()             //毫秒   
			  };   
			  if(/(y+)/.test(fmt))   
			    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
			  for(var k in o)   
			    if(new RegExp("("+ k +")").test(fmt))   
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  return fmt;   
		}
	
	
     layui.use(['laydate','element','laypage','layer','laytpl'], function(){
             $ = layui.jquery;//jquery
           lement = layui.element;//面包导航
           laypage = layui.laypage;//分页
           layer = layui.layer;//弹出层
           laytpl = layui.laytpl;//模板引擎
          
          var type=${type};
          
          
          function printMsg(table,merchant,cardDes){
                var orderStrarr=[];
                orderStrarr.push("酒店："+merchant);
                
                orderStrarr.push("时间："+new Date().Format("yyyy-MM-dd hh:mm:ss"));
                orderStrarr.push("桌号："+table.num);
                orderStrarr.push("=========================");
                var count=0;
		        for(var j=0;j<table.orderInfoVOs.length;j++){
		           var order=table.orderInfoVOs[j];
		           orderStrarr.push(order.productName+"*********"+order.productPrice);
		           count+=order.productPrice;
		        }
		        orderStrarr.push("=========================");
		        orderStrarr.push("总额:"+count);
		        orderStrarr.push("*************************");
		        orderStrarr.push(cardDes);
                return orderStrarr.join('<br>');
          }
          
          
          
          function dealMsg(msgs,i){
           if(i>=msgs.length){
                 setTimeout( function(){
								                 initData();
								              },5000);
                   return;
                }
                
              var message=msgs[i];
						       layer.alert(message.tableNum+'桌:'+message.msg,function(index){
						       layer.close(index);
						      dealMsg(msgs,i+1);
						       });
          
          
          }
          
          
          function initMsg(){
          var data={};
           $.ajax({
						type:"post",
						url:"../msg",
			            data:data,
						success:function(msg){
						    if(msg.msgList.length>0){
						    
						    dealMsg(msg.msgList,0);
						    }else{
						    
						    setTimeout( function(){
								                 initData();
								              },5000);
						    
						    }
						      
					
						    
						}
				});
          
          }

          function dealOrder(tables,i,merchant,cardDes){
                if(i>=tables.length){
                 setTimeout( function(){
								                 initPayData();
								              },5000);
                   return;
                }
                
                var table=tables[i];
		        var pmsg=printMsg(table,merchant,cardDes);
		       
		 		layer.confirm('有新的订单请打印？<br>'+pmsg,function(index){
		  						var innerData={};
		  						innerData.tableId=table.id;
		  						$.ajax({
							type:"post",
							url:"../admin/orderinfo/print",
				            data:innerData,
							success:function(msg){
							     $('#printContent').html(pmsg);
		                         jQuery.print('#printContent');
							
							}
				     });
		  						
		  						layer.close(index);
		  					    dealOrder(tables,i+1,merchant,cardDes)
	   
							},function(index){
							
							   
								layer.close(index);
		  						dealOrder(tables,i+1,merchant,cardDes)
							});
          
          }
          
          
          function initData(){
          
          var data={};
               $.ajax({
						type:"post",
						url:"../admin/orderinfo/getAllPrint",
			            data:data,
						success:function(msg){
						    if(msg.tableList.length>0){
						      dealOrder(msg.tableList,0,msg.merchant,msg.cardDes);
							}else{
							
							 setTimeout( function(){
								                 initPayData();
								              },5000);
							}
						     
				          
						    
						  
							
						}
			   });
          
          }
          
           function dealPayOrder(tables,i,merchant,cardDes){
                if(i>=tables.length){
                 setTimeout( function(){
										                 initMsg(); 
										              },5000);
                   return;
                }
                
                var table=tables[i];
                
							        var orderStrarr=[];
							        var pmsg=printMsg(table,merchant,cardDes);
							 		layer.confirm('有新的结账订单请打印？<br>'+pmsg,function(index){
			   	  						var innerData={};
			   	  						innerData.tableId=table.id;
			   	  						$.ajax({
												type:"post",
												url:"../admin/orderinfo/payprint",
									            data:innerData,
												success:function(msg){
												
												   $('#printContent').html(pmsg);
		                                           jQuery.print('#printContent');
												
												}
									     });
			   	  			
			   	  						layer.close(index);
			   	  						 dealPayOrder(tables,i+1,merchant,cardDes)
         
			   						},function(index){
			   						
			   			
			   							layer.close(index);
			   	  						 dealPayOrder(tables,i+1,merchant,cardDes)
			   						});
                
                
           }
          
           function initPayData(){
          
          var data={};
               $.ajax({
						type:"post",
						url:"../admin/orderinfo/getAllPayPrint",
			            data:data,
						success:function(msg){
						    if(msg.tableList.length>0){
						    dealPayOrder(msg.tableList,0,msg.merchant,msg.cardDes);
							}
							else{
							
							 setTimeout( function(){
										                 initMsg(); 
										              },5000);
							}
						}
			   });
          
          }
          
			  if(type==1){
              
                 initData(); 
             
              }        
        
       
           
         }); 





                 


    
</script>
</body>
</html>