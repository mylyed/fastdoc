<#include "../common/layout.ftl">
<#include "../common/pagination.ftl">
<#assign title="项目空间列表">
<@head>
</@head>
<@body>
    <div class="container manual-body">
        <div class="row">
            <div class="manual-list">
                <#if itemsets?? && itemsets?size gt 0>
                    <#list itemsets as item>
                        <div class="list-item">
                            <dl class="manual-item-standard">
                                <dt>
                                    <a href="${ctx}/items/${item.identify!}" title="${item.bookName!}-${item.createName!}" target="_blank">
                                    <img src="${ctx}${item.cover!}" class="cover" alt="${item.bookName!}-${item.createName!}">
                                    </a>
                                </dt>
                                <dd>
                                    <a href="${ctx}/items/${item.Identify!}" class="name" title="${item.bookName!}-${item.createName!}" target="_blank">${item.bookName!}</a>
                                </dd>
                                <dd>
                            <span class="author">
                                <b class="text">作者</b>
                                <b class="text">-</b>
                                <b class="text">
                                 <#if item.realName?? && item.realName!="">
                                     ${item.realName!}
                                 <#else >
                                     ${item.createName!}
                                 </#if>
                                </b>
                            </span>
                                </dd>
                            </dl>
                        </div>

                    </#list>
                <#else>
                    <div class="search-empty">
                        <img src="${ctx}/static/images/search_empty.png" class="empty-image">
                        <span class="empty-text">没有项目空间</span>
                    </div>
                </#if>

                <@_pagination min=1/>

            </div>
        </div>
    </div>


</@body>
<@footer>

</@footer>