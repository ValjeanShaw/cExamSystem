/**
 * Created by 风萧萧兮 on 2017/5/15.
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
    window.location.href="/StuExam/fontStuExamPage.port?paperId="+paperId;
}