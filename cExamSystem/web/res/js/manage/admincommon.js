/**
 * Created by 风萧萧兮 on 2017/4/15.
 *
 *
 * 公用js，不要修改
 *
 *
 */
//新建
function Add(){
    flag = "add";
    $("#Form").form("clear");
    $("#dlg").dialog({title: "新建"});
    $('#dlg').dialog('open');
    $("#dlg").get(0).scrollTop=0;
}

//修改
function Edit(){
    flag = "edit";
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $("#Form").form("clear");
        $('#Form').form('load', row);
        $("#dlg").dialog({title: "修改"});
        $('#dlg').dialog('open');
        $("#dlg").get(0).scrollTop=0;
    }else {
        ShowMsg("请选中一条数据");
    }
}

//删除
function Delete(){
    flag = "delete";
    var row = $('#dg').datagrid('getSelected');
    if (row){
        $.messager.confirm('提示', '确认删除此条数据吗?', function(result){
            if (result){
                //删除数据库记录
                var selectId = row[deleteId];
                //ajax异步提交方式
                var ajax= $.ajax({
                    url: deleteUrl,
                    type: 'post',
                    data: {
                        id:selectId
                    },
                    timeout: 3000,
                    success: function (data) {
                        if(data == "ok"){
                            layer.msg("删除成功");
                        }else{
                            layer.msg("删除失败，请联系管理员");
                        }
                        layer.closeAll('loading')
                    }, error: function () {

                    }, complete: function (XMLHttpRequest, status) {
                        if (status == 'timeout') {
                            ajax.abort();
                            layer.msg("请求超时，请重试！");
                        }
                        //关闭对话框，重新加载界面
                        $('#dlg').dialog('close');
                        $("#dg").datagrid("reload");
                    }
                })
            }
        });
    }else {
        ShowMsg("请选中一条数据");
    }
}