<#macro formatOrderStatus status>
    <#if status==0>
    新订单
    <#elseif status==1>
    已完结
    <#elseif status==2>
    已取消
    </#if>
</#macro>

<#macro formatPayStatus status>
    <#if status==0>
    等待支付
    <#elseif status==1>
    支付成功
    </#if>
</#macro>
