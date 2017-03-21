<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/24
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>注册界面</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/login_register.css" />
    <link rel="Shortcut Icon" href="<%=request.getContextPath()%>/res/image/manage/logo.png" type="image/x-icon">

    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/respond.js"></script>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/jquery-1.11.1.min.js"></script>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/admin/login_register.js"></script>

</head>
<body>
<div class="sider"></div>
<div class="wrap_register">
    <div class="shade">
        <span>注册-Register</span>
    </div>
    <div class="mainPanel_register">
        <form method="POST" name="register" action="registerServlet" autocomplete="off">
            <p class="inputTitle">用户名
            </p>
            <div class="input">
                <input type="text" name="username" class="userInput" id="username"/>
                <img src="<%=request.getContextPath()%>/res/image/common/userlogo.png" alt="" class="inputLogo"/>
            </div>
            <p class="inputTitle">密码</p>
            <div class="input">
                <input type="password" name="userpass" class="passInput" id="userpass"/>
                <img src="<%=request.getContextPath()%>/res/image/common/passlock.png" alt="" class="inputLogo"/>
            </div>
            <p class="inputTitle">确认密码</p>
            <div class="input">
                <input type="password" name="reuserpass" class="repassInput"/>
                <img src="<%=request.getContextPath()%>/res/image/common/passlock.png" alt="" class="inputLogo"/>
            </div>
            <div class="btnSubmit" onclick="registsub()">
                <img src="<%=request.getContextPath()%>/res/image/common/checklogo.png" alt="" />
            </div>
        </form>
        <div class="linkText">

            <a href="<%=request.getContextPath()%>/AdminLogin/Login.port" class="toRegister"><span >登录</span></a>
        </div>
    </div>
</div>
</body>
</html>
