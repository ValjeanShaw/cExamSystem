<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/5/16
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Internship Sign In & Sign Up Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- Custom Theme files -->
    <link rel="shortcut icon" href="../../../../res/image/font/logoicon.png" >
    <link href="<%=request.getContextPath()%>/res/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
    <link href="<%=request.getContextPath()%>/res/css/snow.css" rel="stylesheet" type="text/css" media="all" />
    <link href="<%=request.getContextPath()%>/res/css/fontlogin.css" rel="stylesheet" type="text/css" media="all" />
    <script src="<%=request.getContextPath()%>/res/js/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/res/js/font/fontRegister.js"></script>
    <script src="<%=request.getContextPath()%>/res/layer/layer.js"></script>

</head>
<body>
<div class="snow-container">
    <div class="snow foreground"></div>
    <div class="snow foreground layered"></div>
    <div class="snow middleground"></div>
    <div class="snow middleground layered"></div>
    <div class="snow background"></div>
    <div class="snow background layered"></div>
</div>
<div class="top-buttons-agileinfo">
</div>
<h1>C语言在线考试系统</h1>
<div class="main-agileits">
    <!--form-stars-here-->
    <div class="form-w3-agile">
        <h2 class="sub-agileits-w3layouts">学生注册</h2>
        <form action="#" method="post">
            <input type="text" id="username" placeholder="请输入学号" required="" />
            <input type="password" id="userpass" placeholder="请输入密码" required="" />
            <input type="password" id="reuserpass" placeholder="请重复密码" required="" />
            <input type="text" id="stuName" placeholder="请输入姓名" required="" />
            <input type="text" id="sex" value="男" hidden />
            <input type="text" id="college" placeholder="请输入学院" required="" />
            <input type="text" id="grade" placeholder="请输入年级" required="" />
            <input type="text" id="classNum" placeholder="请输入班级" required="" />
            <div class="top-buttons-agileinfo" style="color: #ffffff;float: left; height: 20px; margin-top: -30px; padding: 12px;margin: 5px 0;margin-bottom: 30px;cursor:pointer">
                请选择性别：<a class="active sexWrap">男</a><a class="sexWrap">女</a>
            </div>
            <div class="submit-w3l" id="btnSubmit">
                <input type="button" value="注册">
            </div>
            <p class="p-bottom-w3ls"><a href="/FontLogin/LoginPage.port" style="color:#019163">点击返回登录</a></p>
            <p class="p-bottom-w3ls">Shenyang Aerospace University</p>
        </form>
    </div>
</div>
<div class="copyright w3-agile">
    <p>© 2016-2017 xiaoran 版权所有</p>
</div>
</body>
</html>
