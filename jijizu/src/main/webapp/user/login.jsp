<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/sign.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="/favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/login.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.cookie.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
</head>
<body class="sign">
<div class="top_line"></div>
<div class="W_signbg">
  <div class="wrap">
    <div class="signBg">
      <div class="logo"><a href="../index.html" title="集集组"><img src="${IMG_PATH}/sign_logo.jpg" width="95" height="50" /></a></div>
      <div class="login_bg"></div>
    </div>
    <div class="loginMain">
      <form id="login_form">
      <div class="login_box">
      	<input type="text" id="logName" name="logName" onblur="if (value=='') {value='邮箱'}" onfocus="if(value=='邮箱') {value=''}" value="邮箱" id="account_input" class="W_input"/>
        <input type="password" id="password" name="password" class="W_input"/>
        <input type="hidden" id="target" name="target" value="<s:property value="target"/>" class="W_input"/>
        <div class="loginBtn"> <a id="loginBtn" href="javascript:void(0);" onclick="return false;"></a> </div>
        <div class="login_link"><a href="/user/forget.jspa">忘记密码了？</a> | <a href="/user/sign.jspa">新用户注册</a></div>
      	<div class="remeber_id"><label><input type="checkbox" value="true" name="autoLogin"/>下次自动登录</label></div>
      </div>
      </form>
      <div class="tips" style="display:none">您也可以用其他账号登录：</div>
      <div class="loginBtn" style="display:none">
      	<a href="#" class="qq"></a>
        <a href="#" class="weibo"></a>
        <a href="#" class="renren"></a>
        <a href="#" class="douban"></a>
      </div>
      
    </div>
  </div>
</div>
<%@include file="/common/footer.jsp" %>

<script type="text/javascript">
$(document).keydown(function(e){
	var e=e.keyCode;
	if(e==13){
		$("#loginBtn").click();
	}
});
$(document).ready(function(){
	if($.cookie('log_name') != null){
		$("#logName").val($.cookie('log_name'));
	}
});
</script>
</body>
</html>
