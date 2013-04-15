

$(document).ready(function(){
	
	$("#find-pwd-btn").click(function(){
		findPwd();
	});
	
	$("#reset-pwd-btn").click(function(){
		
		var logName = $("#logName");
		var userId = $("#userId");
		var random = $("#random");
		var password = $("#password");
		var passwordRepeat = $("#passwordRepeat");
		
		if(password.val()==""){
			alert("请输入密码！");
			password.focus();
			return;
		}
		
		if(passwordRepeat.val()==""){
			alert("请再次输入密码！");
			passwordRepeat.focus();
			return;
		}
		
		if(password.val()!=passwordRepeat.val()){
			alert("两次输入密码不一致！");
			passwordRepeat.focus();
			return;
		}
		
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/user/resetPwd.jspa',
			data:({"logName":logName.val(),"userId":userId.val(),"random":random.val(),"password":password.val()
			      }), 
			success:function(data){
				if(data.flag==0){
					alert("修改密码成功，请重新登录");
					location.href="/";
				}else if(data.flag==-3){
					alert(data.msg);
					location.href="/user/forget.jspa"
				}else{
					alert(data.msg);
				}
			}
		});
	});
	
});

function findPwd(){
	var icode = $("[name='iCode']").val();
	var logName = $("[name='logName']").val();
	
	if(logName==""){
		alert("请填写用户名！");
		$("[name='logName']").focus();
		return;
	}
	
	if(!isEmail(logName)){
		alert("用户名格式不正确！");
		$("[name='logName']").focus();
		return;
	}
	
	if(icode==""){
		alert("请填写验证码！");
		$("[name='iCode']").focus();
		return;
	}
	
	$("#find-pwd-btn").unbind("click");
	
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/user/findPwd.jspa',
		data:({"iCode":icode,"logName":logName
		      }), 
		success:function(data){
			if(data.flag==0){
				alert("密码重置邮件已发送至您的邮箱，请注意查收！");
				location.href="/";
			}else{
				alert(data.msg);
				flushICode();
				$("#find-pwd-btn").click(function(){
					findPwd();
				});
			}
		}
	});
}

function flushICode(){
	var time = (new Date()).valueOf();
	$("#iCode-img").attr("src","/user/identifyingCode.jspa?random="+time);
}