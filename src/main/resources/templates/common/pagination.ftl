<#macro _pagination min=0>
<#--    min  最少几页才显示分页条 -->
    <#assign  uri=request.getRequestUri()>


    <nav class="pagination-container">
        <#if pagination.totalPages gt min>
            <ul class="pagination">

                <#--首页和上一页-->
                <#if pagination.number gt 1>
                    <li><a href="${uri}?page=1">首页</a></li>
                    <li><a href="${uri}?page=${pagination.number-1}">上一页</a></li>
                <#else>
                    <li class="disabled"><a href="#">首页</a></li>
                    <li class="disabled"><a href="#">上一页</a></li>
                </#if>

                <#--中间页码-->
                <#assign  startPos= pagination.number - 3>
                <#assign  endPos= pagination.number + 3>
                <#if startPos lt 1>
                    <#assign  endPos= endPos+(startPos?abs)+1>
                    <#assign  startPos= 1>
                </#if>

                <#if endPos gt pagination.totalPages>
                    <#assign  endPos= pagination.totalPages>
                </#if>
                <#list startPos..endPos as i>
                    <#if i==pagination.number>
                        <li class="active"><a href="${uri}?page=${i}">${i}</a></li>
                    <#else>
                        <li><a href="${uri}?page=${i}">${i}</a></li>
                    </#if>
                </#list>

                <#--最后一页-->
                <#if pagination.number lt pagination.totalPages>
                    <li><a href="${uri}?page=${pagination.number+1}">下一页</a></li>
                    <li><a href="${uri}?page=${pagination.totalPages}">末页</a></li>
                <#else>
                    <li class="disabled"><a href="#">下一页</a></li>
                    <li class="disabled"><a href="#">末页</a></li>
                </#if>
            </ul>
        </#if>
        <div class="clearfix"></div>
    </nav>
</#macro>