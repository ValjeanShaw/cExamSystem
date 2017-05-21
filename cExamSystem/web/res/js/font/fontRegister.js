/**
 * Created by 风萧萧兮 on 2017/5/16.
 */
var flag = false;
$(function(){
    $("#username").blur(function(){
        var username = $("#username").val();
        var ajax = $.ajax({
            url: '/Student/fontJudgeRegisterUnique.port',
            type: 'post',
            data: {
                stuNum:username
            },
            timeout: 3000,
            success: function (data) {
                if(data == "error"){
                    layer.msg("此学号已经注册，不允许重复注册");
                    $("#username").val("");
                    $("#username").focus();
                    flag = false;
                }else{
                    flag = true;
                }
            }, error: function () {

            }, complete: function (XMLHttpRequest, status) {
                if (status == 'timeout') {
                    ajax.abort();
                    layer.msg("请求超时，请重试！");
                }
            }
        });
    });
    //性别的点击效果
    $(".sexWrap").click(function(){
        var value = $(this).text();
        $("#sex").val(value);
        $(".sexWrap").removeClass("active");
        $(this).addClass("active");
    });
    //判断
    $("#btnSubmit").click(function(){
        if($("#username").val() == null||$("#username").val() == ""){
            $("#username").focus();
            layer.msg("请填写学号");
            return;
        }
        if($("#userpass").val() == null||$("#userpass").val() == ""){
            $("#userpass").focus();
            layer.msg("请填写密码");
            return;
        }
        if($("#reuserpass").val() == null||$("#reuserpass").val() == ""){
            $("#reuserpass").focus();
            layer.msg("请重复密码");
            return;
        }
        if($("#stuName").val() == null||$("#stuName").val() == ""){
            $("#stuName").focus();
            layer.msg("请填写姓名");
            return;
        }
        if($("#college").val() == null||$("#college").val() == ""){
            $("#college").focus();
            layer.msg("请填写学院");
            return;
        }
        if($("#grade").val() == null||$("#grade").val() == ""){
            $("#grade").focus();
            layer.msg("请填写年级");
            return;
        }
        if($("#classNum").val() == null||$("#classNum").val() == ""){
            $("#classNum").focus();
            layer.msg("请填写班级");
            return;
        }
        //
        layer.load(2);
        if(flag){
            var username = $("#username").val();
            var userpass = $("#userpass").val();
            var stuName = $("#stuName").val();
            var sex = $("#sex").val();
            var college = $("#college").val();
            var grade = $("#grade").val();
            var classNum = $("#classNum").val();
            layer.load(2);
            var ajax = $.ajax({
                url: '/Register/fontRegistStudent.port',
                type: 'post',
                data: {
                    stuNum:username,
                    userpass:userpass,
                    stuName:stuName,
                    sex:sex,
                    college:college,
                    grade:grade,
                    classNum:classNum
                },
                timeout: 3000,
                success: function (data) {
                    if(data == "ok"){
                        layer.msg("注册成功！");
                        setInterval(function(){
                            window.location.href="/FontLogin/LoginPage.port";
                        },1000);
                    }else{
                        layer.msg("注册失败，请联系管理员");
                    }

                }, error: function () {

                }, complete: function (XMLHttpRequest, status) {
                    if (status == 'timeout') {
                        ajax.abort();
                        layer.msg("请求超时，请重试！");
                    }
                    layer.closeAll("loading");
                }
            });
        }else{
            layer.msg("此学号已经注册，不允许重复注册");
        }
    })
    //重复密码失去焦点
    $("#reuserpass").blur(function(){
        var userpass = $("#userpass").val();
        var reuserpass = $("#reuserpass").val();
        if(userpass != reuserpass){
            layer.msg("两次密码不一样");
            $("#reusepass").val("");
            $("#reusepass").focus();
        }
    });
});