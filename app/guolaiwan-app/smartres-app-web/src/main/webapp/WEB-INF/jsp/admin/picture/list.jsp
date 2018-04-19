<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>过来玩餐厅管理平台</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>

	

	<div class="x-body" style="height:1500px">
	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
		<xblock>
		<button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button>
		<button type="button" class="layui-btn" id="testListAction">开始上传</button>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<span class="x-right" style="line-height:40px">共有数据：<span id="count">${count}</span>
			条</span></xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" id="chooseAll" onclick="chooseAll()"></th>
					<th>缩略图</th>
					<th>文件名</th>
					<th>大小</th>
					<th>上传时间</th>
					<th>状态</th>
					<th>操作</th>
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
                            <input type="checkbox" value="{{ item.uuid }}" name="selected">
                        </td>
						<td>
                            <img  src={{ item.webUrl }} width="90" height="90">	
                        </td>
                        <td>
                            {{ item.oldName }}
                        </td>
                        <td>
                            {{ item.fileSize }}
                        </td>
						<td>
                            {{ item.update }}
                        </td>
                        <td>
                            <span id="introduce" style="color: #5FB878">{{ item.introduce }}</span>
                        </td>
						<td>
                        	<a title="删除" href="javascript:;" onclick="pic_del(this,'{{ item.uuid }}')" 
                           	style="text-decoration:none">
                            	<i class="layui-icon">&#xe640;</i>
                        	</a>
						</td>
                        
                    </tr>
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  没有相关信息
{{#  } }} 
</script>
	</div>

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script>
		//时间格式化
		Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
	}
	var ipage ;	
	var ilimit;
	var doneCount = 0;
	layui.use(['element','layer','laytpl','upload','laydate','laypage'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        laypage = layui.laypage;//分页
        upload = layui.upload;
        laydate = layui.laydate;
       
 		//以上模块根据需要引入
 		fenye();	
 			
 			
                      
 
 		
 
 
  var files;
  //多文件列表示例
  var demoListView = $('#view')
  ,uploadListIns = upload.render({
    elem: '#testList'
    ,url: 'upload.do'
    ,accept: 'file'
    ,field: 'images'
    ,multiple: true
    ,auto: false
    ,bindAction: '#testListAction'
    ,choose: function(obj){ 
     var date = new Date().Format("yyyy-MM-dd hh:mm:ss");
     files = obj.pushFile(); //将每次选择的文件追加到文件队列
      //读取本地文件
      obj.preview(function(index, file, result){
      	
        var tr = $(['<tr id="upload-'+ index +'">'
          ,'<td><input type="checkbox" value="1" name="selected"></td>'
          ,'<td><img width="90" height="90" src="'+result+'"/></td>'
          ,'<td>'+ file.name +'</td>'
          ,'<td>'+ (file.size/1014).toFixed(0) +'kb</td>'
          ,'<td>'+date+'</td>'
          ,'<td>等待上传</td>'
          ,'<td>'
            ,'<i class="layui-btn layui-btn-mini demo-reload layui-hide">重传</i>'
            ,'<i class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</i>'
          ,'</td>'
        ,'</tr>'].join(''));
        
        
        
        //单个重传
        tr.find('.demo-reload').on('click', function(){
          obj.upload(index, file);
        });
        
        //删除
        tr.find('.demo-delete').on('click', function(){
          delete files[index]; //删除对应的文件
          tr.remove();
        });
        demoListView.prepend(tr);
      });
    }
    ,done: function(res, index, upload){
     if(res.code == 0){ //上传成功
     	doneCount++;
     	/* var list = new Array(1);
       	list[0] = res.pic;
     	var data = {
        	"list":list
       	}
		//操作模板引擎
		var getTpl = alist.innerHTML;
				laytpl(getTpl).render(data, function(html){
            		$("#view").prepend(html);
        		}); */
        var tr = demoListView.find('tr#upload-'+ index);
        tr.remove(); 
        
        delete files[index]; //删除文件队列已经上传成功的文件
        
        
       
        
        
        if($.isEmptyObject(files)){
         	var count = parseInt($("#count").text())+doneCount;
        	$("#count").html(count);
        	getlist(ipage,ilimit);
        	layer.msg('成功上传'+doneCount+'条数据!',{icon:1,time:2000});
        	doneCount = 0;
        }
        /* $("#count").html(1); */
        return; 
      }
      
      if(res.code == 1){ //上传成功
     	var tr = demoListView.find('tr#upload-'+ index)
      	,tds = tr.children();
      	tds.eq(5).html('<span style="color: #FF5722;">'+res.error+'</span>');
      	tds.eq(6).find('.demo-reload').removeClass('layui-hide'); //显示重传
      	return;
      }
      
      this.error();
      }
     ,error: function(index,upload) {
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(5).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(6).find('.demo-reload').removeClass('layui-hide'); //显示重传
   	 }
    
  });
  
});
	         
	//异步加载列表
	function getlist(pagecurr,ilimit){
		$.ajax({
			type:"post",
			url:"picList.do",
			async:false,
            data:{'pagecurr':pagecurr,'ilimit':ilimit},
			success:function(msg){
			
				var data = {
           		"list":msg.list
       	 		}
       	 		console.log(data);
				//操作模板引擎
				var getTpl = alist.innerHTML;
				laytpl(getTpl).render(data, function(html){
            		view.innerHTML = html;
        		});
			}
		})
	}
	
	
	//异步删除列表 
	function pic_del(obj,id){
    	layer.confirm('确认要删除吗？',function(index){
   		//发异步删除数据
    		$.ajax({
        		type:"post",
        		url:"picdel.do",
        		data:{"uuid":id},
       			success:function(msg){
        			if(msg=="success"){
            			getlist(ipage,ilimit);
            			$("#count").html(parseInt($("#count").text()) - 1);
            			layer.msg('已删除!',{icon:1,time:1000});
            		}
            	}
       		}) 
   		});
	}
	
	
	//全选和全不选（**）
	function chooseAll(){
		var list = $("input[name='selected']")
		if($("#chooseAll").prop("checked")){
			$("input[name='selected']").prop("checked", "checked");
		}else{
			$("input[name='selected']").removeAttr("checked");			
		}
	}
	
	function delAll(){
		var list =$("input[name='selected']:checked");
		var uuids = new Array(list.length);
		for(var i=0;i<list.length;i++){
			uuids[i] = $(list[i]).val();
		}
		if(uuids.length == 0){
			layer.msg("请选择删除项！");
		}else{
		layer.confirm('确认要删除吗？',function(){
   		
    		$.ajax({
        		type:"post",
        		url:"delAll.do",
        		data:{'uuids':uuids},
       			success:function(msg){
        			if(msg=="success"){
            			/* $("input[name='selected']:checked").parents("tr").remove();  */
            			getlist(ipage,ilimit);
            			if($("#chooseAll").prop("checked")){
            				$("#chooseAll").removeAttr("checked");
            			} 
            			$("#count").html($("#count").text() - list.length);
            			layer.msg('已删除'+list.length+'条数据!',{icon:1,time:1000});
            			
            		}
            		
            	}
       		})
       		 
   		});
   		}
	}
	
	function fenye(){
 			laypage.render({
                elem: 'page'
                ,count: parseInt($("#count").text())
                ,first: '首页'
    			,last: '尾页'
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
    			,layout: ['limit', 'page', 'skip']
                ,jump: function(obj){
                	ilimit =obj.limit;
                	ipage = obj.curr;
                	console.log(obj.count);
                	getlist(obj.curr,obj.limit);
                   }
              });
 		}
</script>

</body>
</html>