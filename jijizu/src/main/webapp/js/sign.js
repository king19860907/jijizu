// JavaScript Document
$(document).ready(function(){
	// 返回顶部 
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
	
	$('#signButton').click(function(){
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					if(data.data!=""){
						window.location.href=data.data;
					}else{
						window.location.href="/user/recommend.jspa"
					}
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			$(".error").each(function(){
				return;
			});
			validateSignForm();
			if($('.error').length<=0){
				if(!$('#service').attr('checked')){
					alert('请选择接受集集组用户服务条款');
				}else{
					$("#register_form").attr('action','/user/userRegister.jspa');
					$("#register_form").ajaxSubmit(options);
				}
			}
	});
	
	//注册表单验证
	$("#account_input").focus(function(){
		$("#account_input").addClass("W_input_focus");
		$("#email").html("<p class='notice'><span class='icon'></span><span>请输入您的常用邮箱</span></p>")
	});
	$("#account_input").blur(function(){
		validateEmail();
	});
	
	$("#realname").focus(function(){
		$("#realname").addClass("W_input_focus");
		$("#name").html("<p class='notice'><span class='icon'></span><span>为您加好友方便，强烈建议使用用真实姓名</span></p>")
	});
	$("#realname").blur(function(){
		validateName();
	});
	
	$("#nickName").blur(function(){
		validateNickName();
	});
	
	$("#password").focus(function(){
		$("#password").addClass("W_input_focus");
		if($(this).val()==""){
			$("#passwd_tips").html("<p class='notice'><span class='icon'></span><span>6-16字符组成，区分大小写</span></p>");
		}
		else if($(this).val().length<"6"){
			$("#passwd_tips").html("<p class='error'><span class='icon'></span><span>密码为6-16位</span></p>");
		}
		
		else{
		}
	});
	$("#password").blur(function(){
		validatePassword();
	});	
	
	$("#passwordRepeat").focus(function(){
		$("#passwordRepeat").addClass("W_input_focus");
		if($(this).val()==""){
			$("#passwdRepeat_tips").html("<p class='notice'><span class='icon'></span><span>请再次输入密码</span></p>")
		}
	});
	$("#passwordRepeat").blur(function(){
		validatePasswordRepeat();
	});	
	$("#first").blur(function(){
		validateLocation();
	});
	$("#second").blur(function(){
		validateLocation();
	});
	$("#third").blur(function(){
		validateLocation();
	});
	
});

function validateSignForm(){
	validateEmail();
	validateName();
	validatePassword();
	validatePasswordRepeat();
	validateLocation();
	validateNickName();
}

function validateNickName(){
	$("#nickName").removeClass("W_input_focus");
	if($("#nickName").val().length < 2 || $("#nickName").val().length > 25){
		$("#nickNameTip").html("<p class='error'><span class='icon'></span><span>长度在2-25个字内！</span></p>")
	}else if(!$("#nickName").val().match(/^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5_]+$/)){
		$("#nickNameTip").html("<p class='error'><span class='icon'></span><span>中英文开头,不得有特殊字符！</span></p>")
	}else{
		$.ajax({
			type:'POST',
			url:'/user/checkNickName.jspa',
			data:({"nickName":$('#nickName').val()
			      }), 
			success:function(data){
				if (data != null && data.flag!=0){
					$("#nickNameTip").html("<p class='error'><span class='icon'></span><span>该昵称已被注册！</span></p>")
				}
				else {
					$("#nickNameTip").html("<p class='icon_succ'><span class='icon'></span></p>")
				}
			}
		});
	}
}

function validateLocation(){
	if($("#first").val()=="" || $("#second").val() == "" || $("#third").val() == ""){
		$("#location_tips").html("<p class='error'><span class='icon'></span><span>请选择所在地！</span></p>")
	}else{
		$("#location_tips").html("<p class='icon_succ'><span class='icon'></span></p>")
	}
}

function validatePasswordRepeat(){
	$("#passwordRepeat").removeClass("W_input_focus");
	if($("#passwordRepeat").val()==$("#password").val()){
		$("#passwdRepeat_tips").html("<p class='icon_succ'><span class='icon'></span></p>")
	}
	else{
		$("#passwdRepeat_tips").html("<p class='error'><span class='icon'></span><span>两次输入的密码不一致！</span></p>")
	}
}

function validatePassword(){
	$("#password").removeClass("W_input_focus");
	if($("#password").val()==""){
		$("#passwd_tips").html("<p class='error'><span class='icon'></span><span>请输入密码</span></p>")
	}else if($("#password").val().length<"6"){
		$("#passwd_tips").html("<p class='error'><span class='icon'></span><span>密码为6-16位<span></p>")
	} 
	else {
		$("#passwd_tips").html("<p class='icon_succ'><span class='icon'></span></p>")
		var $dd = $("#passwd_tips").html();
		$("#passwd_tips").html($dd);
	}
}

function validateName(){
	$("#realname").removeClass("W_input_focus");
	if($("#realname").val().length < 2 || $("#realname").val().length > 25){
		$("#name").html("<p class='error'><span class='icon'></span><span>长度在2-25个字内！</span></p>")
	}else if(!$("#realname").val().match(/^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5_]+$/)){
		$("#name").html("<p class='error'><span class='icon'></span><span>中英文开头,不得有特殊字符！</span></p>")
	}else if($("#realname").val()=="集集组"){
		$("#name").html("<p class='error'><span class='icon'></span><span>姓名不能为集集组官方名称！</span></p>")
	}
	else {
		$("#name").html("<p class='icon_succ'><span class='icon'></span></p>")
	}
}

function validateEmail(){
	$("#account_input").removeClass("W_input_focus");
	if($('#account_input').val()==""){
		$("#email").html("<p class='error'><span class='icon'></span><span>请输入您的常用邮箱</span></p>")
	}
	else if(!$('#account_input').val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
		$("#email").html("<p class='error'><span class='icon'></span><span>邮箱格式不正确</span></p>")
	}
	else {
		$.ajax({
			type:'POST',
			url:'/user/checkLogName.jspa',
			data:({"logName":$('#account_input').val()
			      }), 
			success:function(data){
				if (data != null && data.flag!=0){
					$("#email").html("<p class='error'><span class='icon'></span><span>该邮箱已被注册！</span></p>")
				}
				else {
					$("#email").html("<p class='icon_succ'><span class='icon'></span></p>")
				}
			}
		});
	}
}