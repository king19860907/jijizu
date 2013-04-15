// JavaScript Document

<!-- 设置勋章在微博显示 -->
$(document).ready(function(){
	$("#medalOfShow li .img_show .ico_closeZ a").live("click",function(){
		$(this).addClass("show")
		var $li_content = $(this).parents("li").html();
		var $li_a = $(this).html("<a href='javascript:void(0);' onclick='abc()'></a>")
		var $li_emty_content = "<li>"+$li_content+"</li>";
		$("#medalOfHidden").append($li_emty_content);	
		$(this).parents("li").remove();
		$("#medalOfShow").append("<li class='empty'></li>");
	})
	
	
	$("#medalOfHidden li .img_show .ico_closeZ a").live("click",function(){
		$(this).removeClass("show");
		var $li_content = $(this).parents("li").html();
		//alert($("#medalOfShow li.empty:first").html())
		$("#medalOfShow li.empty:eq(0)").removeClass("empty").append($li_content)
		$(this).parents("li").remove();
	})
	
	$(".tab_badge li a").click(function(){
		$(".tab_badge li").removeClass("on");
		$(this).parent().addClass("on");
	})
});