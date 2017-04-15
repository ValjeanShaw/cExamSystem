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
    <%--引入本页面专属js--%>
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/admin/teacher.js"></script>
    <%--引入图片上传--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ajaxfileupload.js"></script>
    <script>
        var flag = 'add';     //标记是修改还是新建
        var deleteId="id";        //删除的根据--id
        var deleteUrl = "/Teacher/AdminDeleteTeacher.port";      //删除所用url

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
                   url:'<%=request.getContextPath()%>/Teacher/AdminAllTeachers.port'">
    <thead>
    <tr>
        <%-- 3、第三处修改，修改此处的列名称，与数据库一致，显示有必要的（跨行的字符串不要显示）--%>
        <th field="id" width="100px" hidden>ID</th>
        <th field="teaName" width="100px">教师名</th>
        <th field="sex" width="100px">性别</th>
        <th field="profesRanks" width="100px">教师职称</th>
        <th field="telphone" width="100px">电话</th>
        <th field="email" width="100px">邮箱</th>
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
     style="width:600px;height:400px;padding:10px;"
     data-options="iconCls: 'icon-save',buttons: '#dlg-buttons',modal:true" closed="true" >
    <%--5、第五处修改，修改表单信息，与上面的列名称一致--%>
    <form id="Form">
        <div style="margin-bottom:10px;margin-top: 28px;display: none;">
            <div class="word">ID:</div>
            <input class="easyui-textbox" name="id" id="id">
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">教师姓</span>名:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="teaName" id="teaName" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 10px;">教师性</span>别:</span>
            <select class="easyui-combobox" data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,25]'"
                    name="sex" id="sex" style="width:400px;height:32px;">
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 10px;">教师职</span>称:</span>
            <select class="easyui-combobox" data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,25]'"
                    name="profesRanks" id="profesRanks" style="width:400px;height:32px;">
                <option value="教授">教授</option>
                <option value="副教授">副教授</option>
                <option value="讲师">讲师</option>
                <option value="助教">助教</option>
            </select>
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 10px;">电话号</span>码:</span>
            <input class="easyui-numberbox" data-options="required:true,min:0,precision:0"
                   name="telphone" id="telphone" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 10px;">电子邮</span>箱:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="email" id="email" style="width:400px;height:32px" >
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
