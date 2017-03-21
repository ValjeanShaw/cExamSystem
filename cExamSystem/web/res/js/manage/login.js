/**
 * Created by liulei on 2016-08-18.
 */
$(function(){
    //加载背景图片
    imgLoad(BackgroundImg, function() {
        $(".background").eq(0).fadeIn("fast");
        var i = 0;
        var timeOut = setInterval(function(){
            if(i<2){
                i++;
            }else {
                i = 0;
            }
            $(".background").fadeOut("fast");
            $(".background").eq(i).fadeIn("fast");
        },3000);
    });
    //点击登录
    $("#Login").click(function(){
        var username = $.trim($("#UserName").val());
        var password = $.md5($("#Password").val());
        if(username==""){
            layer.msg("请输入用户名");
            return;
        }
        if(password==""){
            layer.msg("请输入密码");
            return;
        }
        var loginLoad = layer.load(1, {
            shade: [0.5,'#fff'] //0.1透明度的白色背景
        });
        //登录验证
         var ajax = $.ajax({
            url:"/AdminLogin/No_Intercept_SystemLogin.form",
            type:"post",
            data:{
                username:username,
                password:password
            },
            timeout:30000,
            success:function(data){
                if(data=="1"){//登录成功
                    window.location = "menu.form";
                }else {//登录失败
                    layer.close(loginLoad);
                    layer.msg(data);
                    return;
                }
            },
            error:function(data){
                layer.msg("出错误啦，重试一下");
                layer.close(loginLoad);
            },
            complete:function(XMLHttpRequest,status){ //请求完成后最终执行参数
                if(status=='timeout'){//超时,status还有success,error等值的情况
                    ajax.abort(); //取消请求
                    layer.msg("请求超时，重试一下");
                    layer.close(loginLoad);
                }
            }
        });
    });
    //绑定Enter键
    $("#Password").keyup(function(e){
        if(e.keyCode==13){
            $("#Login").click();
        }
    });
});
function imgLoad(img, callback) {
    var timer = setInterval(function() {
        if (img.complete) {
            callback(img);
            clearInterval(timer);
        }
    }, 50);
}