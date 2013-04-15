<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【<s:property value="groupInfo.cityStr" />】<s:property value="groupInfo.title" />-集集组</title>
<meta name="keywords" content="<s:property value="groupInfo.cityStr" /> - <s:property value="groupInfo.districtStr"/><s:property value="groupInfo.address"/>" />
<meta name="description"
	content="【<s:property value="groupInfo.cityStr" />】<s:property value="groupInfo.title" /> 活动开始时间 <s:date name="groupInfo.startDate" format="yyyy年MM月dd日 HH:mm" />(<s:property value="groupInfo.startDayOfWeek"/>)
	【<s:property value="groupInfo.cityStr" />】<s:property value="groupInfo.title" /> 活动结束时间<s:date name="groupInfo.endDate" format="yyyy年MM月dd日 HH:mm" />(<s:property value="groupInfo.endDayOfWeek"/>)
	【<s:property value="groupInfo.cityStr" />】<s:property value="groupInfo.title" /> 活动地点：<s:property value="groupInfo.cityStr"/><s:property value="groupInfo.districtStr"/><s:property value="groupInfo.address"/>
	【<s:property value="groupInfo.cityStr" />】<s:property value="groupInfo.title" /> 发起人：<s:property value="groupInfo.userName"/>
	【<s:property value="groupInfo.cityStr" />】<s:property value="groupInfo.title" /> 活动费用：<s:if test="groupInfo.cost == 0.0">免费</s:if><s:else><s:property value="groupInfo.cost" /></s:else>"/>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/userCard.css"/>
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/jquery.autocomplete.css"/>
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
<script type="text/javascript" src="${JS_PATH}/group.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/validate.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
<script type="text/javascript">
window.onload = function ()
{
	
	atRelease('', 'text', {box : 'publisherTop_box',list : 'publisherTop_list'});
};
</script>
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main_sub">
  <div class="W_main_sub_bg clearfix"> 
    <!--内容部分-->
    <div class="plc_main_sub">
     <!--左边-->
      <div class="W_main_sub_b">
      	<div class="A_head_con">
        	<div class="ev_detail_top">
            	<div class="ev_detail_img"><img src="<s:property value="groupInfo.posterImgUrl"/>" width="180" height="240" /></div>
                <div class="ev_detail_cont">
                	<h3 class="ev_title">
                    	<p><s:property value="groupInfo.title"/></p>
                    		<s:if test="groupStatus==3">
	                    		<span class="ev_Start">正在报名</span>
	                    	</s:if>
                        	<s:elseif test="groupInfo.groupStatus==0">
	                    		 <span class="ev_Start">即将开始</span>
	                    	</s:elseif>
	                    	<s:elseif test="groupInfo.groupStatus==1">
	                    		 <span class="ev_Start">正在进行</span>
	                    	</s:elseif>
	                    	<s:elseif test="groupInfo.groupStatus==2">
	                    		 <span class="ev_over">已经结束</span>
	                    	</s:elseif>
                    </h3>
                    <s:if test="groupInfo.applyEndDate != null">
                    <p class="txt">
                    	<span>报名截止时间：</span>
                        <span><s:date name="groupInfo.applyEndDate" format="yyyy-MM-dd HH:mm" />（<s:property value="groupInfo.ApplyEndDayOfWeek"/>）</span>
                    </p>
                    </s:if>
                    <p class="txt">
                    	<span>活动开始时间：</span>
                        <span><s:date name="groupInfo.startDate" format="yyyy-MM-dd HH:mm" />（<s:property value="groupInfo.startDayOfWeek"/>）</span>
                    </p>
                    <p class="txt">
                    	<span>活动结束时间：</span>
                        <span><s:date name="groupInfo.endDate" format="yyyy-MM-dd HH:mm" />（<s:property value="groupInfo.endDayOfWeek"/>）</span>
                    </p>
                    <p class="txt">
                    	<span>允许参加人数：</span>
                        <span><s:property value="groupInfo.applyNumStr"/></span>
                        <span style="color:red"><s:property value="groupInfo.parentsLimitStr"/></span>
                    </p>
                    <p class="txt">
                    	<span>报名人数：</span>
                        <span><s:property value="groupInfo.enterNum"/>人</span>
                    </p>
                    <p class="txt">
                    	<span>地点：</span>
                        <span><s:property value="groupInfo.cityStr"/><s:property value="groupInfo.districtStr"/><s:property value="groupInfo.address"/></span>
                    </p>
                    <s:if test="groupInfo.ageRange != null">
                    <p class="txt">
                    	<span>年龄范围：</span>
                        <span><s:property value="groupInfo.ageRange"/></span>
                    </p>
                    </s:if>
                    <p class="txt">
                    	<span>发起人：</span>
                        <span><a href="/u/<s:property value="groupInfo.userId"/>"><s:property value="groupInfo.userName"/></a>
                        <a target="_blank" href="/auth/">
	                  		<s:if test="groupInfo.vFlag==1">
			              		<ins class="vip"></ins>
			              	</s:if>
			              	<s:if test="groupInfo.enterpriseFlag==1">
			              		<ins class="vip_agency"></ins>
			              	</s:if>
	                  	</a>
                        </span>
                    </p>
                    <p class="txt">
                    	<span>活动审核：</span>
                        <span><s:property value="groupInfo.enterTypeStr"/></span>
                    </p>
                    <p class="txt">
                    	<span>费用：</span>
                        <span>
                        	<s:if test="groupInfo.cost==0">
                        		免费
                        	</s:if>
                        	<s:else>
                        		<s:property value="groupInfo.cost"/>
                        	</s:else>
                        </span>
                    </p>
                    <div class="ev_detail_btn">
                        <!-- Baidu Button BEGIN -->
                        <div id="bdshare" class="bdshare_b" style="line-height: 12px;">
                        <img src="http://bdimg.share.baidu.com/static/images/type-button-1.jpg?cdnversion=20120831" />
                        </div>
                        <script type="text/javascript" id="bdshare_js" data="type=button&amp;uid=6560792" ></script>
                        <script type="text/javascript" id="bdshell_js"></script>
                        <script type="text/javascript">
                        document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
                        </script>
                        <!-- Baidu Button END -->
                        <s:if test="%{groupInfo.canJoin(sessionUserInfo)}">
                    		<a href="javascript:show_join_event(<s:property value="groupInfo.groupId"/>);" class="W_btn_d" id="join_btn" onclick="return show_login(this);">我要参加</a>
                   		</s:if>
                   		<s:if test="%{groupInfo.canCancel(sessionUserInfo)}">
                   		 <span class="Cancel_btn">
                   		 	<s:if test='groupInfo.groupUsers.joinStatus=="GROUP_JOIN_STATUS_AUDIT"'>
                   		 		正在审核,
                   		 	</s:if>
                   		 	<s:elseif test='groupInfo.groupUsers.joinStatus=="GROUP_JOIN_STATUS_WAIT"'>
                   		 		等待区域,
                   		 	</s:elseif>
                   		 	<s:else>
                   		 		已报名, 
                   		 	</s:else>
                   		 	<a onclick="show_Cancel(<s:property value="groupId"/>)" href="javascript:void(0);">取消</a></span>
                    	</s:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="event_page_main">
        	<div class="ev_idSpTitle">
            	<h2>活动介绍</h2>
            </div>
            <div class="pl_event_eventIntro">
            	<s:property value="groupInfo.groupDescHtml" escape="false"/>
            </div>
        </div>
        <div class="pl_event_eventPhoto">
        	<div class="ev_idSpTitle">
            	<h2>相关照片<span>（<a href="/group/photo.jspa?groupId=<s:property value="groupId"/>">全部<i><s:property value="photoPage.recordCnt"/></i>张照片</a> 
            	<s:if test="%{groupInfo.canUpload(sessionUserInfo)}">
            	 <em>|</em> <a href="javascript:void(0);" onclick="show_box_upload_img()">添加照片</a>
            	</s:if>
            	 ）</span></h2>
            </div>
            <div class="ev_album_photolist">
            	<ul>
            		<s:iterator value="photoPage.result">
	                	<li><a href="/group/photoDetail.jspa?photoId=<s:property value="photoId"/>&groupId=<s:property value="groupId"/>"><img src="<s:property value="smallImgUrl"/>" width="90" height="90" /></a></li>
            		</s:iterator>
                </ul>
            </div>
        </div>
        <div class="pl_event_publisher sub">
        	<div class="title"><span id="num140" class="color" style="display:none">发言请遵守社区公约，委员会名单，还可以输入<em>138</em>字</span>我有话说</div>
             <form id="post_status_form">
              <input type="hidden" id="post_status_media_type" name="mediaType"  />
              <input type="hidden" id="post_status_media_url" name="mediaUrl" />
              <input type="hidden" id="post_status_is_sync" name="isSync" />
              <input type="hidden" id="post_status_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
              <input type="hidden" id="post_status_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_GROUP" />
              <input type="hidden" id="groupId" name="groupId" value="<s:property value="groupInfo.groupId"/>" />
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
        <div id="publisherTop_list" class="publisherTop_list">
              <ul>
                <li class="first">选择最近@的人或直接输入</li>
                <li class="hove"><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
                <li><div class="pic"><img src="${IMG_PATH}/people/pic_03.jpg" width="30" height="30" /></div><div class="name">wh乡下人</div></li>
              </ul>
            </div>
        <div class="pl_event_content">
        	<div id="showmore_top" class="WB_feed"><a class="notes" style="display:none" href="javascript:void(0);">有49条新微博，点击查看</a></div>
        	<s:iterator value="statusPage.result">
            	<%@include file="/common/status-inc.jsp" %>
            </s:iterator>
            <s:if test="statusPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="statusPage.befPageNum>0 && statusPage.curPageNum!=1">      	
						   <a href="/group/<s:property value="groupId"/>/?page=<s:property value="statusPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="statusPage.pageList" id="curPage">
							<s:if test="#curPage==statusPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/group/<s:property value="groupId"/>/?page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="statusPage.curPageNum!=statusPage.pageCnt">
						   <a href="/group/<s:property value="groupId"/>/?page=<s:property value="statusPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
      </div>
        </div>
      </div>
      <!--右边-->
        <%@include file="/group/inc/group-right-inc.jsp" %>
      <!--右边END-->
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 参加集集组弹出层 -->
<%@include file="/group/layer/enter-group-layer.jsp" %>
<!-- 取消参加集集组碳层 -->
<%@include file="/group/layer/cancel-enter-group-layer.jsp" %>
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
<!--上传图片-->
<%@include file="/layer/img-upload-layer.jsp"%>
<!-- 删除集集组弹层 -->
<%@include file="/group/layer/delete-group-layer.jsp"%>
<!-- 上传照片弹出层 -->
<%@include file="/group/layer/upload-photo-layer.jsp"%>
<!-- 选择用户相册弹出层 -->
<%@include file="/group/layer/choose-album-layer.jsp"%>
<!-- 选择相册照片弹出层 -->
<%@include file="/group/layer/choose-photo-layer.jsp"%>
<!-- 本地上传照片弹出层 -->
<%@include file="/group/layer/local-upload-photo-layer.jsp"%>
<!-- 未填写内容为空弹层 -->
<%@include file="/status/layer/none-box-layer.jsp"%>
<!-- 删除微博弹层 -->
<%@include file="/status/layer/del-status-layer.jsp"%>
<!-- 删除评论弹层 -->
<%@include file="/status/layer/del-comment-layer.jsp"%>

<script language="javascript">
$(document).ready(function(){
	prepareAutoComplete($(this).find("#text"));
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
});

$(document).ready(function(){
	//show_badge();
});
</script>
</body>
</html>
