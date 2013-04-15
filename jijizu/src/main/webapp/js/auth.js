$(document).ready(function(){
	
	/**
	 * 提交企业认证申请
	 */
	$("#add-experties-auth-btn").click(function(){
		
		var companyName = $("[name='companyName']");
		if(companyName.val() == ""){
			alert("请填写企业全称");
			companyName.focus();
			return;
		}
		
		var name = $("[name='name']");
		if(name.val() == ""){
			alert("请填写联系人姓名");
			name.focus();
			return;
		}
		
		var mobile = $("[name='mobile']");
		if(mobile.val() == ""){
			alert("请填写联系手机");
			mobile.focus();
			return;
		}
		
		var businessLicense = $("[name='businessLicense']");
		if(businessLicense.val() == ""){
			alert("请上传营业执照");
			return;
		}
		
		var registerNo = $("[name='registerNo']");
		if(registerNo.val() == ""){
			alert("请填写营业执照注册号");
			registerNo.focus();
			return;
		}
		
		$.ajax({
			type:'POST',
			url:'/auth/addEnterpriseAuth.jspa',
			dataType:'json',
			 data:({"companyName":$("[name='companyName']").val(),
				   "name":$("[name='name']").val(),
				   "mobile":$("[name='mobile']").val(),
				   "businessLicense":$("[name='businessLicense']").val(),
				   "registerNo":$("[name='registerNo']").val()
			      }),  
			success:function(json){
				if(json.flag == 0){
					 show_medal();
				}else{
					alert(json.msg);
				}
			}
		});
		
	});
	
	/**
	 * 提交个人认证申请
	 */
	$("#add-personal-auth-btn").click(function(){
		var name = $("[name='name']");
		if(name.val() == ""){
			alert("请填写姓名");
			name.focus();
			return;
		}
		
		var title = $("[name='title']");
		if(title.val() == ""){
			alert("请填写职业/头衔");
			title.focus();
			return;
		}
		
		var authDesc = $("[name='authDesc']");
		if(authDesc.val() == ""){
			alert("请填写认证说明");
			authDesc.focus();
			return;
		}
		
		var email = $("[name='email']");
		if(email.val() == ""){
			alert("请填写电子邮箱");
			email.focus();
			return;
		}
		
		var mobile = $("[name='mobile']");
		if(mobile.val() == ""){
			alert("请填写联系手机");
			mobile.focus();
			return;
		}
		
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag==0){
					  show_medal();
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			$("#add-personal-auth-form").attr('action','/auth/addPersonalAuth.jspa');
			$("#add-personal-auth-form").ajaxSubmit(options);
		
	});
	
});

/**
 * 上传海报
 * @return
 */
function uploadBusinessLicense (){
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
					var imgNameWithoutExt = data.substring(0,data.lastIndexOf('.'));
					imgNameWithoutExt = imgNameWithoutExt+"_t"+data.substring(data.lastIndexOf('.'));
					$("#post_status_media_url").val(imgNameWithoutExt);
					$("#post_status_media_img").show();
					$("#post_status_media_img").attr("src",imgNameWithoutExt);
				  }else{
				  	alert(data);
				  }
				}
			};
			$("#auth_upload_pic_form").attr('action','/status/fileUploadStatusAjax.jspa');
			$("#auth_upload_pic_form").ajaxSubmit(options);

}