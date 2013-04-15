$(document).ready(function() {
	
	$(".WB_detail .WB_func .WB_handle .pinglun2").click(function(){
		$(this).parent().parent().next().toggle();
		$(this).parent().parent().next().children(".WB_arrow").css("right","10px");
		$(".WB_media_expand p.btn a").html("回复");
		$(".WB_media_expand textarea.W_input").val("");
		$(".comment_lists").show();
	});
	
	$("[name='reply-btn']").click(function(){
		$(this).parent().parent().next().toggle();
		$(this).parent().parent().next().children(".WB_arrow").css("right","10px");
		var userName = $(this).attr("userName");
		var commentId = $(this).attr("commentId");
		var content = "回复@"+userName+":";
		$("#reply_content_"+commentId).val(content);
		if($("#replay_expand_"+commentId).is(":visible")==true){
			setCurPost($("#reply_content_"+commentId),content.length);
		}
	});
	
	
	/**
	 * 上传图片
	 */
	$("#upload_img").change(function(){
		var filepath=$(this).val(); 
		var extStart=filepath.lastIndexOf(".");
		var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			alert("图片限于bmp,png,gif,jpeg,jpg格式");
			return ;
		} 
		$("#loading").show();
		var options = { 
				cache : false,
				dataType:"data",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.indexOf(':') != -1){
					$("#upload_img").val('');
					if ($("#text").val()==''){
						$("#text").val('分享图片');
						$("#text").focus();
					}
					
					//修复bug：FF6下返回的图片路径包括<div ...，需要特殊处理。
					/*var divPosition = data.indexOf("<div");
					if (divPosition>0){
						data = data.substring(0,data.indexOf("<div"));
					}*/
					$("#post_status_media_url").val(data);
					$("#post_status_media_type").val('MEDIA_TYPE_IMAGE');
					
				  	var nameStart=data.lastIndexOf("/");
				  	var fileName=data.substring(nameStart+1,data.length);
				  	
				  	
				  	$("#imgUrl").attr('src', data);
				  	$("#loading").hide();
				  	$("#laPic").show();
				  }else{
				  	alert(data);
				  }
				}
			};
			$("#status_upload_pic_form").attr('action','/status/fileUploadStatusAjax.jspa');
			$("#status_upload_pic_form").ajaxSubmit(options);
	});
	
	$('#img_upload_layer_close').click(function(){
		closeImgUploadLayer();
	});
	
	$(".thumbnail").click(function(){
		var $this = $(this);
		$this.siblings(".bigImage_box").show();
		var width = $this.siblings(".bigImage_box").find("img").width();
		var rate = 1;
		if(width>440){
			$this.siblings(".bigImage_box").find("img").width(440);	
		}
		var height = $this.siblings(".bigImage_box").find("img").height();
		$this.siblings(".bigImage_box").find("img").height(height);
		$this.siblings(".bigImage_box").hide();
	});
	
	/**
	 * 点击删除评论
	 */
	$("[name='del_status']").click(function(){
		show_sixin_del();
		var statusId = $(this).attr("statusId");
		$("#confirm_del_status").attr("statusId",statusId);
	});
	
	/**
	 * 点击确认删除评论
	 */
	$("#confirm_del_status").click(function(){
		var statusId = $(this).attr("statusId");
		deleteStatus(statusId);
	});
	
	/*$(".addFriend").click(function(){
		var userId = $(this).attr("uid");
		followUser(61);
	});*/
	
	init($(this));
	
});

/**
 * 初始话方法
 */
function init(html){
	prepareCommentFace(html.find("[name='face_comment']"));
	prepareStatusFace(html.find("[name='icon_sw_img']"));
	prepareReplyFace(html.find("[name='face_reply']"));
	prepareCommentBtn(html.find("[name='comment-btn']"));
	prepareFrowardBtn(html.find("[name='forward-btn']"));
	prepareCommentSubmitBtn(html.find("[name='inside']"));
	prepareImg(html);
}

/**
 * 初始化评论提交
 * @param selector
 * @return
 */
function prepareCommentSubmitBtn(selector){
	selector.click(function(){
		var text = $(this).text();
		var statusId = $(this).attr("statusId");
		if(text=='评论' && $("#comment_content_"+statusId).val()==""){
			show_box_none();
			return;
		}
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					  if(text == '评论'){
						loadComments(statusId);  
					  }else{
						alert('转发成功');
						getNewestStatus(data.data);
					  }
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			var $form = $(this).parent().parent().parent();
			if(text == '评论'){
				$form.attr('action','/status/postComment.jspa');
			}else{
				$form.attr('action','/status/forwardStatus.jspa');
			}
			
			$form.ajaxSubmit(options);
	});
}

/**
 * 删除微薄
 * @param statusId
 */
