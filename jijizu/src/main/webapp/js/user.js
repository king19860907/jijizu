$(document).ready(function(){
	
	/**
	 * 更新用户信息
	 */
	$("#update-user-info-btn").click(function(){
		var name = $("#name").val();
		if(name == ""){
			alert("请输入姓名");
			$("#name").focus();
			return;
		}
		
		var nickName = $("#nickName").val();
		if(nickName == ""){
			alert("请输入昵称");
			$("#nickName").focus();
			return;
		}
		
		var livingCommunity = $("#livingCommunity").val();
	
		if(livingCommunity == "请输入小区名或路名..."){
			alert("请输入现居地小区名或路名");
			$("#livingCommunity").focus();
			return;
		}
		
		var living1 = $("#living1").val();
		var livingCommunityId = $("#living_community_hidden").val();
		if(living1 != "请输入小区名或路名..."  && livingCommunityId == ""){
			alert("您输入现居地的生活小区不存在");
			$("#livingCommunity").focus();
			return;
		}
		
		var first = $("#first").val();
		var second = $("#second").val();
		var third = $("#third").val();
		if(first == "" || second == "" || third==""){
			alert("请选择现居地");
			return;
		}
		
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					  window.location.reload();
				  }else{
				  	alert(data.msg);
				  }
				}
			};
		$("#update-user-info-form").attr('action','/user/updateUserInfo.jspa');
		$("#update-user-info-form").ajaxSubmit(options);
	});
	
	$(".info_tab01 td.info_tab_vm input.PY_input").blur(function(){
		if($(this).val()==""){
			$(this).val("请输入小区名或路名...");
			$(this).next().hide();
		}
	});
	$(".info_tab01 td.info_tab_vm input.PY_input").focus(function(){
		if($(this).val()=="请输入小区名或路名..."){
			$(this).val("");
			$(this).next().show();
		}
	});
});

/**
 * 更新孩子信息
 */
function updateChild(){
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					findChildInfo();
				  }else{
				  	alert(data.msg);
				  }
				}
			};
		$("#add-child-form").attr('action','/user/updateChild.jspa');
		$("#add-child-form").ajaxSubmit(options);
}

/**
 * 保存孩子信息
 */
function addChild(){
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					  findChildInfo();
				  }else{
				  	alert(data.msg);
				  }
				}
			};
		$("#add-child-form").attr('action','/user/addChild.jspa');
		$("#add-child-form").ajaxSubmit(options);
}


