<#assign ctx=request.contextPath>
<#assign title="标题">
<#macro head>
    <!DOCTYPE html>
    <html lang="zh-cn">
    <html>
    <head>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="renderer" content="webkit"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>${title} - Powered by ${LOGO}</title>
        <meta name="description" content="${site_description!}">
        <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="${ctx}/static/css/main.css?v=20190311163719" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="${ctx}/static/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="${ctx}/static/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <#nested >
    </head>
</#macro>
<#macro body>
    <body>
    <div class="manual-reader manual-container manual-search-reader">
        <#include "../common/header.ftl">
        <#nested >
        <#include "../common/footer.ftl">
    </div>
    </body>
</#macro>
<#macro footer>
    <script src="${ctx}/static/jquery/1.12.4/jquery.min.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <#nested >
    <html>
</#macro>