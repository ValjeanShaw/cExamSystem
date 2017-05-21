$(function(){
	var num = 0;
	//图片轮播
	var photoflag = 0;
	var clone = $(".imgunit").first().clone();
	$(".carousel").append(clone);
	//3秒轮播
	setInterval(moveLeft,5000);
	function moveLeft(){
		photoflag++;
		if(photoflag == 4){
			$(".carousel").css({left:0})
			photoflag = 1;
		}
		$(".carousel").stop().animate({left:-photoflag*1000},1000);
	}
	//初始状态加载新闻列表
	var ajax= $.ajax({
		url:'/News/FontLoadNews.port',
		type:'post',
		datatype:"json",
		data:{
			num:num
		},
		timeout:3000,
		success:function(data){
			var arr = data["objects"];
			for(var i=0;i<arr.length;i++){
				var obj = arr[i];
				var str = '<li>'+
							'<span class="newstime">'+obj["createDate"]+'</span>'+
							'<span class="newstitle">'+obj["newsTitle"]+'</span>'+
							'<span class="newsContent">'+obj["newsText"]+'</span>'+
							'</li>';
				$("#newsList").append(str);
			}
		},error:function(){

		},complete:function(XMLHttpRequest,status){
			if(status=='timeout'){
				ajax.abort();
				layer.msg("请求超时，请重试！");
			}
		}
	})
	//点击更多时候加载
	$("#morewrap").click(function(){
		num += 5;
		var ajax= $.ajax({
			url:'/News/FontLoadNews.port',
			type:'post',
			datatype:"json",
			data:{
				num:num
			},
			timeout:3000,
			success:function(data){
				var arr = data["objects"];
				if(arr.length == 0){
					layer.msg("没有更多啦！");
				}
				for(var i=0;i<arr.length;i++){
					var obj = arr[i];
					var str = '<li>'+
						'<span class="newstime">'+obj["createDate"]+'</span>'+
						'<span class="newstitle">'+obj["newsTitle"]+'</span>'+
						'<span class="newsContent">'+obj["newsText"]+'</span>'+
						'</li>';
					$("#newsList").append(str);
				}
			},error:function(){

			},complete:function(XMLHttpRequest,status){
				if(status=='timeout'){
					ajax.abort();
					layer.msg("请求超时，请重试！");
				}
			}
		})
	})

});

