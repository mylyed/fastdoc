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
        <meta name="author" content="MinDoc"/>
        <title>${title} - Powered by MinDoc</title>
        <meta name="keywords" content="MinDoc,文档在线管理系统,WIKI,wiki,wiki在线,文档在线管理,接口文档在线管理,接口文档管理">
        <meta name="description"
              content="MinDoc文档在线管理系统 MinDoc 是一款针对IT团队开发的简单好用的文档管理系统，可以用来储存日常接口文档，数据库字典，手册说明等文档。内置项目管理，用户管理，权限管理等功能，支持Markdown和富文本两种编辑器，能够满足大部分中小团队的文档管理需求。">

        <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="${ctx}/static/css/main.css?v=20190311163719" rel="stylesheet">
        <script src="${ctx}/static/jquery/1.12.4/jquery.min.js"></script>
        <#nested >
    </head>
</#macro>
<#macro body>
    <body class="manual-container">
    <#nested >
    </body>
</#macro>
<#macro footer>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/layer/layer.js" type="text/javascript"></script>
    <#nested >
    <html>
</#macro>