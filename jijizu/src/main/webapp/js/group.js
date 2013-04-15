$(document).ready(function(){
	
	/**
	 * 拒绝用户参加集集组
	 */
	$("#refuse-group-user-btn").click(function(){
		var groupId = $(this).attr("groupId");
		var userId = $(this).attr("userId");
		$.ajax({
			type:'POST',
			url:'/group/refuseGroupUser.jspa',
			dataType:'json',
			 data:({"groupId":groupId,
				 	"userId":userId
			      }),  
			success:function(json){
				if(json.flag == 0){
					location.reload();
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
	/**
	 * 删除集集组
	 */
	$("#delete-group-btn").click(function(){
		
		var groupId = $(this).attr("groupId");
		$.ajax({
			type:'POST',
			url:'/group/deleteGroup.jspa',
			dataType:'json',
			 data:({"groupId":groupId
			      }),  
			success:function(json){
				if(json.flag == 0){
					location.href="/group/"
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
	/**
	 * 取消参加集集组
	 */
	$("#cancel-enter-group-btn").click(function(){
		
		var groupId = $(this).attr("groupId");
		$.ajax({
			type:'POST',
			url:'/group/cancelEnterGroup.jspa',
			dataType:'json',
			 data:({"groupId":groupId
			      }),  
			success:function(json){
				if(json.flag == 0){
					location.reload();
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
	/**
	 * 参加集集组
	 */
	$("#enter-group-btn").click(function(){
		
		var name = $("[name='name']");
		if(name.val()==""){
			alert("请填写您的真实姓名");
			name.focus();
			return;
		}
		
		var childName = $("[name='childName']");
		if(childName.val()==""){
			alert("请填写参与宝宝姓名");
			childName.focus();
			return;
		}
		
		var mobile = $("[name='mobile']");
		if(mobile.val()==""){
			alert("请填写联系手机");
			mobile.focus();
			return;
		}
		
		var address = $("[name='address']");
		if(address.val()==""){
			alert("请填写联系地址");
			address.focus();
			return;
		}
		
		var adultNum = $("#adultNum");
		if(typeof(adultNum.val())!="undefined"){
			if(adultNum.val() == ""){
				alert("请填写要参与的大人人数！");
				adultNum.focus();
				return;
			}else{
				if(!isNumber(adultNum.val()) || adultNum.val()=="0"){
					alert("要参与的大人人数必须为正整数！");
					adultNum.focus();
					return;
				}
			}
		}
		
		var childIds = "";
		$("[name='childIds']:checked").each(function(){
			var childId = $(this).val();
			childIds+=childId+",";
		});
		if(childIds == ""){
			if(!confirm("确定不带孩子参加该集集组吗？")){
				return;
			}
		}
		
		
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag==0){
					location.reload();
				  }else if(data.flag==-3){
					  if(confirm("您所报名的人数已超过该集集组的允许参加人数上限！ \r\n不过您可以加入该集集的等待区域，如果有人取消参加该集集组，届时您将优先加入该集集组。\r\n 点击'确认'加入该集集组的等待区域或点击'取消'修改报名人数后重新进行报名！")){
						  enterWaitList();
					  }
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			$("#enter-group-form").attr('action','/group/enterGroup.jspa');
			$("#enter-group-form").ajaxSubmit(options);
	});
	
	/**
	 * 修改集集组
	 */
	$("#update-group-btn").click(function(){
		if(!validateAddGroupForm()){
			return;
		}
		
		var enterType = $("[name='enterType']:checked").val();
		var enterTypeDetail = "";
		if(enterType == "GROUP_ENTER_TYPE_FRIENDS"){
			enterTypeDetail = $("[name='enterTypeDetail']").val();
		}
		
		var posterImgUrl = $("[name='posterImgUrl']").val();
		if(posterImgUrl.indexOf("/images/event_img_bigl.jpg") != -1){
			posterImgUrl = "";
		}
		
		$.ajax({
			type:'POST',
			url:'/group/updateGroup.jspa',
			dataType:'json',
			 data:({"startMinute":$("[name='startMinute']").val(),
				   "endDay":$("[name='endDay']").val(),
				   "second":$("[name='second']").val(),
				   "groupDesc":$("[name='groupDesc']").val(),
				   "posterImgUrl":posterImgUrl,
				   "endMinute":$("[name='endMinute']").val(),
				   "enterType":enterType,
				   "cost":$("[name='cost']").val(),
				   "endHour":$("[name='endHour']").val(),
				   "province":$("[name='province']").val(),
				   "city":$("[name='city']").val(),
				   "district":$("[name='district']").val(),
				   "fee":$("[name='fee']:checked").val(),
				   "title":$("[name='title']").val(),
				   "address":$("[name='address']").val(),
				   "startDay":$("[name='startDay']").val(),
				   "startHour":$("[name='startHour']").val(),
				   "first":$("[name='first']").val(),
				   "groupId":$("[name='groupId']").val(),
				   "enterTypeDetail":enterTypeDetail,
				   "applyEndDay":$("[name='applyEndDay']").val(),
				   "applyEndHour":$("[name='applyEndHour']").val(),
				   "applyEndMinute":$("[name='applyEndMinute']").val(),
				   "startAge":$("[name='startAge']").val(),
				   "endAge":$("[name='endAge']").val(),
				   "applyNum":$("[name='applyNum']").val(),
				   "people":$("[name='people']:checked").val(),
				   "onlyOne":$("[name='onlyOne']:checked").val(),
				   "parentsLimit":$("[name='parentsLimit']").val()
			      }),  
			success:function(json){
				if(json.flag == 0){
					alert("修改成功");
					location.href="/group/"+json.data
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
	/**
	 * 发起集集组
	 */
	$("#add-group-btn").click(function(){
		
		if(!validateAddGroupForm()){
			return;
		}
		
		var enterType = $("[name='enterType']:checked").val();
		var enterTypeDetail = "";
		if(enterType == "GROUP_ENTER_TYPE_FRIENDS"){
			enterTypeDetail = $("[name='enterTypeDetail']").val();
		}
		
		$.ajax({
			type:'POST',
			url:'/group/addGroup.jspa',
			dataType:'json',
			 data:({"startMinute":$("[name='startMinute']").val(),
				   "endDay":$("[name='endDay']").val(),
				   "second":$("[name='second']").val(),
				   "groupDesc":$("[name='groupDesc']").val(),
				   "posterImgUrl":$("[name='posterImgUrl']").val(),
				   "endMinute":$("[name='endMinute']").val(),
				   "enterType":enterType,
				   "cost":$("[name='cost']").val(),
				   "endHour":$("[name='endHour']").val(),
				   "province":$("[name='province']").val(),
				   "city":$("[name='city']").val(),
				   "district":$("[name='district']").val(),
				   "fee":$("[name='fee']:checked").val(),
				   "title":$("[name='title']").val(),
				   "address":$("[name='address']").val(),
				   "startDay":$("[name='startDay']").val(),
				   "startHour":$("[name='startHour']").val(),
				   "first":$("[name='first']").val(),
				   "enterTypeDetail":enterTypeDetail,
				   "applyEndDay":$("[name='applyEndDay']").val(),
				   "applyEndHour":$("[name='applyEndHour']").val(),
				   "applyEndMinute":$("[name='applyEndMinute']").val(),
				   "startAge":$("[name='startAge']").val(),
				   "endAge":$("[name='endAge']").val(),
				   "applyNum":$("[name='applyNum']").val(),
				   "people":$("[name='people']:checked").val(),
				   "onlyOne":$("[name='onlyOne']:checked").val(),
				   "parentsLimit":$("[name='parentsLimit']").val()
			      }),  
			success:function(json){
				if(json.flag == 0){
					alert("创建成功");
					location.href="/group/"+json.data
				}else{
					alert(json.msg);
				}
			}
		});
	});
});

function enterWaitList(){
	var options = { 
			cache : false,
			dataType:"json",	
			type :"post",
			async:false,
			success: function(data) { 
			  if(data.flag==0){
				location.reload();
			  }else{
				  alert(data.msg);
			  }
			}
		};
	$("#enter-group-form").attr('action','/group/enterWaitList.jspa');
	$("#enter-group-form").ajaxSubmit(options);
}

function validateAddGroupForm(){
	
	var title = $("#title");
	if(title.val() == ""){
		alert("请输入标题");
		title.focus();
		return false;
	}
	
	var startDay = $("#startDay");
	if(startDay.val() == ""){
		alert("请选择活动开始时间");
		return false;
	}
	
	var endDay = $("#endDay");
	if(endDay.val() == ""){
		alert("请选择活动结束时间");
		return false;
	}
	
	var applyEndDay = $("#applyEndDay");
	if(applyEndDay.val() == ""){
		alert("请选择报名截止时间");
		return false;
	}
	
	var groupDesc = $("#groupDesc");
	if(groupDesc.val()== ""){
		alert("请输入描述");
		groupDesc.focus();
		return false;
	}
	if(groupDesc.val().length > 2000){
		alert("集集组描述过长");
		groupDesc.focus();
		return false;
	}
	
	var first = $("#first");
	var second = $("#second");
	var third = $("#third");
	if(first.val() == "" || second.val() == "" || third.val()==""){
		alert("请选择参与区域");
		third.focus();
		return false;
	}
	
	var address = $("#address");
	if(address.val() == ""){
		alert("请选择参与地址");
		address.focus();
		return false;
	}
	
	var cost = $("#cost");
	if($("#ev_radio1").attr("checked") && cost.val() == ""){
		alert("请填写人均费用");
		cost.focus();
		return false;
	}
	var regu = "^[0-9]+[\.][0-9]{0,3}$";
	var re = new RegExp(regu); 
	var regNum = /^\d+$/;
	if ($("#ev_radio1").attr("checked") && !re.test(cost.val())&&!regNum.test(cost.val())){
		alert('人均费用必须是金额格式，如：80或11.13');
		cost.focus();
		return false;
	}
	
	var startAge = $("#startAge");
	var endAge = $("#endAge");
	if(startAge.val() == "" && endAge.val() == ""){
		alert("请填写年龄范围！");
		startAge.focus();
		return false;
	}
	
	if(startAge.val() != "" && !isNumber(startAge.val())){
		alert("请输入正确的年龄范围！");
		startAge.focus();
		return false;
	}
	
	if(endAge.val() != "" && !isNumber(endAge.val())){
		alert("请输入正确的年龄范围！");
		endAge.focus();
		return false;
	}
	
	if(startAge.val() != "" && endAge.val() != "" && parseInt(endAge.val())<parseInt(startAge.val())){
		alert("请输入正确的年龄范围！");
		endAge.focus();
		return false;
	}
	
	var people = $("[name='people']:checked").val();
	var applyNum = $("#applyNum");
	if(people == 1){
		if(applyNum.val() == ""){
			alert("请选择最多报名人数！");
			applyNum.focus();
			return;
		}
		if(!isNumber(applyNum.val())){
			alert("报名人数必须为正整数！");
			applyNum.focus();
			return;
		}
	}
	
	return true;
}

/**
 * 上传海报
 * @return
 */
function uploadPosterImg(){
		var filepath=$("#upload_img").val(); 
		var extStart=filepath.lastIndexOf(".");
		var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			alert("图片限于bmp,png,gif,jpeg,jpg格式");
			return ;
		} 
		var options = { 
				cache : false,
				dataType:"data",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.indexOf(':') != -1){
					alert("上传成功");
					$("#post_status_media_url").val(data);
					$("#post_status_media_img").show();
					$("#post_status_media_img").attr("src",data);
				  }else{
				  	alert(data);
				  }
				}
			};
			$("#status_upload_pic_form").attr('action','/status/fileUploadStatusAjax.jspa');
			$("#status_upload_pic_form").ajaxSubmit(options);

}

/**
 * 查询集集组的参加用户
 * @param groupId
 * @return
 */
function findGroupUsers(groupId){
	$.ajax({
		type:'POST',
		url:'/group/findGroupUsers.jspa',
		data:({"groupId":groupId
		      }),  
		success:function(data){
			$("#group-users-div").html(data);
		}
	});
}

/**
 * 通过集集组
 * @param groupId
 * @param userId
 * @return
 */
function passGroupUser(groupId,userId){
	$.ajax({
		type:'POST',
		url:'/group/passGroupUser.jspa',
		dataType:'json',
		 data:({"groupId":groupId,
			 	"userId":userId
		      }),  
		success:function(json){
			if(json.flag == 0){
				location.reload();
			}else{
				alert(json.msg);
			}
		}
	});
}

function findRecommendGroup(){
	$.ajax({
		type:'POST',
		url:'/group/findRecommendGroup.jspa',
		success:function(data){
			$("#group-recommend-div").html(data);
		}
	});
}