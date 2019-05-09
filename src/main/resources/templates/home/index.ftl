<#include "../common/layout.ftl">
<#include "../common/pagination.ftl">
<#assign title=SITE_NAME>
<@head>

</@head>
<@body>
    <div class="container manual-body">
        <div class="row">
            <div class="manual-list">
                <#list books as item>

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

                <#if books?size=0 >
                    <div class="text-center" style="height: 200px;margin: 100px;font-size: 28px;">暂无项目</div>
                </#if>
                <div class="clearfix"></div>
            </div>
            <@_pagination min=1/>
        </div>
    </div>
</@body>
<@footer>

</@footer>