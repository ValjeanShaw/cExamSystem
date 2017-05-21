<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/5/7
  Time: 22:34
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
    <link rel="stylesheet" href="../../../res/css/questionBase.css" />
    <script src="../../../res/js/jquery-1.11.1.min.js"></script>
    <script src="../../../res/layer/layer.js"></script>
    <script src="../../../res/js/font/home.js"></script>
    <script src="../../../res/js/font/questionBase.js"></script>
</head>
<body>
<!--头部导航菜单-->
<div class="banner">
    <img class="logo" src="../../../res/image/font/logo.png" alt="" />
    <span class="type">Teacher</span>
    <ul class="barwrap">
        <li class="barli"><a href="/FontHome/TeacherHome.port">首页</a></li>
        <li class="barli on"><a href="/Question/fontQuestionBase.port">组卷</a></li>
        <li class="barli"><a href="/TestPaper/fontTestpaperSpace.port">试卷</a></li>
        <li class="barli"><a href="/Top/fontTopPage.port">排行榜</a></li>
    </ul>
    <div class="userwrap">
        <img class="userlogo" src="../../../res/image/font/userlogo.png" alt="" />
        <span class="user"><%=session.getAttribute("fontUserName")%></span>
        <img id="btnlogout" class="userlogo" src="../../../res/image/font/logout1.png" title="退出" />
    </div>
</div>
<div class="fen">C语言在线组卷</div>
<!--主要内容-->
<div class="content">
    <div class="titlewrap">
        <div class="titlename1 typeon">智能组卷</div>
        <div class="titlename2">手动组卷</div>
    </div>
    <div class="optionSpace">
        <ul class="optionSpaceUl">
            <span class="spanulname">C语言知识点<span>（参照《C语言程序设计》谭浩强版）</span></span>
            <li>
                <span class="chapter">编程基础</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
            <li>
                <span class="chapter">逻辑编程</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
            <li>
                <span class="chapter">数组</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
            <li>
                <span class="chapter">函数</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
            <li>
                <span class="chapter">指针</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
            <li>
                <span class="chapter">结构体</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
            <li>
                <span class="chapter">文件</span>
                <div class="numwrap">
                    <img src="../../../res/image/font/btnminus.png" alt="" class="btnAM btnminus"/>
                    <span class="queNum">0</span>
                    <img src="../../../res/image/font/btnadd.png" alt="" class="btnAM btnadd"/>
                </div>
            </li>
        </ul>
        <div class="allNumWrap">
            <span class="allNumName">选中题目数量</span>
            <p class="allNum">0</p>
        </div>
        <div class="btnOK" onclick="submitNeed()">确认组卷</div>
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

