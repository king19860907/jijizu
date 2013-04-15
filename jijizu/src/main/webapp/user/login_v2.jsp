<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/login.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.cookie.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="login_banner">
	<form id="login_form">
      <div class="login_box">
      	<h2>用户登录</h2>
      	<input type="text" id="logName" name="logName" onblur="if (value=='') {value='邮箱'}" onfocus="if(value=='邮箱') {value=''}" value="邮箱" id="account_input" class="W_input"/>
        <input type="password" id="password" name="password" class="W_input"/>
        <div class="remeber_id"><label><input type="checkbox" value="true" name="autoLogin"/>下次自动登录</label></div>
        <input type="hidden" id="target" name="target" value="<s:property value="target"/>" class="W_input"/>
        <div class="loginBtn"> <a id="loginBtn" name="loginBtn" href="javascript:void(0);" onclick="return false;"></a> </div>
        <div class="login_link"><a href="/user/forget.jspa">忘记密码了？</a> | <a href="/user/sign.jspa">新用户注册</a></div>
      </div>
      </form>
</div>
<div class="login_main">
    <!--内容部分-->
      <div class="W_main_b clearfix">
        <div class="group_read">
        	<h3>全部集集组</h3>
        </div>
        <div class="evlist_main">
        	<ul>
        		<s:iterator value="groupList">
        			<li>
	                	<div class="evlist_img"><a href="/group/<s:property value="groupId"/>"><img src="<s:property value="smallPosterImgUrl"/>" width="60" height="80" /></a></div>
	                    <div class="event_action">
	                    	<a href="/group/<s:property value="groupId"/>">
	                    	<s:if test="groupStatus==3">
	                    		<i class="State">正在报名</i>
	                    	</s:if>
	                    	<s:elseif test="groupStatus==0">
	                    		<i class="State">即将开始</i>
	                    	</s:elseif>
	                    	<s:elseif test="groupStatus==1">
	                    		<i class="State">正在进行</i>
	                    	</s:elseif>
	                    	<s:elseif test="groupStatus==2">
	                    		<i class="State over">已经结束</i>
	                    	</s:elseif>
	                    	</a>
	                    	<p class="event_comm"></p>
	                    </div>
	                    <div class="evlist_cont">
	                    	<p class="evlist_title"><a href="/group/<s:property value="groupId"/>"><s:property value="title"/></a></p>
	                        <p class="evlist_infor">时　间：<s:date name="startDate" format="MM月dd日 HH:mm" /> - <s:date name="endDate" format="MM月dd日 HH:mm" /></p>
	                        <p class="evlist_infor" title="<s:property value="cityStr"/><s:property value="districtStr"/><s:property value="address"/>">地　点：<s:property value="cityStr"/><s:property value="districtStr"/><s:property value="%{getAddress(18)}"/></p>
	                        <p class="evlist_infor">发　起：<a href="/u/<s:property value="userId"/>" target="_blank"><s:property value="userName"/></a>
	                        </p>
	                        <p class="evlist_infor">活动审核：<s:property value="enterTypeStr"/></p>
	                    </div>
	                </li>
        		</s:iterator>
            </ul>
        </div>
      </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<script type="text/javascript">

</script>

</body>
</html>
