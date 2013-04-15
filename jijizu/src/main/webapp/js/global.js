// JavaScript Document


<!-- Tab菜单 -->
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var tab=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  tab.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}
<!-- 照片上传成功 -->
function upload_btn(){
	$(".flash_up .flash_content .upload_content ul li .upload_loading").hide();
	self.location='相册上传完成.html'
};
<!-- 个人资料部分没有100%完成时 -->
function Information(){
	if($(".W_person_info .nameBox .icon_bed span").html()!=="100%"){
		$(".W_person_info .nameBox .icon_bed .tips").show();
	}
	else{
		$(".W_person_info .nameBox .icon_bed .tips").hide();
	}
};
function InformationHide(){
	$(".W_person_info .nameBox .icon_bed .tips").hide();
};


<!-- 第一次访问弹出框 -->
function firstLoad(){
	var maxHeight= $(document).height()+"px";
	var maxWidth= $(document).width()+"px";
	$(".W_layer").show().width(maxWidth).height(maxHeight);
	$(".W_layer .tips_01 a").click(function(){
		$(".W_layer .tips_02").show();
		$(".W_layer .tips_01").hide();
	});
	$(".W_layer .tips_02 a").click(function(){
		$(".W_layer .tips_03").show();
		$(".W_layer .tips_02").hide();
	});
	$(".W_layer .tips_03 a").click(function(){
		$(".W_layer .tips_04").show();
		$(".W_layer .tips_03").hide();
	});
	$(".W_layer .tips_04 a").click(function(){
		$(".W_layer .tips_04, .W_layer").hide();
	});
};

function delMessageList(){
	$(".bread_crumbs, .message_wrap .search_title .operation_btn").hide();
	$(".message_wrap .search_title .del_operation, .message_wrap .search_title .del_operation .operation_btn").show();
}

function delMessageList_confirm(){
	$(".private_dialogue").hide();
	$(".message_wrap .search_title .del_operation, .message_wrap .search_title .del_operation .operation_btn").hide();
}

function cancelMessageList(){
	$(".message_wrap .search_title .del_operation, .message_wrap .search_title .del_operation .operation_btn").hide();
	$(".bread_crumbs, .message_wrap .search_title .operation_btn").show();
}


<!-- 弹出框 -->


function loading_upimg(){
	$(".loading_icon").hide();
	$(".layer_event_upimg").show()
}


function del_sixin_btn(){	//点击"删除"私信按钮
	jQuery(".pl_content .WB_feed_type:last").detach();
	jQuery('.webox').css({display:'none'});
	jQuery('.background').css({display:'none'});
};


function cancel_select(){	//取消选择-好友管理
	$(".myfollow_list").removeClass("selected");
	$("#add_group_btn, #cancel_friend").removeClass("on");
	$("#add_group_btn span").removeClass("on");
	$(".chosen, #cancel_select").hide();
};


function del_firendbox(){	//删除好友确认按钮
	hidecancel_friend_box();
	$(".mylistBox .selected").removeClass("selected");
	$(".tab_normal .bar_left p.chosen, .cancel_select").hide();
	$("#add_group_btn, #cancel_friend, .tab_normal .bar_left a.W_btn_c_disable span").removeClass("on");
	
};

function new_myfollow(){	//在浮层中创建好友新分组
	$(".myfollow_select .ms_opt .opt").hide();
	$(".myfollow_select .ms_opt .notetxt").show();
};
function cancel_myfollow(){	//在浮层中取消创建好友新分组
	$(".myfollow_select .ms_opt .opt").show();
	$(".myfollow_select .ms_opt .notetxt").hide();
	$(".myfollow_select .ms_opt .error").hide();
};
function ok_myfollow(){	//在浮层中取消创建好友新分组
	var $notetxt = $("#new_notetxt").val();
	if($notetxt == "新分组"){
		//alert("请输入分组名称")
		$(".myfollow_select .ms_opt .error").show();
	}
	else{
		$(".myfollow_select .ms_opt .error").hide();
		$(".myfollow_select .ms_list li:last").after("<li><a href='javascript:void(0)'><input type='checkbox' class='myfollow_W_checkbox'><label title='"+ $("#new_notetxt").val() +"' class='S_txt2'>"+ $("#new_notetxt").val() +"</label></a></li>");
	}
};

