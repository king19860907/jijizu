// JavaScript Document


$(document).ready(function(){
	<!-- 搜索TAB -->
	$(".search_head_formbox .formbox_tab li a").click(function(){
		$(".search_head_formbox .formbox_tab li a").removeClass("cur");
		$(this).addClass("cur");
	});
	$(".search_tab .all_choose a").click(function(){
		$(".search_tab .all_choose a").removeClass("cur");
		$(this).addClass("cur");
	});
	$(".pl_piclist .list_pic").mouseover(function(){
		$(this).addClass("list_pic_hover");
	});
	$(".pl_piclist .list_pic").mouseout(function(){
		$(this).removeClass("list_pic_hover");
	});
	
	$(".search_tab .tab_list .show_gender").click(function(){
		p=$(this).offset();
		var $left = p.left + "px";
		var $top = p.top + 20 + "px";
		$(".search_more_ul_03").toggle().css("left",$left).css("top",$top);
	});
	$(".search_more_ul_03 li").click(function(){
		$(".search_more_ul_03 li a").removeClass("current");
		$(this).children().addClass("current");
		$(".search_more_ul_03").hide();
		$(".show_gender span:first").html($(this).children().html());
	});
	
	
	$(".search_tab .tab_list .showAgeLayer").click(function(){
		p=$(this).offset();
		var $left = p.left + "px";
		var $top = p.top + 20 + "px";
		$(".search_more_ul_04").toggle().css("left",$left).css("top",$top);
	});
	$(".search_more_ul_04 li").click(function(){
		$(".search_more_ul_04 li a").removeClass("current");
		$(this).children().addClass("current");
		$(".search_more_ul_04").hide();
		$(".showAgeLayer span:first").html($(this).children().html());
	});
	
	$(".show_status").click(function(){
		p=$(this).offset();
		var $left = p.left + "px";
		var $top = p.top + 20 + "px";
		$(".search_more_ul_07").toggle().css("left",$left).css("top",$top);
	});
	$(".search_more_ul_07 li").click(function(){
		$(".search_more_ul_07 li a").removeClass("current");
		$(this).children().addClass("current");
		$(".search_more_ul_07").hide();
		$(".show_status span:first").html($(this).children().html());
	});
	
	
	$(".more_title1 .close_btn").click(function(){
		$(".search_district").hide();
		$(".more_detail").removeClass("more_detail_area");
		$(".more_title1 .dis_state").html("地区");
		$(".more_detail").html("<dd><a href='javascript:void(0);' onclick='more_detail(this)'><strong>所有</strong></a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>安徽</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>北京</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>重庆</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>福建</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>甘肃</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>广东</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>广西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>贵州</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>海南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>河北</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>黑龙江</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>河南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>湖北</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>湖南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>内蒙古</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>江苏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>江西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>吉林</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>辽宁</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>宁夏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>青海</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>山西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>山东</a></dd><dd><a class='current' href='javascript:void(0);' onclick='more_detail(this)'>上海</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>四川</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>天津</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>西藏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>新疆</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>云南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>浙江</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>陕西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>台湾</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>香港</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>澳门</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>海外</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>其他</a></dd>");
	});
	
});



function change(ele){
	p=$(ele).offset();
	var $left = p.left + "px";
	var $top = p.top + 20 + "px";
	$(".search_district").show().css("left",$left).css("top",$top);
	$(".more_title1 .dis_state").html("地区");
}

function address(){
	$(".more_detail").removeClass("more_detail_area");
	$(".more_detail").html("<dd><a href='javascript:void(0);' onclick='more_detail(this)'><strong>所有</strong></a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>安徽</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>北京</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>重庆</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>福建</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>甘肃</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>广东</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>广西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>贵州</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>海南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>河北</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>黑龙江</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>河南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>湖北</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>湖南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>内蒙古</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>江苏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>江西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>吉林</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>辽宁</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>宁夏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>青海</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>山西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>山东</a></dd><dd><a class='current' href='javascript:void(0);' onclick='more_detail(this)'>上海</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>四川</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>天津</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>西藏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>新疆</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>云南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>浙江</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>陕西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>台湾</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>香港</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>澳门</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>海外</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>其他</a></dd>");
	$(".dis_state").html("地区");
}

function more_detail(ele){
	$(".more_detail").addClass("more_detail_area");
	$(".more_title1 .dis_state").html("<a href='javascript:void(0);' class='address' onclick='address()'>地区</a><em class='space'>&gt;</em>" + "<span>" + $(ele).html() + "</span>");
	$(".more_detail").html("<dd><a href='javascript:void(0);' onclick='more_detail_area(this)'><strong>所有</strong></a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>黄浦区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>卢湾区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>徐汇区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>长宁区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>静安区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>普陀区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>闸北区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>虹口区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class='current'>杨浦区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>闵行区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>宝山区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>嘉定区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>浦东新区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>金山区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>松江区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>青浦区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>南汇区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>奉贤区</a></dd><dd><a href='javascript:void(0);' onclick='more_detail_area(this)' class=''>崇明县</a></dd>")
};

function more_detail_area(ele){
	$(".search_tab .tab_l ul.tab_list li:first").html($(".more_title1 .dis_state span").html() + "-" + $(ele).html() + "<a href='javascript:void(0)' class='change' onclick='change(this)'>[切换]</a>");
	$(".more_title1 .dis_state span").html();
	$(".search_district").hide();
	$(".more_detail").removeClass("more_detail_area");
	$(".more_detail").html("<dd><a href='javascript:void(0);' onclick='more_detail(this)'><strong>所有</strong></a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>安徽</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>北京</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>重庆</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>福建</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>甘肃</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>广东</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>广西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>贵州</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>海南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>河北</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>黑龙江</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>河南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>湖北</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>湖南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>内蒙古</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>江苏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>江西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>吉林</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>辽宁</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>宁夏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>青海</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>山西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>山东</a></dd><dd><a class='current' href='javascript:void(0);' onclick='more_detail(this)'>上海</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>四川</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>天津</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>西藏</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>新疆</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>云南</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>浙江</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>陕西</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>台湾</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>香港</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>澳门</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>海外</a></dd><dd><a class='' href='javascript:void(0);' onclick='more_detail(this)'>其他</a></dd>");
};