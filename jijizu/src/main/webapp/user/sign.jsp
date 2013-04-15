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
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/area.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/city.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/select_util.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/sign.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.cookie.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script language="javascript">
$(document).ready(function() {
	area.curFirst = 1;
	area.curSecond = 2;
	area.curThird = 3;
	try{
		area.init();
		area.firstChange();
	}catch(ex){}
});
</script>
</head>
<body class="sign">
<div class="top_line"></div>
<div class="W_signbg">
  <div class="wrap">
    <div class="signBg">
      <div class="logo"><a href="/" title="集集组"><img src="${IMG_PATH}/sign_logo.jpg" width="95" height="50" /></a></div>
      <div class="login_bg"></div>
    </div>
    <form id="register_form">
    <input type="hidden" name="target" value="<s:property value="target"/>" />
    <div class="signMain">
      <h1></h1>
      <div class="sign_form">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>邮箱：</div>
          <div class="inp">
            <input type="text" name="logName" class="W_input" id="account_input" value="请输入E-mail地址" onfocus="if(value=='请输入E-mail地址') {value=''}" onblur="if (value=='') {value='请输入E-mail地址'}" />
          </div>
          <div class="clearfix"></div>
          <div class="tips" id="email"></div>
        </div>
      </div>
      <div class="sign_form">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>姓名：</div>
          <div class="inp">
            <input type="text" name="name" class="W_input" id="realname" value="" />
          </div>
          <div class="clearfix"></div>
          <div class="tips" id="name"></div>
        </div>
      </div>
      <div class="sign_form">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>昵称：</div>
          <div class="inp">
            <input type="text" name="nickName" class="W_input" id="nickName" value="" />
          </div>
          <div class="clearfix"></div>
          <div class="tips" id="nickNameTip"></div>
        </div>
      </div>
      <div class="sign_form">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>密码：</div>
          <div class="inp">
            <input type="password" name="password" class="W_input" id="password" value="" />
          </div>
          <div class="clearfix"></div>
          <div class="tips" id="passwd_tips"></div>
        </div>
      </div>
      <div class="sign_form">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>确认密码：</div>
          <div class="inp">
            <input type="password" name="passwordRepeat" class="W_input" id="passwordRepeat" value="" />
          </div>
          <div class="clearfix"></div>
          <div class="tips" id="passwdRepeat_tips"></div>
        </div>
      </div>
      <div class="sign_form">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>所在地：</div>
          <div class="inp">
            <select>
              <option>中国</option>
            </select>
            <select name="province" id="first">
            </select>
            <select name="city" id="second">
            </select>
            <select name="district" id="third" class="last">
            </select>
          </div>
          <div class="clearfix"></div>
          <div class="tips" id="location_tips"></div>
        </div>
      </div>
      <div class="sign_form" style="display:none">
        <div class="info_list clearfix">
          <div class="tit"><i>*</i>家庭状况：</div>
          <div class="inp">
            <select name="familyStatus" class="family">
            	<s:iterator value="familyStatus">
            		<option value="<s:property value="dictCode"/>"><s:property value="name"/></option>
            	</s:iterator>
            </select>
          </div>
        </div>
      </div>
      <div class="signBtn"> <a href="javascript:void(0);" onclick="return false;" id="signButton"></a> </div>
      <div class="Clause">
        <input id="service" name="" type="checkbox" value="" checked="checked" />
        <span>我接受</span><span><a target="_blank" href="/doc/private.jsp">集集组用户服务条款</a></span> </div>
    </div>
    </form>
  </div>
</div>
<%@include file="/common/footer.jsp" %>
</body>
</html>
