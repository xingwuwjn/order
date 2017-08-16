<#import "/macro/orderMacro.ftl"as orderMacro>
<#import "/macro/pagging.ftl"as paggingMacro>
<html>
<head>
    <meta charset="utf-8">
    <title>卖家订单列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>

<body>
<div id="wrapper" class="toggled">
    <#--边栏-->
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDtoPage.content as orderDto>
                        <tr>
                            <td>${orderDto.orderId}</td>
                            <td>${orderDto.buyerName}</td>
                            <td>${orderDto.buyerPhone}</td>
                            <td>${orderDto.buyerAddress}</td>
                            <td>${orderDto.orderAmount}</td>
                            <td><@orderMacro.formatOrderStatus status=orderDto.orderStatus/></td>
                            <td><@orderMacro.formatPayStatus status=orderDto.payStatus/></td>
                            <td>${orderDto.createTime}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a></td>
                            <td>
                                <#if orderDto.orderStatus==0>
                                    <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <@paggingMacro.pagging currentPage1=currentPage orderDtoPage1=orderDtoPage size1=size />
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModel" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                               提醒
                            </h4>
                        </div>
                        <div class="modal-body">
                            你有新订单
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:document.getElementById('notice').pause();">关闭</button>
                            <button type="button" onclick="location.reload()" class="btn btn-primary">查看新的订单</button>
                        </div>
                    </div>

                </div>

</div>
<audio id="notice" loop="loop">
    <source src="/sell/mp3/haigui.mp3" type="audio/mpeg"/>
</audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket=null;
    if('WebSocket' in window){
        websocket=new WebSocket('ws://localhost:8080/sell/webSocket');
    }
    else{
        alert('该浏览器不支持websocket!');
    }
    websocket.onopen=function (event) {
        console.log('建立连接');
    }
    websocket.onclose=function (event) {
        console.log("连接关闭");
    }
    //消息来了的时候
    websocket.onmessage=function (event) {
        console.log('收到消息'+event.data);
        //音乐播放
        document.getElementById("notice").play();
        //弹窗提醒
        $('#myModel').modal('show');

    }
    //通信发生错误
    websocket.onerror=function () {
        websocket.close();
    }
    //离开页面的时候
    window.onbeforeunload=function () {
        websocket.close();
    }
</script>
<#--
-------------
&lt;#&ndash;${orderDtoPage}<br>&ndash;&gt;
--------
&lt;#&ndash;${orderDtoPage.content}<br>&ndash;&gt;
--------------
${orderDtoPage.getTotalElements()}<br><br>
-----------------------------------------------------
<#list orderDtoPage.content as orderDto>
${orderDto.orderId}
</#list>
-->
</body>
</html>
