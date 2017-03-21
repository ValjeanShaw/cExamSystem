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

    <%--引入公共JS--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/kindeditor-4.1.10/KindeditorConfig.js"></script>
    <%--引入图片上传--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/js/ajaxfileupload.js"></script>
    <script>
        function Add(){
            $("#Form").form("clear");
            $("#dlg").dialog({title: "新建"});
            $('#dlg').dialog('open');
            $("#dlg").get(0).scrollTop=0;
        }

    </script>
</head>
<body>
<div id="Board" style="width: calc(100% - 10px);height: 100%;position: absolute;top: 0px;z-index: 100;background: #fff; display:none"></div>
<%--数据表格--%>
<table id="dg" class="easyui-datagrid" style="width:100%;min-height:556px;max-height: 100%;"
       data-options="pagination:true,
                   rownumbers:true,
                   singleSelect:true,
                   method:'post',
                   autoRowHeight:false,
                   toolbar:'#tb',
                   fitColumns:true,
                   pageList:[10,20,50],
                   <%--2、第二处修改，修改此处的Json服务，用于加载数据--%>
                   url:'/item/list.port'">
    <thead>
    <tr>
        <%-- 3、第三处修改，修改此处的列名称，与数据库一致，显示有必要的（跨行的字符串不要显示）--%>
        <th field="id" width="100px" >ID</th>
        <th field="title" width="200px">商品名称</th>
        <th field="price" width="100px" >单价（分）</th>
        <th field="num" width="100px" >数量</th>
    </tr>
    </thead>
</table>
<%--表格头部按钮--%>
<div id="tb" style="height:auto">
    <%--4、第四处修改，修改菜单按钮，选择哪些按钮需要--%>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="Add()">新建</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="Edit()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="Delete()">删除</a>
    <input id="SearchText" class="easyui-searchbox" data-options="prompt:'请输入项目名称'" style="width:200px"/>
    <%--
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="LookPicture()">查看图片</a>
    --%>
</div>
<%--对话框--%>
<div id="dlg" class="easyui-dialog hide" title=""
     style="width:600px;height:350px;padding:10px;"
     data-options="iconCls: 'icon-save',buttons: '#dlg-buttons',modal:true" closed="true" >
    <%--5、第五处修改，修改表单信息，与上面的列名称一致--%>
    <form id="Form">
        <div style="margin-bottom:10px;margin-top: 28px;display: none;">
            <div class="word">ID:</div>
            <input class="easyui-textbox" name="projectId" id="projectId">
            <%--如果有图片（文件）上传，在此处加一个Name为字段名称的input，避免修改时覆盖原图片--%>
            <%--  <input class="easyui-textbox" name="caseIcon">--%>
        </div>
        <div style="margin-bottom:10px;margin-top: 28px;">
            <span class="word"><span style="letter-spacing: 10px;">项目名</span>称:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="projectName" id="projectName" style="width:400px;height:32px " >
        </div>
        <div style="margin-bottom:10px;">
            <span class="word"><span style="letter-spacing: 4px;">项目负责</span>人:</span>
            <select class="easyui-combobox" data-options="panelHeight:'auto',editable:false,required:true,validType:'length[1,25]'"
                    name="projectPrincipalId" id="projectPrincipalId" style="width:400px;height:32px;">
                <c:forEach items="${data}" var="role">
                    <option value="${role.sysuserid}">${role.remark}</option>
                </c:forEach>
            </select>
        </div>
        <div style="margin-bottom:10px;display:none">
            <span class="word"><span style="letter-spacing: 4px;">项目负责</span>人:</span>
            <input class="easyui-textbox" data-options="required:true,validType:'length[1,50]'"
                   name="projectPrincipal" id="projectPrincipal" style="width:400px;height:32px;">
            </input>
        </div>
        <div id="status" style="margin-bottom:10px;">
            <span class="word" ><span style="letter-spacing: 10px;">项目状</span>态:</span>
            <select class="easyui-combobox" data-options="panelHeight:'auto',editable:false,required:true"
                    name="projectStatus" id="projectStatus" style="width:400px;height:32px;">
                <option value="进行中">进行中</option>
                <option value="已完成">已完成</option>
            </select>
        </div>
        <div style="margin-bottom:10px;">
            <span class="word">项目开始日期:</span>
            <input class="easyui-datebox" data-options="editable:false,required:true"
                   name="projectStartDate" id="projectStartDate" style="width:400px;height:32px;">
        </div>
        <div style="margin-bottom:10px;">
            <span class="word">项目结束日期:</span>
            <input class="easyui-datebox" data-options="editable:false,required:true"
                   name="projectEndDate" id="projectEndDate" style="width:400px;height:32px;">
        </div>
    </form>
    <%--图片上传，放在Form外面，如果不需要图片上传，注释掉--%>
    <%-- <div style="margin-bottom:10px;margin-top: 10px;">
         <span class="word" style="letter-spacing: 3px;">案例图片:</span>
         <input type="file" name="caseIcon" id="caseIcon" style="height:32px;width:200px;">
     </div>--%>
    <%--KindEditor文本框放在Form外面，如果不需要KindEditor，注释掉--%>
    <div style="margin-bottom:10px;margin-top: 10px; ">
        <div class="word" style="letter-spacing: 3px;letter-spacing: 3px">案例内容:</div>
        <textarea id="caseContent" name="caseContent" style="width:100%;height:500px;"></textarea>
    </div>
</div>
<%--对话框保存、取消按钮--%>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls: 'icon-ok'" onclick="Save()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls: 'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
</body>
</html>
