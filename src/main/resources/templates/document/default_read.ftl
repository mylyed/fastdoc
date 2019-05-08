<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>${title!} - Powered by MinDoc</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta name="author" content="Minho"/>
    <meta name="site" content="https://www.iminho.me"/>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/jstree/3.3.4/themes/default/style.min.css" rel="stylesheet">
    <link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/nprogress/nprogress.css" rel="stylesheet">
    <link href="${ctx}/static/css/kancloud.css" rel="stylesheet">
    <link href="${ctx}/static/css/jstree.css" rel="stylesheet">
    <link href="${ctx}/static/editor.md/lib/mermaid/mermaid.css" rel="stylesheet">
    <link href="${ctx}/static/editor.md/lib/sequence/sequence-diagram-min.css" rel="stylesheet">
    <link href="${ctx}/static/editor.md/css/editormd.preview.css" rel="stylesheet">
    <link href="${ctx}/static/css/markdown.preview.css" rel="stylesheet">
    <#--    高亮风格切换TODO-->
    <link href="${ctx}/static/editor.md/lib/highlight/styles/github.css" rel="stylesheet">
    <link href="${ctx}/static/katex/katex.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/print.css" media="print" rel="stylesheet">

    <script type="text/javascript">window.book = {"identify": "${book.identify!}"};</script>
</head>
<#-- 递归 -->
<#macro docTree documentTrees>
    <#if documentTrees?? && documentTrees?size gt 0>
        <#list documentTrees as item>

            <ul>
                <li id="${item.documentId}" <#if item.open>class="jstree-open"</#if>>
                    <a href="${ctx}/docs/${item.bookIdentify}/${item.identify}" title="${item.documentName}"
                       data-version="${item.version!}"
                       <#if docIdentify??&&docIdentify==item.identify>class="jstree-clicked"</#if>
                    >${item.documentName}</a>
                    <#if item.children?? && item.children?size gt 0>
                        <@docTree documentTrees=item.children />
                    </#if>

                </li>
            </ul>
        </#list>
    </#if>
</#macro>
<body>
<div class="m-manual manual-mode-view manual-reader">
    <header class="navbar navbar-static-top manual-head" role="banner">
        <div class="container-fluid">
            <div class="navbar-header pull-left manual-title">
                <span class="slidebar" id="slidebar"><i class="fa fa-align-justify"></i></span>
                <a href="${ctx}/docs/${book.identify!}" title="${book.bookName!}"
                   class="book-title">${book.bookName!}</a>
                <span style="font-size: 12px;font-weight: 100;"></span>
            </div>
            <div class="navbar-header pull-right manual-menu">
                <a href="javascript:window.print();" id="printSinglePage" class="btn btn-default"
                   style="margin-right: 10px;"><i class="fa fa-print"></i> 打印</a>
                <div class="dropdown pull-right" style="margin-right: 10px;">
                    <a href="/${ctx}" class="btn btn-default"><i class="fa fa-home" aria-hidden="true"></i> 首页</a>
                </div>
                <div class="dropdown pull-right" style="margin-right: 10px;">

                </div>

            </div>
        </div>
    </header>
    <article class="container-fluid manual-body">
        <div class="manual-left">
            <div class="manual-tab">
                <div class="tab-navg">
                    <span data-mode="view" class="navg-item active"><i class="fa fa-align-justify"></i><b class="text">目录</b></span>
                    <span data-mode="search" class="navg-item"><i class="fa fa-search"></i><b class="text">搜索</b></span>
                </div>
                <div class="tab-util">
                    <span class="manual-fullscreen-switch">
                        <b class="open fa fa-angle-right" title="展开"></b>
                        <b class="close fa fa-angle-left" title="关闭"></b>
                    </span>
                </div>
                <div class="tab-wrap">
                    <div class="tab-item manual-catalog">
                        <div class="catalog-list read-book-preview" id="sidebar">
                            <#--                            导航栏-->
                            <@docTree documentTrees=documentTrees />

                        </div>

                    </div>
                    <div class="tab-item manual-search">
                        <div class="search-container">
                            <div class="search-form">
                                <form id="searchForm" action="${ctx}/docs/${book.identify}/search" method="post">
                                    <div class="form-group">
                                        <input type="search" placeholder="请输入搜索关键字" class="form-control" name="keyword">
                                        <button type="submit" class="btn btn-default btn-search" id="btnSearch">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <div class="search-result">
                                <div class="search-empty">
                                    <i class="fa fa-search-plus" aria-hidden="true"></i>
                                    <b class="text">暂无相关搜索结果！</b>
                                </div>
                                <ul class="search-list" id="searchList">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="m-copyright">
                <p>
                    本文档使用 <a href="https://www.iminho.me" target="_blank">MinDoc</a> 发布
                </p>
            </div>
        </div>
        <div class="manual-right">
            <div class="manual-article">
                <div class="article-head">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-2">

                            </div>
                            <div class="col-md-8 text-center">
                                <h1 id="article-title">${title}</h1>
                            </div>
                            <div class="col-md-2">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="article-content">
                    <div class="article-body
                    <#if book.editor="markdown">markdown-body editormd-preview-container<#else>editor-content</#if>"
                         id="page-content">
                        <#--正文-->
                        ${content!}
                    </div>