<!-- 发起集集组 -->
function notice_title_focus(){
	$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_warn").show();
	$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_del").hide();
	$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_succ").hide();
};
function notice_title_blur(){
	if($("input#notice_title").val()!==""){
		$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_warn").hide();
		$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_del").hide();
		$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_succ").show();
	}
	else{
		$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_warn").hide();
		$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_del").show();
		$(".jjz_left_box .ev_notice_area dl dd .tipbox .M_notice_succ").hide();
	}
};


<!-- 邀请好友 -->
function msn(){
	$(".find_content_box").show()
	$("#msn_box_content").show();
	$("#qq_box_content").hide();
	$("#email_box_content").hide();
	$("#weibo_box_content").hide();
	$("#kaixin_box_content").hide();
	$("#douban_box_content").hide();
	$(".find_big_tab .tab_layer .conResult_sam_sub").hide();
};
function qq(){
	$(".find_content_box").show()
	$("#msn_box_content").hide();
	$("#qq_box_content").show();
	$("#email_box_content").hide();
	$("#weibo_box_content").hide();
	$("#kaixin_box_content").hide();
	$("#douban_box_content").hide();
	$(".find_big_tab .tab_layer .conResult_sam_sub").hide();
};
function email(){
	$(".find_content_box").show()
	$("#msn_box_content").hide();
	$("#qq_box_content").hide();
	$("#email_box_content").show();
	$("#weibo_box_content").hide();
	$("#kaixin_box_content").hide();
	$("#douban_box_content").hide();
	$(".find_big_tab .tab_layer .conResult_sam_sub").hide();
};
function weibo(){
	$(".find_content_box").show()
	$("#msn_box_content").hide();
	$("#qq_box_content").hide();
	$("#email_box_content").hide();
	$("#weibo_box_content").show();
	$("#kaixin_box_content").hide();
	$("#douban_box_content").hide();
	$(".find_big_tab .tab_layer .conResult_sam_sub").hide();
};
function kaixin(){
	$(".find_content_box").show()
	$("#msn_box_content").hide();
	$("#qq_box_content").hide();
	$("#email_box_content").hide();
	$("#weibo_box_content").hide();
	$("#kaixin_box_content").show();
	$("#douban_box_content").hide();
	$(".find_big_tab .tab_layer .conResult_sam_sub").hide();
};
function douban(){
	$(".find_content_box").show()
	$("#msn_box_content").hide();
	$("#qq_box_content").hide();
	$("#email_box_content").hide();
	$("#weibo_box_content").hide();
	$("#kaixin_box_content").hide();
	$("#douban_box_content").show();
	$(".find_big_tab .tab_layer .conResult_sam_sub").hide();
};
function find_people(){
	$(".find_content_box").show()
	$("#msn_box_content").hide();
	$("#qq_box_content").hide();
	$("#email_box_content").hide();
	$("#weibo_box_content").hide();
	$("#kaixin_box_content").hide();
	$("#douban_box_content").hide();
	$(".find_big_tab .tab_layer .conResult_sam_sub").show();
};


<!-- 输入框状态 -->
function publisherTop_box_focus(){
	$("#publisherTop_box").addClass("hover");
};
function publisherTop_box_blur(){
	$("#publisherTop_box").removeClass("hover");
};
function WB_media_expand_textarea_focus(){
	$(".WB_media_expand textarea.W_input").addClass("hover");
};
function WB_media_expand_textarea_blur(){
	$(".WB_media_expand textarea.W_input").removeClass("hover");
};

