<#import "/macro/pagging.ftl"as paggingMacro>
<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
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
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>价格</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>状态</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list ProductPage.content as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><image src="${productInfo.productIcon}"></image></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.getProductStatusEnum().message}</td>
                            <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.productStatus==0>
                                    <a href="/sell/seller/product/down?productId=${productInfo.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/up?productId=${productInfo.productId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <@paggingMacro.pagging currentPage1=currentPage orderDtoPage1=ProductPage size1=size />
            </div>
        </div>
    </div>
</div>

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
