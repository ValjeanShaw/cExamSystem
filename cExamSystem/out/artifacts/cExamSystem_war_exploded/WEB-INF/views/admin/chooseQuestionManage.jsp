<%--
  Created by IntelliJ IDEA.
  User: 风萧萧兮
  Date: 2017/4/29
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%--引入EasyUi--%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/manage/massage-helper.js"></script>
    <%--引入kindereditor--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/kindeditor-4.1.10/themes/default/default.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/res/kindeditor-4.1.10/plugins/code/prettify.css" />
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/kindeditor-4.1.10/kindeditor.js"></script>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/kindeditor-4.1.10/lang/zh_CN.js"></script>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/kindeditor-4.1.10/plugins/code/prettify.js"></script>
    <%--引入layer--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <%--引入公共JS--%>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/manage/admincommon.js"></script>
    <%--引入本页面专属js             需要添加所属的js      --%>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/admin/chooseQuestion.js"></script>
    <%--引入图片上传--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ajaxfileupload.js"></script>
    <script>
        var flag = 'add';     //标记是修改还是新建
        <!--第一处修改-->
        var deleteId="id";        //删除的根据--id
        var deleteUrl = "/Question/AdminDeleteChoose.port";      //删除所用url

        $(function(){
            $("#Board").fadeOut();
        });
    </script>
</head>
<body>
<div id="Board" style="width: calc(100% - 10px);height: 100%;position: absolute;top: 0px;z-index: 100;background: #fff;"></div>
<%--数据表格--%>
<table id="dg" class="easyui-datagrid" style="calc(100% - 5px);min-height:500px;max-height: 100%;"
       data-options="pagination:true,
                   rownumbers:true,
                   singleSelect:true,
                   method:'post',
                   autoRowHeight:false,
                   toolbar:'#tb',
                   fitColumns:true,
                   pageList:[20,30,50],
                   <%--2、第二处修改，修改此处的Json服务，用于加载数据--%>
                   url:'<%=request.getContextPath()%>/Question/AdminAllChooseList.port'">
    <thead>
    <tr>
        <%-- 3、第三处修改，修改此处的列名称，与数据库一致，显示有必要的（跨行的字符串不要显示）--%>
        <th field="id" width="100px" hidden>ID</th>
        <th field="questionText" width="100px">题目</th>
        <th field="chapter" width="100px">知识点</th>
        <th field="finishNum" width="100px">总做题数</th>
        <th field="rightNum" width="100px">答对次数</th>
        <th field="zanNum" width="100px" hidden>点赞次数</th>
    </tr>
    </thead>
</table>
<%--表格头部按钮--%>
<div id="tb" style="height:auto">
    <%--4、第四处修改，修改菜单按钮，选择哪些按钮需要--%>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="Add()">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="Edit()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="Delete()">删除</a>
</div>
<%--对话框--%>
<div id="dlg" class="easyui-dialog hide" title=""
     style="width:600px;height:500px;padding:10px;"
     data-options="iconCls: 'icon-save',buttons: '#dlg-buttons',modal:true" closed="true" >
    <%--5、第五处修改，修改表单信息，与上面的列名称一致--%>
    <form id="Form">
        <div style="margin-bottom:10px;margin-top: 28px;display: none;">
            <div class="word">ID:</div>
            <input class="easyui-textbox" name="id" id="id">
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 14px;">知识</span>点:</span>
            <select class="easyui-combobox" data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,25]'"
                    name="chapter" id="chapter" style="width:400px;height:32px;">
                <option value="编程基础">编程基础</option>
                <option value="逻辑编程">逻辑编程</option>
                <option value="数组">数组</option>
                <option value="函数">函数</option>
                <option value="指针">指针</option>
                <option value="结构体">结构体</option>
                <option value="文件">文件</option>
            </select>
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 4px;">题目内</span>容:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,2000]',multiline:true"
                   name="questionText" id="questionText" style="width:400px;height:130px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 16px;">选项</span>A:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,100]'"
                   name="chooseA" id="chooseA" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 16px;">选项</span>B:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,100]'"
                   name="chooseB" id="chooseB" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 16px;">选项</span>C:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,100]'"
                   name="chooseC" id="chooseC" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 16px;">选项</span>D:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,100]'"
                   name="chooseD" id="chooseD" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 42px;">答</span>案:</span>
            <select class="easyui-combobox" data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,25]'"
                    name="answer" id="answer" style="width:400px;height:32px;">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 42px;">解</span>释:</span>
            <input class="easyui-textbox" data-options="validType:'length[1,2000]',multiline:true"
                   name="qExplain" id="qExplain" style="width:400px;height:130px " >
        </div>

        <div style="margin-bottom:10px;display: none;" >
            <span class="word"><span style="letter-spacing: 3px;">总做题</span>数:</span>
            <input class="easyui-textbox" data-options="validType:'length[1,50]'"
                   name="finishNum" id="finishNum" style="width:60px;height:32px " >
            <span class="word"><span style="letter-spacing: 3px;">答对次</span>数:</span>
            <input class="easyui-textbox" data-options="validType:'length[1,50]'"
                   name="rightNum" id="rightNum" style="width:60px;height:32px " >
            <span class="word"><span style="letter-spacing: 3px;">点赞次</span>数:</span>
            <input class="easyui-textbox" data-options="validType:'length[1,50]'"
                   name="zanNum" id="zanNum" style="width:60px;height:32px" >
        </div>
    </form>
</div>
<%--对话框保存、取消按钮--%>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls: 'icon-ok'" onclick="Save()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls: 'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
</body>
</html>

