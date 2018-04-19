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
<script src="../js/lib/zepto.min.js"></script>
<script src="../js/frozen.js"></script>
<script src="../layui/js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    
	  function shoppingCartAnimate(dom){
	    var goodsItem = $("#"+dom);
	    var flyElm = goodsItem.clone();
	    $('body').append(flyElm);
	    flyElm.css({
	        'z-index': 9000,
	        'display': 'block',
	        'position': 'absolute',
	        'top': goodsItem.offset().top +'px',
	        'left': goodsItem.offset().left +'px',
	        'width': goodsItem.width() +'px',
	        'height': goodsItem.height() +'px'
	    });
	    flyElm.animate({
	        top: $('#basket').offset().top,
	        left: $('#basket').offset().left,
	        width: 10,
	        height: 10
	    }, 'slow', function() {
	        flyElm.remove();
	    });
	}
	  
    
      $('.buy').on('click',function(){
      
        var id=this.id.split('-')[1];
        var data={};
		    data.proId=id;
			$.ajax({
				type : "post",
				url : "../order/add",
				data : data,
				success : function(msg) {
					if (msg == "success") {
						shoppingCartAnimate('item-'+id);
					} else {
	
					}
				}
			});
        });
    
    
    var tab = new fz.Scroll('.ui-tab', {
		        role: 'tab',
		        autoplay: false,
		        interval: 3000
		    });
		    /* 滑动开始前 */
		    tab.on('beforeScrollStart', function(fromIndex, toIndex) {
		        console.log(fromIndex,toIndex);// from 为当前页，to 为下一页
		    })
    
    
    });
	
</script>
<title>菜单</title>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>菜单</h1>
	<i class="ui-icon-cart" id="basket" style="width:35px;position:absolute;right:5px;top:0px;" onclick="location.href='../order/index'">
	
	</i>
	<i class="ui-icon-home" style="width:35px;position:absolute;right:50px;top:0px;" onclick="location.href='../first/index'"></i>
	</header>



    <div class="ui-tab" style="margin-top:50px;">
    
    
			    <ul class="ui-tab-nav ui-border-b">
			        <c:forEach items="${modularList}" var="modular">
			          <li>${modular.modularName}</li>
			        </c:forEach>
			        
			    </ul>
			    <ul class="ui-tab-content ui-list ui-border-tb" style="width: 600%;margin-left:10px; transition-property: transform; transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(-414px, 0px) translateZ(0px);">
			         <c:forEach items="${modularList}" var="modular">
			         <c:forEach items="${modular.products}" var="product">
			         <li class="ui-border-t" style="margin-left:0px;">
							<div class="ui-list-img">
								<span id="item-${product.id}" style="background-image:url(${product.productShowPic})"></span>
							</div>
							<div class="ui-list-info">
								<h4 class="ui-nowrap">${product.productName}&nbsp;&nbsp;￥:${product.productPrice}</h4>
								<p class="ui-nowrap">${product.productSubtitle}</p>
							</div>
							<a id="buy-${product.id}" class="buy" href="javascript:void(0)"><i class="ui-icon-add" style="line-height:80px;margin-right:10px;"></i></a>
						</li>
			         </c:forEach>
			        </c:forEach>
			    </ul>
   </div>



	
</body>

</html>