$(function(){

	layer.load(2);
	var ajax= $.ajax({
		url:"/Student/fontSelChapterNumByNum.port",
		type:'post',
		datatype:"json",
		data:{
		},
		timeout:3000,
		success:function(data){

				var obj = data;
				var pieData = echarts.init(document.getElementById("pieData"));
				pieData.setOption({
					series : [
						{
							name: '做题章节占比',
							type: 'pie',
							radius: '55%',
							data:[
								{value:obj['chapter_1_2'], name:'编程基础'},
								{value:obj['chapter_3_4_5'], name:'逻辑编程'},
								{value:obj['chapter_6'], name:'数组'},
								{value:obj['chapter_7'], name:'函数'},
								{value:obj['chapter_8'], name:'指针'},
								{value:obj['chapter_9'], name:'结构体'},
								{value:obj['chapter_10'], name:'文件'}
							]
						}
					]
				});

		},error:function(){

		},complete:function(XMLHttpRequest,status){
			if(status=='timeout'){
				ajax.abort();
				layer.msg("请求超时，请重试！");
			}
			layer.closeAll('loading');
		}
	});
	//加载柱状图
	layer.load(2);
	var ajax= $.ajax({
		url:"/Student/fontSelAllNumByNum.port",
		type:'post',
		datatype:"json",
		data:{
		},
		timeout:3000,
		success:function(data){
			cal(data);
		},error:function(){

		},complete:function(XMLHttpRequest,status){
			if(status=='timeout'){
				ajax.abort();
				layer.msg("请求超时，请重试！");
			}
			layer.closeAll('loading');
		}
	});

});
function cal(obj){
	var numData = echarts.init(document.getElementById("numData"));
	option = {
		tooltip : {
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data:[ '错误数','正确数']
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'value'
			}
		],
		yAxis : [
			{
				type : 'category',
				axisTick : {show: false},
				data : ['编程基础','逻辑编程','数组','函数','指针','结构体','文件']
			}
		],
		series : [
			{
				name:'正确数',
				type:'bar',
				stack: '总量',
				label: {
					normal: {
						show: true
					}
				},
				data:[obj['chapter_1_2'],obj['chapter_3_4_5'],obj['chapter_6'],obj['chapter_7'],obj['chapter_8'],obj['chapter_9'],obj['chapter_10']]
			},
			{
				name:'错误数',
				type:'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'left'
					}
				},
				data:[obj['chapter_1_2_R']-obj['chapter_1_2'],obj['chapter_3_4_5_R']-obj['chapter_3_4_5'],
					obj['chapter_6_R']-obj['chapter_6'],obj['chapter_7_R']-obj['chapter_7'],
					obj['chapter_8_R']-obj['chapter_8'],obj['chapter_9_R']-obj['chapter_9'],
					obj['chapter_10_R']-obj['chapter_10']]
			}
		]
	};
	numData.setOption(option);
}
