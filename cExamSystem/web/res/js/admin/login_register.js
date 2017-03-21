$(function(){
	//点击输入密码，先判断用户名是否为空
	$(".passInput").click(function(){
		var usertext = $(".userInput").val();
		var regex = /\s+/;  //使用正则表达式使其不能含有空格
		if(usertext == ""||regex.test(usertext)){
			//使用聚焦方式提醒用户填写
			$(".userInput").focus();
			$(".userInput").parent().stop().css("border","1px #D84C29 solid");
			var time = setInterval(function(){
				$(".userInput").parent().stop().css("border","1px #cccccc solid");
				clearInterval(time);
			},1000);
		}
	});
	//点击重复输入密码，一级一级判断
	$(".repassInput").click(function(){
		var usertext = $(".userInput").val();
		var passtext = $(".passInput").val();
		var regex = /\s+/;  //使用正则表达式使其不能含有空格
		if(usertext == ""||regex.test(usertext)){
			//使用聚焦方式提醒用户填写
			$(".userInput").focus();
			$(".userInput").parent().stop().css("border","1px #D84C29 solid");
			var time = setInterval(function(){
				$(".userInput").parent().stop().css("border","1px #cccccc solid");
				clearInterval(time);
			},1000);
		}else if(passtext == ""||regex.test(passtext)){
			//使用聚焦方式提醒用户填写
			$(".passInput").focus();
			$(".passInput").parent().stop().css("border","1px #D84C29 solid");
			var time = setInterval(function(){
				$(".passInput").parent().stop().css("border","1px #cccccc solid");
				clearInterval(time);
			},1000);	
		}
	});

});
	function registsub(){
		var apassword = $(".passInput").val();
		var bpassword = $(".repassInput").val();
		if(apassword == bpassword){
			var username = $("#username").val();
			var userpass = $("#userpass").val();
			layer.load();
			//ajax异步提交方式
			var ajax= $.ajax({
				url:'/AdminManage/RegisterIn.port',
				type:'post',
				data:{
					username:username,
					password:userpass
				},
				timeout:3000,
				success:function(data){
					if(data == "ok"){
						setInterval(function(){
							layer.closeAll('loading');
							window.location.href="/AdminLogin/Login.port";
						},500);
					}else{
						layer.msg(data);
					}
				},error:function(){

				},complete:function(XMLHttpRequest,status){
					if(status=='timeout'){
						ajax.abort();
						layer.msg("请求超时，请重试！");
					}
				}
			})
		}else{
			layer.msg("两次密码不一致，请重新输入！");
			$(".repassInput").val("");
			$(".repassInput").focus();
			$(".repassInput").parent().stop().css("border","1px #D84C29 solid");

		}
	}