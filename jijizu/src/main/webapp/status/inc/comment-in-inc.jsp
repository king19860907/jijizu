<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
 <s:iterator value="commentsWithStatusPage.result">
           	 <div class="WB_feed_type" id="comment_list_<s:property value="commentId"/>">
              <div class="WB_feed_datail">
                <div class="WB_face"> <a href="/u/<s:property value="userId"/>" target="_blank"><img class="USER_CARD" uid="<s:property value="userId"/>" src="<s:property value="headImgUrl"/>" width="50" height="50" /></a> </div>
                <div class="WB_detail">
                  <div class="WB_func">
                  	<div class="Comment_detail"><a class="USER_CARD" uid="<s:property value="userId"/>" href="/u/<s:property value="userId"/>"><s:property value="name"/></a>
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
                    		评论 我 的微博：<a target="_blank" href="/status/<s:property value="statusId"/>">"<s:property value="%{status.getContentWithOutHtmlTag(32,'...')}" escape="false"/>"</a>
                    	</s:if>
                    	<s:else>
                    		回复 我 的评论：<a target="_blank" href="/status/<s:property value="statusId"/>">"<s:property value="%{comment.getContentWithOutHtmlTag(32,'...')}" escape="false"/>"</a>
                    	</s:else>
                    </div>
                    <div class="WB_handle">
                    	<span class="hover">
                    		<a class="del" href="javascript:void(0);" name="del_comment" commentid="<s:property value="commentId"/>">删除</a> <i class="S_txt3">|</i>
                    	</span> 
                    	<a href="javascript:void(0);" name="reply-btn" commentId="<s:property value="commentId"/>" userName="<s:property value="name"/>(<s:property value="nickName"/>)">回复</a> 
                    </div>
                    <div class="WB_from"><em class="S_txt2">来自</em> <a href="<s:property value="sourceUrl"/>" target="_blank" class="WB_where"><s:property value="sourceText"/></a> </div>
                  </div>
                  
                  <div class="WB_media_expand" id="replay_expand_<s:property value="commentId"/>">
                    <div class="WB_arrow"></div>
	                  <form id="post-comment-form-<s:property value="commentId"/>">
	                   <input type="hidden" id="post_comment_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
	              		<input type="hidden" id="post_comment_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_STATUS" />
	              		<input type="hidden" id="post_comment_reply_id" name="replyId" value="<s:property value="commentId"/>" />
	              		<input type="hidden" id="post_comment_reply_user_id" name="replyUserId" value="<s:property value="userId"/>" />
	              		<input type="hidden" id="post_comment_status_id" name="statusId" value="<s:property value="statusId"/>" />
	                    <div>
	                      <div class="clearfix">
	                        <div id="publisherTop_box2" class="relative">
	                          <textarea name="content" id="reply_content_<s:property value="commentId"/>" class="W_input" onfocus="WB_media_expand_textarea_focus()" onblur="WB_media_expand_textarea_blur()">回复@<s:property value="name"/>:</textarea>
	                        </div>
	                        <p class="btn"><a href="javascript:void(0);" class="inside" userName="<s:property value="name"/>(<s:property value="nickName"/>)" statusId="<s:property value="statusId"/>" commentId="<s:property value="commentId"/>" name="postReplyBtn">回复</a></p>
	                        <div class="action">
	                          <a class="ico_faces" commentId="<s:property value="commentId"/>" name="face_reply" href="javascript:void(0);" ></a>
	                          <ul class="commoned_list">
	                            <li><label>
	                              <input type="checkbox" class="W_checkbox"
									name="forward" value="true"/>同时转发到我的微博</label>
	                            </li>
	                          </ul>
	                        </div>
	                     </div>
	                    </div>
	                    </form>
                  </div>
                </div>
              </div>
            </div>
           </s:iterator>
 <script type="text/javascript">
	$(document).ready(function(){
		$("[name='postReplyBtn']").click(function(){
			var statusId = $(this).attr("statusId");
			var commentId = $(this).attr("commentId");
			var userName = $(this).attr("userName");
			var content = $("#reply_content_"+commentId).val();
			if(content==""||content==("回复@"+userName+":")){
				show_box_none();
				return;
			}
			var options = { 
					cache : false,
					dataType:"json",	
					type :"post",
					async:false,
					success: function(data) { 
					  if(data.flag == 0){
						alert("发布成功");
						$("#replay_expand_"+commentId).hide();
						$("#reply_content_"+commentId).val("");
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

		$("#confirm_del_comment").click(function(){
			var commentId = $(this).attr("commentId");
			deleteComment(commentId,"#comment_list_"+commentId);
		});
	});
	</script>