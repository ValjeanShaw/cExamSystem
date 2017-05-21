var isStudent = true;  //是否是学生标记
var urlPath = "/FontLogin/StudentIn.port";
$(function(){
	//点击切换学生登录
	$(".studentbutton").click(function(){
		isStudent = true;
		urlPath = "/FontLogin/StudentIn.port";
		$(".usertype").text("学生登录");
		$(".btnregist").show();
		$(".studentbutton").addClass("active");
		$(".teacherbutton").removeClass("active");
	});
	//点击切换教师登录
	$(".teacherbutton").click(function(){
		isStudent = false;
		urlPath = "/FontLogin/TeacherIn.port";
		$(".usertype").text("教师登录");
		$(".btnregist").hide();
		$(".teacherbutton").addClass("active");
		$(".studentbutton").removeClass("active");
	});
	//点击登录
	$("#loginIn").click(function(){
		var username = $("#username").val();
		var userpass = $("#userpass").val();
		layer.load(2);
		var ajax= $.ajax({
			url:urlPath,
			type:'post',
			datatype:"json",
			data:{
				username:username,
				userpass:userpass
			},
			timeout:3000,
			success:function(data){
				if(data == "teacherok"){
					layer.msg("登录成功");
					window.location.href="/FontHome/TeacherHome.port";
				}else if(data == "studentok") {
					layer.msg("登录成功");
					window.location.href="/FontHome/StudentHome.port";
				}else{
						layer.msg("密码或用户名不正确");
						$("#userpass").val("");
				}
			},error:function(){

			},complete:function(XMLHttpRequest,status){
				if(status=='timeout'){
					ajax.abort();
					layer.msg("请求超时，请重试！");
				}
				layer.closeAll('loading');
			}
		})
	})
});
