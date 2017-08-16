<#macro pagging currentPage1 orderDtoPage1 size1>
<div class="col-md-12 column">
    <ul class="pagination">
    <#if currentPage1 lte 1>
        <li class="disabled"><a href="#">上一页</a></li>
    <#else>
        <li><a href="/sell/seller/order/list?page=${currentPage1-1}&size=${size1}">上一页</a></li>
    </#if>
    <#if (currentPage1-3) gt 1>
        <li><a>···</a></li>
    </#if>
    <#list 1..orderDtoPage1.getTotalPages() as index>
        <#if index gte currentPage1-3 && index lte currentPage1+3>
            <#if index==currentPage1>
                <li class="disabled"><a href="#" style="color:white;background-color:#576ec3;">${index}</a></li>
            <#else>
                <li><a href="/sell/seller/order/list?page=${index}&size=${size1}">${index}</a></li>
            </#if>
        </#if>
    </#list>
    <#if (currentPage1+3)<orderDtoPage1.getTotalPages()>
        <li><a>···</a></li>
    </#if>
    <#if currentPage1 gte orderDtoPage1.getTotalPages()>
        <li class="disabled"><a href="#">下一页</a></li>
    <#else>
        <li><a href="/sell/seller/order/list?page=${currentPage1+1}&size=${size1}">下一页</a></li>
    </#if>
    </ul>
</div>
</#macro>