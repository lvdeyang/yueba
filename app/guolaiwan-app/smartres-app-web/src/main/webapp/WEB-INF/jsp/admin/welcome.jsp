<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            过来玩餐厅管理平台
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/css/bootstrap.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/js/bootstrap.min.js" media="all">
    </head>
    <body>
    <style type="text/css">
	legend { display: block; width:100px; border-bottom:0px; font-family: "Microsoft YaHei","Helvetica Neue";}
	legend a{ color:#666;} legend a:hover{ text-decoration:none;}
	.layui-table{ font-family: "Microsoft YaHei","Helvetica Neue";}
	</style>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
                欢迎使用过来玩餐厅管理平台！<span class="f-14">v1.0</span> &nbsp;&nbsp;
            </blockquote>
<div class="row">
            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-blue"> <i class="fa fa-address-card"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="用户总数"><i class="iconfont " data-icon=""></i>
                            <h1>${userCount}</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="用户总数"> <i class="iconfont " data-icon=""></i><span>用户总数</span></a>

                    </div>
                </section>
            </div>
            <div class="col-xs-6 col-sm-4 col-md-3" style="display:none;">
                <section class="panel">
                    <div class="symbol bgcolor-commred"> <i class="fa fa-handshake-o"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon=""></i>
                            <h1>10</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon=""></i><span>今日新增</span></a>

                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-3"  style="display:none;">
                <section class="panel">
                    <div class="symbol bgcolor-dark-green"> <i class="fa fa-bar-chart"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="订单总数"> <i class="iconfont " data-icon=""></i>
                            <h1>10</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="订单总数"> <i class="iconfont " data-icon=""></i><span>订单总数</span></a>
                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-yellow-green"> <i class="fa fa-cubes"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日订单"> <i class="iconfont " data-icon=""></i>
                            <h1>${orderCount}</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日订单"> <i class="iconfont " data-icon=""></i><span>今日订单</span></a>
                    </div>
                </section>
            </div>

        </div>
            
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default">信息统计</a></legend>
            </fieldset>
             
        <!--相关统计-->
        <div class="row">
        <div class="col-sm-6" style="display:none;">
         <section class="panel">
                    <div class="panel-heading">区域统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="area" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">订单统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="main" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6" >
         <section class="panel">
                    <div class="panel-heading">产品统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="years" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6" style="display:none;">
         <section class="panel">
                    <div class="panel-heading">产品统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="product" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        </div>
    
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/index.js"></script>
        <script src="<%=path %>/layui/js/echarts.min.js"></script>
        <script src="<%=path %>/layui/js/echart.js"></script>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var orderTitleDatas=[];
        var orderValueDatas=[];
        <c:forEach items="${orderReports}" var="report">  
             orderTitleDatas.push('${report.title}');
             orderValueDatas.push(${report.value});
        </c:forEach> 


        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['销量']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: orderTitleDatas
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'销售额(份)',
                    type:'line',
                    stack: '总量',
                    data:orderValueDatas
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 指定图表的配置项和数据
       var myChart = echarts.init(document.getElementById('area'));

        // 指定图表的配置项和数据
    var option = {
        title: {
            text: '地区订单统计',
            subtext: '纯属虚构',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['广州', '佛山', '东莞', '中山', '深圳']
        },
        series: [{
            name: '地区会员数量',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
                { value: 6335, name: '广州' },
                { value: 4310, name: '佛山' },
                { value: 2310, name: '东莞' },
                { value: 3310, name: '中山' },
                { value: 9310, name: '深圳' }
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('years'));

	    var proTitleDatas=[];
        var proValueDatas=[];
 
        <c:forEach items="${proReports}" var="report">  
             proTitleDatas.push('${report.title}');
             proValueDatas.push(${report.value});
        </c:forEach> 

        // 指定图表的配置项和数据
        var option = {
            title: {
            text: '最火爆商品统计',
            subtext: '',
            x: 'center'
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: proTitleDatas,
            axisTick: {
                alignWithLabel: true
            }
        }],
        yAxis: [{
            type: 'value'
        }],
        series: [{
            name: '销量(份)',
            type: 'bar',
            barWidth: '60%',
            data: proValueDatas
        }]
    };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('product'));

        // 指定图表的配置项和数据
        var option = {
             title: {
                text: '产品订单比例',
                subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['车贷', '房贷']
            },
            series: [{
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    { value: 1335, name: '车贷' },
                    { value: 310, name: '房贷' }
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
            
        </div>
        <div class="layui-footer footer footer-demo">
            <div class="layui-main">
                <p>
                    <a href="/">
                        Copyright ©2017-2018 www.guolaiwan.net All Rights Reserved.
                    </a>
                </p>
                
            </div>
        </div>
        
    </body>
</html>