<html>
<head>
    <meta charset="utf-8">
    <title>商品分类列表</title>
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
                    <form role="form" method="post" action="/sell/seller/category/add">
                        <div class="form-group">
                            <label>分类名称</label><input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目编号</label><input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!0}"/>
                        </div>
                        <input type="hidden" name="categoryId" value="${(category.categoryId)!''}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
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
