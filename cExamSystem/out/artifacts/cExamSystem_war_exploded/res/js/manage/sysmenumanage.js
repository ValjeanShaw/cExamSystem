/**
 * Created by liulei on 2016-08-17.
 */
var postURL = "";//发送Post的请求地址
var childrenId = [];//子菜单的ID
var moduleType = GetQueryString("moduleType");
$(function(){
    if(moduleType==null){
        moduleType = "";
    }
    $("#Board").fadeOut("slow");
    //添加按钮
    $('#tt').datagrid({
        toolbar: [{
            text: '新建',
            iconCls: 'icon-add',
            handler: function () {
                Add();
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                Edit();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                Delete();
            }
        }]
    });
});
//新建
function Add(){
    childrenId = [];
    postURL = "/jsons/AdminaddMenu.form";
    $("#Form").form("clear");
    $('#sort').numberbox('setValue', 1);
    $("#dlg").dialog({title: "新建"});
    $('#dlg').dialog('open');
}
//修改
function Edit(){
    postURL = "/jsons/AdmineditMenu.form";
    var row = $('#tt').datagrid('getSelected');
    if (row){
        childrenId = [];
        getChildrenId(row);//获取当前菜单的所有子菜单的ID
        if(row.sysmenuid=="2c659331-0d1a-11e6-b867-0025b6dd0800"){
            ShowMsg("菜单根目录不允许修改");
            return;
        }
        $("#Form").form("clear");
        $('#Form').form('load', row);
        $("#dlg").dialog({title: "修改"});
        $('#dlg').dialog('open');
    }else {
        ShowMsg("请选中一条数据");
    }
}
//删除
function Delete(){
    var row = $('#tt').datagrid('getSelected');
    if (row){
        if(row.issysmanagemenu=="是"){
            ShowMsg("系统菜单不能删除");
            return;
        }
        childrenId = [];
        getChildrenId(row);//获取当前菜单的所有子菜单的ID
        var message = "";
        if(childrenId.length>1){
            message = "此菜单含有子菜单，将删除所有子菜单，确定继续吗？";
        }else {
            message = "确认删除此条数据吗?";
        }
        $.messager.confirm('提示', message, function(result){
            if (result){
                $.post("/AdminMenu/deleteMenu.form",{
                    menuIdList:childrenId,
                    moduleType:moduleType
                },function(data){
                    if(data=="1"){
                        ShowMsg("删除成功");
                        //重新加载数据
                        $('#tt').treegrid('reload');
                        $('#parentmenuid').combotree({url: '/AdminMenu/getParentMenu.form',required: true});
                    }else {
                        ShowMsg("删除失败，请重新登录或联系管理员");
                    }
                });
            }
        });
    }else {
        ShowMsg("请选中一条数据");
    }
}
//保存
function Save(){
    if($("#Form").form('validate')){
        var jsonObject = $("#Form").serializeObject();
        jsonObject["moduleType"] = moduleType;//将moduleType添加到JSON对象做为参数
        if(isContainStr(childrenId,jsonObject.parentmenuid)){
            ShowMsg("所选父菜单不能为当前菜单或其子菜单");
            return;
        }
        $.post(postURL,jsonObject,function(data){
            if(data.result){
                $('#dlg').dialog('close');
                ShowMsg("保存成功");
                //重新加载数据
                $('#tt').treegrid('reload');
                $('#parentmenuid').combotree({url: '/AdminMenu/getParentMenu.form',required: true});
            }else {
                ShowMsg("保存中出现错误，请联系管理员");
            }
        });
    }else {
        ShowMsg("请按照要求填写");
    }
}
//序列化
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
//获取所有子菜单的ID
function getChildrenId(row){
    childrenId.push(row.sysmenuid);
    if(row.children!=null){
        for(var i=0;i<row.children.length;i++){
            getChildrenId(row.children[i]);
        }
    }
}
//判断字符串集合是否包含指定字符串
function isContainStr(list,str){
    for(var i=0;i<list.length;i++){
        if(list[i]==str){
            return true;
        }
    }
    return false;
}