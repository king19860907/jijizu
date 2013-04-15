<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>@我的评论-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
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
            	<a href="/atMeStatus.jspa">@我的微博</a>
                <a href="/atMeComment.jspa" class="hover">@我的评论</a>
            </div>
          <div class="pl_content">
           	 <%@include file="/status/inc/comment-in-inc.jsp" %>
          </div>
          <s:if test="commentsWithStatusPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="commentsWithStatusPage.befPageNum>0 && commentsWithStatusPage.curPageNum!=1">      	
						   <a href="/atMeComment.jspa?page=<s:property value="commentsWithStatusPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="commentsWithStatusPage.pageList" id="curPage">
							<s:if test="#curPage==commentsWithStatusPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/atMeComment.jspa?page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="commentsWithStatusPage.curPageNum!=commentsWithStatusPage.pageCnt">
						   <a href="/atMeComment.jspa?page=<s:property value="commentsWithStatusPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
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
<!-- 未填写内容为空弹层 -->
<%@include file="/status/layer/none-box-layer.jsp"%>
<!-- 删除评论弹层 -->
<%@include file="/status/layer/del-comment-layer.jsp"%>
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
<script language="javascript">
$(document).ready(function(){
	prepareAutoComplete($(this).find("[name='content']"));
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
});
</script>
</body>
</html>