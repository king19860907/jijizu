<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="login-box" style="display:none;">
  <div class="layer_point ly14 lay_box">
    <div class="bg">
      <h1>用户登录<a href="javascript:void(0);" class="span" onclick="hide_login()"></a></h1>
      <form id="login_form">
      <div class="login_box layer">
     	<input type="text" id="logName" name="logName" onblur="if (value=='') {value='邮箱'}" onfocus="if(value=='邮箱') {value=''}" value="邮箱" id="account_input" class="W_input"/>
        <input type="password" id="password" name="password" class="W_input"/>
        <div class="remeber_id"><label><input type="checkbox" value="true" name="autoLogin"/>下次自动登录</label></div>
        <input type="hidden" id="target" name="target" value="<s:property value="target"/>" class="W_input"/>
        <div class="loginBtn"> <a id="loginBtn-layer" name="loginBtn" href="javascript:void(0);" onclick="return false;"></a> </div>
        <div class="login_link"><a href="/user/forget.jspa">忘记密码了？</a> | <a href="/user/sign.jspa">新用户注册</a></div>
      </div>
      </form>
    </div>
  </div>
</div>
<script language="javascript">
var loginBox= $("#login-box");
loginBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_login(e){
	if (typeof(LOGIN_USER_ID)=="undefined"){
		loginBox.overlay().load();

		var href = $(e).attr("href");
		if(href.indexOf('javascript')!=-1){
			href = location.href;
		}
		$("[name='target']").val(href);
		
		return false;
	}
	return true;
}
	
function hide_login(){
	loginBox.overlay().close();
}
</script>