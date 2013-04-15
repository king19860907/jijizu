<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="userInfo.name"/>的微博-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/jquery.autocomplete.css"/>
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
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
        <div class="jjz_left_box">
           <%@ include file="/status/inc/user-detail-inc.jsp" %> 
          <div class="pl_event_content">
           <s:if test="%{userInfo.canViewStatus(sessionUserInfo)}">
           	<s:iterator value="statusPage.result">
            	<%@include file="/common/status-inc.jsp" %>
            </s:iterator>
             <s:if test="statusPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="statusPage.befPageNum>0 && statusPage.curPageNum!=1">      	
						   <a href="/u/<s:property value="userId"/>?page=<s:property value="statusPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="statusPage.pageList" id="curPage">
							<s:if test="#curPage==statusPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/u/<s:property value="userId"/>?page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="statusPage.curPageNum!=statusPage.pageCnt">
						   <a href="/u/<s:property value="userId"/>?page=<s:property value="statusPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
           </s:if>
           <s:else>
           	该用户尚未公开微博内容
           </s:else>
          </div>
        </div>
      </div>
      <!--右边-->
      <div class="W_main_sub_r">
        <div class="others_people">
          <!-- 共同好友 -->        	
          <div class="WB_right_module" id="common_friends">
          </div>
          <!-- 他的好友 -->
          <div class="WB_right_module" id="friends">
          </div>
          <!-- 他的集集组 -->
          <div class="WB_right_module" id="user-group">
          </div>
        </div>
      </div>
      <!--右边END--> 
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!-- 取消好友弹层 -->
<%@include file="/user/layer/cancel-follow-layer.jsp"%>
<!-- 发送私信弹层 -->
<%@include file="/mail/layer/create-mail-layer.jsp"%>
<!-- 未填写内容为空弹层 -->
<%@include file="/status/layer/none-box-layer.jsp"%>
<!-- 删除评论弹层 -->
<%@include file="/status/layer/del-comment-layer.jsp"%>
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
<!-- 删除微博弹层 -->
<%@include file="/status/layer/del-status-layer.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	prepareUserCardTip($(this).find(".USER_CARD"));
	findCommonFirends(<s:property value="userInfo.userId"/>);
	findFirends(<s:property value="userInfo.userId"/>);
	findUserGroup(<s:property value="userInfo.userId"/>);
});
</script>
</body>
</html>
