/**
 * Created by 风萧萧兮 on 2017/4/15.
 */
$(function(){

});

//保存       针对的是教师管理界面
function Save(){
    if($("#Form").form('validate')){
        //获取页面的输入框的元素
        var id = $("#id").textbox("getValue");
        var teaName = $("#teaName").textbox("getValue");
        var sex = $("#sex").combobox('getValue');
        var profesRanks = $("#profesRanks").combobox('getValue');
        var telphone = $("#telphone").textbox("getValue");
        var email = $("#email").textbox("getValue");

        if(flag == "add"){     //如果是添加
            layer.load(2);
            //ajax异步提交方式
            var ajax= $.ajax({
                url: '/Teacher/AdminAddTeacher.port',
                type: 'post',
                data: {
                    teaName: teaName,
                    sex: sex,
                    profesRanks: profesRanks,
                    telphone: telphone,
                    email: email
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
                url: '/Teacher/AdminEditTeacher.port',
                type: 'post',
                data: {
                    id:id,
                    teaName: teaName,
                    sex: sex,
                    profesRanks: profesRanks,
                    telphone: telphone,
                    email: email
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