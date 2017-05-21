/**
 * Created by 风萧萧兮 on 2017/5/16.
 */
var nowpage = 1;
var total = 0;
$(function(){
    layer.load(2);
    //先加载试卷列表
    var ajax= $.ajax({
        url:'/TestPaper/fontGetExamTitle.port',
        type:'post',
        datatype:"json",
        data:{
        },
        timeout:3000,
        success:function(data){
            $(".paperTitle").text(data);
        },error:function(){

        },complete:function(XMLHttpRequest,status){
            if(status=='timeout'){
                ajax.abort();
                layer.msg("请求超时，请重试！");
            }
        }
    });

    //加载题目
    var ajax = $.ajax({
        url: '/TestPaper/fontGetPaperQuestion.port',
        type: 'post',
        data: {},
        async:false,
        timeout: 3000,
        success: function (data) {
            var arr = data["Object"];
            if (data.length == 0) {
                layer.msg("没有更多啦！");
            }
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                var string = '<li class="quesLi">'+
                    '<div class="id" hidden>' + obj["id"] + '</div>' +
                    '<div class="quesText">'+obj["questionText"]+'</div>'+
                    '<ul class="choosewrap">'+
                    '<span class="ultitle">选项：</span>'+
                    '<li>A:'+
                    '<span class="option">' + obj["chooseA"] + '</span>'+
                    '</li>'+
                    '<li>B:'+
                    '<span class="option">' + obj["chooseB"] + '</span>'+
                    '</li>'+
                    '<li>C:'+
                    '<span class="option">' + obj["chooseC"] + '</span>'+
                    '</li>'+
                    '<li>D:'+
                    '<span class="option">' + obj["chooseD"] + '</span>'+
                    '</li>'+
                    '</ul>'+
                    '<span class="ultitle">我的选择：</span>'+
                    '<span class="myChooseAnswer"></span>'+
                    '<span class="trueAnwerTitle">正确答案：</span>'+
                    '<span class="trueAnwer">' + obj["answer"] + '</span>'+
                    '<div class="trueExplainwrap">'+
                        '<span class="trueExplainTitle">解答：</span>'+
                        '<span class="trueExplain">'+ obj["qExplain"]+'</span>'+
                    '</div>'+
                    '</li>';
                $("#quesUl").append(string);
            }
            loadResult();
            setNowPage();
            calTotal();
            //展示第一个题目
            $(".quesLi").eq(0).show();
            loadFunction();
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
function loadFunction(){
    //点击选项
    $(".preChoose").click(function(){
        $(this).parent().find(".preChoose").removeClass("chooseOn");
        $(this).addClass("chooseOn");
        var choose = $(this).text();
        $(this).parent().find(".myChoose").val(choose);
    })
    //点击上一题
    $(".last").click(function(){
        if(nowpage >1){
            nowpage--;
            $(".quesLi").fadeOut();
            $(".quesLi").eq(nowpage-1).fadeIn();
            setNowPage();
        }
        layer.msg("没有上一题啦");
    })
    //点击下一题
    $(".next").click(function(){
        if(nowpage < total){
            nowpage++;
            $(".quesLi").fadeOut();
            $(".quesLi").eq(nowpage-1).fadeIn();
            setNowPage();
        }
        layer.msg("没有下一题啦");
    })
    $(".submit").click(function(){
        window.location.href="/StuExam/fontStuPaperPage.port";
    })
}
//查询总数
function calTotal(){
    var i=0;
    $(".quesLi").each(function(){
        i++;
    })
    $(".allpage").text(i);
    total = i;
}
//设置当前页面
function setNowPage(){
    $(".nowpage").text(nowpage);
}
//加载做题结果
function loadResult(){
    layer.load(2);
    var ajax= $.ajax({
        url: '/TestPaper/fontGetJdugeResult.port',
        type: 'post',
        data: {
        },
        timeout: 3000,
        success: function (data) {
            var list = data["list"];
            var rightNum = data["rightNum"];

            var i =0;
            $(".myChooseAnswer").each(function(){
                i++;
                $(this).text(list[i]);
            });
            $(".totalRight span").text(rightNum);

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
