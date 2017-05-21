/**
 * Created by 风萧萧兮 on 2017/5/21.
 */
$(function(){
    layer.load(2);
    var ajax= $.ajax({
        url: '/Top/fontTopAllChapterNum.port',
        type: 'post',
        data: {
        },
        timeout: 3000,
        success: function (data) {
            var arr = data;
            for(var i=0;i<arr.length;i++){
                var obj = arr[i];
                var string = '<li class="allNumLi">'+obj['chapter']+'<span class="detailData">'+obj['num']+'</span></li>';
                $(".allNum").append(string);
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
    layer.load(2);
    var ajax= $.ajax({
        url: '/Top/fontTopRightChapterNum.port',
        type: 'post',
        data: {
        },
        timeout: 3000,
        success: function (data) {
            var arr = data;
            for(var i=0;i<arr.length;i++){
                var obj = arr[i];
                var string = '<li class="rightNumLi">'+obj['chapter']+'<span class="detailData">'+(obj['num']*100).toFixed(2)+'%'+'</span></li>';
                $(".rightNum").append(string);
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
    layer.load(2);
    var ajax= $.ajax({
        url: '/Top/fontTopAllStudentNum.port',
        type: 'post',
        data: {
        },
        timeout: 3000,
        success: function (data) {
            var arr = data;
            for(var i=0;i<arr.length;i++){
                var obj = arr[i];
                var string = '<li class="allNumLiStu">'+obj['stuName']+'<span class="detailData">'+obj['num']+'</span></li>';
                $(".allNumStu").append(string);
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
    layer.load(2);
    var ajax= $.ajax({
        url: '/Top/fontTopRightStudentNum.port',
        type: 'post',
        data: {
        },
        timeout: 3000,
        success: function (data) {
            var arr = data;
            for(var i=0;i<arr.length;i++){
                var obj = arr[i];
                var string = '<li class="rightNumLiStu">'+obj['stuName']+'<span class="detailData">'+(obj['num']*100).toFixed(2)+'%'+'</span></li>';
                $(".rightNumStu").append(string);
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
})