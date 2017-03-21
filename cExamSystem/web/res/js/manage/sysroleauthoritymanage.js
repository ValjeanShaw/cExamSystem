/**
 * Created by liulei on 2016-08-18.
 */
var menuIdList = [];//系统菜单
var parentMenuIdList = [];//系统菜单父节点
var sysroleId = "";
var moduleType = GetQueryString("moduleType");//模块ID
$(function(){
    //菜单树选中状态变化时
    $('#tt').tree({
        onCheck: function(node,checked){
            var row = $('#dg').datagrid('getSelected');
            if(row){
                sysroleId = row.sysroleid;
                var nodes = $('#tt').tree('getChecked');
                menuIdList = [];
                parentMenuIdList = [];
                for(var i=0;i<nodes.length;i++){
                    menuIdList.push(nodes[i].id);
                    getParentNode(nodes[i]);
                }
                menuIdList = menuIdList.concat(parentMenuIdList);
                menuIdList = unique(menuIdList);
            }else {
                ShowMsg("请选择一个角色");
                $("#tt").tree('uncheck',node.target);
                return;
            }
        }
    });
    //角色表格选中状态变化时
    $('#dg').datagrid({
        onClickRow: function(rowIndex, rowData){
            var nodes = $("#tt").tree("getChecked");
            for(var i=0;i<nodes.length;i++){
                $("#tt").tree('uncheck',nodes[i].target);
            }
            $.post("/jsons/AdmingetRoleMenu.form",{
                sysroleId:rowData.sysroleid,
                moduleType:moduleType
            },function(data){
                if(data.result){
                    var node;
                    for(var i=0;i<data.resultSet.length;i++){
                        node = $('#tt').tree('find',data.resultSet[i].sysmenuid);
                        if(node.children.length==0){
                            $('#tt').tree('check', node.target);
                        }
                    }
                }else {
                    ShowMsg("获取角色权限菜单失败："+data.errormessage);
                }
            });
        }
    });
});

//获取父节点信息
function getParentNode(node){
    if(node.parentId!=""){
        parentMenuIdList.push(node.parentId);
        getParentNode($("#tt").tree('getParent',node.target));
    }
}

//去除重复的数组信息
function unique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    return result;
}

//保存
function Save(){
    if(sysroleId==""){
        ShowMsg("请选择一个角色");
        return;
    }
    if(menuIdList.length==0){
        menuIdList.push("none");
    }
    load();
    $.post("/AdminMenu/saveRoleMenu.form",{
        sysroleId:sysroleId,
        menuIdList:menuIdList,
        moduleType:moduleType
    },function(data){
        $("#BackBoard,#LoadWord").remove();
        if(data=="1"){
            ShowMsg("保存成功");
        }else if(data=="-1"){
            ShowMsg("保存失败");
        }else {
            ShowMsg(data);
        }
    });
}
//弹出加载层
function load() {
    $("<div class=\"datagrid-mask\" id='BackBoard'></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\" id='LoadWord'></div>").html("正在加载，请稍候...").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
}