function input_focus(){
	$(".W_private_letter dl dd input.text").parent().addClass("hover");
	$(".W_private_letter dl dd .M_notice_del").hide();
	if($(".W_private_letter dl dd input.text").val()=="请输入对方昵称"){
		$(".W_private_letter dl dd input.text").val("");
	};
};
function input_blur(){
	$(".W_private_letter dl dd input.text").parent().removeClass("hover");
	if ($(".W_private_letter dl dd input.text").val()==""){
		$(".W_private_letter dl dd input.text").val("请输入对方昵称");
	};
	if($(".W_private_letter dl dd input.text").val()=="请输入分组名称，最多8个字"){
		$(".W_private_letter dl dd .M_notice_del").show();
	};
};

function textarea_focus(){
	$(".W_private_letter dl dd textarea.W_no_outline").parent().addClass("hover");
	if($(".W_private_letter dl dd textarea.W_no_outline").html()=="请输入内容，最多300个字"){
		$(".W_private_letter dl dd textarea.W_no_outline").html("")
	}
};
function textarea_blur(){
	$(".W_private_letter dl dd textarea.W_no_outline").parent().removeClass("hover");
	if($(".W_private_letter dl dd textarea.W_no_outline").html()==""){
		$(".W_private_letter dl dd textarea.W_no_outline").html("请输入内容，最多300个字")
	}
};

function gn_search_focus(){
	$(".gn_search input").parent().removeClass("seach_hover");
	if ($(".gn_search input").val()==""){
		$(".gn_search input").val("找人");
	}
}
function gn_search_blur(){
	$(".gn_search input").parent().addClass("seach_hover");
	if($(".gn_search input").val()=="找人"){
		$(".gn_search input").val("");
	}
}

function hide_faces(){
	$(".layoutContent").hide();
}
function hide_send_pic(){
	$(".layer_send_box").hide();
}


function setCurPost(e,pos){
	 var e=$(e).get(0);

     e.focus();

     if (e.setSelectionRange) {

         e.setSelectionRange(pos, pos);

     } else if (e.createTextRange) {

         var range = e.createTextRange();

         range.collapse(true);

         range.moveEnd('character', pos);

         range.moveStart('character', pos);

         range.select();

     }
}


