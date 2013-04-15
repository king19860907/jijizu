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
<div class="W_signbg">
	<div class="wrap2">
        <div class="verified_title">集集组达人认证：机构认证</div>
        <div class="verified_content">
        	<dl class="apply_form">
            	<dt><em class="red">*</em>企业全称：</dt>
                <dd><input type="text" class="W_inputStp error" name="companyName"/></dd>
            </dl>
            <dl class="apply_form">
            	<dt><em class="red">*</em>联系人姓名：</dt>
                <dd><input type="text" class="W_inputStp" name="name"/></dd>
            </dl>
            <dl class="apply_form">
            	<dt><em class="red">*</em>联系手机：</dt>
                <dd><input type="text" class="W_inputStp" name="mobile"/></dd>
            </dl>
            <dl class="apply_form">
            	<dt><em class="red">*加盖企业公章的营业执照：</em></dt>
                <dd>
                	<form id="auth_upload_pic_form" method="post" enctype="multipart/form-data">
                		<input type="file" id="upload_img" onchange="uploadBusinessLicense();" name="myFile" />
                	</form>	
                	<input type="hidden" id="post_status_media_url" name="businessLicense"/>
                    <img style="display:none" id="post_status_media_img" src=""/>
                </dd>	
            </dl>
            <dl class="apply_form">
            	<dt>&nbsp;</dt>
                <dd class="tips">请上传加盖企业公章的营业执照最新年检副本的扫描件或照片！</dd>
            </dl>
            <dl class="apply_form">
            	<dt><em class="red">*</em>营业执照注册号：</dt>
                <dd><input type="text" class="W_inputStp" name="registerNo"/></dd>
            </dl>
            <dl class="apply_form">
            	<dt>&nbsp;</dt>
                <dd class="tips">当您的微博昵称与营业执照上企业名称不同时，您需要上传其他补充材料查看补充材料，支持jpg,png,gif格式，大小不超过2M/张 </dd>
            </dl>
            <dl class="apply_form">
            	<dt>&nbsp;</dt>
                <dd class="btn"><a id="add-experties-auth-btn" href="javascript:void(0);"></a></dd>
            </dl>
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