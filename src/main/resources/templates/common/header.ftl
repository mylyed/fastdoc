<header class="navbar navbar-static-top navbar-fixed-top manual-header" role="banner">
    <div class="container">
        <div class="navbar-header col-sm-12 col-md-9 col-lg-8">
            <a href="${ctx}/" class="navbar-brand" title="${SITE_NAME}">
                ${SITE_NAME}
            </a>
            <nav class="collapse navbar-collapse col-sm-10">
                <ul class="nav navbar-nav">
                    <li <#if ControllerName??&&ControllerName=="HomeController">class="active"</#if>>
                        <a href="${ctx}/" title="首页">首页</a>
                    </li>

                    <li <#if ControllerName??&&ControllerName=="ItemsetsController">class="active"</#if>>
                        <a href="${ctx}/items" title="项目空间">项目空间</a>
                    </li>

                </ul>

                <div class="searchbar pull-left visible-lg-inline-block visible-md-inline-block">
                    <form class="form-inline" action="${ctx}/search" method="get">
                        <input class="form-control" name="keyword" type="search" style="width: 230px;"
                               placeholder="请输入关键词..." value="${keyword!}">
                        <button class="search-btn">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>
                </div>
            </nav>
        </div>
        <nav class="navbar-collapse hidden-xs hidden-sm" role="navigation">
            <ul class="nav navbar-nav navbar-right">
                <#if member??>
                    <li>
                        <div class="img user-info" data-toggle="dropdown">
                            <img src="${ctx}${(member.avatar)!}"
                                 onerror="this.src='${ctx}/static/images/headimgurl.jpg';"
                                 class="img-circle userbar-avatar" alt="${(member.account)!}">
                            <div class="userbar-content">
                                <span>${(member.account)!}</span>
                                <#--                                    <div>${(member.role)!}</div>-->
                            </div>
                            <i class="fa fa-chevron-down" aria-hidden="true"></i>
                        </div>
                        <ul class="dropdown-menu user-info-dropdown" role="menu">
                            <li>
                                <a href="${ctx}/setting" title="个人中心"><i class="fa fa-user"
                                                                         aria-hidden="true"></i>
                                    个人中心</a>
                            </li>
                            <li>
                                <a href="${ctx}/book" title="我的项目"><i class="fa fa-book"
                                                                      aria-hidden="true"></i>
                                    我的项目</a>
                            </li>
                            <li>
                                <a href="${ctx}/manage/blogs" title="我的文章"><i class="fa fa-file"
                                                                              aria-hidden="true"></i>
                                    我的文章</a>
                            </li>

                            <#if member.role==0>
                                <li>
                                    <a href="${ctx}/manager" title="管理后台"><i class="fa fa-university"
                                                                             aria-hidden="true"></i>
                                        管理后台</a>
                                </li>
                            </#if>

                            <li>
                                <a href="${ctx}/logout" title="退出登录"><i
                                            class="fa fa-sign-out"></i> 退出登录</a>
                            </li>
                        </ul>
                    </li>
                <#else>
                    <li><a href="${ctx}/login" title="用户登录">登录</a></li>
                </#if>
            </ul>
        </nav>
    </div>
</header>