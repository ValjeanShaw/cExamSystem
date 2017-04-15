<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>             <!--首页-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>评测系统后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/res/easyui/themes/icon.css">
	<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/res/image/manage/logo.png" type="image/x-icon">
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/res/layer/layer.js"></script>

<style type="text/css">
	.index_box{
		width:500px;
		margin:0 auto;
		text-align: center;
		font-family: "楷体";
		font-size:30px;
	}
	.editPass{
		background: #ffffff;
		position: absolute;
		z-index: 101;
		left: 181px;
		top: 71px;
		width: calc(100% - 180px);
		height: 100%;
		display:none;
	}
	.text{
		margin-left:60px;
		margin-top:50px;
		font-size:18px;
		color:#477C9C;
		float:left;
	}
	#personPass,#personRePass{
		border:solid #477C9C 1px;
		width:200px;
		height:20px;
		margin-top:50px;
		float:right;
	}

	.title{
		color: #666666;
		font-weight: bold;
		font-size: 24px;
		margin-top: 30px;
		margin-left: 30px;
	}
	.fen{
		width:400px;
		height:5px;
		margin-left: 30px;
		background:#aaaaaa;
	}
	.inputwrap{
		width:400px;
		overflow:hidden;
		margin-bottom: 50px;
	}
	.btnwrap{
		width:200px;
		margin-left:110px;
		cursor:pointer;
	}
	.btnSubmit{
		background: #76c6e6;
		width:50px;
		height:25px;
		line-height:25px;
		text-align:center;
		float:left;
		color:#ffffff;
	}
	.btnCancel{
		background: #76c6e6;
		width:50px;
		height:25px;
		line-height:25px;
		text-align:center;
		float:right;
		color:#ffffff;
	}
</style>
	<script type="text/javascript">
		$(function(){
			//将权限放入
			var ajax= $.ajax({
				url:'/AdminMenu/GetMenuJson.port',
				type:'post',
				async:false,
				data:{
				},
				timeout:3000,
				success:function(data){
					string = '<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;font-size:20px">'+ data+ '</ul>';
					$("#tt").append(string);
				},error:function(){

				},complete:function(XMLHttpRequest,status){
					if(status=='timeout'){
						ajax.abort();
						layer.msg("请求超时，请重试！");
					}
				}
			})
			//菜单的点击效果
			$('#menu').tree({
				onClick: function(node){
					if($('#menu').tree("isLeaf",node.target)){
						var tabs = $("#tabs");
						var tab = tabs.tabs("getTab",node.text);
						if(tab){
							tabs.tabs("select",node.text);
						}else{
							var content = '<iframe scrolling="auto" frameborder="0"  src="'+node.attributes.url+'" style="width:100%;height:100%;"></iframe>';
							tabs.tabs('add',{
								title:node.text,
								content:content,
								closable:true,
								bodyCls:"content"
							});
						}
					}
				}
			});

			//蒙版层淡出
			$("#Board").fadeOut("slow");
		});
		//退出登录
		function Exit(){
			$.messager.confirm('提示', '确认退出吗?', function(result){
				if(result){
					layer.load();
					var ajax= $.ajax({
						url:'/AdminMenu/ExitLogin.port',
						type:'post',
						data:{
						},
						timeout:3000,
						success:function(data){
							if(data == "ok"){
								layer.msg("退出成功");
								window.location.href = "/AdminManage/AdminLogin.port";
							}
						},error:function(){

						},complete:function(XMLHttpRequest,status){
							if(status=='timeout'){
								ajax.abort();
								layer.msg("请求超时，请重试！");
							}
							layer.closeAll(loading);
						}
					})
				}
			});
		}
		//点击修改密码按钮
		function EditPassword(){
			$("#personPass").val("");
			$("#personRePass").val("");
			$(".editPass").fadeToggle();
		}
		//取消修改密码
		function CancalEditPass(){
			$(".editPass").fadeOut();
		}
		//点击修改密码
		function PassSubmit(){
			var pass = $("#personPass").val();
			var repass = $("#personRePass").val();
			if(pass == "" ||pass == null){
				layer.msg("密码不能为空");
				$("#personPass").focus();
				return;
			}
			if(pass != repass){
				layer.msg("两次密码不一样");
				$("#personRePass").val("");
				$("#personRePass").focus();
			}else{
				//显示等待界面
				layer.load();
				//上传
				var ajax= $.ajax({
					url:'/AdminMenu/EditSysPass.port',
					type:'post',
					data:{
						pass:pass
					},
					timeout:3000,
					success:function(data){
						if(data == "ok"){
							layer.msg("修改成功！");
							$("#personPass").val("");
							$("#personRePass").val("");
							$(".editPass").fadeToggle();
						}else{
							if(data == "error"){
								layer.msg("请求失败，请联系管理员！");
							}
						}
						layer.closeAll('loading')
					},error:function(){

					},complete:function(XMLHttpRequest,status){
						if(status=='timeout'){
							ajax.abort();
							layer.msg("请求超时，请重试！");
						}
						layer.closeAll('loading');
					}
				})
			}
		}
	</script>

