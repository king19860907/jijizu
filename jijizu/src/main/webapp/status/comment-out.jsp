<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发出的评论-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
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
            	<a href="/commentIn.jspa">收到的评论</a>
                <a href="/commentOut.jspa" class="hover">发出的评论</a>
            </div>
          <div class="pl_content">
           <s:iterator value="commentsWithStatusPage.result">
           	 <div class="WB_feed_type" id="comment_list_<s:property value="commentId"/>">
              <div class="WB_feed_datail">
                <div class="WB_face"> <a href="/u/<s:property value="userId"/>" target="_blank"><img class="USER_CARD" uid="<s:property value="userId"/>" src="<s:property value="headImgUrl"/>" width="50" height="50" /></a> </div>
                <div class="WB_detail">
                  <div class="WB_func">
                  	<div class="Comment_detail"><a target="_blank" class="USER_CARD" uid="<s:property value="userId"/>" href="/u/<s:property value="userId"/>"><s:property value="name"/></a>
                  	<a target="_blank" href="/auth/">
                  		<s:if test="userInfo.vFlag==1">
		              		<ins class="vip"></ins>
		              	</s:if>
		              	<s:if test="userInfo.enterpriseFlag==1">
		              		<ins class="vip_agency"></ins>
		              	</s:if>
                  	</a>
                  	:<s:property value="content" escape="false"/> (<s:date name="postTime" format="yyyy-MM-dd HH:mm" />)</div>
                    <div class="Comment_detail">
                    	<s:if test="comment == null">
                    		评论 <a href="/u/<s:property value="status.userId"/>" class="USER_CARD" uid="<s:property value="status.userId"/>"><s:property value="status.name"/></a> 的微博：<a href="/status/<s:property value="statusId"/>">"<s:property value="%{status.getContentWithOutHtmlTag(32,'...')}"/>"</a>
                    	</s:if>
                    	<s:else>
                    		回复 <a href="/u/<s:property value="comment.userId"/>" class="USER_CARD" uid="<s:property value="comment.userId"/>"><s:property value="comment.name"/></a> 的评论：<a href="/status/<s:property value="statusId"/>">"<s:property value="%{comment.getContentWithOutHtmlTag(32,'...')}"/>"</a>
                    	</s:else>
                    </div>
                    <div class="WB_handle"><span class="hover"><a class="del" name="del_comment" commentid="<s:property value="commentId"/>" href="javascript:void(0);" >删除</a></span></div>
                    <div class="WB_from"><em class="S_txt2">来自</em> <a href="<s:property value="sourceUrl"/>" target="_blank" class="WB_where"><s:property value="sourceText"/></a> </div>
                  </div>
                </div>
              </div>
            </div>
           </s:iterator>
          </div>
          <s:if test="commentsWithStatusPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="commentsWithStatusPage.befPageNum>0 && commentsWithStatusPage.curPageNum!=1">      	
						   <a href="/commentOut.jspa?page=<s:property value="commentsWithStatusPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="commentsWithStatusPage.pageList" id="curPage">
							<s:if test="#curPage==commentsWithStatusPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/commentOut.jspa?page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="commentsWithStatusPage.curPageNum!=commentsWithStatusPage.pageCnt">
						   <a href="/commentOut.jspa?page=<s:property value="commentsWithStatusPage.aftPageNum" />" class="btn_page">下一页</a>
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
  <script type="text/javascript">
	$(document).ready(function(){
		prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
		$("[name='del_comment']").click(function(){
			show_comment_del();
			var commentId = $(this).attr("commentId");
			$("#confirm_del_comment").attr("commentId",commentId);
		});

		$("#confirm_del_comment").click(function(){
			var commentId = $(this).attr("commentId");
			deleteComment(commentId,"#comment_list_"+commentId);
		});
	});
	</script>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->

<!-- 未填写内容为空弹层 -->
<%@include file="/status/layer/none-box-layer.jsp"%>
<!-- 删除评论弹层 -->
<%@include file="/status/layer/del-comment-layer.jsp"%>
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
</body>
</html>
