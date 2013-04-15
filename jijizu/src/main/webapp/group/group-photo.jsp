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
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main_sub2">
    <!--内容部分-->
    <div class="W_main_pic clearfix">
    	<div class="album_data_box clearfix">
        	<p class="album_data clearfix">
            	<span class="a_tit"><a href="/group/<s:property value="groupId"/>"><img class="tit_pic" alt="<s:property value="groupInfo.title"/>" title="<s:property value="groupInfo.title"/>" src="<s:property value="groupInfo.smallPosterImgUrl"/>" width="50"/></a><a href="/group/<s:property value="groupId"/>"><s:property value="groupInfo.title"/></a>-活动照片<em>(<s:property value="photoPage.recordCnt"/>)</em></span>
                <span class="a_plus">
                	<s:if test="%{groupInfo.canUpload(sessionUserInfo)}">
                		<a href="javascript:void(0);" class="upbtn_small" onclick="show_box_upload_img()">上传照片</a>
                	</s:if>
                </span>
            </p>
        </div>
       <div class="imgs img_padding clearfix">
            <div class="ev_album clearfix">
            	<s:iterator value="photoPage.result">
	                <div class="a_item"><a href="/group/photoDetail.jspa?photoId=<s:property value="photoId"/>&groupId=<s:property value="groupId"/>"><span><img src="<s:property value="imgUrl"/>" /></span></a></div>
            	</s:iterator>
            </div>
         </div>
         <s:if test="photoPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="photoPage.befPageNum>0 && photoPage.curPageNum!=1">      	
						   <a href="/group/photo.jspa?groupId=<s:property value="groupId"/>&page=<s:property value="photoPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="photoPage.pageList" id="curPage">
							<s:if test="#curPage==photoPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/group/photo.jspa?groupId=<s:property value="groupId"/>&page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="photoPage.curPageNum!=photoPage.pageCnt">
						   <a href="/group/photo.jspa?groupId=<s:property value="groupId"/>&page=<s:property value="photoPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
    </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 上传照片弹出层 -->
<%@include file="/group/layer/upload-photo-layer.jsp"%>
<!-- 选择用户相册弹出层 -->
<%@include file="/group/layer/choose-album-layer.jsp"%>
<!-- 选择相册照片弹出层 -->
<%@include file="/group/layer/choose-photo-layer.jsp"%>
<!-- 本地上传照片弹出层 -->
<%@include file="/group/layer/local-upload-photo-layer.jsp"%>
<script language="javascript">

</script>
</body>
</html>
