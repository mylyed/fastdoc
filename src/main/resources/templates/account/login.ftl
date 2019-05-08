<#include "../common/accountLayout.ftl">
<#assign title="会员登录">
<@head>

</@head>
<@body>
    <header class="navbar navbar-static-top smart-nav navbar-fixed-top manual-header" role="banner">
        <div class="container">
            <div class="navbar-header col-sm-12 col-md-6 col-lg-5">
                <a href="/${ctx}" class="navbar-brand">${SITE_NAME!}</a>
            </div>
        </div>
    </header>
    <div class="container manual-body">
        <div class="row login">
            <div class="login-body">
                <form role="form" method="post">
                    <h3 class="text-center">用户登录</h3>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </div>
                            <input type="text" class="form-control" placeholder="邮箱 / 用户名" name="account"
                                   id="account"
                                   autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-lock"></i>
                            </div>
                            <input type="password" class="form-control" placeholder="密码" name="password"
                                   id="password"
                                   autocomplete="off">
                        </div>
                    </div>

                    <#if ENABLED_CAPTCHA?? && !(ENABLED_CAPTCHA == "false")>
                    <#--不启用验证码-->
                        <div class="form-group">
                            <div class="input-group" style="float: left;width: 195px;">
                                <div class="input-group-addon">
                                    <i class="fa fa-check-square"></i>
                                </div>
                                <input type="text" name="code" id="code" class="form-control" style="width: 150px"
                                       maxlength="5"
                                       placeholder="验证码" autocomplete="off">&nbsp;
                            </div>
                            <img id="captcha-img" style="width: 140px;height: 40px;display: inline-block;float: right"
                                 src="${ctx}/captcha"
                                 onclick="this.src='${ctx}/captcha?time='+(new Date()).getTime();"
                                 title="点击换一张">
                            <div class="clearfix"></div>
                        </div>
                    </#if>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="is_remember" value="yes"> 保持登录
                        </label>
                        <a href="${ctx}/find_password"
                           style="display: inline-block;float: right">忘记密码？</a>
                    </div>
                    <div class="form-group">
                        <button type="button" id="btn-login" class="btn btn-success" style="width: 100%"
                                data-loading-text="正在登录..." autocomplete="off">立即登录
                        </button>
                    </div>


                </form>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <#include "../common/footer.ftl">

</@body>
<@footer>
    <script type="text/javascript">
        $(function () {
            $("#account,#password,#code").on('focus', function () {
                $(this).tooltip('destroy').parents('.form-group').removeClass('has-error');
            });

            $(document).keydown(function (e) {
                var event = document.all ? window.event : e;
                if (event.keyCode === 13) {
                    $("#btn-login").click();
                }
            });

            $("#btn-login").on('click', function () {
                $(this).tooltip('destroy').parents('.form-group').removeClass('has-error');
                var $btn = $(this).button('loading');

                var account = $.trim($("#account").val());
                var password = $.trim($("#password").val());
                var code = $("#code").val();

                if (account === "") {
                    $("#account").tooltip({placement: "auto", title: "账号不能为空", trigger: 'manual'})
                        .tooltip('show')
                        .parents('.form-group').addClass('has-error');
                    $btn.button('reset');
                    return false;
                } else if (password === "") {
                    $("#password").tooltip({title: '密码不能为空', trigger: 'manual'})
                        .tooltip('show')
                        .parents('.form-group').addClass('has-error');
                    $btn.button('reset');
                    return false;
                } else if (code !== undefined && code === "") {
                    $("#code").tooltip({title: '验证码不能为空', trigger: 'manual'})
                        .tooltip('show')
                        .parents('.form-group').addClass('has-error');
                    $btn.button('reset');
                    return false;
                } else {
                    //登录
                    $.ajax({
                        url: "${ctx}/login?url=${url!}",
                        data: $("form").serializeArray(),
                        dataType: "json",
                        type: "POST",
                        success: function (res) {
                            if (res.errcode !== 0) {
                                $("#captcha-img").click();
                                $("#code").val('');
                                layer.msg(res.message);
                                $btn.button('reset');
                            } else {
                                turl = res.data;
                                if (turl === "") {
                                    turl = "/";
                                }
                                window.location = turl;
                            }
                        },
                        error: function () {
                            $("#captcha-img").click();
                            $("#code").val('');
                            layer.msg('系统错误');
                            $btn.button('reset');
                        }
                    });
                }

                return false;
            });
        });
    </script>
</@footer>