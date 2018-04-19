<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            模块管理
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
        <link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    </head>
    
    <body>
        <div class="x-body" style="height:1500px">
        	<form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="productName" class="layui-form-label">
                    		商品名称：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productName" name="productName" required lay-verify="required" value="${product.productName}"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 					<div class="layui-inline">
 						<label for="productSubtitle" class="layui-form-label">
							副标题：
                   		</label>
                    	<div class="layui-input-inline">
                       		<input type="text" id="productSubtitle" name="productSubtitle" required lay-verify="required" value="${product.productSubtitle}"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 				</div>
                
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">板块名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly" value="${product.productModularCodeName}">
							<input type="hidden" id="L_title" name="modularCode" 
							lay-verify="title" autocomplete="off" class="layui-input" value="${product.productModularCode}">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">分类名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularClass" required lay-verify="required" value="${product.productClassName}"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="hidden" id="L_title" name="modularClassId" 
							lay-verify="title" autocomplete="off" class="layui-input"  value="${product.productClassCode}">
						</div>
					</div>
				</div>
				
				
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商家名称</label>
						<div class="layui-input-inline">
							<input type="text" id="MerchantName" name="productMerchantName" required lay-verify="required" value="${product.productMerchantName}"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="hidden" id="MerchantID" name="productMerchantID"  value="${product.productMerchantID}"
							lay-verify="title" autocomplete="off" class="layui-input" >
						</div>
					</div>
				</div>
                
                
                
                
               <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productOldPrice" class="layui-form-label">
						原价：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productOldPrice" name="productOldPrice" required lay-verify="required" placeholder="（单位：分）"
                        autocomplete="off" class="layui-input" value="${product.productOldPrice}">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productPrice" class="layui-form-label">
						现价：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productPrice" name="productPrice" required lay-verify="required" placeholder="（单位：分）"
                        autocomplete="off" class="layui-input" value="${product.productPrice}">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productStock" class="layui-form-label">
						库存：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productStock" name="productStock" required lay-verify="required" placeholder="数量"
                        autocomplete="off" class="layui-input" value="${product.productStock}">
                 	</div>
                 </div>
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginDate" name="productBeginDate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${beginDate}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEnddate" name="productEnddate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${endDate}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">有效期：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEctivedate" name="productEctivedate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${ectiveDate}">
                    	</div>
                    </div>
                </div>
                
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 图片： </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="${sysConfig.webUrl}${product.productShowPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						lay-verify="title" autocomplete="off"  class="layui-input" value="${product.productShowPic}">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 轮播图： </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td align="center">
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl1" name="imgurl">
					<input type="hidden" id="img1" name="img" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl2" name="imgurl">
					<input type="hidden" id="img2" name="img" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl3" name="imgurl">
					<input type="hidden" id="img3" name="img"" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl4" name="imgurl">
					<input type="hidden" id="img4" name="img" >
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl5" name="imgurl">
					<input type="hidden" id="img5" name="img" >
					</td>
					</tr>
				</table>
					<input type="hidden" id="shopMpic" name="shopMpic" required
						lay-verify="title" autocomplete="off" class="layui-input" value="${product.productMorePic}">
		        </div>
			</div>
			
			<div class="layui-form-item">
				<div class = "layui-inline">
    				<label class="layui-form-label">提成：</label>
    				<div class="layui-input-inline">
      					<input type="text"   id="Commission" class="layui-input" autocomplete="off">
    				</div>
    			</div>
  			</div>
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						显示：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="productIsShow" lay-skin="switch" id="productIsShow" lay-text="ON|OFF" lay-filter="switchTest" value="${product.productIsShow}" disabled="disabled">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
                        	<em>OFF</em>
                        	<i></i>
                        </div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">
 							限购：
                    	</label>
                    	<div class="layui-input-inline" onclick="changeNum()">
                        	<input type="checkbox" name="productLimitType" lay-skin="switch" id="productLimitType" lay-text="ON|OFF" lay-filter="switchTest" value="${product.productLimitType}" disabled="disabled">
                        	<div class="layui-unselect	layui-form-switch" lay-skin="_switch">
                        	<em>OFF</em>
                        	<i></i>
                        	</div>
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum" style="color:#757575">限购数量：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productLimitNum" name="productLimitNum"  placeholder="个数"
                        	autocomplete="off" class="layui-input" disabled="disabled" value="${product.productLimitNum}" >
                    	</div>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						推荐：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="like[productIndexRecommend]" value="${product.productIndexRecommend}" id="productIndexRecommend" title="首页推荐" >
                    	<input type="checkbox" name="like[productListRecommend]" value="${product.productListRecommend}" id="productListRecommend" title="列表推荐" >
                    </div>
                </div>
                
                <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productSort" class="layui-form-label">
						排序：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productSort" name="productSort"
                        autocomplete="off" class="layui-input" value="${product.productSort}">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productShowNum" class="layui-form-label">
						浏览量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productShowNum" name="productShowNum" 
                        autocomplete="off" class="layui-input" value="${product.productShowNum}">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productSaleNum" class="layui-form-label">
						销量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productSaleNum" name="productSaleNum"
                        autocomplete="off" class="layui-input" value="${product.productSaleNum}">
                 	</div>
                 </div>
                 <div class="layui-inline">
                    <label for="productntegral" class="layui-form-label">
						积分：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productntegral" name="productntegral"
                        autocomplete="off" class="layui-input" value="${product.productntegral}">
                 	</div>
                 </div>
                </div>
                
 				<div class="layui-form-item">
                    <label for="productIntroduce" class="layui-form-label">
 						产品介绍：
                    </label>
                    <div class="layui-input-block">
                        ${product.productIntroduce}
                    </div>
                </div>
            </form>
            <hr/><hr/>
            <br/>
            <br/>
            <br/>
            
            <hr/>
            <hr/>
           
            	<div class="layui-form-item">
            		<input type="hidden" id="uuid" value="${product.uuid}">
                    <label for="auditAdvice" class="layui-form-label">
 						审核意见：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="auditAdvice" id="auditAdvice"  class="layui-input"  style="height:200px"></textarea>
                	</div>
                </div>
            	<div class="layui-form-item">
                    <button class="layui-btn" onclick="pass()">
						审核通过
                    </button>
                    <button class="layui-btn layui-btn-danger" onclick="nopass()">
						审核不通过
                    </button>
                </div>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        
         //实例化编辑器
        	var str = "${product.productMorePic}";
        	var strA = str.split(",");
        	console.log(strA);
        	var webUrl = '${sysConfig.webUrl}';
             
             layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
			$("input").attr("style","border-width:0");
			$("input").attr("readonly","readonly");
			$("label").attr("style","border-width:0");
			
			var html = $("#productIntroduce").val();
			$("#productIntroduce").val($(html).text());
	        //是否限购    
            if($("#productLimitType").val()=="1"){
             		$("#productLimitType").prop("checked",true);
             		$("#productLimitNum").removeAttr("disabled");
           			$("#LimitNum").removeAttr("style");
            }
            if($("#productLimitType").val()=="0"){
             		$("#productLimitNum").val("");
            }
            
            //佣金
            if("${product.productCommissionCode}"=="1"){
            	var commission = "(佣金)"+${product.productCommissionPrice}+"分";
            	$("#Commission").val(commission);
            }
            if("${product.productCommissionCode}"=="0"){
            	var commission = "(比例)"+${product.productCommissionPrice}+"%";
            	$("#Commission").val(commission);
            }
            
            //是否显示
            if($("#productIsShow").val()=="1"){
             		$("#productIsShow").prop("checked",true);
            }
            //是否前端推荐
            if($("#productListRecommend").val()=="1"){
             		$("#productListRecommend").prop("checked",true);
            }
            //是否页面推荐
            if($("#productIndexRecommend").val()=="1"){
             		$("#productIndexRecommend").prop("checked",true);
            }
            
            //图片显示
  			for(var i=0;i<strA.length;i++){
  				var imgurl = "#imgurl"+(i+1);
  				$(imgurl).attr("src",webUrl+strA[i])
  			}
  			
  			//最后一行
  			if($("#productSort").val()=="0"){
  				$("#productSort").val("");
  			}
  			if($("#productShowNum").val()=="0"){
  				$("#productShowNum").val("");
  			}
  			if($("#productSaleNum").val()=="0"){
  				$("#productSaleNum").val("");
  			}
  			if($("#productntegral").val()=="0"){
  				$("#productntegral").val("");
  			}
            //渲染
            layui.form.render();
             
   });           
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
          function pass(){
          	var message = "审核通过！"
           	var pass="yes";
          	var advice = $("#auditAdvice").val();
          	if(advice==""){
          		layer.msg("请输入审核意见！",{time: 5000, icon:7});
          		return false;
          	}
          	var uuid = $("#uuid").val();
          	audit(pass,advice,uuid,message); 
           } 
            
           function nopass(){
           	var message = "已经将审核结果成功反馈给商家！"
           	var pass="no";
          	var advice = $("#auditAdvice").val();
          	if(advice==""){
          		layer.msg("请输入审核意见！",{time: 5000, icon:7});
          		return false;
          	}
          	var uuid = $("#uuid").val(); 
           	audit(pass,advice,uuid,message);  
            } 
           
            function audit(pass,advice,uuid,message){
           	$.ajax({
                type:"post",
           		url:"audit.do",
                data:{"pass":pass,"advice":advice,"uuid":uuid},
                success:function(msg){
                   if(msg=="success"){
                       layer.alert(message, {icon: 6},function () {
                           // 获得frame索引
                        try{
                          	var index = parent.layer.getFrameIndex(window.name);
                          	console.log(index);
                          	//关闭当前frame
                         	parent.window.location.reload();
                          	parent.layer.close(index);
                          }catch(exception){
                          	window.location.reload();
                          }
                        });
                   }
                }
           })
		  } 
		 
		  
          
           
        </script>
        
    </body>

</html>