function deleteStatus(statusId){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/status/deleteStatus.jspa',
		data:({"statusId":statusId
		      }), 
		success:function(data){
			if(data.flag==0){
				hide_sixin_del();
				var $ele = $("#div_status_"+data.data);
				if($ele.length==0){
					window.location.reload();
				}else{
					$ele.remove();
				}
			}else{
				alert(data.msg);
			}
		}
	});
}


/**
 * 加载评论
 * @param statusId
 * @return
 */
function loadComments(statusId,page,showPage){
	$.ajax({
		type:'POST',
		url:'/status/loadComment.jspa',
		data:({"statusId":statusId,"pageNum":page,"showPage":showPage
		      }), 
		success:function(data){
			var html = $(data);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			prepareAutoComplete($(html).find("[name='content']"));
			$("#comment_lists_"+statusId).html(html);
		}
	});
}

/**
 * 重新加载评论组件
 * @param selector
 * @return
 */
function prepareCommentBtn(selector){
	selector.click(function(){
		var statusId = $(this).attr("statusId");
		$("#comment_lists_"+statusId).show();
		var showPage = $(this).attr("show-page");
		loadComments(statusId,0,showPage);
		$("#label_comment_"+statusId).hide();
		$("#label_forward_"+statusId).show();	
		var type = $(this).parent().parent().next().attr("type");
		if(type=="comment"){
			$(this).parent().parent().next().toggle();
		}else{
			$(this).parent().parent().next().show();
		}
		$(this).parent().parent().next().attr("type","comment");
		$(this).parent().parent().next().children(".WB_arrow").css("right","10px");
		$(".WB_media_expand p.btn a").html("评论");
		$(".WB_media_expand textarea.W_input").val("");
		$(".comment_lists").show();
	});
}

/**
 * 获取最新的一条微薄
 */
function getNewestStatus(statusId){
	$.ajax({
		type:'POST',
		url:'/status/getNewestStatus.jspa',
		data:({"statusId":statusId
		      }), 
		success:function(json){
			var html = $(json);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			init(html);
			$("#showmore_top").after(html);
		}
	});
}

/**
 * 显示更多微博
 * @return
 */
function showmore(){
	var statusList = $(".pl_content>.WB_feed_type");
	var lastStatus = statusList[statusList.length-1];
	var lastStatusStr = $(lastStatus).attr("id");
	var statusId = lastStatusStr.split("_")[2];
	$.ajax({
		type:'POST',
		url:'/status/showMoreStatus.jspa',
		data:({"statusId":statusId
	      }),
		success:function(json){
			var html = $(json);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			init(html);
			$("#showmore_btn").before(html);
			$("#showmore_btn").remove();
		}
	});
}

function findNewStatus(){
	var statusList = $(".pl_content>.WB_feed_type");
	var lastStatus = statusList[0];
	var lastStatusStr = $(lastStatus).attr("id");
	var statusId = lastStatusStr.split("_")[2];
	$.ajax({
		type:'POST',
		url:'/status/findNewStatus.jspa',
		data:({"statusId":statusId
		      }), 
		success:function(data){
			$("#showmore_top").hide();
			var html = $(data);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			init(html);
			$("#showmore_top").after(html);
		}
	});
}

function countNewStatus(){
	var statusList = $(".pl_content>.WB_feed_type");
	var lastStatus = statusList[0];
	var lastStatusStr = $(lastStatus).attr("id");
	var statusId = lastStatusStr.split("_")[2];
	$.ajax({
		type:'POST',
		url:'/status/countNewStatus.jspa',
		data:({"statusId":statusId
		      }), 
		success:function(data){
			$("#new-status-count").html(data);
			if(data>0){
				$("#showmore_top").show();
			}else{
				$("#showmore_top").hide();
			}
		}
	});
}



/**
 * 初始化转发按钮
 * @param selector
 * @return
 */
function prepareFrowardBtn(selector){
	selector.click(function(){
		var type = $(this).parent().parent().next().attr("type");
		if(type=="forward"){
			$(this).parent().parent().next().toggle();
		}else{
			$(this).parent().parent().next().show();
		}
		$(this).parent().parent().next().attr("type","forward");
		
		var statusId = $(this).attr("statusId");
		$(this).parent().parent().next().children(".WB_arrow").css("right","55px");
		$(".WB_media_expand p.btn a").html("转发");
		$(".comment_lists").hide();
		var content = $(this).attr("content");
		$("#comment_content_"+statusId).val(content);
		$("#label_forward_"+statusId).hide();
		$("#label_comment_"+statusId).show();
		$("#comment_content_"+statusId).focus();
		if($(this).parent().parent().next().is(":visible")==true){
			setCurPost($("#comment_content_"+statusId),0);
		}
		$("#forward-status-form-"+statusId).find("#post_status_source_type_detail")
		.val("SOURCE_TYPE_DETAIL_GROUP");
	});
}

