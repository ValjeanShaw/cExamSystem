/**
 * Created by 风萧萧兮 on 2017/5/10.
 */
$(function(){
    layer.load(2);
    //加载题目
    var ajax= $.ajax({
        url: '/TestPaper/fontGetPaperQuestion.port',
        type: 'post',
        data: {
        },
        timeout: 3000,
        success: function (data) {
            var arr = data["Object"];
            if(data.length == 0){
                layer.msg("没有更多啦！");
            }
            for(var i=0;i<data.length;i++){
                var obj = data[i];
                var string = '<li class="questionunit">'+
                                '<div class="question">'+
                                    '<div class="id" hidden>'+obj["id"]+'</div>'+
                                    '<div class="questionText">'+obj["questionText"]+'</div>'+
                                        '<ul>'+
                                            '<span class="ultitle">选项：</span>'+
                                            '<li>A:'+
                                                '<span class="option optionA">'+obj["chooseA"]+'</span>'+
                                            '</li>'+
                                            '<li>B:'+
                                                '<span class="option optionB">'+obj["chooseB"]+'</span>'+
                                            '</li>'+
                                            '<li>C:'+
                                                '<span class="option optionC">'+obj["chooseC"]+'</span>'+
                                            '</li>'+
                                            '<li>D:'+
                                                '<span class="option optionD">'+obj["chooseD"]+'</span>'+
                                            '</li>'+
                                        '</ul>'+
                                        '<div class="answer">正确答案：<span>'+obj["answer"]+'</span>' +
                                        '<span style="margin-left:60px;">知识点：</span><span class="chaptername">'+obj["chapter"]+'</span></div>'+
                                    '<div class="explain">解答：<span>'+obj["qExplain"]+'</span></div>'+
                                '</div>'+
                                '<div class="btndel" >删除此题</div>'+
                                '<div class="btnedit" onclick="changeQuestion(this)">换题</div>'+
                            '</li>';
                $("#questionlistul").append(string);
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
    //点击确认组卷
    $(".makeUp").click(function(){
        //打包json数组
        var arr = new Array();
        $(".id").each(function(){
            var id = $(this).text();
            arr.push(id);
        });
        var jsonStr = "{data:";
        jsonStr += JSON.stringify(arr);
        jsonStr +="}"
        var title = $("#paperName").val();
        var ajax = $.ajax({
            url: '/TestPaper/fontCreateQuesIntoPaper.port',
            type: 'post',
            data: {
                jsonStr:jsonStr,
                title:title
            },
            timeout: 3000,
            success: function (data) {
                if(data == "ok"){
                    layer.msg("组卷成功");
                    window.location.href="/TestPaper/fontTestpaperSpace.port"
                }else{
                    layer.msg("组卷失败，请联系管理员");
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

});
//换题
function changeQuestion(obj){
    var chapter = $(obj).parent().find(".question .answer .chaptername").text();
    //加载层-风格4
    layer.load(2);
    //加载题目
    var ajax= $.ajax({
        url: '/Question/fontChangePaperChoose.port',
        type: 'post',
        async:false,
        data: {
            chapter:chapter
        },
        timeout: 3000,
        success: function (data) {
            var newid = data["id"];
            var flag = false;
            $(".id").each(function(){
                //看是否重复，重复则再次加载
                if(newid == $(this).text()){
                    flag = true;
                }
            });
            if(flag){
                changeQuestion(obj);
            }else{
                //修改
                $(obj).parent().find(".question .id").text(data["id"]);
                $(obj).parent().find(".question .questionText").text(data["questionText"]);
                $(obj).parent().find(".question .optionA").text(data["chooseA"]);
                $(obj).parent().find(".question .optionB").text(data["chooseB"]);
                $(obj).parent().find(".question .optionC").text(data["chooseC"]);
                $(obj).parent().find(".question .optionD").text(data["chooseD"]);
                $(obj).parent().find(".question .answer .chaptername span").text(data["answer"]);
                $(obj).parent().find(".question .explain").text(data["qExplain"]);
                layer.closeAll('loading');
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