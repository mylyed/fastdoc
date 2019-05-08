<#include "../common/layout.ftl">
<#include "../common/pagination.ftl">
<#assign title="用户中心">
<@head>
    <link href="${ctx}/static/webuploader/webuploader.css"" rel="stylesheet">
    <link href="${ctx}/static/cropper/2.3.4/cropper.min.css"" rel="stylesheet">
</@head>
<@body>
    <div class="container manual-body">
        <div class="row">
            <div class="page-left">
                <ul class="menu">
                    <li><a href="${ctx}/setting" class="item"><i class="fa fa-sitemap" aria-hidden="true"></i> 基本信息</a> </li>
                    <li class="active"><a href="${ctx}/setting/password" class="item"><i class="fa fa-user" aria-hidden="true"></i> 修改密码</a> </li>
                </ul>
            </div>
            <div class="page-right">
                <div class="m-box">
                    <div class="box-head">
                        <strong class="box-title">修改密码</strong>
                    </div>
                </div>
                <div class="box-body" style="width: 300px;">
                    <form role="form" method="post" id="securityForm">
                        <div class="form-group">
                            <label for="password1">原始密码</label>
                            <input type="password" name="password1" id="password1" class="form-control disabled" placeholder="原始密码">
                        </div>
                        <div class="form-group">
                            <label for="password2">新密码</label>
                            <input type="password" class="form-control" name="password2" id="password2" max="50" placeholder="新密码">
                        </div>
                        <div class="form-group">
                            <label for="password3">确认密码</label>
                            <input type="password" class="form-control" id="password3" name="password3" placeholder="确认密码">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success" data-loading-text="保存中...">保存修改</button>
                            <span id="form-error-message" class="error-message"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@body>
<@footer>
    <script src="${ctx}/static/js/jquery.form.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/main.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {

            $("#securityForm").ajaxForm({
                beforeSubmit : function () {
                    var oldPasswd = $("#password1").val();
                    var newPasswd = $("#password2").val();
                    var confirmPassword = $("#password3").val();
                    if(!oldPasswd ){
                        showError("原始密码不能为空");
                        return false;
                    }
                    if(!newPasswd){
                        showError("新密码不能为空");
                        return false;
                    }
                    if(!confirmPassword){
                        showError("确认密码不能为空");
                        return false;
                    }
                    if(confirmPassword !== newPasswd){
                        showError("确认密码不正确");
                        return false;
                    }
                },
                success : function (res) {
                    if(res.errcode === 0){
                        showSuccess('保存成功');
                        $("#password1").val('');
                        $("#password2").val('');
                        $("#password3").val('');
                    }else{
                        showError(res.message);
                    }
                }
            }) ;
        });
    </script>
</@footer>