/**
 * 初始化评论表情
 * @param selector
 * @return
 */
function prepareCommentFace(selector){
	selector.click(function(){
		var p=$(this).offset();
		var $left = p.left - 10 + "px";
		var $top = p.top + 30 + "px";
		if($(".layoutContent").is(":visible")==true && ($(".layoutContent").attr("type") == "comment")){
			$(".layoutContent").hide();
		}else{
			$(".layoutContent").show();
		}
		$(".layoutContent").attr("type","comment").css("left",$left).css("top",$top);
		var statusId = $(this).attr("statusId");
		$("[name='face_btn']").attr("areaType","comment").attr("statusId",statusId);
	});
}

/**
 * 加载回复表情
 * @param selector
 * @return
 */
function prepareReplyFace(selector){
	selector.click(function(){
		var p=$(this).offset();
		var $left = p.left - 10 + "px";
		var $top = p.top + 30 + "px";
		if($(".layoutContent").is(":visible")==true && ($(".layoutContent").attr("type") == "reply")){
			$(".layoutContent").hide();
		}else{
			$(".layoutContent").show();
		}
		$(".layoutContent").attr("type","reply").css("left",$left).css("top",$top);
		var commentId = $(this).attr("commentId");
		$("[name='face_btn']").attr("areaType","reply").attr("commentId",commentId);
	});
}

/**
 * 初始话图片操作
 * @return
 */
function prepareImg(html){
	//旋转图片
	$(html).find(".z_left").click(function(){
		var $this = $(this);
		$this.parent().siblings().find("*").rotateLeft(90);
		var width = $this.parent().siblings().find("*").width();
		if(width>400){
			$this.parent().siblings().find("*").width(400);
		}	
	})
	$(html).find(".z_right").click(function(){
		var $this = $(this);
		$this.parent().siblings().find("*").rotateRight(90);
		var width = $this.parent().siblings().find("*").width();	
		if(width>400){
			$this.parent().siblings().find("*").width(400);
		}	
	})
	//收起
	$(html).find(".sq,").click(function(){
		var $this = $(this);
		$this.parent().parent().parent().addClass("dis_none");	
		$this.parent().parent().parent().siblings(".rot_box").removeClass("dis_none");
	});
	$(html).find(".js_s_bimg").click(function(){
		var $this = $(this);
		$this.parent().parent().addClass("dis_none");
		$this.parent().parent().siblings(".rot_box").removeClass("dis_none");	
	})
	$(html).find(".simg").click(function(){
		var $this = $(this);
		$this.parent().addClass("dis_none");
		$this.parent().siblings(".bigImage_box").removeClass("dis_none");
		var width = $this.parent().siblings(".bigImage_box").find("img").width();
		if(width>400){
			$this.parent().siblings(".bigImage_box").find("img").width(400)	
		}
		var height = $this.siblings(".jy_big_img").find("img").height();
		$this.siblings(".jy_big_img").find("img").height(height)
	});
	$(html).find(".simg_01").click(function(){
		var $this = $(this);
		
		$this.addClass("dis_none");
		$this.siblings(".jy_big_img").removeClass("dis_none");	
		var width = $this.siblings(".jy_big_img").find("img").width();
		if(width>400){
			$this.siblings(".jy_big_img").find("img").width(400)	
		}
		var height = $this.siblings(".jy_big_img").find("img").height();
		$this.siblings(".jy_big_img").find("img").height(height)
	});
	$(html).find(".js_s_bimg_01").click(function(){
		var $this = $(this);
		
		$this.parent().addClass("dis_none");
		$this.parent().siblings(".simg_01").removeClass("dis_none");
		
	});
	$(html).find(".sq_01").click(function(){
		var $this = $(this);
		$this.parent().parent().addClass("dis_none");
		$this.parent().parent().siblings(".simg_01").removeClass("dis_none");	
	});
}

/**
 * 初始化微博表情
 * @param selector
 * @return
 */
function prepareStatusFace(selector){
	selector.click(function(){
		var x= this.offsetLeft;
		var y= this.offsetTop;
		var $left = x - 10 + "px";
		var $top = y + 30 + "px";
		if($(".layer_send_box").is(":visible")==true){
			$(".layer_send_box").hide();
		}else{
			$(".layer_send_box").show();
		}
		$(".layer_send_box").css("left",$left).css("top",$top);
	});
}

function prepareAutoComplete(selector){
	selector.autocomplete("/follow/findAtFirendsJson.jspa", {formatItem: function(row, i, max) {
		return "<img width='30' height='30' src='"+row[1]+"'/><font name='at-user-name'>"+row[0]+"</font>";
	},at:true});
}

function closeImgUploadLayer(){
	$(".layer_send_box").hide();
	$("#post_status_media_url").val('');
	$("#post_status_media_type").val('');
	$("#imgUrl").attr('src', '');
}

