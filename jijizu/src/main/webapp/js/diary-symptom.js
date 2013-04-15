$(document).ready(function(){
	/**
	 * 症状填写 start
	 */
	var oldVal="";
	$("#symptom_input").autocomplete("/diary/findSymptomNames.jspa");
	$("#symptom_input").keyup(function(e){
		var inputVal =  $.trim($(this).val());
		var e=e.keyCode;
		if(strlen(inputVal)>18&&oldVal!=inputVal){
			$(this).val(oldVal);
			return;
		}
		oldVal = inputVal;
	});
	$("#symptom_input").keydown(function(e){
		var inputVal =  $.trim($(this).val());
		var e=e.keyCode;
		if(e==32){
			if(inputVal.trim() != ""){
				$(this).parent().before("<div class='tag'><span>"+ inputVal +"<a href='javascript:void(0);' class='icon_close'></a><input type='hidden' name='symptoms' value='"+inputVal+"'/></span></div>");
			}
			$(this).val("");
			if($("#Sick_content").height()>379){
				$('.little_book_nav').css("height","auto");
			}
		}
	});
	$(".symptom_input .tag a.icon_close").live("click",function(){
		if($("#Sick_content").height()<=379){
			$('.little_book_nav').css("height",$('.little_book_nav').height());
		}
		$(this).parent().parent(".tag").remove();
	});

	$("#sickName").autocomplete("/diary/sickNames.jspa");
	/**
	 * 症状填写 end
	 */
});