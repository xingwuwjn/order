<html>
<head>
    <meta charset="utf-8">
    <title>商品类别列表</title>
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
                            <th>分类id</th>
                            <th>分类名称</th>
                            <th>类目编号</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list CategoryList as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${category.updateTime}</td>
                            <td>${category.createTime}</td>
                            <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
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
