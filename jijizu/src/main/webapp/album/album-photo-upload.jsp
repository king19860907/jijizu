<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/jquery.plupload.queue.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/plupload.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/plupload.gears.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/plupload.flash.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.plupload.queue.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/plupload.full.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/zh-cn.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/album.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main">
  <div class="W_main_bg clearfix"> 
    <!--菜单部分-->
    <%@include file="/status/inc/left-tab-inc.jsp" %>
    <!--内容部分-->
    <div class="plc_main">
      <div class="W_main_a">
        <div class="W_main_c">
        	<div class="tab">
            	<a href="javascript:void(0);" class="hover">上传照片</a>
            </div>
          <div class="pl_content">
          	<div class="m_browse_menu">
            	<span class="sa">上传至：</span>
                <div class="se"><select name="" id="choose-upload-album" onchange="chooseUploadAlbum()">
                  <s:set value="albumId" name="paraAlbumId"></s:set>
                  <s:iterator value="albumPage.result"  var="album">
	                  <option <s:if test="#paraAlbumId==albumId">selected="selected" </s:if> value="<s:property value="albumId"/>"><s:property value="name"/></option>
                  </s:iterator>
                </select></div>
                <span class="crt"><a href="javascript:void(0);" class="M_btn_c" onclick="show_CreateAlbums()">创建专辑</a></span>
            </div>
            <div class="flash_up">
            	<div class="flash_title">
                	<div class="default"><span class="txt">支持jpg/gif/png/jpeg格式，单张不超过5M，单次上传最多支持120张</span><div class="clear"></div></div>
                    <div class="uploaded"></div>
                </div>
                <div id="uploader" class="flash_content">
					<p>You browser doesn't have Flash, Silverlight, Gears, BrowserPlus or HTML5 support.</p>
				</div>
				<div class="flash_title">
					<div class="default">
		            	<span class="btn"><a href="javascript:void(0);" onclick="return false;" albumid="<s:property value="albumId"/>" id="confirm-upload-btn">确认</a></span>
					</div>
				</div>
            </div>
          </div>
        </div>
        
        <!--右边-->
         <%@include file="/status/inc/status-right-inc.jsp" %>
        <!--右边END-->
      </div>
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 创建相册弹出层 -->
<%@include file="/album/layer/create-album-layer.jsp" %>
<!-- 创建相册成功弹出层 -->
<%@include file="/album/layer/create-album-success-layer.jsp" %>
<script language="javascript">
$(document).ready(function(){
	$("#uploader").pluploadQueue({
		// General settings
		runtimes : 'gears,flash,silverlight,browserplus,html5',
		url : '/album/fileUpload.jspa?albumId='+<s:property value="albumId"/>,
		max_file_size : '5mb',
		unique_names : true,

		// Resize images on clientside if we can
		resize : {quality : 100},

		// Specify what files to browse for
		filters : [
			{title : "Image files", extensions : "jpg,gif,png"}
		],

		// Flash settings
		flash_swf_url : '/js/plupload.flash.swf',

		// Silverlight settings
		silverlight_xap_url : '/js/plupload.silverlight.xap'
	});
	
	$(".plupload_header").remove();
	$(".plupload_droptext").remove();
	
	$("#confirm-upload-btn").click(function(){
		var length = $("#uploader_filelist > li").length;
		if(length == 0){
			alert("请选择要上传的图片");
			return;
		}
		var statusStr = $(".plupload_total_status").html();
		var status = statusStr.substring(0,statusStr.length-1);
		if(status != 100){
			alert("上传尚未完成，请等待完成后点击确认");
			return;
		}
		var albumId = $(this).attr("albumid");
		location.href="/album/upload2.jspa?albumId="+albumId+"&length="+length;
	});
});
function chooseUploadAlbum(){
	var albumId = $("#choose-upload-album").val();
	location.href="/album/upload.jspa?albumId="+albumId;
}
</script>
</body>
</html>
