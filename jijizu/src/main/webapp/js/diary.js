$(document).ready(function(){
	$(".little_book_nav dl.tag li span.prompt .txt").focus(function(){
		$(".little_book_nav dl.tag li span.prompt .prompt_list").show();
	});
	$(".little_book_nav dl.tag li span.prompt .prompt_list ul li").click(function(){
		$(".little_book_nav dl.tag li span.prompt .prompt_list").hide();
		$(".little_book_nav dl.tag li span.prompt .txt").val($(this).html());
	});
	$(".little_book_nav dl.tag li span.prompt .prompt_list ul li").mouseover(function(){
		$(this).addClass("hover");
	});
	$(".little_book_nav dl.tag li span.prompt .prompt_list ul li").mouseout(function(){
		$(this).removeClass("hover");
	});
	
	$("#child-select").change(function(){
		var childId = $(this).val();
		$("#child-id-hidden").val(childId);
	});
	
	$(".month_box").click(function(){
		var day = $(this).attr("day");
		location.href="/diary/create.jspa?diaryDate="+day;
	});
	
	$(".week_box").click(function(){
		var day = $(this).attr("day");
		location.href="/diary/create.jspa?diaryDate="+day;
	});
	
	$("#firstTimeType").change(function(){
		var checkText=$(this).find("option:selected").text();
		$("#first-time-str").html(checkText);
	});
	
	/**
	 * 删除小本本
	 */
	$("#delete-diary-btn").click(function(){
		var diaryId = $(this).attr("diaryId");
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/diary/deleteDiary.jspa',
			data:({"diaryId":diaryId
			      }), 
			success:function(data){
				if(data.flag==0){
					location.href=$("#cancel_btn").attr("href");
				}else{
					alert(data.msg);
				}
			}
		});
	});
	
	/**
	 * 保存小本本
	 */
	$("#add-diary-btn").click(function(){
		
		if(!show_login(this)){
			return;
		}
		
		if(!validateAddFormDiary()){
			return ;
		}
		
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
					if(data.flag == 0){
						alert("保存成功！");
						location.href="/diary/"+data.data;
					  }else{
					  	alert(data.msg);
					  }
				}
			};
			$("#add-diary-form").attr('action','/diary/addDiary.jspa');
			$("#add-diary-form").ajaxSubmit(options);
	});
	
	/**
	 * 更新小本本
	 */
	$("#update-diary-btn").click(function(){
		if(!validateAddFormDiary()){
			return ;
		}
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
					if(data.flag == 0){
						alert("更新成功！");
						location.href="/diary/"+data.data;
					  }else{
					  	alert(data.msg);
					  }
				}
			};
			$("#update-diary-form").attr('action','/diary/updateDiary.jspa');
			$("#update-diary-form").ajaxSubmit(options);
	});
	
	$("[name='tag']").click(function(){
		$("[name='tag_content']").hide();
		var id = $(this).attr("id");
		var tagName = id.substring(0,id.indexOf("_"));
		$("#name_hidden").show();	
		$("#"+tagName+"_name").append($("#name_hidden"));
		$("#"+tagName+"_content").show();
		$("#diary-type-hidden").val($(this).attr("value"));
		$(this).parent().parent().siblings().removeClass("on");
		$(this).parent().parent().addClass("on");
	});
	
	$("[name='tag_content']").hide();
	
	$("#congratulation").hide();
	
	$("#save_btn").click(function(){
		var val=$('input:radio[name="cured"]:checked').val();
		if(val==null){
			return false;
		}
		else if(val=='是'){
			$("#congratulation").show();
		}
	 });
	
	$(".month_table th:last").css("border-right","none");
	
	var _h=Math.max($('.little_book_nav').height(),$('.little_book_right').height());
		$('.little_book_nav').height(_h);
		$('.little_book_right').height(_h);
});

