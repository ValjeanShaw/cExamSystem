<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/5/7
  Time: 22:35
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
    <link rel="stylesheet" href="../../../res/css/testPaperManage.css" />
    <script src="../../../res/js/jquery-1.11.1.min.js"></script>
    <script src="../../../res/js/font/home.js"></script>
    <script src="../../../res/js/font/testpaperSpace.js"></script>
    <script src="../../../res/layer/layer.js"></script>
</head>
<body>
<!--头部导航菜单-->
<div class="banner">
    <img class="logo" src="../../../res/image/font/logo.png" alt="" />
    <span class="type">Teacher</span>
    <ul class="barwrap">
        <li class="barli"><a href="/FontHome/TeacherHome.port">首页</a></li>
        <li class="barli"><a href="/Question/fontQuestionBase.port">组卷</a></li>
        <li class="barli on"><a href="/TestPaper/fontTestpaperSpace.port">试卷</a></li>
        <li class="barli"><a href="/Top/fontTopPage.port">排行榜</a></li>
    </ul>
    <div class="userwrap">
        <img class="userlogo" src="../../../res/image/font/userlogo.png" alt="" />
        <span class="user" id="user"><%=session.getAttribute("fontUserName")%></span>
        <img id="btnlogout" class="userlogo" src="../../../res/image/font/logout1.png" title="退出" />
    </div>
</div>
<div class="fen">试卷广场</div>
<!--主要内容-->
<div class="content">
    <div class="titlewrap">
        <%--<img hidden class="btnback" src="../../../../res/image/font/btnback.png" alt="" />--%>
        <%--<img class="btnreload" src="../../../../res/image/font/btnreload.png" alt="" />--%>
    </div>
    <div class="paperlist">
        <ul class="listul" id="listul">
            <%--<li class="listunit">--%>
                <%--<div class="clickwrap">--%>
                    <%--<img class="paperlogo" src="../../../../res/image/font/paperlogo.png" alt="" />--%>
                    <%--<div class="paperTitle">--%>
                        <%--2017年浙江省宁波市鄞州区中考数学模拟试卷（3月份）--%>
                    <%--</div>--%>
                    <%--<div class="createDate">创建日期：<span>2017-10-10</span></div>--%>
                    <%--<div class="createMan">出题人：<span>二狗子</span></div>--%>
                <%--</div>--%>
                <%--<img class="btnDel" src="../../../../res/image/font/btndel.png" alt="" title="删除"/>--%>
            <%--</li>--%>
        </ul>
        <div class="morewrap">
            <img src="../../../res/image/font/moreicon.png" alt="" />
            更多
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

