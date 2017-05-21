$(function(){
	//点击切换导航条
	$(".barli").click(function(){
		$(this).siblings().stop().removeClass("on");
		$(this).stop().addClass("on");
	})
	$("#btnlogout").click(function(){
		//询问框
		layer.confirm('确定退出吗？', {
			title:"提示",
			btn: ['确定','点错了'] //按钮
		}, function(){
			window.location.href="/FontLogin/fontLoginOut.port";
		}, function(){
			//点击第二个按钮
		});

	})
});
