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
      $('#num').val("${nums}");
    
    });
    
    function reserve(tableId){
        location.href="payreserve?tableId="+tableId
    }
    
    
</script>
<title>预定</title>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>预定</h1>
	<button class="ui-btn" onclick="location.href='myreserve'">我的预定</button>
	</header>
<form action="index">
    <div class="ui-form-item ui-border-b" style="margin-top:50px;">
                        <label>人数</label>
                        <div class="ui-select">
                            <select id="num" name="nums" style="line-height:44px;">
                                <option value="">未选择</option>
                                <option value="1-4">1-4人</option>
                                <option value="5-10">5-10人</option>
                                <option value="10-15">10-15人</option>
                                <option value="15-20">15-20人</option>
                                <option value="20-30">20-30人</option>
                            </select>
                        </div>
                    </div>
                    
                    <div
						class="ui-form-item ui-border-b">
						<label>名称</label> <input style="line-height:44px" type="text" value="${name}"
							name="name" id="name">
					</div>
                    
   
   <button class="ui-btn ui-btn-primary" style="margin-left:10px;margin-top:10px;">
                    搜索
                </button>
   </form>
   
   <div class="demo-item">
   
   <c:forEach items="${reserves}" var="reserve">
   
 
        <p class="demo-desc">${reserve.merchantName}</p>
        <div class="demo-block">
            <ul class="ui-list ui-list-function ui-border-tb">
                 <c:forEach items="${reserve.tableVOs}" var="table">
                <li class="ui-border-t">
                    <div class="ui-list-info">
                        <h4 class="ui-nowrap">桌号${table.num} 人数${table.count}</h4>
                    </div>
                    <div class="ui-btn" onclick="reserve(${table.id})">预定</div>
                </li>
                 </c:forEach>
            </ul>
        </div>
          </c:forEach>
        
    </div>
   
   
</body>

</html>