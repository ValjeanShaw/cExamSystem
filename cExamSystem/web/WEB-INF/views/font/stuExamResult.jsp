<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/5/16
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <link rel="shortcut icon" href="../../../res/image/font/logoicon.png" >
    <link rel="stylesheet" href="../../../res/css/home.css" />
    <link rel="stylesheet" href="../../../res/css/examine.css" />
    <script src="../../../res/js/jquery-1.11.1.min.js"></script>
    <script src="../../../res/js/font/home.js"></script>
    <script src="../../../res/js/font/examineResult.js"></script>
    <script src="../../../res/layer/layer.js"></script>
    <script></script>
</head>
<body>
<!--头部导航菜单-->
<div class="banner">
    <img class="logo" src="../../../res/image/font/logo.png" alt="" />
    <span class="type">Student</span>
    <ul class="barwrap">
        <li class="barli "><a href="/FontHome/StudentHome.port">首页</a></li>
        <li class="barli  on"><a href="/StuExam/fontStuPaperPage.port">考试</a></li>
        <li class="barli"><a href="/Top/fontStuTopPage.port">排行榜</a></li>
        <li class="barli"><a href="/Student/fontPersonalData.port">个人中心</a></li>
    </ul>
    <div class="userwrap">
        <img  class="userlogo" src="../../../res/image/font/userlogo.png" alt="" />
        <span class="user"><%=session.getAttribute("fontUserName")%></span>
        <img  id="btnlogout" class="userlogo" src="../../../res/image/font/logout1.png" title="退出" />
    </div>
</div>
<div class="fen">在线考试</div>
<div class="content">
    <div class="titlewrap">
        <span class="paperTitle"></span>
        <span class="totalRight">共做对<span>0</span>道题</span>
        <div class="page">
            第<span class="nowpage">0</span>题/
            共<span class="allpage">0</span>题
        </div>
    </div>
    <div class="flodwrap1"> </div>
    <div class="flodwrap2"> </div>
    <div class="flodwrap3"> </div>
    <ul class="quesUl" id="quesUl">

        <div class="btnwrap">
            <div class="last">
                <img src="../../../res/image/font/last.png"/>
            </div>
            <div class="next">
                <img src="../../../res/image/font/next.png" /></div>
            <div class="submit">
                <img src="../../../res/image/font/btnback2.png"/></div>
        </div>
        <div class="btnwrap2">
            <div class="last">
                <div>上一题</div>
            </div>
            <div class="next">
                <div>下一题</div>
            </div>
            <div class="submit">
                <div>返回</div>
            </div>
        </div>
    </ul>
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