$(document).ready(function(){
	
	<!-- 好友 -->
	$(window).scroll(function () {
		if($(window).scrollTop() >218){
			$(".tab_normal").css("top","0").css("background","#f4f4f4").css("position","fixed")
			//document.getElementById("tips").style.top = $(window).scrollTop() + "px";
		}
		else{
			$(".tab_normal").css("background","#fff").css("position","inherit")
		}  
	}); 
	
	
	
	<!-- 返回顶部 -->
	var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
		.text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
			$("html, body").animate({ scrollTop: 0 }, 120);
	}), $backToTopFun = function() {
		var st = $(document).scrollTop(), winh = $(window).height();
		(st > 0)? $backToTopEle.show(): $backToTopEle.hide();	
		//IE6下的定位
		if (!window.XMLHttpRequest) {
			$backToTopEle.css("top", st + winh - 166);	
		}
	};
	$(window).bind("scroll", $backToTopFun);
	$(function() { $backToTopFun(); });
	
	
	
	
	<!-- 浮层 -->
	//选择分组
	$(".myfollow_select .ms_list li").click(function(){
		if($(this).children().children("input").attr("checked")==true){
			$(this).children().children("input").removeAttr("checked");
		}
		else{
			$(this).children().children("input").attr("checked","checked");
		}
		
		if($(".myfollow_select .ms_list input:checked").length == 0){
			$(".ms_tit").show();
			$(".tips_succ").hide();
		}
		else{
			$(".ms_tit").hide();
			$(".tips_succ").show();
			$(".myfollow_select .tips_succ p.txt span").html($(".myfollow_select .ms_list input:checked").length);
		}
	});
	
	$("[name='face_status']").click(function(){
		var x= this.offsetLeft;
		var y= this.offsetTop;
		var $left = x - 10 + "px";
		var $top = y + 30 + "px";
		if($(".layoutContent").is(":visible")==true && ($(".layoutContent").attr("type") == "status")){
			$(".layoutContent").hide();
		}else{
			$(".layoutContent").show();
		}
		$(".layoutContent").attr("type","status").css("left",$left).css("top",$top);
		$("[name='face_btn']").attr("areaType","status");
	});
	/////////////////////////////
	
	<!--------------------------------------------------------------------------------------------------------->
	
	$(".conResult_sam dl dt a").mouseover(function(){
		var x= this.offsetLeft;
		var y= this.offsetTop;
		var $left = x - 54 + "px";
		var $top = y - 300 + "px";
		var $left_val = x;
		var $top_val = y;
		if(y  < 352){
			var $left = x - 54 + "px";
			var $top = y + 20 + "px";
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("bottom","220px").css("background-position","-433px -215px");
		}
		else{
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("bottom","-9px").css("background-position","-433px -223px");
		}		
	});
	$(".conResult_sam dl dt a").mouseout(function(){
		$(".W_layer_people").hide();
		$(".name_card .arrow").css("bottom","-9px").css("background-position","-433px -223px");
	});
	<!--------------------------------------------------------------------------------------------------------->
	$(".find_big_tab .tab_layer .conResult_sam_sub dl dt img, .find_big_tab .tab_layer .conResult_sam_sub dl dd.name a, .conResult_sam dl dd.name a, .evlist_main ul li .evlist_state ul li img, .mylistBox .myfollow_list .info a.S_func1").mouseover(function(){
		var x= this.offsetLeft;
		var y= this.offsetTop;
		var $left = x - 54 + "px";
		var $top = y - 230 + "px";
		var $left_val = x;
		var $top_val = y;
		if(y  < 352){
			var $left = x - 54 + "px";
			var $top = y + 20 + "px";
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("bottom","220px").css("background-position","-433px -215px");
		}
		else{
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("bottom","-9px").css("background-position","-433px -223px");
		}		
	});
	$(".find_big_tab .tab_layer .conResult_sam_sub dl dt img, .find_big_tab .tab_layer .conResult_sam_sub dl dd.name a, .conResult_sam dl dd.name a, .evlist_main ul li .evlist_state ul li img, .mylistBox .myfollow_list .info a.S_func1").mouseout(function(){
		$(".W_layer_people").hide();
		$(".name_card .arrow").css("bottom","-9px").css("background-position","-433px -223px");
	});
	<!--------------------------------------------------------------------------------------------------------->
	$(".WB_face img, .WB_detail .WB_info a, .WB_detail .WB_friend dl dt img, .WB_detail .WB_friend dl dd.name a, .pl_group_main ul li a.name_group, .Comment_detail a.at_name, .myfollow_list .face img").mouseover(function(){
		var x= this.offsetLeft;
		var y= this.offsetTop;
		var $left = x - 54 + "px";
		var $top = y - 220 + "px";
		var $left_val = x;
		var $top_val = y;
		if(y  < 352){
			var $left = x - 54 + "px";
			var $top = y + 50 + "px";
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("bottom","220px").css("background-position","-433px -215px");
		}
		else{
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("bottom","-9px").css("background-position","-433px -223px");
		}		
	});
	$(".WB_face img, .WB_detail .WB_info a, .WB_detail .WB_friend dl dt img, .WB_detail .WB_friend dl dd.name a, .pl_group_main ul li a.name_group, .Comment_detail a.at_name, .myfollow_list .face img").mouseout(function(){
		$(".W_layer_people").hide();
		$(".name_card .arrow").css("bottom","-9px").css("background-position","-433px -223px");
	});
	<!--------------------------------------------------------------------------------------------------------->
	$(".person_list .name_pic img, .person_list .name_pic .name a").mouseover(function(){
		var x= 0;
		var y= 0;
		var e=this;
		  while(e=e.offsetParent)
			{
			   x   +=   e.offsetLeft;  
			   y   +=   e.offsetTop;
			}
		var $left = x - 54 + "px";
		var $top = y - 205 + "px";
		var $left_val = x;
		var $top_val = y;
		if($(document).width() - $left_val < 352){
			var $left = x - 150 + "px";
			var $top = y - 205 + "px";
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("left","165px");
		}
		else{
			$(".W_layer_people").show().css("left",$left).css("top",$top);
			$(".name_card .arrow").css("left","65px");
		}			
	});
	$(".person_list .name_pic img, .person_list .name_pic .name a").mouseout(function(){
		$(".W_layer_people").hide();
	});
	<!--------------------------------------------------------------------------------------------------------->
	$(".W_layer_people").mouseover(function(){
		$(".W_layer_people").show();
	});
	$(".W_layer_people").mouseout(function(){
		$(".W_layer_people").hide();
	});
	
	$(".WB_feed_type").mouseover(function(){
		$(this).addClass("hover");
	});
	$(".WB_feed_type").mouseout(function(){
		$(this).removeClass("hover");
	});
	$(".comment_lists dl.comment_list dd").mouseover(function(){
		$(this).addClass("hover");
	});
	$(".comment_lists dl.comment_list dd").mouseout(function(){
		$(this).removeClass("hover");
	});
	$(".comment_lists dl.comment_list dd .info .huifu").click(function(){
		$(this).parent().next().toggle();
	});
	
	
	
	<!-- 好友 -->
	$(".mylistBox .myfollow_list").mouseover(function(){
		$(this).removeClass("myfollow_list").addClass("mylihover");
	});
	$(".mylistBox .myfollow_list").mouseout(function(){
		$(this).removeClass("mylihover").addClass("myfollow_list");
	});
	$(".mylistBox .myfollow_list").click(function(){
		if($(this).hasClass("selected")){
			$(this).removeClass("selected");
			var length=getSelectNum();
			$("#select_num").html(length);
			if(length == 0){
				$("#add_group_btn, #cancel_friend").removeClass("on");
				$("#add_group_btn span").removeClass("on");
				$(".chosen, #cancel_select").hide();
			}
		}
		else{
			$(this).addClass("selected");
			$("#add_group_btn, #cancel_friend").addClass("on");
			$("#add_group_btn span").addClass("on");
			$(".chosen, #cancel_select").show();
			var length=getSelectNum();
			$("#select_num").html(length);
		}
	});
	
	
	
	$(".private_dialogue .private_SRLl .content, .private_dialogue .private_SRLr .content").mouseover(function(){
		$(this).children(".close").show();
	});
	$(".private_dialogue .private_SRLl .content, .private_dialogue .private_SRLr .content").mouseout(function(){
		$(this).children(".close").hide();
	});
	
	
	<!-- 广告 -->
	$(".pl_advertisement").mouseover(function(){
		$(this).children("a.close").show();
	});
	$(".pl_advertisement").mouseout(function(){
		$(this).children("a.close").hide();
	});
	$(".pl_advertisement a.close").click(function(){
		$("#pl_advertisement").hide();
	});
	
	$(".m_user_albumlist dl").mouseover(function(){
		$(this).children().next().next().children().parent().children(".number").hide();
		$(this).children().next().next().children().parent().children(".operation").show();
	});
	$(".m_user_albumlist dl").mouseout(function(){
		$(this).children().next().next().children().parent().children(".number").show();
		$(this).children().next().next().children().parent().children(".operation").hide();
	});
	/*$(".flash_up .flash_title span.btn a, .flash_up .flash_content .upload_btn a").click(function(){
		$(".flash_up .flash_title .default, .flash_up .flash_content .upload_btn").hide();
		$(".flash_up .flash_title .uploaded").show();
		$(".flash_up .flash_content .upload_content").show();
	});
	$(".flash_up .flash_content .upload_content ul li").mouseover(function(){
		$(this).children(".close").show();
		$(this).children(".upload_name").show();
	});
	$(".flash_up .flash_content .upload_content ul li").mouseout(function(){
		$(this).children(".close").hide();
		$(this).children(".upload_name").hide();
	});
	
	$(".flash_up .flash_title span.btn a#upload_btn").click(function(){
		$(".flash_up .flash_content .upload_content ul li .upload_loading").show();
		setTimeout("upload_btn()", 1000 );
	});
	$(".flash_up .flash_content .upload_content ul li .close").click(function(){
		$(".flash_up .flash_title .default, .flash_up .flash_content .upload_btn").show();
		$(".flash_up .flash_title .uploaded").hide();
		$(".flash_up .flash_content .upload_content").hide();
	});*/
	
	$(".m_photo_list_a, .m_photo_list_a dl").mouseover(function(){
		$(this).children().next().next().children().parent().children(".number").hide();
		$(this).children().next().next().children().parent().children(".operation").show();
	});
	$(".m_photo_list_a, .m_photo_list_a dl").mouseout(function(){
		$(this).children().next().next().children().parent().children(".number").show();
		$(this).children().next().next().children().parent().children(".operation").hide();
	});
	
	$(".m_photo_list_a dl.m_selectpic_list dt").click(function(){
		$(this).toggleClass("selected");
		$(".m_browse_menu .menu_box .manage_count em").html($(".m_photo_list_a dl.m_selectpic_list dt.selected").length);
	});
	$(".m_browse_menu .menu_box .manage_check .M_checkbox").click(function(){
		if($(this).attr("checked")){
			$(".pl_content .m_photo_list_a dl.m_selectpic_list dt").addClass("selected");
		}else{
			$(".pl_content .m_photo_list_a dl.m_selectpic_list dt").removeClass("selected");
		}
		$(".m_browse_menu .menu_box .manage_count em").html($(".m_photo_list_a dl.m_selectpic_list dt.selected").length);
	})
	
	$(".M_comment_sm").click(function(){
		$(".m_commentBox .cmnt_area textarea").focus();
	});
	/*$(".pl_content_account .infoblock .info_title fieldset .btns a").click(function(){
		if($(this).html()=="保存"){
			$(this).parent().parent().parent().next().show();
			$(this).parent().parent().parent().next().next().hide();
			$(this).html("编辑");
		}
		else{
			$(this).parent().parent().parent().next().hide();
			$(this).parent().parent().parent().next().next().show();
			$(this).html("保存");
		}
	});*/
	
	$(".pl_content_account .pf_add").click(function(){
		$(this).next().show();
		$(this).hide();
	});
	$(".pl_content_account .pf_item .btn a.cancel, .pl_content_account .pf_item .btn a.save").click(function(){
		//alert($(this).parent().parent().parent().parent().parent().next().html());
		$(this).parent().parent().parent().parent().parent().children("a.pf_add").show();
		$(this).parent().parent().parent().parent().parent().parent().find(".pf_item").show();
		$(this).parent().parent().parent().parent().hide();
	});
	$(".pl_content_account .pf_item .action .icon_edit").click(function(){
		
		$(this).parent().parent().parent().parent().parent().find(".pf_item").hide();
		$(this).parent().parent().parent().parent().parent().children().children("a.pf_add").hide();
		$(this).parent().parent().parent().parent().parent().children().children(".edit_area").show();
		$(this).parent().parent().parent().parent().parent().children().children().children(".pf_item").show();
		
	});
	
	$(".schSearchLayer .sch_letter li a").click(function(){
		$(".schSearchLayer .sch_letter li a").removeClass("on");
		$(this).addClass("on");
		findSchool();
	});

	$(".gn_setting").mouseover(function(){
		$(this).children(".gn_topmenulist_set").show();
	});
	$(".gn_setting").mouseout(function(){
		$(this).children(".gn_topmenulist_set").hide();
	});	
	
	
	////////////////////////视频////////////////////////////
	$(".pl_publisherTop .btnf .icon_detail a.icon_sw_movie, .btnf .icon_detail a.icon_sw_movie").click(function(){
		p=$(this).offset();
		var $left = p.left - 10 + "px";
		var $top = p.top + 30 + "px";
		$(".layer_send_box2").toggle().css("left",$left).css("top",$top);
	});
	$(".icon_detail a[name=icon_sw_movie]").click(function(){
		p=$(this).offset();
		var $left = p.left - 10 + "px";
		var $top = p.top + 30 + "px";
		$(".layer_send_box2").toggle().css("left",$left).css("top",$top);
	});
	
});

function getSelectNum(){
	var length = 0;
	$(".mylistBox").children().each(function(){
		if($(this).hasClass("selected")){
			length++;
		}
	});
	return length;
}
