<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/5/19
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="shortcut icon" href="../../../res/image/font/logoicon.png" >
    <link rel="stylesheet" href="../../../res/css/home.css" />
    <link rel="stylesheet" href="../../../res/css/personalData.css" />
    <script src="../../../res/js/jquery-1.11.1.min.js"></script>
    <script src="../../../res/js/echarts.min.js"></script>
    <script src="../../../res/js/font/home.js"></script>
    <script src="../../../res/js/font/personalData.js"></script>
    <script src="../../../res/layer/layer.js"></script>

</head>
<body>
<!--头部导航菜单-->
<div class="banner">
    <img class="logo" src="../../../res/image/font/logo.png" alt="" />
    <span class="type">Teacher</span>
    <ul class="barwrap">
        <li class="barli "><a href="/FontHome/StudentHome.port">首页</a></li>
        <li class="barli"><a href="/StuExam/fontStuPaperPage.port">考试</a></li>
        <li class="barli"><a href="/Top/fontStuTopPage.port">排行榜</a></li>
        <li class="barli on"><a href="/Student/fontPersonalData.port">个人中心</a></li>
    </ul>
    <div class="userwrap">
        <img class="userlogo" src="../../../res/image/font/userlogo.png" alt="" />
        <span class="user"><%=session.getAttribute("fontUserName")%></span>
        <img id="btnlogout" class="userlogo" src="../../../res/image/font/logout1.png" title="退出" />
    </div>
</div>


<div class="fen">我的数据</div>
<div class="content">
    <div class="firstMess">
        <div class="type_one borderLeft">学号：<span>2013040101229</span></div>
        <div class="type_one">姓名：<span>肖冉</span></div>
        <div class="type_one sizetype">性别：<span>男</span></div>
        <div class="type_one borderLeft">学院：<span>计算机学院</span></div>
        <div class="type_one">年级：<span>2013</span></div>
        <div class="type_one sizetype">班级：<span>34010105</span></div>
    </div>
    <div class="secondMess">
        <span class="Formtitle">数据分析图表：</span>
        <div class="pieForm">
            <div class="pieData" id="pieData"></div>
        </div>
        <div class="numForm">
            <div class="numData" id="numData"></div>
        </div>

    </div>
</div>

<!--底部菜单-->
<div class="foot">
    <div class="footone">
        <ul class="saulinkul">
            <li>
                <img src="../../../res/image/font/email.png" alt="" />
                <a href="" class="saulink">校长信箱</a>
            </li>
            <li>
                <img src="../../../res/image/font/school.png" alt="" />
                <a href="" class="saulink">数字沈航</a>
            </li>
            <li>
                <img src="../../../res/image/font/visitor.png" alt="" />
                <a href="" class="saulink">访客留言</a>
            </li>
            <li>
                <img src="../../../res/image/font/office.png" alt="" />
                <a href="" class="saulink">协同办公</a>
            </li>
        </ul>
    </div>
    <div class="foottwo">
        <span>沈阳航空航天大学</span>
        <span>沈阳市沈北新区道义南大街37号</span>
    </div>

</div>
</body>
</html>

