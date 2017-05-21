/**
 * Created by 风萧萧兮 on 2017/4/22.
 */

//保存       针对的是通知管理界面
function Save(){
    if($("#Form").form('validate')){
        //获取页面的输入框的元素
        var id = $("#id").textbox("getValue");
        var newsTitle = $("#newsTitle").textbox("getValue");
        var newsText = $("#newsText").textbox('getValue');
        var createMan = $("#createMan").textbox('getValue');

        if(flag == "add"){     //如果是添加
            layer.load(2);
            //ajax异步提交方式
            var ajax= $.ajax({
                url: '/News/AdminAddNews.port',
                type: 'post',
                data: {
                    newsTitle : newsTitle,
                    newsText : newsText,
                    createMan : createMan
                },
                timeout: 3000,
                success: function (data) {
                    if(data == "ok"){
                        ShowMsg("添加成功");
                    }else{
                        ShowMsg("添加失败，请联系管理员");
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
        }else{                 //如果是修改
            layer.load(2);
            //ajax异步提交方式
            var ajax= $.ajax({
                url: '/News/AdminEditNews.port',
                type: 'post',
                data: {
                    id:id,
                    newsTitle : newsTitle,
                    newsText : newsText,
                    createMan : createMan
                },
                timeout: 3000,
                success: function (data) {
                    if(data == "ok"){

                        ShowMsg("修改成功");
                    }else{
                        ShowMsg("修改失败，请联系管理员");
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
    }else{
        ShowMsg("请按照要求填写");
    }
}