function validateAddFormDiary(){
	var diaryType = $("[name='diaryType']");
	if(diaryType.val() == ""){
		alert("请选择标签！");
		return false;
	}
	if(diaryType.val() == "DIARY_TYPE_HEIGHT"){
		var height = $("[name='height']");
		if(height.val() == ""){
			alert("请填写身高！");
			height.focus();
			return false;
		}
		if(!isMoney(height.val())){
			alert("身高格式填写不正确！");
			height.focus();
			return false;
		}
	}else if(diaryType.val() == "DIARY_TYPE_WEIGHT"){
		var weight = $("[name='weight']");
		if(weight.val() == ""){
			alert("请填写体重！");
			weight.focus();
			return false;
		}
		if(!isMoney(weight.val())){
			alert("体重格式填写不正确！");
			weight.focus();
			return false;
		}
	}else if(diaryType.val() == "DIARY_TYPE_MILK_POWDER"){
		
		var milkName = $("[name='milkName']");
		if(milkName.val() == ""){
			alert("请填写奶品牌！");
			milkName.focus();
			return false;
		}
		
		var milkMl = $("[name='milkMl']");
		if(milkMl.val() == ""){
			alert("请填写奶粉量！");
			milkMl.focus();
			return false;
		}
		if(!isMoney(milkMl.val())){
			alert("奶粉量填写不正确！");
			milkMl.focus();
			return false;
		}
	}else if(diaryType.val() == "DIARY_TYPE_FIRST_TIME"){
		var firstDay = $("[name='firstDay']");
		if(firstDay.val()==""){
			alert("请天写第一次天数！");
			firstDay.focus();
			return false;
		}
		if(!isNumber(firstDay.val()) || firstDay.val() <= 0){
			alert("第一次天数格式不正确！");
			firstDay.focus();
			return false;
		}
	}else if(diaryType.val() == "DIARY_TYPE_SICK"){
		var symptom = $('[name="symptoms"]');
		if($(".symptom_input>.tag").length==0&&$.trim(symptom.val())==""){
			alert("请填写症状");
			symptom.focus();
			return false;
	}
	}
	
	var childId = $("[name='childId']");
	if(childId.val()==""){
		alert("请选择孩子！");
		return false;
	}
	
	var title = $("#title");
	if(title.val()=="添加标题" || title.val()==""){
		alert("请填写标题！");
		title.focus();
		return false;
	}
	
	var content = $("[name='content']");
	if(content.val() == ""){
		alert("请填写内容！");
		content.focus();
		return false;
	}
	return true;
	
}

function getSickData(diaryId){
	$.ajax({
		type:'POST',
		url:'/diary/getSameSickData.jspa',
		data:({"diaryId":diaryId
	      }), 
		success:function(json){
			var html = $(json);
			$("#graph").html(html);
		}
	});
}

function getDate(){
	var childId = $("#child-id-hidden").val();
	var diaryType = $("#diary-type-hidden").val();
		
	$.ajax({
		type:'POST',
		url:'/diary/findDiary.jspa',
		dataType:'json',
		data:({"childId":childId,"diaryType":diaryType
	      }), 
		success:function(json){
		 var childJson = json[0];
		 var avgJson = json[1];
		 var childInfo = json[2];
		 var categories = new Array();
		 var childData = new Array();
		 var avgData = new Array();
		 //孩子小本本数据(到过来取)
		 var j = 0;
		 for(var i=(childJson.length-1);i>=0;i--){
			 if(diaryType == "DIARY_TYPE_HEIGHT"){
				 childData[j]=childJson[i].height;
			 }else if(diaryType == "DIARY_TYPE_WEIGHT"){
				 childData[j]=childJson[i].weight;
			 }else if(diaryType == "DIARY_TYPE_MILK_POWDER"){
				 childData[j]=childJson[i].milkMl;
			 }
			 j++;
		 }
		 
		 //平均小本本数据
		 for(var i=0;i<avgJson.length;i++){
			 categories[i]=avgJson[i].CHILD_MONTH;
			 avgData[i]=avgJson[i].AVG_DATA;
		 }
		 
		 var seriesName = "";
		 var graphTitle = "";
		 var yAxisText = "";
		 var valueSuffix = "";
		 if(diaryType == "DIARY_TYPE_HEIGHT"){
			 seriesName = "平均身高";
			 graphTitle = "宝宝身高趋势";
			 yAxisText = "身高（公分）";
			 valueSuffix = "公分";
		 }else if(diaryType == "DIARY_TYPE_WEIGHT"){
			 seriesName = "平均体重";
			 graphTitle = "宝宝体重趋势";
			 yAxisText = "体重（公斤）";
			 valueSuffix = "公斤";
		 }else if(diaryType == "DIARY_TYPE_MILK_POWDER"){
			 seriesName = "平均奶粉量";
			 graphTitle = "宝宝奶粉量趋势";
			 yAxisText = "毫升（ml）";
			 valueSuffix = "毫升";
		 }
			 
		 
		 var series = new Array();
		 if(childInfo != null){
			 series[0]={name:childInfo.name,data:childData};
			 series[1]={name: seriesName, data: avgData};
		 }else{
			 series[0]={name: seriesName, data: avgData};
		 }
		 
		 $('#graph').highcharts({
	            chart: {
	                type: 'line'
	            },
	            title: {
	                text: graphTitle,
	                x: -20 //center
	            },
	            xAxis: {
	            	min:0,
	            	title:{
	            		text: '月数'
	            	},
	                categories: categories
	            },
	            yAxis: {
	                title: {
	                    text: yAxisText
	                },
	                min:0,
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                valueSuffix: valueSuffix
	                /*formatter: function() {
	                	return "aaa";
	                }*/
	            },
	            legend: {
	                layout: 'horizontal',
	                horizontalAlign: 'left',
	                x: -160,
	                //y: -206,
	                borderWidth: 0
	            },
	            series: series
	        });
		 $("tspan").each(function(){
			 if($(this).text()=="Highcharts.com"){
				 $(this).remove();
			 }
		 });
		}
	});
}
