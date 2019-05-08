<#include "../common/layout.ftl">
<#include "../common/pagination.ftl">
<#assign title="用户中心">
<@head>
   <link href="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/css/fileinput.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/themes/explorer-fa/theme.css" rel="stylesheet" type="text/css">
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
                    <li <#if ControllerName="BookController">class="active"</#if>><a href="${ctx}/book"  class="item"><i class="fa fa-sitemap" aria-hidden="true"></i> 我的项目</a> </li>
                    <li <#if ControllerName="BlogController">class="active"</#if>><a href="${ctx}/manage/blogs" class="item"><i class="fa fa-file" aria-hidden="true"></i> 我的文章</a> </li>
                </ul>
            </div>
            <div class="page-right">
                <div class="m-box">
                    <div class="box-head">
                        <strong class="box-title">项目列表</strong>
                        &nbsp;
                        <button type="button" data-toggle="modal" data-target="#addBookDialogModal" class="btn btn-success btn-sm pull-right">添加项目</button>
                        <button type="button" data-toggle="modal" data-target="#importBookDialogModal" class="btn btn-primary btn-sm pull-right" style="margin-right: 5px;">导入项目</button>
                    </div>
                </div>
                <div class="box-body" id="bookList">
                    <div class="book-list">
                        <template v-if="lists.length <= 0">
                        <div class="text-center">暂无数据</div>
                        </template>
                        <template v-else>

                        <div class="list-item" v-for="item in lists">
                            <div class="book-title">
                                <div class="pull-left">
                                    <a :href="'{{.BaseUrl}}/book/' + item.identify + '/dashboard'" title="项目概要" data-toggle="tooltip">
                                       <template v-if="item.privately_owned == 0">
                                           <i class="fa fa-unlock" aria-hidden="true"></i>
                                       </template>
                                       <template v-else-if="item.privately_owned == 1">
                                           <i class="fa fa-lock" aria-hidden="true"></i>
                                       </template>
                                        ${item.book_name}
                                    </a>
                                </div>
                                <div class="pull-right">
                                    <div class="btn-group">
                                        <a  :href="'{{.BaseUrl}}/book/' + item.identify + '/dashboard'" class="btn btn-default">设置</a>

                                        <a href="javascript:;" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a :href="'{{urlfor "DocumentController.Index" ":key" ""}}' + item.identify" target="_blank">阅读</a></li>
                                            <template v-if="item.role_id != 3">
                                            <li><a :href="'{{.BaseUrl}}/api/' + item.identify + '/edit'" target="_blank">编辑</a></li>
                                            </template>
                                            <template v-if="item.role_id == 0">
                                            <li><a :href="'javascript:deleteBook(\''+item.identify+'\');'">删除</a></li>
                                            <li><a :href="'javascript:copyBook(\''+item.identify+'\');'">复制</a></li>
                                            </template>
                                        </ul>

                                    </div>

                                    {{/*<a :href="'{{urlfor "DocumentController.Index" ":key" ""}}' + item.identify" title="查看文档" data-toggle="tooltip" target="_blank"><i class="fa fa-eye"></i> 查看文档</a>*/}}
                                    {{/*<template v-if="item.role_id != 3">*/}}
                                        {{/*<a :href="'/api/' + item.identify + '/edit'" title="编辑文档" data-toggle="tooltip" target="_blank"><i class="fa fa-edit" aria-hidden="true"></i> 编辑文档</a>*/}}
                                    {{/*</template>*/}}
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="desc-text">
                                    <template v-if="item.description === ''">
                                        &nbsp;
                                    </template>
                                    <template v-else="">
                                        <a :href="'{{.BaseUrl}}/book/' + item.identify + '/dashboard'" title="项目概要" style="font-size: 12px;">
                                        ${item.description}
                                        </a>
                                    </template>
                            </div>
                            <div class="info">
                                <span title="创建时间" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-clock-o"></i>
                                    ${(new Date(item.create_time)).format("yyyy-MM-dd hh:mm:ss")}

                                </span>
                                <span title="创建者" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-user"></i> ${item.create_name}</span>
                                <span title="文档数量" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-pie-chart"></i> ${item.doc_count}</span>
                                <span title="项目角色" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-user-secret"></i> ${item.role_name}</span>
                                <template v-if="item.last_modify_text !== ''">
                                    <span title="最后编辑" data-toggle="tooltip" data-placement="bottom"><i class="fa fa-pencil"></i> 最后编辑: ${item.last_modify_text}</span>
                                </template>

                            </div>
                        </div>
                        </template>
                    </div>
                    <template v-if="lists.length >= 0">
                        <nav class="pagination-container">
                            {{.PageHtml}}
                        </nav>
                    </template>
                </div>
            </div>
        </div>
    </div>



    <!-- Modal -->
    <div class="modal fade" id="addBookDialogModal" tabindex="-1" role="dialog" aria-labelledby="addBookDialogModalLabel">
        <div class="modal-dialog modal-lg" role="document" style="min-width: 900px;">
            <form method="post" autocomplete="off" action="{{urlfor "BookController.Create" id="addBookDialogForm" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加项目</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <div class="pull-left" style="width: 620px">
                            <div class="form-group required">
                                <label class="text-label col-sm-2">项目空间</label>
                                <div class="col-sm-10">
                                    <select class="js-data-example-ajax-add form-control" multiple="multiple" name="itemId" id="itemId">
                                        {{if .Item}}<option value="{{.Item.ItemId selected>{{.Item.ItemName}}</option> {{end}}
                                    </select>
                                    <p class="text">每个项目必须归属一个项目空间，超级管理员可在后台管理和维护</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="form-group required">
                                <label class="text-label col-sm-2">项目标题</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" placeholder="标题(不超过100字)" name="book_name" id="bookName">
                                    <p class="text">项目名称不能超过100字符</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="form-group required">
                                <label class="text-label col-sm-2">项目标识</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" placeholder="项目唯一标识(不超过50字)" name="identify" id="identify">
                                    <p class="text">文档标识只能包含小写字母、数字，以及“-”、“.”和“_”符号.</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="form-group">
                                <textarea name="description" id="description" class="form-control" placeholder="描述信息不超过500个字符" style="height: 90px;"></textarea>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-6">
                                    <label>
                                        <input type="radio" name="privately_owned" value="0" checked> 公开<span class="text">(任何人都可以访问)</span>
                                    </label>
                                </div>
                                <div class="col-lg-6">
                                    <label>
                                        <input type="radio" name="privately_owned" value="1"> 私有<span class="text">(只要参与者或使用令牌才能访问)</span>
                                    </label>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="pull-right text-center" style="width: 235px;">
                            <canvas id="bookCover" height="230px" width="170px"><img src="{{cdnimg "/static/images/book.jpg"> </canvas>
                            <p class="text">项目图片可在项目设置中修改</p>
                        </div>
                    </div>


                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <span id="form-error-message"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" id="btnSaveDocument" data-loading-text="保存中...">保存</button>
                </div>
            </div>
            </form>
        </div>
    </div>
    <!--END Modal-->
    <!-- importBookDialogModal -->
    <div class="modal fade" id="importBookDialogModal" tabindex="-1" role="dialog" aria-labelledby="importBookDialogModalLabel">
        <div class="modal-dialog" role="document" style="min-width: 900px;">
            <form method="post" autocomplete="off" action="{{urlfor "BookController.Import" id="importBookDialogForm" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">导入项目</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <div class="form-group required">
                            <label class="text-label">项目空间</label>
                            <select class="js-data-example-ajax-import form-control" multiple="multiple" name="itemId">
                                {{if .Item}}<option value="{{.Item.ItemId selected>{{.Item.ItemName}}</option> {{end}}
                            </select>
                            <p class="text">每个项目必须归属一个项目空间，超级管理员可在后台管理和维护</p>
                        </div>
                        <div class="form-group required">
                            <label class="text-label">项目标题</label>
                            <input type="text" class="form-control" placeholder="项目标题(不超过100字)" name="book_name" maxlength="100" value="">
                            <p class="text">项目名称不能超过100字符</p>
                        </div>
                        <div class="form-group required">
                            <label class="text-label">项目标识</label>
                            <input type="text" class="form-control"  placeholder="项目唯一标识(不超过50字)" name="identify" value="">
                            <div class="clearfix"></div>
                            <p class="text">文档标识只能包含小写字母、数字，以及“-”、“.”和“_”符号.</p>
                        </div>
                        <div class="form-group">
                            <label class="text-label">项目描述</label>
                            <textarea name="description" id="description" class="form-control" placeholder="描述信息不超过500个字符" style="height: 90px;"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-6">
                                <label>
                                    <input type="radio" name="privately_owned" value="0" checked> 公开<span class="text">(任何人都可以访问)</span>
                                </label>
                            </div>
                            <div class="col-lg-6">
                                <label>
                                    <input type="radio" name="privately_owned" value="1"> 私有<span class="text">(只要参与者或使用令牌才能访问)</span>
                                </label>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="form-group">
                            <div class="file-loading">
                                <input id="import-book-upload" name="import-file" type="file" accept=".zip">
                            </div>
                            <div id="kartik-file-errors"></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="modal-footer">
                    <span id="import-book-form-error-message" style="background-color: #ffffff;border: none;margin: 0;padding: 0;"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" id="btnImportBook" data-loading-text="创建中...">创建</button>
                </div>
            </div>
            </form>
        </div>
    </div>
    <!--END importBookDialogModal-->
    <!-- Delete Book Modal -->
    <div class="modal fade" id="deleteBookModal" tabindex="-1" role="dialog" aria-labelledby="deleteBookModalLabel">
        <div class="modal-dialog" role="document">
            <form method="post" id="deleteBookForm" action="{{urlfor "BookController.Delete">
            <input type="hidden" name="identify" value="">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">删除项目</h4>
                </div>
                <div class="modal-body">
                    <span style="font-size: 14px;font-weight: 400;">确定删除项目吗？</span>
                    <p></p>
                    <p class="text error-message">删除项目后将无法找回。</p>
                </div>
                <div class="modal-footer">
                    <span id="form-error-message2" class="error-message"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" id="btnDeleteBook" class="btn btn-primary" data-loading-text="删除中...">确定删除</button>
                </div>
            </div>
            </form>
        </div>
    </div>

</@body>
<@footer>
    <script src="${ctx}/static/vuejs/vue.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jquery.form.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/js/fileinput.min.js"></script>
    <script src="${ctx}/static/bootstrap/plugins/bootstrap-fileinput/4.4.7/js/locales/zh.js"></script>
    <script src="${ctx}/static/layer/layer.js" type="text/javascript" ></script>
    <script src="${ctx}/static/select2/4.0.5/js/select2.full.min.js"></script>
    <script src="${ctx}/static/select2/4.0.5/js/i18n/zh-CN.js"></script>
    <script src="${ctx}/static/js/main.js" type="text/javascript"></script>

</@footer>