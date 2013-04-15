<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<s:iterator value="commentsPage.result">
	<dl class="comment_list" id="comment_list_<s:property value="commentId"/>">
		<dt>
			<a href="javascript:void(0);" target="_blank"><img
				src="<s:property value="headImgUrl"/>" width="30" height="30" /></a>
		</dt>
		<dd>
			<a href="javascript:void(0);" target="_blank" class="USER_CARD" uid="<s:property value="userId"/>"><s:property value="name"/></a>
			<a target="_blank" href="/auth/">
                  		<s:if test="vFlag==1">
		              		<ins class="vip"></ins>
		              	</s:if>
		              	<s:if test="enterpriseFlag==1">
		              		<ins class="vip_agency"></ins>
		              	</s:if>
             </a>
			:<s:property value="content" escape="false"/>
			(<s:if test="overOneDay">	
                    		<s:date name="postTime" format="yyyy-MM-dd HH:mm:ss" />
                    	</s:if>
                    	<s:else>
                    		<s:date name="postTime" nice="true" />
                    	</s:else>)
			<div class="info">
				<span class="hover">
					<s:if test="sessionUserInfo!=null && (sessionUserInfo.userId==userId || sessionUserInfo.userId==statusFwdSrc.userId)">
						<a href="javascript:void(0);" commentId="<s:property value="commentId"/>" name="del_comment" class="del">删除</a><i class="S_txt3">|</i>
					</s:if>
				</span> <a href="javascript:void(0);" name="huifu" userName="<s:property value="name"/>(<s:property value="nickName"/>)" commentId="<s:property value="commentId"/>" statusId="<s:property value="statusId"/>" class="huifu">回复</a>
			</div>
			<form id="post-comment-form-<s:property value="commentId"/>">
			<div class="repeat">
				<div class="WB_arrow"></div>
				<div class="clearfix">
					<div class="relative" id="publisherTop_box_<s:property value="commentId"/>">
						<textarea name="content" id="reply_content_<s:property value="commentId"/>" rows="" cols="" action-type="check">回复@<s:property value="name"/>(<s:property value="nickName"/>):</textarea>
					</div>
             	    <input type="hidden" id="post_comment_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
              		<input type="hidden" id="post_comment_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_STATUS" />
              		<input type="hidden" id="post_comment_reply_id" name="replyId" value="<s:property value="commentId"/>" />
              		<input type="hidden" id="post_comment_reply_user_id" name="replyUserId" value="<s:property value="userId"/>" />
              		<input type="hidden" id="post_comment_status_id" name="statusId" value="<s:property value="statusId"/>" />
					<p class="btn">
						<a href="javascript:void(0);" id="inside2" statusId="<s:property value="statusId"/>" name="postReplyBtn">评论</a>
					</p>
					<div class="action">
						<span class="ico_faces" commentId="<s:property value="commentId"/>" name="face_reply"></span>
						<ul class="commoned_list">
							<li><label><input type="checkbox" class="W_checkbox"
									name="forward" value="true">同时转发到我的微博</label></li>
						</ul>
					</div>
				</div>
			</div>
			</form>
		</dd>
	</dl>
</s:iterator>
<s:if test="commentsPage.pageCnt>1">
	<s:if test="showPage">
		<div class="W_pages_minibtn WidthAuto">
			<s:if test="commentsPage.befPageNum>0 && commentsPage.curPageNum!=1">      	
			   <a href="javascript:loadComments(<s:property value="statusId"/>,<s:property value="commentsPage.befPageNum" />,true)" class="btn_page">上一页</a>
			</s:if>
			<s:iterator value="commentsPage.pageList" id="curPage">
				<s:if test="#curPage==commentsPage.curPageNum">
					 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
				</s:if>
				<s:else>
					<a href="javascript:loadComments(<s:property value="statusId"/>,<s:property value="#curPage" />,true)"><s:property value="#curPage" /></a>
				</s:else>
			</s:iterator>
			   <s:if test="commentsPage.curPageNum!=commentsPage.pageCnt">
			   <a href="javascript:loadComments(<s:property value="statusId"/>,<s:property value="commentsPage.aftPageNum" />,true)" class="btn_page">下一页</a>
			   </s:if>
		</div>
	</s:if>
	<s:else>
		<a href="/status/<s:property value="statusId"/>" target="_blank" style="color:#0A8CD2;float:right">查看更多》</a>
	</s:else>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	prepareReplyFace($("[name='face_reply']"));
	$("#comment_num_"+<s:property value="statusId"/>).text(<s:property value="commentsPage.recordCnt"/>);
	//atRelease('', 'comment_content_'+<s:property value="statusId"/>, {box : 'publisherTop_box',list : 'publisherTop_list'});
	$("[name='huifu']").click(function(){
		$(this).parent().next().children().toggle();
		if($(this).parent().next().children().is(":visible")==true){
			var userName = $(this).attr("userName");
			var commentId = $(this).attr("commentId");
			var content = "回复@"+userName+":";
			$("#reply_content_"+commentId).val(content);
			setCurPost($("#reply_content_"+commentId),content.length);
		}
	});
	$(".comment_lists dl.comment_list dd").mouseover(function(){
		$(this).addClass("hover");
	});
	$(".comment_lists dl.comment_list dd").mouseout(function(){
		$(this).removeClass("hover");
	});
	$("[name='postReplyBtn']").click(function(){
		var statusId = $(this).attr("statusId");
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					loadComments(statusId);  
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			var $form = $(this).parent().parent().parent().parent();
			$form.attr('action','/status/postComment.jspa');
			$form.ajaxSubmit(options);
	});

	$("[name='del_comment']").click(function(){
		show_comment_del();
		var commentId = $(this).attr("commentId");
		$("#confirm_del_comment").attr("commentId",commentId);
	});

});
</script>
