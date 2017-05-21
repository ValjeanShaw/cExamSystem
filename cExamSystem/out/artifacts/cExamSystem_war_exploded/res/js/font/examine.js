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
								'<ul class="mychoosewrap">'+
									'<li class="preChoose">A</li>'+
									'<li class="preChoose">B</li>'+
									'<li class="preChoose">C</li>'+
									'<li class="preChoose">D</li>'+
									'<input type="text" hidden class="myChoose"/>'+
									'</ul>'+
									'</li>';
				$("#quesUl").append(string);
			}

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
	})
	//点击下一题
	$(".next").click(function(){
		if(nowpage < total){
			nowpage++;
			$(".quesLi").fadeOut();
			$(".quesLi").eq(nowpage-1).fadeIn();
			setNowPage();
		}
	})
	//点击提交
	$(".submit").click(function(){
		var index = 0;
		var arr = new Array();
		//检查完成度
		$(".myChoose").each(function(){
			index ++;
			var value = $(this).val();
			if(value == null || value == ""){
				layer.msg("你的第"+index+"题还没完成");
				return;
			}
		});
		//存入数组
		$(".myChoose").each(function(){
			var value = $(this).val();
			arr.push(value);
		});
		var jsonStr = "{data:";
		jsonStr += JSON.stringify(arr);
		jsonStr +="}";
		//上传答案
		layer.load(2);
		layer.msg("判卷中，请稍后");
		var ajax= $.ajax({
			url: '/TestPaper/fontJudgePaper.port',
			type: 'post',
			data: {
				arr:jsonStr
			},
			timeout: 3000,
			success: function (data) {
				if(data == "ok"){
					window.location.href="/TestPaper/fontExamResultPage.port";
				}else{
					layer.msg("交卷失败，请重试")
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


