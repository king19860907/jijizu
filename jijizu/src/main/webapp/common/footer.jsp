<%@page language="java" pageEncoding="UTF-8"%>
<div class="footer">
 <p>Copyright © 2012 - 2020  jijizu.com All Rights Reserved.</p>
</div>
<!-- 登录弹出层 -->
<%@include file="/user/layer/login-layer.jsp"%>
<script type="text/javascript">
function popClose(){
	$("#popMsg").hide();
}
function getNewMessage(){
	$.ajax({
		cache : false,
		type : "POST",
		url : "/user/getNewMessage.jspa",
		dataType : 'json',
		success : function(json) {
			if (json.flag == 0) {
				if(json.data.show){
					$("#commentNum").html(json.data.commentNum);
					$("#atMeNum").html(json.data.atMeNum);
					$("#messageNum").html(json.data.mailNum);
					$("#noticeNum").html(json.data.noticeNum);
					if (json.data.commentNum == 0) {
						$("#comment").hide();
					} else {
						$("#comment").show();
					}
					if (json.data.atMeNum == 0) {
						$("#atMe").hide();
					} else {
						$("#atMe").show();
					}
					if (json.data.mailNum == 0) {
						$("#message").hide();
					} else {
						$("#message").show();
					}
					if (json.data.noticeNum == 0) {
						$("#notice").hide();
					} else {
						$("#notice").show();
					}
					$("#popMsg").show();
					//$("#popMsg").css('visibility', 'visible');
					//$("#popMsg").css('z-index', '9999');
				}else{
					$("#popMsg").hide();
				}
			} else {
				$("#popMsg").hide();
				//$("#popMsg").css('visibility', 'hidden');
			}
		}
		
	});
}

$(document).ready(function(){

	if(typeof(LOGIN_USER_ID) =="undefined"){
		$(document).keydown(function(e){
			var e=e.keyCode;
			if(e==13){
				if($("#login-box").is(":visible")){
					$("#loginBtn-layer").click();
				}else{
					$("#loginBtn").click();
				}
			}
		});
	}
	
	if($.cookie('log_name') != null){
		$("[name='logName']").val($.cookie('log_name'));
	}
	
	$("[name='loginBtn']").click(function(){
		var logName = $(this).parent().siblings().filter("[name='logName']");
		if(logName.val() == "" || logName.val() == "邮箱"){
			alert('请输入邮箱地址');
			logName.focus();
			return;
		}
		
		var password = $(this).parent().siblings().filter("[name='password']");
		if(password.val() == ""){
			alert("请输入密码");
			password.focus();
			return;
		}
		
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					 if(data.data != ""){
						 window.location.href=data.data;
					 }else{
						 window.location.reload();
					 }
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			
			$(this).parent().parent().parent().attr('action','/user/userLogin.jspa');
			$(this).parent().parent().parent().ajaxSubmit(options);
	});
});
</script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F29655ff4beaf06b86b7c5519b5756856' type='text/javascript'%3E%3C/script%3E"));
</script>
