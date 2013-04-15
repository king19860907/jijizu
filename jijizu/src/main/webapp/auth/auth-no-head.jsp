	<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人认证-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/photo_wall.css" rel="stylesheet" type="text/css" />
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/photo_wall.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/auth.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>
<body class="sign">
<%@include file="/common/header.jsp" %>
<div class="verified_header">
	<div class="wrap2 verified_bg"><a href="/auth/authPersonal.jspa"></a></div>
</div>
<s:set name="actionName" value="#request['struts.request_uri']"></s:set>  
<div class="W_signbg">
	<div class="wrap2">
        <div class="verified_title">集集组达人认证</div>
        <div class="condition_list">
        	<div class="text_info">抱歉，由于您以下条件尚未满足，暂时不可以申请集集组个人认证，请达到下列要求后再来申请吧！ <br />认证标准：微博账号需有头像。</div>
            <ul class="list_info_personal clearfix">
            	<li>
                    <div class="icon_condition icon_face"> </div>
                    <div class="fix_btn"> <a href="/user/photo.jspa?target=<s:property value="#actionName"/>"></a></div>
               </li>
            </ul>
            <ul class="list_info_personal clearfix" style="display:none">
            	<li>
                    <div class="icon_condition icon_face_ok"> </div>
                    <div class="fix_btn_other"></div>
               </li>
            </ul>
        </div>
    </div>
</div>
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 提交个人申请成功弹出层 -->
<%@include file="/auth/layer/personal-auth-success-layer.jsp" %>
<script language="javascript">
</script> 
</body>
</html>