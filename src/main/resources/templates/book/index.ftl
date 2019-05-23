<#include "../common/layout.ftl">
<#include "../common/pagination.ftl">
<#assign title="用户中心">
<@head>
    <link href="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/css/fileinput.min.css" rel="stylesheet"
          type="text/css">
    <link href="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/themes/explorer-fa/theme.css" rel="stylesheet"
          type="text/css">
    <link href="${ctx}/static/select2/4.0.5/css/select2.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/main.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="${ctx}/static/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</@head>
<@body>
    <div class="container manual-body">
        <div class="row">
            <div class="page-left">
                <ul class="menu">
                    <li <#if ControllerName="BookController">class="active"</#if>><a href="${ctx}/book" class="item"><i
                                    class="fa fa-sitemap" aria-hidden="true"></i> 我的项目</a></li>
                    <#--                    <li <#if ControllerName="BlogController">class="active"</#if>><a href="${ctx}/manage/blogs" class="item"><i class="fa fa-file" aria-hidden="true"></i> 我的文章</a> </li>-->
                </ul>
            </div>
            <div class="page-right">
                <div class="m-box">
                    <div class="box-head">
                        <strong class="box-title">项目列表</strong>
                        &nbsp;
                        <button type="button" data-toggle="modal" data-target="#addBookDialogModal"
                                class="btn btn-success btn-sm pull-right">添加项目
                        </button>
                        <button type="button" data-toggle="modal" data-target="#importBookDialogModal"
                                class="btn btn-primary btn-sm pull-right" style="margin-right: 5px;">导入项目
                        </button>
                    </div>
                </div>
                <div class="box-body" id="bookList">
                    <div class="book-list">
                    <#if books?size=0>
                    <div class="text-center">暂无数据</div>
                    <#else >
                    <#list books as item>
                        <div class="list-item" v-for="item in lists">
                            <div class="book-title">
                                <div class="pull-left">
                                    <a title="项目概要" data-toggle="tooltip">
                                    <#if item.privatelyOwned>
                                    <i class="fa fa-lock" aria-hidden="true"></i>
                                    <#else >
                                    <i class="fa fa-unlock" aria-hidden="true"></i>
                                    </#if>
                                        ${item.bookName}
                                    </a>
                                </div>
                                <div class="pull-right">
                                    <div class="btn-group">
                                        <a class="btn btn-default">设置</a>

                                        <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a target="_blank">阅读</a></li>

                                            <li><a target="_blank">编辑</a></li>


                                            <li><a>删除</a></li>
                                            <li><a>复制</a></li>

                                        </ul>

                                    </div>


                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="desc-text">
                                <a title="项目概要" style="font-size: 12px;">
                                        ${item.description}
                                        </a>
                            </div>
                            <div class="info">
                                <span title="创建时间" data-toggle="tooltip" data-placement="bottom">
                                <i class="fa fa-clock-o"></i>
                                    ${item.createTime?date}
                                </span>
                                <span title="创建者" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-user"></i> ${name(item.memberId)}</span>
                                <span title="文档数量" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-pie-chart"></i> ${item.docCount!}</span>
                                <span title="项目角色" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-user-secret"></i> ${item.roleName!}项目角色</span>
                                <span title="最后编辑" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-pencil"></i> 最后编辑: ${item.modifyTime?date!}</span>
                            </div>
                        </div>
                    </#list>
                    </#if>
                    </div>
            <nav class="pagination-container">
                <@_pagination></@_pagination>
            </nav>
        </div>
    </div>



    <!-- Modal -->


</@body>
<@footer>
    <script src="${ctx}/static/vuejs/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jquery.form.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/js/fileinput.min.js"></script>
    <script src="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/js/locales/zh.js"></script>
    <script src="${ctx}/static/layer/layer.js" type="text/javascript"></script>
    <script src="${ctx}/static/select2/4.0.5/js/select2.full.min.js"></script>
    <script src="${ctx}/static/select2/4.0.5/js/i18n/zh-CN.js"></script>
    <script src="${ctx}/static/js/main.js" type="text/javascript"></script>

</@footer>