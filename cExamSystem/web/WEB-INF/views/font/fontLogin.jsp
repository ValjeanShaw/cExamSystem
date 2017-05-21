<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/5/13
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>C语言on-line</title>
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
    <script src="<%=request.getContextPath()%>/res/js/font/fonlogin.js"></script>
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
    <a href="#" class="active studentbutton">学生</a><a href="#" class="teacherbutton">教师</a>
</div>
<h1>C语言在线考试系统
</h1>
<div class="main-agileits">
    <!--form-stars-here-->
    <div class="form-w3-agile">
        <h2 class="sub-agileits-w3layouts usertype">学生登录</h2>
        <form action="#" method="post">
            <input id="username" type="text" name="username" placeholder="用户" required="" />
            <input id="userpass" type="password" name="Password" placeholder="密码" required="" />
            <div class="submit-w3l" id="loginIn">
                <input type="button" value="登录">
            </div>
            <p class="p-bottom-w3ls btnregist"><a href="/Register/fontRegisterPage.port" style="color:#019163">点击注册</a>如果你没有一个帐户</p>
            <p class="p-bottom-w3ls">Shenyang Aerospace University</p>
        </form>
    </div>
</div>
<div class="copyright w3-agile">
    <p>© 2016-2017 xiaoran 版权所有</p>
</div>

</body>
</html>