function deleteChild(childId){
	$.ajax({
		type:'POST',
		url:'/user/deleteChild.jspa',
		dataType:'json',
		data:({"childId":childId
	      }),
		success:function(data){
			if(data.flag==0){
				hidedel_lay();
				findChildInfo();
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 保存学校信息
 * @param q
 * @param type
 * @return
 */
function saveSchoolInfo(q,type){
	$.ajax({
		type:'POST',
		url:'/user/saveSchoolInfo.jspa',
		data:({"q":q,"type":type
	      }),
		success:function(data){
			/*if(data.flag==0){
				findUserJob();
			}else{
				alert(data.msg);
			}*/
		}
	});
}

function initAddChildDiv(){
	$("[name='childId']").val("");
	$("#childName").val("");
	$("#childNickName").val("");
	$("#department").val("");
	$("#childMale").attr("checked",true);	
	$(".jSelectDateBorder").each(function(i){
		if(i == 1){
			$(this).remove();
		}
	});
	$("#childBirthday").val("");
	jSelectDate.init($("#childBirthday"));
	$("#school_type").val("");
	$("#schoolName").val("");
	$("#schoolId").val("");
}

/**
 * 查询孩子信息
 */
function findChildInfo(){
	$.ajax({
		type:'POST',
		url:'/user/findChildInfo.jspa',
		success:function(data){
			initAddChildDiv();
			$(".setting_box").after($("#add-child-div"));
			$("#child-info-div").html(data);
			if(data.indexOf("form") == -1){
				$("#add-child-div").show();
			}else{
				$("#add-child-div").hide();
			}
		}
	});
}

/**
 * 添加用户学校信息
 * @return
 */
function addUserSchool(){
	var schoolName = $("#schoolName").val();
	if(schoolName == '点击选择学校'){
		alert("请选择学校！");
		return;
	}
	var department = $("#department").val();
	if(department == '请输入完整的班级名称或/院系'){
		$("#department").val('');
	}
	var options = { 
			cache : false,
			dataType:"json",	
			type :"post",
			async:false,
			success: function(data) { 
			  if(data.flag == 0){
				  findUserSchool();
			  }else{
			  	alert(data.msg);
			  }
			}
		};
	$("#add-user-school-form").attr('action','/user/addUserSchool.jspa');
	$("#add-user-school-form").ajaxSubmit(options);
}

/**
 * 更新用户学校信息
 * @return
 */
function updateUserSchool(){
	var schoolName = $("#schoolName").val();
	if(schoolName == '点击选择学校'){
		alter("请选择学校！");
		return;
	}
	var department = $("#department").val();
	if(department == '请输入完整的班级名称或/院系'){
		$("#department").val('');
	}
	var options = { 
			cache : false,
			dataType:"json",	
			type :"post",
			async:false,
			success: function(data) { 
			  if(data.flag == 0){
				findUserSchool();
			  }else{
			  	alert(data.msg);
			  }
			}
		};
	$("#add-user-school-form").attr('action','/user/updateUserSchool.jspa');
	$("#add-user-school-form").ajaxSubmit(options);
}

/**
 * 添加工作信息
 */
function addUserJob(){
	var companyName = $("#companyName").val();
	if(companyName == '请输入完整的单位名称'){
		alert("请输入完整的单位名称");
		$("#companyName").focus();
		return;
	}
	
	var department = $("#department").val();
	if(department == '请输入所在的部门或者职位'){
		$("#department").val('');
	}
	
	var options = { 
			cache : false,
			dataType:"json",	
			type :"post",
			async:false,
			success: function(data) { 
			  if(data.flag == 0){
				findUserJob();
			  }else{
			  	alert(data.msg);
			  }
			}
		};
	$("#add-user-job-form").attr('action','/user/addUserJob.jspa');
	$("#add-user-job-form").ajaxSubmit(options);
}

function updateUserJob(){
	var companyName = $("#companyName").val();
	if(companyName == '请输入完整的单位名称'){
		alert("请输入完整的单位名称");
		$("#companyName").focus();
		return;
	}
	
	var department = $("#department").val();
	if(department == '请输入所在的部门或者职位'){
		$("#department").val('');
	}
	var options = { 
			cache : false,
			dataType:"json",	
			type :"post",
			async:false,
			success: function(data) { 
			  if(data.flag == 0){
				findUserJob();
			  }else{
			  	alert(data.msg);
			  }
			}
		};
	$("#add-user-job-form").attr('action','/user/updateUserJob.jspa');
	$("#add-user-job-form").ajaxSubmit(options);
}

/**
 * 删除工作信息
 * @return
 */
function deleteUserJob(jobId){
	$.ajax({
		type:'POST',
		url:'/user/deleteUserJob.jspa',
		data:({"jobId":jobId
	      }),
		success:function(data){
			if(data.flag==0){
				findUserJob();
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 删除用户学校信息
 * @param userSchoolId
 * @return
 */
function deleteUserSchool(userSchoolId){
	$.ajax({
		type:'POST',
		url:'/user/deleteUserSchool.jspa',
		data:({"userSchoolId":userSchoolId
	      }),
		success:function(data){
			if(data.flag==0){
				findUserSchool();
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 查询用户学校信息
 * @return
 */
function findUserSchool(){
	$.ajax({
		type:'POST',
		url:'/user/findUserSchool.jspa',
		success:function(data){
			var $editSchool = $("#edit-school-div");
			$("#edit-school-title").html("添加教育信息");
			$("#add-school-btn").attr("href","javascript:addUserSchool();");
			$("#userSchoolId-hidden").val("");
			$("#user-school-div").after($editSchool);
			var html = $(data);
			$("#user-school-div").html(data);
		}
	});
}

/**
 * 查询工作信息
 * @return
 */
function findUserJob(){
	$.ajax({
		type:'POST',
		url:'/user/findUserJob.jspa',
		success:function(data){
			$("#companyName").val('请输入完整的单位名称');
			$("#first").val('请选择');
			$("#second").val('请选择');
			$("#job_start_year").val('请选择');
			$("#job_end_year").val('请选择');
			var $editJob = $("#edit_job_div");
			$("#edit-job-title").html("添加工作信息");
			$("#add-job-btn").attr("href","javascript:addUserJob();");
			$("#jobid-hidden").val("");
			$("#job_info").after($editJob);
			var html = $(data);
			//prepareUserCardTip($(html).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
			$("#job_info").html(data);
		}
	});
}

function updateImage(){
	var target=getQueryString('target');
	var filepath=$("input[name='myFile']").val(); 
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
			window.location.href="/user/photoCut.jspa?img="+ encodeURIComponent(data)+"&target="+target;
		  }else{
		  	alert(data);
		  }
		}
	};
	$("#FileUploadAction").ajaxSubmit(options);
}