<#--                    <div id="articleComment" class="m-comment">-->
<#--                        <div class="comment-result">-->
<#--                            <strong class="title">相关评论(<b class="comment-total">{{.Model.CommentCount}}</b>)</strong>-->
<#--                            <div class="comment-post">-->
<#--                                <form class="form" action="/comment/create" method="post">-->
<#--                                    <label class="enter w-textarea textarea-full">-->
<#--                                        <textarea class="textarea-input form-control" name="content" placeholder="文明上网，理性发言" style="height: 72px;"></textarea>-->
<#--                                        <input type="hidden" name="doc_id" value="118003">-->
<#--                                    </label>-->
<#--                                    <div class="util cf">-->
<#--                                        <div class="pull-left"><span style="font-size: 12px;color: #999"> 支持Markdown语法 </span></div>-->
<#--                                        <div class="pull-right">-->
<#--                                            <span class="form-tip w-fragment fragment-tip">Ctrl + Enter快速发布</span>-->
<#--                                            <label class="form-submit w-btn btn-success btn-m">-->
<#--                                                <button class="btn btn-success btn-sm" type="submit">发布</button>-->
<#--                                            </label>-->
<#--                                        </div>-->
<#--                                    </div>-->
<#--                                </form>-->
<#--                            </div>-->
<#--                            <div class="clearfix"></div>-->
<#--                            <div class="comment-list">-->
<#--                                <div class="comment-empty"><b class="text">暂无相关评论</b></div>-->
<#--                                <div class="comment-item" data-id="5841">-->
<#--                                    <p class="info"><a href="/@phptest" class="name">静夜思</a><span class="date">9月1日评论</span></p>-->
<#--                                    <div class="content">一直不明白，控制器分层和模型分层调用起来到底有什么区别</div>-->
<#--                                    <p class="util">-->
<#--                                        <span class="vote">-->
<#--                                            <a class="agree e-agree" href="javascript:;" data-id="5841" title="赞成">-->
<#--                                                <i class="fa fa-thumbs-o-up"></i></a><b class="count">4</b>-->
<#--                                            <a class="oppose e-oppose" href="javascript:;" data-id="5841" title="反对"><i class="fa fa-thumbs-o-down"></i></a>-->
<#--                                        </span>-->
<#--                                        <a class="reply e-reply" data-account="phptest">回复</a>-->
<#--                                        <span class="operate toggle">-->
<#--                                            <a class="delete e-delete" data-id="5841" data-href="/comment/delete"><i class="icon icon-cross"></i></a>-->
<#--                                            <span class="number">23#</span>-->
<#--                                        </span>-->
<#--                                    </p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->

                    <div class="jump-top">
                        <a href="javascript:;" class="view-backtop"><i class="fa fa-arrow-up"
                                                                       aria-hidden="true"></i></a>
                    </div>
                </div>

            </div>
        </div>
        <div class="manual-progress"><b class="progress-bar"></b></div>
    </article>
    <div class="manual-mask"></div>
</div>

<#--todo 分享和下载-->

<script src="${ctx}/static/jquery/1.12.4/jquery.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/js/jquery.form.js" type="text/javascript"></script>
<script src="${ctx}/static/layer/layer.js" type="text/javascript"></script>
<script src="${ctx}/static/jstree/3.3.4/jstree.min.js" type="text/javascript"></script>
<script src="${ctx}/static/nprogress/nprogress.js" type="text/javascript"></script>
<script src="${ctx}/static/editor.md/lib/highlight/highlight.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.highlight.js" type="text/javascript"></script>
<script src="${ctx}/static/js/kancloud.js" type="text/javascript"></script>
<script src="${ctx}/static/js/splitbar.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $("#searchList").on("click", "a", function () {
            var id = $(this).attr("data-id");
            var url = "${ctx}/docs/${book.identify!}" + id;
            $(this).parent("li").siblings().find("a").removeClass("active");
            $(this).addClass("active");
            loadDocument(url, id, function (body) {
                return $(body).highlight(window.keyword);
            });
        });
    });
</script>
{{.Scripts}}
</body>
</html>