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
	
</script>

<title>会员中心</title>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b"> <i
		class="ui-icon-return" onclick="history.back()"></i>
	<h1>会员中心</h1>
		<i class="ui-icon-home" style="width:35px;position:absolute;right:5px;top:0px;" onclick="location.href='../first/index'"></i>
</header>

	<div class="demo-item">
		<p class="demo-desc" style="margin-top:50px;">会员信息</p>
		<div class="demo-block">
			<div class="ui-form ui-border-t">
				<form action="#">
					<div
						class="ui-form-item ui-form-item-show ui-form-item-link ui-border-b">
						<label for="#">账号</label> <input style="line-height:44px" type="text" value="${user.userOpenID }"
							readonly="">
					</div>
					<div class="ui-form-item ui-form-item-show ui-border-b">
						<label for="#">积分</label> <input style="line-height:44px" type="text"
							value="${user.userIntegral }">
					</div>
				</form>
			</div>
		</div>
	</div>
   
</body>

</html>