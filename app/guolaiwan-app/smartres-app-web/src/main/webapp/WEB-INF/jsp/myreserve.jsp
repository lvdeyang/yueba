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
      
    
    });
    function cancelRes(tableId){
    
    }
</script>
<title>我的预定</title>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>我的预定</h1>
	</header>
	
   <div class="demo-item" style="margin-top:50px;">
   
   <c:forEach items="${reserves}" var="reserve">
   
 
        <p class="demo-desc">${reserve.merchantName}</p>
        <div class="demo-block">
            <ul class="ui-list ui-list-function ui-border-tb">
                 <c:forEach items="${reserve.tableVOs}" var="table">
                <li class="ui-border-t">
                    <div class="ui-list-info">
                        <h4 class="ui-nowrap">桌号${table.num} 人数${table.count}</h4>
                    </div>
                     <div style="display:none;" class="ui-btn" onclick="cancelRes(${table.id})">取消</div>
                </li>
                 </c:forEach>
            </ul>
        </div>
          </c:forEach>
        
    </div>
   
   
</body>

</html>