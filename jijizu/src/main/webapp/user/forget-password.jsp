<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/sign.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon">
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.cookie.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/sign.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/password.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/validate.js${JS_EDITION}"></script>
</head>
<body class="sign">
<div class="top_line"></div>
<div class="W_signbg">
  <div class="wrap">
  	<div class="reg_header">
    	<div class="logo"><a href="/"><img src="${IMG_PATH}/sign_logo.jpg" width="122" height="50" /></a></div>
    </div>
    <div class="reg_mid">
		<h4>找回密码</h4>
      <div class="return_way">请填写登录名</div>
        <table width="600" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th width="64" align="right">登录名：</th>
            <td width="190"><input name="logName" type="text" /></td>
            <td colspan="2">
            </td>
          </tr>
          <tr>
            <th align="right">验证码：</th>
            <td><input name="iCode" type="text" /></td>
            <td width="90px"><img id="iCode-img" src="/user/identifyingCode.jspa"/></td>
            <td width="224"><a class="noSee" href="javascript:flushICode();">看不清图片</a>
            </td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td colspan="3"><a id="find-pwd-btn" href="javascript:void(0);" class="return_btn">提交</a></td>
          </tr>
        </table>
    </div>
  </div>
</div>
<%@include file="/common/footer.jsp" %>
</body>
</html>
