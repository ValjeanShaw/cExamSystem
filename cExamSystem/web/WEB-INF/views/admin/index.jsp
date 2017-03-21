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
						url:'/AdminLogin/ExitLogin.port',
						type:'post',
						data:{
						},
						timeout:3000,
						success:function(data){
							if(data == "ok"){
								layer.msg("退出成功");
								window.location.href = "/AdminLogin/Login.port";
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
				川西坝子
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
				<a href="/AdminMenu/itemsManage.port" class="easyui-linkbutton" style="width: 100%;margin-top: 5px;font-family: '微软雅黑';color: #0E2D5F;border: #E0ECFF 1px solid;" onclick="EditPerson()">修改个人信息</a><br>
				<a href="#" class="easyui-linkbutton" style="width: 100%;margin-top: 5px;font-family: '微软雅黑';color: #0E2D5F;border: #E0ECFF 1px solid;" onclick="EditPassword()">修改密码</a><br>
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
</body>
</html>