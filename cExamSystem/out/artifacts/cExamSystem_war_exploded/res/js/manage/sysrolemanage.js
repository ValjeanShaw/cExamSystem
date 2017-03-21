/**
 * Created by liulei on 2016-08-18.
 */
var moduleType = GetQueryString("moduleType");//模块ID

$(function(){
    $("#Board").fadeOut("slow");
});

//新建
function Add(){
    postURL = "/jsons/AdminaddRole.form";
    $("#Form").form("clear");
    $("#dlg").dialog({title: "新建"});
    $('#dlg').dialog('open');
}
//修改
function Edit(){
    postURL = "/jsons/AdmineditRole.form";
    var row = $('#dg').datagrid('getSelected');
    if (row){
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
    postURL = "/jsons/AdmindeleteRole.form";
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示', '删除角色同时将删除该角色下的所有用户，确认删除？', function(result){
            if (result){
                $.post(postURL,{
                    sysroleid:row.sysroleid,
                    moduleType:moduleType
                },function(data){
                    if(data.result){
                        ShowMsg("删除成功");
                        $("#dg").datagrid("reload");//重新加载数据
                    }else {
                        ShowMsg("删除失败，请重新登录或联系管理员:"+data.errormessage);
                    }
                });
            }
        });
    }else {
        ShowMsg("请选中一条数据");
    }
}
//搜索
function doSearch(value,name){
    $('#dg').datagrid('reload',{
        sqlStr: value,
        moduleType:moduleType
    });
}
//保存
function Save(){
    if($("#Form").form('validate')){
        var jsonObject = $("#Form").serializeObject();
        jsonObject["moduleType"] = moduleType;
        load();
        $.post(postURL,jsonObject,function(data){
            disLoad();
            if(data.result){
                $('#dlg').dialog('close');
                ShowMsg("保存成功");
                $("#dg").datagrid("reload");
            }else {
                ShowMsg("保存中出现错误");
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
//弹出加载层
function load() {
    $("<div class=\"datagrid-mask\" style='z-index: 9999999999999999999;'></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\" style='z-index: 9999999999999999999;'></div>").html("正在加载，请稍候...").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
}

//取消加载层
function disLoad() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}