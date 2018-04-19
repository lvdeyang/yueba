<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="../css/frozen.css">
<link rel="stylesheet" href="../css/page.css">
<script type="text/javascript" src="../js/tajs.js" charset="UTF-8"></script>
<script src="../js/lib/zepto.min.js"></script>
<script src="../js/frozen.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
 
    $('#callserv').on('click',function(){
    var data={};
           $.ajax({
				type : "post",
				url : "../first/callServ",
				data : data,
				success : function(msg) {
				
				     var dia=$.dialog({
				        title:'温馨提示',
				        content:'已呼叫服务员',
				        button:["确认"]
			        });

				}
			});
    
    });
    
    
    });
	
</script>


<title>首页</title>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>首页</h1>
	<i class="ui-icon-cart" style="width:35px;position:absolute;right:5px;top:0px;" onclick="location.href='../order/index'"></i>
	<i class="ui-icon-personal" style="width:35px;position:absolute;right:50px;top:0px;" onclick="location.href='../person/index'"></i>
	</header>
	
	<image src="${merchant.shopPic}" style="width:100%;height:200px;"/>
	<div style="margin-top:10px;font-size:15px;margin-left:10px;">
	${merchant.shopName}
	</div>
	<div style="width:200px;margin:0 auto;font-size:14px;margin-top:50px">
	    ${tableNum}桌&nbsp;&nbsp;&nbsp;&nbsp;用户<span class="ui-txt-warning">&nbsp;&nbsp;${userName}</span>
	</div>
	
	<div class="demo-block" style="margin-top:50px"> 
         <div class="ui-btn-wrap">
             <button class="ui-btn-lg ui-btn-primary" id="callserv">
                  呼叫
             </button>
         </div>
         <div class="ui-btn-wrap">
             <button class="ui-btn-lg ui-btn-primary" onclick="location.href='../newmenu/index'">
                 点餐                                                
             </button>
         </div>
          <div class="ui-btn-wrap">
             <button class="ui-btn-lg ui-btn-primary" onclick="location.href='../order/index'">
                订单&结账                                               
             </button>
         </div>
     </div>
</body>

</html>