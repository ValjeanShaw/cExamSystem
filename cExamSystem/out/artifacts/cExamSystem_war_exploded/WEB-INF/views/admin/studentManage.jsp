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
    <script charset="utf-8" src="<%=request.getContextPath()%>/res/js/admin/student.js"></script>
    <%--引入图片上传--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ajaxfileupload.js"></script>
    <script>
        var flag = 'add';     //标记是修改还是新建
        <!--第一处修改-->
        var deleteId="id";        //删除的根据--id
        var deleteUrl = "//.port";      //删除所用url

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
                   url:'<%=request.getContextPath()%>/Student/AdminAllStudents.port'">
    <thead>
    <tr>
        <%-- 3、第三处修改，修改此处的列名称，与数据库一致，显示有必要的（跨行的字符串不要显示）--%>
        <th field="id" width="100px" hidden>ID</th>
        <th field="stuNum" width="100px">学号</th>
        <th field="stuName" width="100px">姓名</th>
        <th field="sex" width="100px" hidden>性别</th>
        <th field="college" width="100px">学院</th>
        <th field="grade" width="100px" hidden>年级</th>
        <th field="classNum" width="100px" hidden>班级</th>
    </tr>
    </thead>
</table>
<%--表格头部按钮--%>
<div id="tb" style="height:auto">
    <%--4、第四处修改，修改菜单按钮，选择哪些按钮需要--%>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="Edit()">查看详情</a>
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
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">学</span>号:</span>
            <input class="easyui-numberbox" data-options="required:true,validType:'length[1,50]'"
                   name="stuNum" id="stuNum" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">姓</span>名:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="stuName" id="stuName" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">性</span>别:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="sex" id="sex" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">学</span>院:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="college" id="college" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">年</span>级:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="grade" id="grade" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">班</span>级:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="classNum" id="classNum" style="width:400px;height:32px " >
        </div>
    </form>
</div>
<%--对话框保存、取消按钮--%>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls: 'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
</body>
</html>
