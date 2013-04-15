<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${CSS_PATH}/jquery.jcarousel.css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
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
<script type="text/javascript">
jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel();
});
</script>
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
        <div class="ev_album_title clearfix">
            <h3 class="title_word">
                <em class="icon"></em>活动照片 - <a href="/group/<s:property value="groupId"/>"><s:property value="groupInfo.title"/></a>
            </h3>
            <a class="rgt_btn" href="/group/<s:property value="groupId"/>">«返回当前活动页</a>
        </div>
        
        <div class="A_2col_left">
        	<div class="ev_album_single">
            	<div class="pic_con">
                    <div class="pic_data clearfix">
                        <div class="p_num"></div>
                        <div class="p_opt">
                            <a class="ic_original" href="<s:property value="photoInfo.originalImgUrl"/>" target="_blank">查看大图</a>
                            <a href="/group/photo.jspa?groupId=<s:property value="groupId"/>" class="ic_pic">浏览全部照片</a>
                        </div>
                    </div>
                    <div class="pic_zone">
                        <a node-type="prev" class="p_prv" href="javascript:void(0);" groupid="<s:property value="groupId"/>" photoid="<s:property value="prePhotoInfo.photoId"/>" id="pre-photo-btn" style="height: 840px;">上一页</a>
                        <a node-type="next" class="p_nxt" href="javascript:void(0);" groupid="<s:property value="groupId"/>" photoid="<s:property value="nextPhotoInfo.photoId"/>" id="next-photo-btn" style="height: 840px;">下一页</a>
                        <span class="pic">
                            <img alt="" src="<s:property value="photoInfo.imgUrl"/>" width="860"/>
                        </span>
                    </div>
                    <div class="pic_info" style="display:none"/>
						<a action-type="usercard" namecard="true" uid="2535674244" nick-name="磨刀霍霍向少年" href="/h/2535674244" target="_blank">磨刀霍霍向少年</a>：感觉自己青春依然、<em class="time">（12月19日 16:53）</em>
					</div>
                </div>
                
            </div>
           
        </div>
        </div>
    </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框--> 


<script language="javascript">
$(document).ready(function(){
	/**
	 * 上一张照片
	 */
	$("#pre-photo-btn").click(function(){
		var photoId = $(this).attr("photoid");
		var groupId = $(this).attr("groupid");
		if(photoId == ""){
			alert("没有上一张照片");
			return;
		}
		location.href="/group/photoDetail.jspa?photoId="+photoId+"&groupId="+groupId;
	});
	
	/**
	 * 下一张照片
	 */
	$("#next-photo-btn").click(function(){
		var photoId = $(this).attr("photoid");
		var groupId = $(this).attr("groupid");
		if(photoId == ""){
			alert("没有下一张照片");
			return;
		}
		location.href="/group/photoDetail.jspa?photoId="+photoId+"&groupId="+groupId;
	});
});
</script>
</body>
</html>
