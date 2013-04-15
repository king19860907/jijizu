<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="/favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.Jcrop.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/user.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main bg3">
  <div class="W_main_bg clearfix"> 
    <!--菜单部分-->
    <%@include file="/user/set/inc/left-inc.jsp" %>
    <!--内容部分-->
    <div class="W_main_r3">
      <div class="MIB_setup">
        <div class="setup_main">
          <div class="index_title">
            <h3>修改头像</h3>
            <h4>上传真人头像，有助于你的好友找到你哟。</h4>
          </div>
          <%@include file="/user/set/inc/data-integrity-inc.jsp" %>
          <div class="setting_box">
       	  <div class="setting_btn">
            	选择图片: <span>仅支持JPG、GIF、PNG，且文件小于2M</span>
            	<form id="FileUploadAction" method="post" action="/status/fileUploadStatusAjax.jspa" enctype="multipart/form-data">
     				<input type="file" name="myFile" value="" id="FileUploadAction_myFile" onchange="updateImage();" />
            		<input type="hidden" name="rate" value="1" />
            	</form>
                
          </div>
          <div class="setting_default"><img src="${IMG_PATH}/set_photo.jpg" width="717" height="269" /></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<script type="text/javascript">	
	
</script>

</body>
</html>
