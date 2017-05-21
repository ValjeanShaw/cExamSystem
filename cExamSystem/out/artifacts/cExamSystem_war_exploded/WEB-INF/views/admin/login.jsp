<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/22
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台管理</title>
    <meta name="author" content="DeathGhost" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/css/style.css" tppabs="css/style.css" />
    <style>
        body{height:100%;background:#545656;overflow:hidden;}
        canvas{z-index:-1;position:absolute;}
    </style>
    <script src="<%=request.getContextPath()%>/res/js/admin/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/res/js/admin/verificationNumbers.js" tppabs="js/verificationNumbers.js"></script>
    <script src="<%=request.getContextPath()%>/res/js/admin/Particleground.js" tppabs="js/Particleground.js"></script>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script>
        $(document).ready(function() {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#5cbdaa',
                lineColor: '#5cbdaa'
            });
        });
        //提交
        function sub() {
            var username = $("#username").val();
            var userpass = $("#userpass").val();
            var regex = /\s+/;
            if (username == "" || regex.test(username)) {
                document.login.username.focus();
            }
            else if (userpass == "" || regex.test(userpass)) {
                document.login.userpass.focus();
            }
            else {
                layer.load(2);
                //ajax异步提交
                var ajax = $.ajax({
                    url: '/AdminManage/LoginIn.port',
                    type: 'post',
                    data: {
                        username: username,
                        password: userpass
                    },
                    timeout: 3000,
                    success: function (data) {
                        if (data == "ok") {
                            setInterval(function () {
                                layer.closeAll('loading');
                                window.location.href = "/AdminMenu/index.port";
                            }, 500);
                        } else {
                            layer.msg(data);
                        }
                    }, error: function () {

                    }, complete: function (XMLHttpRequest, status) {
                        if (status == 'timeout') {
                            ajax.abort();
                            layer.msg("请求超时，请重试！");
                        }
                        layer.closeAll('loading');
                    }
                })
            }
        }
    </script>
    <style type="text/css">
        .tb960x90 {display:none!important;display:none}
    </style>
</head>
<body>
<dl class="admin_login">
    <dt>
        <strong>C语言考试系统后台管理</strong>
        <em>C Examination System Online</em>
    </dt>
    <dd class="user_icon">
        <input type="text" placeholder="账号" class="login_txtbx" id="username"/>
    </dd>
    <dd class="pwd_icon">
        <input type="password" placeholder="密码" class="login_txtbx" id="userpass"/>
    </dd>

    <dd>
        <input type="button" value="登录" class="submit_btn" onclick="sub()"/>
    </dd>
    <dd>
        <p>© 2016-2017 xiaoran 版权所有</p>
        <p>Shenyang Aerospace University</p>
    </dd>
</dl>
</body>
</html>

<%--以下是旧的页面--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"--%>
<%--"http://www.w3.org/TR/html4/strict.dtd">--%>

<%--<html xmlns="http://www.w3.org/1999/xhtml" lang="en">--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
    <%--<title>登录界面</title>--%>

    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/login_register.css" />--%>
    <%--<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/res/image/manage/logo.png" type="image/x-icon">--%>

    <%--<script charset="utf-8" src="<%=request.getContextPath()%>/res/js/respond.js"></script>--%>
    <%--<script charset="utf-8" src="<%=request.getContextPath()%>/res/js/jquery-1.11.1.min.js"></script>--%>
    <%--<script charset="utf-8" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>--%>
    <%--<script charset="utf-8" src="<%=request.getContextPath()%>/res/js/admin/login_register.js"></script>--%>
    <%--<script>--%>
        <%--//变换背景--%>
        <%--$(document).ready(function(){--%>
            <%--setInterval("changeBack()",5000);--%>
        <%--})--%>
        <%--function changeBack(){--%>
            <%--if($(".sider img").attr('src')=='../../res/image/common/sau.jpg'){--%>
                <%--$(".sider img").attr('src','../../res/image/common/sau2.jpg');--%>
            <%--}else{--%>
                <%--$(".sider img").attr('src','../../res/image/common/sau.jpg');--%>
            <%--}--%>

        <%--}--%>
        <%--function sub(){--%>
            <%--var username = document.login.username.value;--%>
            <%--var userpass = document.login.userpass.value;--%>
            <%--var regex = /\s+/;--%>
            <%--if(username == ""|| regex.test(username)){--%>
                <%--document.login.username.focus();--%>
            <%--}--%>
            <%--else if(userpass == "" || regex.test(userpass)){--%>
                <%--document.login.userpass.focus();--%>
            <%--}--%>
            <%--else{--%>
                <%--layer.load();--%>
                <%--//ajax异步提交--%>
                <%--var ajax= $.ajax({--%>
                    <%--url:'/AdminManage/LoginIn.port',--%>
                    <%--type:'post',--%>
                    <%--data:{--%>
                        <%--username:username,--%>
                        <%--password:userpass--%>
                    <%--},--%>
                    <%--timeout:3000,--%>
                    <%--success:function(data){--%>
                        <%--if(data == "ok"){--%>
                            <%--setInterval(function() {--%>
                                <%--layer.closeAll('loading');--%>
                                <%--window.location.href = "/AdminMenu/index.port";--%>
                            <%--},500);--%>
                        <%--}else{--%>
                            <%--layer.msg(data);--%>
                        <%--}--%>
                    <%--},error:function(){--%>

                    <%--},complete:function(XMLHttpRequest,status){--%>
                        <%--if(status=='timeout'){--%>
                            <%--ajax.abort();--%>
                            <%--layer.msg("请求超时，请重试！");--%>
                        <%--}--%>
                        <%--layer.closeAll('loading');--%>
                    <%--}--%>
                <%--})--%>
            <%--}--%>

        <%--}--%>
    <%--</script>--%>

<%--</head>--%>
<%--<body>--%>
    <%--<div class="sider">--%>
        <%--<img src="<%=request.getContextPath()%>/res/image/common/sau.jpg" alt=""/>--%>
        <%--<div class="borad"></div>--%>
    <%--</div>--%>
    <%--<div class="wrap">--%>
        <%--<div class="shade">--%>
            <%--<span>登录-Login</span>--%>
        <%--</div>--%>
        <%--<div class="mainPanel">--%>
            <%--<form method="POST" name="login" action="loginServlet" autocomplete="off">--%>
                <%--<p class="inputTitle">用户名</p>--%>
                <%--<div class="input">--%>
                    <%--<input type="text" class="userInput" name="username"/>--%>
                    <%--<img src="<%=request.getContextPath()%>/res/image/common/userlogo.png" alt="" class="inputLogo"/>--%>
                <%--</div>--%>
                <%--<p class="inputTitle">密码</p>--%>
                <%--<div class="input">--%>
                    <%--<input type="password" class="passInput" name="userpass"/>--%>
                    <%--<img src="<%=request.getContextPath()%>/res/image/common/passlock.png" alt="" class="inputLogo"/>--%>
                <%--</div>--%>
                <%--<div class="btnSubmit" onclick="sub()">--%>
                    <%--<img src="<%=request.getContextPath()%>/res/image/common/checklogo.png" alt="" />--%>
                <%--</div>--%>
                <%--<!--后台用户不允许注册，使用系统管理员添加-->--%>
                <%--&lt;%&ndash;<div class="linkText">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<a href="<%=request.getContextPath()%>/Register/register.port" class="toRegister"><span >注册</span></a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</body>--%>
<%--</html>--%>
