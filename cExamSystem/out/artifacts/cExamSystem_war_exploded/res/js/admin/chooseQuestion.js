/**
 * Created by 风萧萧兮 on 2017/4/30.
 */

//保存
function Save(){
    if($("#Form").form('validate')){
        //获取页面的输入框的元素
        var id = $("#id").textbox("getValue");
        var questionText = $("#questionText").textbox("getValue");
        var questionPic = null;//$("#questionPic").textbox('getValue');
        var chooseA = $("#chooseA").textbox('getValue');
        var chooseB = $("#chooseB").textbox('getValue');
        var chooseC = $("#chooseC").textbox('getValue');
        var chooseD = $("#chooseD").textbox('getValue');
        var answer = $("#answer").combobox('getValue');
        var qExplain = $("#qExplain").textbox('getValue');
        var chapter = $("#chapter").combobox('getValue');

        if(flag == "add"){     //如果是添加
            layer.load(2);
            //ajax异步提交方式
            var ajax= $.ajax({
                url: '/Question/AdminAddNewChoose.port',
                type: 'post',
                data: {
                    questionText:questionText,
                    questionPic:questionPic,
                    chooseA:chooseA,
                    chooseB:chooseB,
                    chooseC:chooseC,
                    chooseD:chooseD,
                    answer:answer,
                    qExplain:qExplain,
                    chapter:chapter
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
                url: '/Question/AdminEditChoose.port',
                type: 'post',
                data: {
                    id:id,
                    questionText:questionText,
                    questionPic:questionPic,
                    chooseA:chooseA,
                    chooseB:chooseB,
                    chooseC:chooseC,
                    chooseD:chooseD,
                    answer:answer,
                    qExplain:qExplain,
                    chapter:chapter
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