/**
 * 获取选中的值
 * 
 * @param id
 * @return
 */
function getValueOfSelect(id) {
	var objs = document.getElementById(id);
	if(objs==null){
 		return;
 	}
 	if(objs.selectedIndex>=0){
 		return objs.options[objs.selectedIndex].value;
 	}
 	return "";
	 
}

/**
 * 动态加载js，css文件
 */
function loadjscssfile(filename,filetype){

    if(filetype == "js"){
        var fileref = document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src",filename);
    }else if(filetype == "css"){
    
        var fileref = document.createElement('link');
        fileref.setAttribute("rel","stylesheet");
        fileref.setAttribute("type","text/css");
        fileref.setAttribute("href",filename);
    }
   if(typeof fileref != "undefined"){
        document.getElementsByTagName("head")[0].appendChild(fileref);
    }
}


/**
 * 获取url的参数
 */
function getQueryString(name) 
{ 
    // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空
    if(location.href.indexOf("?")==-1 || location.href.indexOf(name+'=')==-1) 
    { 
        return ''; 
    } 
  
    // 获取链接中参数部分
    var queryString = location.href.substring(location.href.indexOf("?")+1); 
  
    // 分离参数对 ?key=value&key2=value2
    var parameters = queryString.split("&"); 
  
    var pos, paraName, paraValue; 
    for(var i=0; i<parameters.length; i++) 
    { 
        // 获取等号位置
        pos = parameters[i].indexOf('='); 
        if(pos == -1) { continue; } 
  
        // 获取name 和 value
        paraName = parameters[i].substring(0, pos); 
        paraValue = parameters[i].substring(pos + 1); 
  
        // 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格
        if(paraName == name) 
        { 
            return unescape(paraValue.replace(/\+/g, " ")); 
        } 
    } 
    return ''; 
};

/**
 * 查询用户参加的集集组
 * 
 * @param userId
 * @return
 */
function findUserGroup(userId){
	$.ajax({
		type:'POST',
		url:'/group/findUserGroup.jspa',
		data:({"userId":userId
	      }), 
		success:function(json){
			var html = $(json);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			$("#user-group").html(html);
		}
	});
}

/**
 * 查询共同好友
 */
function findCommonFirends(userId){
	$.ajax({
		type:'POST',
		url:'/follow/findCommonFriends.jspa',
		data:({"userId":userId,"pageSize":10
	      }), 
		success:function(json){
			var html = $(json);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			$("#common_friends").html(html);
		}
	});
}

/**
 * 查询好友
 * 
 * @param selector
 */
function findFirends(userId){
	$.ajax({
		type:'POST',
		url:'/follow/findFirends.jspa',
		data:({"userId":userId,"pageSize":10
	      }), 
		success:function(json){
			var html = $(json);
			prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			$("#friends").html(html);
		}
	});
}

/**
 * 删除评论
 */
