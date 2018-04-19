<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="../css/frozen.css">
<link rel="stylesheet" href="../css/page.css">
<script type="text/javascript" src="../js/tajs.js" charset="UTF-8"></script>
<script src="../js/lib/zepto.min.js"></script>
<script src="../js/frozen.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
      $('.delete').on('click',function(){
        var id=this.id.split('-')[1];
        var data={};
		    data.orderId=id;
			$.ajax({
				type : "post",
				url : "../order/delete",
				data : data,
				success : function(msg) {
					if (msg == "success") {
						//$('#item-'+id).remove();
						location.href=location.href;
					} else {
	
					}
				}
			});
        });
        
         $('#confirmOrder').on('click',function(){
         
               var dia=$.dialog({
			        title:'温馨提示',
			        content:'确认后，当前订单开始烹饪，客户端不能再次修改，如果必须要修改，联系服务员',
			        button:["确认","取消"]
			    });

			    dia.on("dialog:action",function(e){
			        if(e.index==0){
			         var data={};
						$.ajax({
							type : "post",
							url : "../order/confirm",
							data : data,
							success : function(msg) {
								if (msg == "success") {
									location.href=location.href;
								} else {
				
								}
							}
						});
			        }
			    });
			    dia.on("dialog:hide",function(e){
			       
			    });
         
         
	       
         });
    
    
    
    
     $('#payOrder').on('click',function(){
         
               var dia=$.dialog({
			        title:'温馨提示',
			        content:'确认支付',
			        button:["确认","取消"]
			    });

                var useScore=0;
                if(!$('#useScore').attr('checked')){
                   useScore=0;
                }else{
                   useScore=1;
                }
			    dia.on("dialog:action",function(e){
			        if(e.index==0){
			        
			         var data={};
						$.ajax({
							type : "post",
							url : "../wx/checkpay",
							data : data,
							success : function(msg) {
								if (msg=='fail') {
									 var dia=$.dialog({
								        title:'温馨提示',
								        content:'订单在服务台确认后才能支付，请稍候支付',
								        button:["确认"]
							        });
								} else {
				 					location.href="../wx/pay?useScore="+useScore;
								}
							}
						});
			        
			        
			       
			         /*var data={};
			         data.useScore=useScore;
						$.ajax({
							type : "post",
							url : "../wx/pay",
							data : data,
							success : function(msg) {
								if (msg) {
									location.href=
								} else {
				
								}
							}
						});*/
			        }
			    });
			    dia.on("dialog:hide",function(e){
			       
			    });
         
         
	       
         });
   
    
    });
</script>
<title>订单</title>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>订单</h1>
	<i class="ui-icon-home"
		style="width:35px;position:absolute;right:5px;top:0px;"
		onclick="location.href='../first/index'"></i> 
		
		
		</header>

    <div style="margin-top:60px;margin-left:15px;"><button id="confirmOrder" class="ui-btn ui-btn-primary">确认订单</button>
    <button class="ui-btn ui-btn-primary" id="payOrder">支付账单</button>
    
    <span style="font-size:12px;">支付:￥<span style="font-size:18px;color:#ff8444">${confirmTotal}</span>&nbsp;&nbsp;总额:￥<span style="font-size:18px;color:#ff8444">${total}</span></span>
    </div>
    
    <div class="ui-form-item ui-form-item-checkbox ui-border-b">
        <label class="ui-checkbox">
            <input id="useScore" type="checkbox">
        </label>
        <p style="font-size:12px;">您的积分可抵用${scoreChange}元,使用积分兑换</p>
    </div>
     <div class="ui-form-item ui-form-item-checkbox ui-border-b">
        <p style="font-size:12px;">可扣除订桌押金${reserveCount}元</p>
    </div>
	<div class="demo-item" style="margin-top:30px;">
		<c:forEach items="${orders}" var="order">
			<div class="demo-block" id="item-${order.id}">
				<ul class="ui-list ui-border-tb">
					<li class="ui-border-t">
						<div class="ui-list-img">
							<span style="background-image:url(${order.productPic})"></span>
						</div>
						<div class="ui-list-info">
							<h4 class="ui-nowrap">${order.productName}&nbsp;&nbsp;￥:${order.productPrice}</h4>
							<c:if test="${order.confirm == 1}">
                                <p class="ui-nowrap confirmtxt">已确认</p> 
                            </c:if>
                            <c:if test="${order.confirm == 0}">
                                <p class="ui-nowrap confirmtxt">未确认</p> 
                            </c:if>
							
						</div> 
						
						<c:if test="${order.confirm == 0}">
                            <a id="delete-${order.id}" class="delete" href="javascript:void(0)"><i
							class="ui-icon-close-page" style="line-height:80px;margin-right:10px;"></i></a>
                        </c:if>
						
					</li>

				</ul>
			</div>
		</c:forEach>
	</div>
   
   
  
   
   
</body>

</html>