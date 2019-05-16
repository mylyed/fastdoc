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
                        <div class="list-item">
                            <dl class="manual-item-standard">
                                <dt>
                                    <a href="${ctx}/doc/read/${item.identify}"
                                       title="${item.bookName!}-${item.author!}" target="_blank">
                                        <img src="${item.cover!}" class="cover"
                                             alt="${item.bookName!}-${item.author!}"
                                             onerror="this.src='${ctx}/static/images/book.jpg';">
                                    </a>
                                </dt>
                                <dd>
                                    <a href="${ctx}/doc/read/${item.identify}" class="name"
                                       title="${item.bookName!}-${item.author!}" target="_blank">${item.bookName!}</a>
                                </dd>
                                <dd>
                            <span class="author">
                                <b class="text">作者</b>
                                <b class="text">-</b>
                                <b class="text">${item.author!}</b>
                            </span>
                                </dd>
                            </dl>
                        </div>
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