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
                    <form role="form" method="post" action="/sell/seller/product/save"  enctype="multipart/form-data">
                        <div class="form-group">
                            <label>商品名称</label><input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商品价格</label><input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商品库存</label><input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商品描述</label><input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>商品图片</label>
                            <input type="file" name="dealImgFile" id="dealImgFile" style="display:inline" />
                        </div>
                        <div class="form-group">
                            <label>商品类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType==category.categoryType>
                                            selected
                                            </#if>
                                          >
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
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
