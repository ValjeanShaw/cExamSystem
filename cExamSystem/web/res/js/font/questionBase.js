$(function(){
	//点击按钮效果
	//点击减
	$(".btnminus").click(function(){
		var obj = $(this).parent().find(".queNum").first();
		var num = obj.text();
		if(num > 0){
			num --;
		}
		obj.text(num);
		calSum();
	})
	//点击加
	$(".btnadd").click(function(){
		var obj = $(this).parent().find(".queNum").first();
		var num = obj.text();
		if(num<20){
			num++
		}
		obj.text(num);
		calSum();
	})
})
//计算总数
function calSum(){
	var sum = 0;
	$(".queNum").each(function(){
		sum += parseInt($(this).text());
	});
	$(".allNum").text(sum);
}
//点击提交按钮，传递需求
function submitNeed(){
	var sum = $(".allNum").text();
	if(sum < 5){
		layer.msg("选择题目不少于5道");
	}else {
		var chapter_1_2 = $(".queNum").eq(0).text();
		var chapter_3_4_5 = $(".queNum").eq(1).text();
		var chapter_6 = $(".queNum").eq(2).text();
		var chapter_7 = $(".queNum").eq(3).text();
		var chapter_8 = $(".queNum").eq(4).text();
		var chapter_9 = $(".queNum").eq(5).text();
		var chapter_10 = $(".queNum").eq(6).text();
		//传输参数
		var ajax = $.ajax({
			url: '/Question/fontCreateTestPaperChoose.port',
			type: 'post',
			data: {
				chapter_1_2: chapter_1_2,
				chapter_3_4_5: chapter_3_4_5,
				chapter_6: chapter_6,
				chapter_7: chapter_7,
				chapter_8: chapter_8,
				chapter_9: chapter_9,
				chapter_10: chapter_10
			},
			timeout: 3000,
			success: function (data) {
				if (data == "ok") {
					window.location.href = "/TestPaper/fontTestpaperDetail.port";
				}
			}, error: function () {

			}, complete: function (XMLHttpRequest, status) {
				if (status == 'timeout') {
					ajax.abort();
					layer.msg("请求超时，请重试！");
				}
			}
		});
	}
}
