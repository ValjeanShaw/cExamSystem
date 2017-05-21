/**
 * Created by 风萧萧兮 on 2017/5/15.
 */
$(function() {
    layer.load(2);
    //加载题目
    var ajax = $.ajax({
        url: '/TestPaper/fontGetPaperQuestion.port',
        type: 'post',
        data: {},
        timeout: 3000,
        success: function (data) {
            var arr = data["Object"];
            if (data.length == 0) {
                layer.msg("没有更多啦！");
            }
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                var string = '<li class="questionunit">' +
                    '<div class="question">' +
                    '<div class="id" hidden>' + obj["id"] + '</div>' +
                    '<div class="questionText">' + obj["questionText"] + '</div>' +
                    '<ul>' +
                    '<span class="ultitle">选项：</span>' +
                    '<li>A:' +
                    '<span class="option optionA">' + obj["chooseA"] + '</span>' +
                    '</li>' +
                    '<li>B:' +
                    '<span class="option optionB">' + obj["chooseB"] + '</span>' +
                    '</li>' +
                    '<li>C:' +
                    '<span class="option optionC">' + obj["chooseC"] + '</span>' +
                    '</li>' +
                    '<li>D:' +
                    '<span class="option optionD">' + obj["chooseD"] + '</span>' +
                    '</li>' +
                    '</ul>' +
                    '<div class="answer">正确答案：<span>' + obj["answer"] + '</span>' +
                    '<span style="margin-left:60px;">知识点：</span><span class="chaptername">' + obj["chapter"] + '</span></div>' +
                    '<div class="explain">解答：<span>' + obj["qExplain"] + '</span></div>' +
                    '</div>' +
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
});