function deleteComment(commentId,id){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/status/deleteComment.jspa',
		data:({"commentId":commentId
		      }), 
		success:function(data){
			if(data.flag==0){
				hide_comment_del();
				$(id).remove();
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 加载生活小区
 * 
 * @param selector
 * @param provinceIdSelect
 * @return
 */
function prepareLivingCommunity(selector,provinceIdSelect){
	selector.unautocomplete();
	selector.autocomplete("/user/findLivingCommunityNames.jspa",
	{
		extraParams: { district: function() { return selector.siblings().filter(".last").find("option:selected").val();}}
	
	}).result(function(event, row, formatted) {
		provinceIdSelect.val(row[1]);
	});
}

function preparePostStatus(contextArea,button){
	contextArea.focus(function(){
		contextArea.addClass("hove");
	}).blur(function(){
		contextArea.removeClass("hove");
	}).keyup(function(){
		var content = $(this).val();
		if(content!=""){
			button.addClass("btnh");
		}else{
			button.removeClass("btnh");
		}
	}).focus(function(){
		var content = $(this).val();
		if(content!=""){
			button.addClass("btnh");
		}else{
			button.removeClass("btnh");
		}
	});
	
	button.click(function(){
		if($(this).attr("class")=='btn btnh'){
			var options = { 
					cache : false,
					dataType:"json",	
					type :"post",
					async:false,
					success: function(data) { 
					  if(data.flag == 0){
						  closeImgUploadLayer();
						  contextArea.val("");
						  contextArea.removeClass("hove");
						  button.removeClass("btnh");
						  $("#post_status_media_type").val(null);
						  $("#post_status_media_url").val(null);
						  
						  contextArea.next().addClass('dis');
						  	/** 判断发布成功 * */
								setTimeout(function(){
									contextArea.next().removeClass('dis');
									contextArea.focus();
								},500);
							
						/** 加载最新的一条微薄 * */
						 getNewestStatus(data.data);
					  }else{
					  	alert(data.msg);
					  }
					}
				};
				var $form = $("#post_status_form");
				$form.attr('action','/status/postStatus.jspa');
		        $form.ajaxSubmit(options);
		}
	});
	
}

/**
 * 预处理用户提示层
 * 
 * @param selector
 */
function prepareUserCardTip(selector){
	
	selector.each(function(){
		$(this).poshytip({
			alignY:'top',
			bgImageFrameSize:3,
			content: function(updateCallback) {
				$.ajax({
					   cache:false,
					   type: "POST",
					   url: "/user/getUserCard.jspa?userId="+$(this).attr("uid"),
					   success:function(data){
					   		if(data != null){
					   			updateCallback(data);
					   		}
					   }
					});
			}
		});
	});
	
	/*
	 * selector.tooltip({ //effect: 'slide', tipClass:'W_layer_people',
	 * predelay:500, delay:150, offset:[-195,100], onBeforeShow:function(){ var
	 * uid = this.getTrigger().attr("uid"); $.ajax({ context:this.getTip(),
	 * cache:false, type: "POST", url: "/user/getUserCard.jspa?userId="+uid,
	 * success:function(data){ if(data != null){ $(this).html(data); } } }); }
	 * });
	 */
	// $("#LetterDialog").overlay({closeOnClick:false, mask:{color:'#000',
	// opacity:0.5}});//创建用户组提示框
	// $("#LetterDialog").draggable({cancel: ".LetterDialogContent"});
	// 旧版的用户提示信息层，有可能有定位错误的情况。
	/*
	 * selector.each(function(i){ var _this = $(this); var _thisParent='';
	 * //区分链接在a,img上 dom结构有区别 if(_this.is("a")){
	 * if(_this.parent(".person_name").size()<=0){ _this.wrap('<div
	 * class="person_name"></div>'); } _thisParent=_this.parent(); }else
	 * if(_this.is("img")){ _thisParent=_this.parent().parent(); }
	 * _thisParent.append('<div class="ij_dr_inforbox" style="left: 0pt;
	 * display: none;"></div>'); _thisParent.hover(function(){
	 * _thisParent.css({"z-index":"9998","position":"relative"}); var divInfo =
	 * _thisParent.find(".ij_dr_inforbox"); var divInfoSub =
	 * _thisParent.find(".ij_dr_inforbox>.name_card"); if(divInfoSub.size()>0){
	 * divInfo.show(); }else{ divInfo.show(); var userId="",nickName='',url="";
	 * if (typeof(_this.attr("uid"))!="undefined"){ userId=_this.attr("uid");
	 * url = "/user/getUserCard.jspa?userId=" +userId; }else{
	 * nickName=_this.attr("nick-name"); url =
	 * "/user/getUserCard.jspa?nickName=" + encodeURIComponent(nickName); }
	 * $.ajax({ cache:false, type: "POST", url: url, success:function(data){
	 * alert(data); } }); $.ajax({ cache : false, type: "POST", url: url,
	 * success:function(data){ //_thisParent.append(data); // alert(data);
	 * divInfo.html(data); _this.attr("title",""); var
	 * height=divInfo.css("height"); if(height=="auto"){ height=-149; }else{
	 * height=-parseFloat(height)-8; } divInfo.css("top",-divInfo.height()-5);
	 * divInfo.css("left",0); }}); } },function(){ var divInfo =
	 * _thisParent.find(".ij_dr_inforbox");
	 * divInfo.hide();_thisParent.css({"z-index":"9995","position":"static"});
	 * }); });
	 */
}

/**
 * 关注用户
 * 
 * @param userId
 * @return
 */
function followUser(userId){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/follow/followUser.jspa',
		data:({"userId":userId
		      }), 
		success:function(data){
			if(data.flag==0){
				alert(data.msg);
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 拒绝关注
 * 
 * @param userId
 */
function refuseFollow(userId){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/follow/refuseFollow.jspa',
		data:({"userId":userId
		      }), 
		success:function(data){
			if(data.flag==0){
				alert(data.msg);
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 取消关注
 * 
 * @param userId
 */
function followCancel(userId,reload){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/follow/followCancel.jspa',
		data:({"userId":userId
		      }), 
		success:function(data){
			if(data.flag==0){
				alert("取消成功！");
				if(reload){
					location.reload();
				}
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 退出
 * 
 * @return
 */
function logOut() {

	$.ajax({
		cache : false,
		type : "POST",
		url : "/user/logOut.jspa",
		dataType : 'json',
		success : function(json) {
		},
		complete : function() {
			// Handle the complete event
			$.cookie("login_username", null, {
				path : "/"
			});
			$.cookie("mc_uuid", null, {
				path : "/"
			});
			$.cookie("LOGIN_SELF_FLAG", null, {
				path : "/"
			});

			location.href = "/";
		}
	});

	// $.cookie("login_username", null, {path: "/"});
	// $.cookie("mc_uuid", null, {path: "/"});

	// location.href = "/microblog/logout.jspa";
}

function chooseFace(id,text){
	$(id).insertAtCaret(text);
	hide_faces(); 
	$(id).focus();
}

function findRecommendPerson(){
	$.ajax({
		type:'POST',
		url:'/user/recommendPerson.jspa',
		success:function(data){
			var html = $(data);
			$("#recommend_person_list").html(html);
		}
	});
}


// 发表微博限制字数1
function displaySpareNumber(_this,size,id)
{
    var len=_this.value.replace(/[^\x00-\xff]/gi,'xx').length/2;
    // var len=_this.value.length;
    var snum=Math.floor(parseInt(size)-len);   
    var html = "";
    if(snum<0)
    {   
    	html = "已超出<font color='red'>"+Math.abs(snum)+"</font>字";
    }else{
    	html = "还可以输入"+snum+"字";
    }
    $("#"+id).html(html);
}


$(document).ready(function(){
	/**
	 * 选中表情
	 */
	$("[name='face_btn']").click(function(){
		var text = "["+$(this).attr('text')+"]";
		var type = $(this).attr("areaType");
		if(type=="comment"){
			var statusId = $(this).attr("statusId");
			chooseFace("#comment_content_"+statusId,text);
		}else if(type=="reply"){
			var commentId= $(this).attr("commentId");
			chooseFace("#reply_content_"+commentId,text);
		}else if(type=="mail"){
			chooseFace("#mail_content",text);
		}else{
			chooseFace("#text",text);
		}
	});
});

/**
 * 判断字符串长度
 * @param str
 * @return
 */
function strlen(str){  
    var len = 0;  
    for (var i=0; i<str.length; i++) {   
     var c = str.charCodeAt(i);   
    //单字节加1    
     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {   
       len++;   
     }   
     else {   
      len+=2;   
     }   
    }   
    return len;  
}  

