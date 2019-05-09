<#assign ctx=request.contextPath>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>${title!} - Powered by ${LOGO}</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
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
    <script>
        var LOGO = "${LOGO}";
        window.book = {"identify": "${book.identify}"};

    </script>
</head>
<body>
<div class="m-manual manual-mode-view manual-reader">
    <header class="navbar navbar-static-top manual-head" role="banner">
        <div class="container-fluid">
            <div class="navbar-header pull-left manual-title">
                <span class="slidebar" id="slidebar"><i class="fa fa-align-justify"></i></span>
                <a href="${ctx}/doc/read/${book.identify!}" title="${book.bookName!}"
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

                        </div>
                    </div>
                    <div class="tab-item manual-search">
                        <div class="search-container">
                            <div class="search-form">
                                <form id="searchForm" action="${ctx}/search/${book.identify}" method="post">
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
                    本文档使用 <a href="${HOME_PAGE}" target="_blank">${LOGO}</a> 发布
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

                    <div class="jump-top">
                        <a href="javascript:;" class="view-backtop">
                            <i class="fa fa-arrow-up" aria-hidden="true"></i>
                        </a>
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
        //获取目录节点
        $.ajax({
            url: "${ctx}/doc/docTree/${book.bookId}",
            type: "GET",
            beforeSend: function (xhr) {
                NProgress.start();
            },
            success: function (res) {
                if (res.errcode === 0) {
                    var nodes = [];
                    for (var i = 0, l = res.data.length; i < l; i++) {
                        //进行转换
                        var json = res.data[i];
                        var selected = ("${currentDocumentId}" == json.documentId);
                        var node = {
                            "id": json.documentId,
                            'parent': json.parentId === 0 ? '#' : json.parentId,
                            "text": json.documentName,
                            "identify": json.identify,
                            "version": json.version,
                            "state": {
                                "opened": json.isOpen,
                                "selected": selected
                            },
                            "a_attr": {"href": "${ctx}/doc/read/${book.identify}/" + json.identify}
                        };
                        nodes.push(node)
                    }
                    initTree(nodes)
                } else if (res.errcode === 6000) {
                    window.location.href = "/";
                } else {
                    layer.msg(res.message);
                }
            },
            complete: function () {
                NProgress.done();
            },
            error: function () {
                layer.msg("加载失败");
            }
        });


        $("#searchList").on("click", "a", function () {
            var identify = $(this).attr("data-id");
            var url = "${ctx}/doc/read/${book.identify}/" + identify;
            $(this).parent("li").siblings().find("a").removeClass("active");
            $(this).addClass("active");
            loadDocument(url, identify, function (body) {
                return $(body).highlight(window.keyword);
            });
        });

    });


</script>


</body>
</html>