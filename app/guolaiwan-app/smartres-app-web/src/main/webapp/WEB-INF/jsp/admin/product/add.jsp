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
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
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
                        	<input type="text" id="productName" name="productName" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 					<div class="layui-inline">
 						<label for="productSubtitle" class="layui-form-label">
							副标题：
                   		</label>
                    	<div class="layui-input-inline">
                       		<input type="text" id="productSubtitle" name="productSubtitle" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 				</div>
                
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">板块名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="modularCode" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<!-- <div class="layui-inline">
						<label class="layui-form-label">分类名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularClass" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="modularClassId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div> -->
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('选择分类','<%=request.getContextPath() %>/admin/modular/comSel','600','400')" class="layui-btn " >选择分类</a>
						</div>
					</div>
				</div>
				
				
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商家名称</label>
						<div class="layui-input-inline">
							<input type="text" id="MerchantName" name="productMerchantName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="MerchantID" name="productMerchantID" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('选择商家','<%=request.getContextPath() %>/admin/merchant/sellist?mcname=MerchantName&mcuuid=MerchantID','600','400')" class="layui-btn " >选择分类</a>
						</div>
					</div>
				</div>
                
                
                
                
               <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productOldPrice" class="layui-form-label">
						原价：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productOldPrice" name="productOldPrice" required lay-verify="required" placeholder="（单位：元）"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productPrice" class="layui-form-label">
						现价：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productPrice" name="productPrice" required lay-verify="required" placeholder="（单位：元）"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productStock" class="layui-form-label">
						库存：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productStock" name="productStock" required lay-verify="required" placeholder="数量"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginDate" name="productBeginDate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEnddate" name="productEnddate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">有效期：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEctivedate" name="productEctivedate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                </div>
                
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 显示图片 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn " >图片素材</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 轮播图 </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td align="center">
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl1">
					<input type="hidden" id="img1" name="img1" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img1&img=imgurl1','600','400')" class="layui-btn " >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl2">
					<input type="hidden" id="img2" name="img2" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img2&img=imgurl2','600','400')" class="layui-btn " >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl3">
					<input type="hidden" id="img3" name="img3"" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img3&img=imgurl3','600','400')" class="layui-btn " >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl4">
					<input type="hidden" id="img4" name="img4" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img4&img=imgurl4','600','400')" class="layui-btn " >图片素材</a>
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl5">
					<input type="hidden" id="img5" name="img5" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img5&img=imgurl5','600','400')" class="layui-btn " >图片素材</a>
					</td>
					</tr>
				</table>
				
					<input type="hidden" id="shopMpic" name="shopMpic" required
						lay-verify="title" autocomplete="off" class="layui-input" >
		        </div>
			</div>
			
			<div class="layui-form-item">
				<div class = "layui-inline">
    				<label class="layui-form-label">提成：</label>
    				<div class="layui-input-block">
      					<input type="radio" name="productCommissionCode" value="1" title="佣金" id="Commission"  lay-filter="filter" checked="">
      					<input type="radio" name="productCommissionCode" value="0" title="比例" id="Proportion"  lay-filter="filter">
    				</div>
    			</div>
    			
    			<div class = "layui-inline">
    				<label class="layui-form-label" id ="commissionPrice">金额：</label>
    				<div class="layui-input-block">
      					<input type="text" name="productCommissionPrice" id="productCommissionPrice" class="layui-input" placeholder="（单位：元）"
      					required lay-verify="required">
    				</div>
    			</div>
  			</div>
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						显示：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="productIsShow" lay-skin="switch" id="productIsShow" lay-text="ON|OFF" lay-filter="switchTest" value="1">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch">
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
                        	<input type="checkbox" name="productLimitType" lay-skin="switch" id="productLimitType" lay-text="ON|OFF" lay-filter="switchTest" value="1" >
                        	<div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
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
                        	autocomplete="off" class="layui-input" disabled="disabled">
                    	</div>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						推荐：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="like[productIndexRecommend]" value="1" id="productIndexRecommend" title="首页推荐">
                    	<input type="checkbox" name="like[productListRecommend]" value="1" id="productListRecommend" title="列表推荐">
                    </div>
                </div>
                
                <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productSort" class="layui-form-label">
						排序：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productSort" name="productSort"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productShowNum" class="layui-form-label">
						浏览量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productShowNum" name="productShowNum" 
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productSaleNum" class="layui-form-label">
						销量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productSaleNum" name="productSaleNum"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 <div class="layui-inline">
                    <label for="productntegral" class="layui-form-label">
						积分：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productntegral" name="productntegral"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                </div>
                
                
                
                
 				
 				<div class="layui-form-item">
                    <label for="productIntroduce" class="layui-form-label">
 						产品介绍：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="productIntroduce"  placeholder="请输入内容" class="layui-input" id="productIntroduce" style="height:300px"></textarea>
                    </div>
                    
                </div>
            	<input type="hidden" name="productAuditstates" value="" class="layui-input">
                <div class="layui-form-item">
                	<button class="layui-btn layui-btn-danger" lay-filter="add1" lay-submit>
						存为草稿
                    </button>
                    <button class="layui-btn" lay-filter="add2" lay-submit >
						提交待审核
                    </button>
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        
         //实例化编辑器
    		var um = UM.getEditor('productIntroduce');
    
        
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
              
             
            form.on('radio(filter)', function(data){
  				if(data.value==1){
  					$("#commissionPrice").html("金额：");
  					$("#productCommissionPrice").attr("placeholder","（单位：分）");
  				}else{
  					$("#commissionPrice").html("百分之：");
  					$("#productCommissionPrice").attr("placeholder","整数");
  				}
  				
			}); 
             
             //监听提交
              form.on('submit(add1)', function(data){
              	data.field.productAuditstates = '草稿';
              	var message = "保存草稿成功！"
              	console.log(data.field);
              	add(data,message);
              	return false;
            	});
              //监听提交
              form.on('submit(add2)', function(data){
              	data.field.productAuditstates = '待审核';
              	var message = "增加成功"
              	console.log(data.field);
              	add(data,message);
              	return false;
             });
             
              	
              
              
              laydate.render({
              	elem: '#productBeginDate'
              	,type: 'datetime'
              });
              
              laydate.render({
              	elem: '#productEnddate'
              	,type: 'datetime'
              });
              
              laydate.render({
              	elem: '#productEctivedate'
              	,type: 'datetime'
              });
            });
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
           function changeNum(){
           		if($("#productLimitType").prop("checked")){
           		console.log("进来了");
           			$("#productLimitNum").removeAttr("disabled");
           			$("#LimitNum").removeAttr("style");
           		}else{
           			$("#productLimitNum").prop("disabled", "disabled");
           			$("#LimitNum").prop("style", "color:#757575");
					$("#productLimitNum").val("");
					
           		}
           }
           
           
           function add(data,message){
           
 				//多图字段          
           		var proMpic="";
           		var strPic;
           		for(var i=1;i<6;i++){
           			var str = "#img"+i;
           			if($(str).val()!=''){
           				if(proMpic==''){
           					strPic = $(str).val();
           				}else{
           					strPic = $(str).val()+",";
           				}
           				proMpic = strPic+proMpic;
           				console.log()
           			}
           		}
           		data.field.shopMpic= proMpic;
           		
              	//校验
              	//原价
              	var OldPrice =$("#productOldPrice").val();
				if(!( /^\d+(\.\d+)?$/).test(OldPrice)){
						layer.msg("原价为数字！",{time: 5000, icon:5});
						return false;
				}   
				
				//现价
				var Price =$("#productPrice").val();
				if(!( /^\d+(\.\d+)?$/).test(Price)){
						layer.msg("现价为数字！",{time: 5000, icon:5});
						return false;
				}
				
				//库存
				var Stock =$("#productStock").val();
				if(!(/^[0-9]*[1-9][0-9]*$/).test(Stock)){
						layer.msg("库存为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//排序
				var Sort =$("#productSort").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(Sort))&&Sort!=""){
						layer.msg("排序为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//浏览量
				var ShowNum =$("#productShowNum").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(ShowNum))&&ShowNum!=""){
						layer.msg("浏览量为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//销量
				var SaleNum =$("#productSaleNum").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(SaleNum))&&SaleNum!=""){
						layer.msg("销量为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//销量
				var ntegral =$("#productntegral").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(ntegral))&&ntegral!=""){
						layer.msg("积分为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
              	//限购数量（正整数）
          		var LimitNum =$("#productLimitNum").val();
				if(!(/^[0-9]*[1-9][0-9]*$/).test(LimitNum)&&$("#productLimitType").prop("checked")){
						layer.msg("限购数量为大于0的数字！",{time: 5000, icon:5});
						return false;
				} 
				
				//提成
				var CommissionPrice =$("#productCommissionPrice").val();
				if(!(/^\d+(\.\d+)?$/).test(CommissionPrice)){
						layer.msg("提成为数字！",{time: 5000, icon:5});
						return false;
				} 
				
				
				//原价小数
				var oldPrice = parseFloat(data.field.productOldPrice);
				var price = parseFloat(data.field.productPrice);
           		data.field.productOldPrice = oldPrice.toFixed(2);
           		data.field.productPrice = price.toFixed(2);
           		
           		//提成
           		var cprice = parseFloat(data.field.productCommissionPrice);
				if(data.field.productCommissionCode==0){
					cprice = cprice/100
				}
				data.field.productCommissionPrice = cprice.toFixed(2);
				
                $.ajax({
                	  type:"post",
           			  url:"add.do",
                      data:data.field,
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
                             }catch(exception)
                             {
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