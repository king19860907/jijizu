<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组-<s:property value="status.name"/>:<s:property value="@com.jijizu.base.util.JijizuUtil@ellipsis(@com.jijizu.base.util.JijizuUtil@removeHtmlTag(status.content),40,'')"/></title>
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
<script type="text/javascript" src="${JS_PATH}/jquery-ui-1.8.18.custom.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.ui.draggable.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
<script type="text/javascript">
$(document).ready(function(){
	prepareUserCardTip($(this).find(".USER_CARD"));
	$("[name='comment-btn']").click();
	findCommonFirends(<s:property value="status.userId"/>);
	findFirends(<s:property value="status.userId"/>);
	findUserGroup(<s:property value="status.userId"/>);
});
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
        <div class="jjz_left_box">
          <%@ include file="/status/inc/user-detail-inc.jsp" %> 
          <div class="pl_event_content">
            <div class="WB_feed_type">
              <div class="WB_feed_datail">
                <div class="WB_detail_Single">
                  <div class="WB_text">
                    <p class="content"><s:property value="status.content" escape="false"/></p>
                  </div>
                  <s:if test="status.mediaUrl != null">
                  <div class="WB_media_expand">
                    <div class="jy_s_body">
                      <p class="jy_s_btn"> <a href="<s:property value="status.originalImgUrl"/>" class="seey" target="_blank">查看原图</a> <a href="javascript:void(0)" class="z_left">向左转</a> <a href="javascript:void(0)" class="z_right last">向右转</a> </p>
                      <p class="js_s_bimg"><img src="<s:property value="status.mediaUrl"/>" class="ximg"/></p>
                    </div>
                  </div>
                  </s:if>
                  <s:if test="status.forwardSrcId!=null">
	                  <div class="WB_media_expand">
	                    <div class="WB_arrow3"></div>
	                  	<s:if test="status.forwardSourceStatus==null">
	                  		该微博已删除
	                  	</s:if>
	                  	<s:else>
		                    <div class="clearfix">
		                      <div class="WB_info"><a href="/u/<s:property value="status.forwardSourceStatus.userId"/>" uid="<s:property value="status.forwardSourceStatus.userId"/>" class="USER_CARD">@<s:property value="status.forwardSourceStatus.name"/></a>
		                      	<a target="_blank" href="/auth/">
			                  		<s:if test="status.forwardSourceStatus.vFlag==1">
					              		<ins class="vip"></ins>
					              	</s:if>
					              	<s:if test="status.forwardSourceStatus.enterpriseFlag==1">
					              		<ins class="vip_agency"></ins>
					              	</s:if>
			                  	</a>
		                      </div>
		                      <div class="WB_text"><s:property value="status.forwardSourceStatus.content" escape="false"/></div>
		                    </div>
		                    <s:if test="status.forwardSourceStatus.mediaUrl != null">
			                    <!--一条记录开始-->
			                    <div class="rot_box WidthAuto"><img src="<s:property value="status.forwardSourceStatus.smallImgUrl"/>" class="simg"/></div>
			                    <div class="bigImage_box dis_none">
			                      <p class="jy_s_t"></p>
			                      <div class="jy_s_body">
			                        <p class="jy_s_btn"> <a href="javascript:void(0)" class="sq">收起</a> <a href="<s:property value="status.forwardSourceStatus.originalImgUrl"/>" class="seey" target="_blank">查看原图</a> <a href="javascript:void(0)" class="z_left">向左转</a> <a href="javascript:void(0)" class="z_right last">向右转</a> </p>
			                        <p class="js_s_bimg cursorHover"><img src="<s:property value="status.forwardSourceStatus.mediaUrl"/>" class="ximg"/></p>
			                      </div>
			                    </div>
		                    </s:if>
	                  	</s:else>
	                    <!--一条记录结束-->
	                  </div>
                  </s:if>
                  <div class="WB_func">
                    <div class="WB_handle">
                    <s:if test="sessionUserInfo!=null && sessionUserInfo.userId==status.userId">
                    <span class="hover">
                    <a class="del" href="javascript:void(0);" statusId="<s:property value="status.statusId"/>" del-type="detail" name="del_status">删除</a> <i class="S_txt3">|</i></span>
                    </s:if>
                    <a class="zhuanfa" name="forward-btn" href="javascript:void(0);" content="<s:property value="status.forwardContent"/>" statusId="<s:property value="statusId"/>">转发(<s:property value="status.forwardNum"/>)</a> <i class="S_txt3">|</i> 
                    <a href="javascript:void(0);"  name="comment-btn" class="pinglun" show-page="true" statusId="<s:property value="statusId"/>">评论(<font id="comment_num_<s:property value="statusId"/>"><s:property value="status.commentNum"/></font>)</a> </div>
                    <div class="WB_from"> 
                    	<a class="WB_time"  href="/status/<s:property value="status.statusId"/>">
                    		<s:if test="status.overOneDay">	
	                    		<s:date name="status.postTime" format="yyyy-MM-dd HH:mm:ss" />
	                    	</s:if>
	                    	<s:else>
	                    		<s:date name="status.postTime" nice="true" />
	                    	</s:else>
	                    	</a> 
                    	<em class="S_txt2">来自</em> <a class="WB_where" target="_blank" href="<s:property value="status.sourceUrl"/>"><s:property value="status.sourceText"/></a> 
                    </div>
                  </div>
                  <div class="WB_media_expand">
                        <div class="WB_arrow" style="right: 10px;"></div>
                        <div>
                         <form id="forward-status-form-<s:property value="status.statusId"/>">
                      <input type="hidden" id="post_status_is_sync" name="isSync" />
             			 <input type="hidden" id="post_status_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
              			 <input type="hidden" id="post_status_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_STATUS" />
              			 <input type="hidden" id="post_status_forward_id" name="forwardId" value="<s:property value="statusId"/>" />
              			 <input type="hidden" id="post_status_status_id" name="statusId" value="<s:property value="statusId"/>" />
                      <div class="clearfix">
                        <div class="relative" id="publisherTop_box_<s:property value="status.statusId"/>">
                          <textarea id="comment_content_<s:property value="status.statusId"/>" name="content" rows="" cols=""  action-type="check" class="W_input" onfocus="WB_media_expand_textarea_focus()" onblur="WB_media_expand_textarea_blur()"></textarea>
                       	  <script>
                       		prepareAutoComplete($("#comment_content_<s:property value="status.statusId"/>"));
                       	  </script>
                        </div>
                        <p class="btn"><a href="javascript:void(0);" statusId="<s:property value="status.statusId"/>" class="inside" name="inside" >评论</a></p>
                        <div class="action">
                          <a class="ico_faces" name="face_comment" statusId="<s:property value="status.statusId"/>" href="javascript:void(0);" ></a>
                          <ul class="commoned_list">
                            <li>
                              <label id="label_comment_<s:property value="status.statusId"/>">
                                <input type="checkbox" name="comment" value="true" class="W_checkbox"/>同时评论给<s:property value="status.name"/>
                              </label>
                              <label id="label_forward_<s:property value="status.statusId"/>" style="display: none">
                              	 <input type="checkbox" name="forward" value="true" class="W_checkbox"/>转发到我的微博
                              </label>
                            </li>
                            <li>
	                            <s:if test="forwardSourceStatus!=null">
	                              <label>
	                                <input type="checkbox" name="commentOriginalAuthor" value="true" class="W_checkbox"/>同时评论给原文作者  <s:property value="status.forwardSourceStatus.name"/>
	                              </label>
	                            </s:if>
                            </li>
                          </ul>
                        </div>
                     </div>
                     </form>
                          <!-- 评论展示 -->
		                     <div class="comment_lists" id="comment_lists_<s:property value="statusId"/>">
		                     
		                     </div>
                        </div>
                      </div>
                  
                  
                  
                </div>
              </div>
            </div>
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
<script language="javascript">

</script>
</body>
</html>
