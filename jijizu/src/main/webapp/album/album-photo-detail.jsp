<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${CSS_PATH}/jquery.jcarousel.css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.jcarousel.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/album.js${JS_EDITION}"></script>
<script type="text/javascript">
jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel();
});
</script>
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
        	<div class="upload_title">
            	<a href="/album/?userId=<s:property value="belongUser.userId"/>" class="hover"><s:property value="belongUser.name"/>的专辑</a><em>&gt;</em><a href="/album/photo.jspa?albumId=<s:property value="albumInfo.albumId"/>" class="hover"><s:property value="albumInfo.name"/></a><em>&gt;</em><span>当前照片</span>
            </div>
          <div class="pl_content">
          	<div class="F_cols_main">
            	<a style="height:100%;" id="pre-photo-btn" photoid="<s:property value="prePhotoInfo.photoId"/>" href="javascript:void(0);" class="p_prv" node-type="prev">上一页</a>
                <a style="height:100%;" id="next-photo-btn" photoid="<s:property value="nextPhotoInfo.photoId"/>" href="javascript:void(0);" class="p_nxt" node-type="next">下一页</a>
                <span class="pic">
                    <img width="534" src="<s:property value="photoInfo.imgUrl"/>" alt="" class="ximg"/>
                </span>
            </div>
            <div class="attr_box">
            	<p class="time M_txtb">
                    来自<a href="<s:property value="photoInfo.status.sourceUrl"/>"><s:property value="photoInfo.status.sourceText"/></a>
                    	上传于<span class="date">
                    		<s:if test="photoInfo.overOneDay">	
				                    <s:date name="photoInfo.createDate" format="yyyy-MM-dd" />
				                </s:if>
				                <s:else>
				                    <s:date name="photoInfo.createDate" nice="true" />
				                </s:else>
                    	</span>
                    <span class="del">
                    	<s:if test="%{photoInfo.canDelete(sessionUserInfo)}">
                    		<a href="javascript:void(0)" onclick="show_photo_del(<s:property value="photoInfo.photoId"/>,'/album/photo.jspa?albumId=<s:property value="photoInfo.albumId"/>')">删除照片</a>
                    	</s:if>
                    </span>
                </p>
                <div class="mark_box">
                    <a class="z_left2" href="javascript:void(0)">向左转</a>
                    <a class="z_right2" href="javascript:void(0)">向右转</a>
                    <a class="M_transmit_sm" href="javascript:void(0)" onclick="show_micro_blog()"><s:property value="photoInfo.status.forwardNum"/></a>
                    <a class="M_comment_sm" href="javascript:void(0)" onclick=""><s:property value="photoInfo.status.commentNum"/></a>
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
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 删除照片弹出层 -->
<%@include file="/album/layer/delete-photo-layer.jsp" %>
<script language="javascript">

</script>
</body>
</html>
