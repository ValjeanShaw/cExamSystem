/**
 * Created by 风萧萧兮 on 2017/5/14.
 */
var index = 0;
$(function(){
    load(index);
    $(".morewrap").click(function(){
        index +=6;
        load(index);
    })
});
function load(index){
    layer.load(2);
    //加载题目
    var ajax= $.ajax({
        url: '/TestPaper/fontGetExamPaper.port',
        type: 'post',
        data: {
            index:index
        },
        timeout: 3000,
        success: function (data) {
            if(data.length == 0){
                layer.msg("没有再多啦！")
            }
            for(var i=0;i<data.length;i++){
                var obj = data[i];
                var str = '<li class="listunit">'+
                    '<div class="clickwrap" onclick="intoQues(this)">'+
                    '<input type="text" hidden class="paperId" value="'+obj["id"]+'">'+
                    '<img class="paperlogo" src="../../../../res/image/font/paperlogo.png" alt="" />'+
                    '<div class="paperTitle">'+obj["exampaperTitle"]+
                    '</div>'+
                    '<div class="createDate">创建日期：<span>'+obj["createDate"]+'</span></div>'+
                    '<div class="createMan">出题人：<span>'+obj["createMan"]+'</span></div>'+
                    '</div>'+
                    '<img onclick="btnDel(this)" class="btnDel" src="../../../../res/image/font/btndel.png" alt="" title="删除"/>'+
                    '</li>';
                $("#listul").append(str);
            }
        }, error: function () {

        }, complete: function (XMLHttpRequest, status) {
            if (status == 'timeout') {
                ajax.abort();
                layer.msg("请求超时，请重试！");
            }
            layer.closeAll('loading');
        }
    });
}
//点击试卷效果
function intoQues(obj){
    var paperId = $(obj).parent().find(".paperId").val();
    window.location.href="/Question/fontTestpaperDetailById.port?paperId="+paperId;
}
//点击删除效果
function btnDel(obj){
    var name = $("#user").text();
    var createName = $(obj).parent().find(".clickwrap .createMan span").text();
    if(name == createName){
        //询问框
        layer.confirm('确定删除吗？', {
            btn: ['确定','点错了'] //按钮
        }, function(){
            //点击第一个按钮
            var paperId = $(obj).parent().find(".paperId").val();
            var ajax= $.ajax({
                url: '/TestPaper/fontDelExamPaperById.port',
                type: 'post',
                data: {
                    paperId:paperId
                },
                timeout: 3000,
                success: function (data) {

                    if(data == "ok"){
                        layer.msg("删除成功");
                        $(obj).parent().parent().remove();
                    }else{
                        layer.msg("删除失败，请重试")
                    }
                }, error: function () {

                }, complete: function (XMLHttpRequest, status) {
                    if (status == 'timeout') {
                        ajax.abort();
                        layer.msg("请求超时，请重试！");
                    }
                    layer.closeAll('loading');
                }
            });
        }, function(){
            //点击第二个按钮
        });

    }else{
        layer.msg("非出题人，无法删除");
    }
}