</head>
<body class="easyui-layout">
	<div id="Board" style="width: calc(100% - 10px);height: 100%;position: absolute;top: 0px;z-index: 100;background: #fff;display:none"></div>
	<%--页面头部，公司名称、退出登录等--%>
	<div data-options="region:'north',border:false" style="height:40px;background:#b3d9ff;
			padding-left: 30px;line-height: 40px;">
		<img src="<%=request.getContextPath()%>/res/image/manage/logo.png" height="30px"
			 style="vertical-align: top;margin-top: 4px; float: left;">
		<span class="word" style="font-size: 25px;font-family: '华文楷体';margin-left: -15px;
				display: block;margin-top: 7px;">
				评测系统
			</span>
		<div style="position: absolute;right: 30px;top: 0px;font-family: '微软雅黑';color:#FF2525;
				font-size: 14px;" class="no_select">
			<input type="button" value="安全退出" style="background: transparent;color: #8B00B8;margin-left:20px;margin-top: 9px;
					font-family: '微软雅黑';font-size: 14px;border: 0px;" class="exit" onclick="Exit()">
		</div>
		<div id="welconme" style="position: absolute;right: 120px;top: 0px;font-family: '微软雅黑';
				font-size: 12px;" class="no_select">
			欢迎您：<span style="font-size: 14px;color: #375580;"><%=session.getAttribute("AdminLoginUsername")%></span>
		</div>
	</div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
		<div id="tt"></div>
		<div class="easyui-accordion" style="width:100%;height:auto;margin-top: 5px;">
			<div title='个人中心' data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
				<a class="easyui-linkbutton" style="width: 100%;margin-top: 5px;font-family: '微软雅黑';color: #0E2D5F;border: #E0ECFF 1px solid;" onclick="EditPassword()">修改密码</a><br>
			</div>
			<div title="" selected></div>
		</div>
    </div>
	<%--右侧主面板--%>
    <div data-options="region:'center',title:''">
    	<div id="tabs" name="xiaoran" class="easyui-tabs" style="height:100%;padding:0px">
			<div title="首页" style="display:none;">
				<div class="index_box" style="margin-top: 150px;">
					<img src="<%=request.getContextPath()%>/res/image/manage/logo.png" height="130px" />
					<div class="index_box_title">欢迎使用项目管理系统</div>
				</div>
			</div>
		</div>
    </div>
	<!--修改密码的提示框-->
	<div class="editPass">
		<div class="title">
			修改密码
		</div>
		<p class="fen"></p>
		<div class="inputwrap">
			<div class="text">
				输入新密码：
			</div>
			<input type="password" id="personPass" />
			<div class="text">
				再次输入密码：
			</div>
			<input type="password" id="personRePass"/>
		</div>
		<p class="fen"></p>
		<div class="btnwrap">
			<div class="btnSubmit" onclick="PassSubmit()">确定</div>
			<div class="btnCancel" onclick="CancalEditPass()">取消</div>
		</div>
	</div>
</body>
</html>