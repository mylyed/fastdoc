<#include "../common/layout.ftl">
<#include "../common/pagination.ftl">
<#assign title="搜索">
<@head>
</@head>
<@body>
    <div class="container manual-body">

        <div class="search-head">
            <strong class="search-title">显示"${keyword!}"的搜索结果</strong>
        </div>
        <div class="row">
            <div class="manual-list">
                <#if lists?? && lists?size gt 0>
                    <#list lists as item>
<#--                        //TODO-->
                    </#list>
                <#else>
                    <div class="search-empty">
                        <img src="${ctx}/static/images/search_empty.png" class="empty-image">
                        <span class="empty-text">暂无相关搜索结果</span>
                    </div>
                </#if>
                <@_pagination/>
            </div>
        </div>
    </div>
</@body>
<@footer>

</@footer>