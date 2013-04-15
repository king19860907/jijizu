<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/jquery.autocomplete.css"/>
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-ui-1.8.18.custom.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.ui.draggable.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
<script type="text/javascript">
$(document).ready(function(){
	prepareAutoComplete($(this).find("#text"));
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
	preparePostStatus($("#text"),$("#post_status_btn"));
});
</script>
</head>
<body>
<!--<body onload="firstLoad()">-->
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
          <div class="pl_publisherTop">
            <div class="box">
              <div class="title">
              	<span id="num140" style="display:none">前任现任人手一个，李晨泡妞神器石头心走红&nbsp;&nbsp;24小时热博</span>
                <div class="publishTitle"></div>
              </div>
              <form id="post_status_form">
              <input type="hidden" id="post_status_media_type" name="mediaType"  />
              <input type="hidden" id="post_status_media_url" name="mediaUrl" />
              <input type="hidden" id="post_status_is_sync" name="isSync" />
              <input type="hidden" id="post_status_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
              <input type="hidden" id="post_status_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_STATUS" />
              <div class="boxf" id="publisherTop_box">
                <textarea id="text" name="content" rows="6" cols="60" onfocus="publisherTop_box_focus()" onblur="publisherTop_box_blur()"></textarea>
                <div class="fbcg"></div>
                <span class="arrow"></span>
              </div>
              </form>
              <div class="btnf"><a class="btn" id="post_status_btn" href="javascript:void(0);"></a>
                <div class="icon_detail"> <a name="face_status" type="status" href="javascript:void(0);"><i class="icon_sw_face"></i>表情</a> <a name="icon_sw_img" href="javascript:void(0);"><i class="icon_sw_img"></i>图片</a></div>
              </div>
            </div>
            <div id="publisherTop_list" class="publisherTop_list"	>
              <ul>
                <li class="first">选择最近@的人或直接输入</li>
              </ul>
            </div>
          </div>
          <div class="pl_advertisement" id="pl_advertisement"><a href="/diary/"><img src="images/ad.jpg" width="534" height="80" /></a><a href="javascript:void(0);" class="close"></a></div>
          <div class="pl_group">
            <div class="title"> <a href="/group/create.jspa" target="_blank">发起集集组</a>
              <p>正在招募的集集组</p>
            </div>
            <div class="pl_group_main">
              <div class="top"></div>
              <ul>
              	<s:if test="groupList != null && groupList.size > 0">
	              	<s:iterator value="groupList">
		                <li><a href="/u/<s:property value="userId"/>" target="_blank" class="name_group"><s:property value="userName"/></a> 发起了 <a href="/group/<s:property value="groupId"/>" target="_blank"><s:property value="title"/></a> <s:date name="startDate" format="yyyy年MM月dd日 HH:mm" />在<s:property value="cityStr"/><s:property value="districtStr"/></li>
	              	</s:iterator>
              	</s:if>
              	<s:else>
              		<li>没有正在招募的集集组</li>
              	</s:else>
              </ul>
              <div class="pl_group_btn"><a href="/group/" target="_blank">更多集集组>></a></div>
              <div class="bottom"></div>
            </div>
          </div>
          
          <div class="pl_content">
            <div class="WB_feed" id="showmore_top" style="display:none"><a href="javascript:void(0);" onclick="findNewStatus();" class="notes">有<font id="new-status-count">0</font>条新微博，点击查看</a></div>
            <s:iterator value="statusPage.result">
            	<%@include file="/common/status-inc.jsp" %>
            </s:iterator>
            
            <s:if test="statusPage.curPageNum < statusPage.pageCnt">
	            <div class="WB_feed" id="showmore_btn" page="2" onclick="showmore()">
	            	<a href="javascript:void(0);" class="notes">更多好友动态</a>
	            </div>
            </s:if>
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
<script type="text/javascript">
	$(document).ready(function(){
		setInterval(countNewStatus, 1 * 45 * 1000);//每45秒刷新一次
	});
</script>
<!--弹出框-->
<div class="W_layer">
  <div class="wrap" style="position:relative">
    <div class="tips_01">
      <div class="pic"><a href="javascript:void(0);">下一条</a></div>
    </div>
    <div class="tips_02">
      <div class="pic"><a href="javascript:void(0);">下一条</a></div>
    </div>
    <div class="tips_03">
      <div class="pic"><a href="javascript:void(0);">下一条</a></div>
    </div>
    <div class="tips_04">
      <div class="pic"><a href="javascript:void(0);">知道啦</a></div>
    </div>
  </div>
</div>
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
<!--上传图片-->
<%@include file="/layer/img-upload-layer.jsp"%>
<!-- 未填写内容为空弹层 -->
<%@include file="/status/layer/none-box-layer.jsp"%>
<!-- 删除微博弹层 -->
<%@include file="/status/layer/del-status-layer.jsp"%>
<!-- 删除评论弹层 -->
<%@include file="/status/layer/del-comment-layer.jsp"%>
</body>
</html>
