<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>

<!-- 结交好友微博形式 -->
<!-- 
<div class="WB_feed_type">
              <div class="WB_feed_datail">
                <div class="WB_face"> <a href="首页.html" target="_blank"><img src="images/people/pic_04.jpg" width="50" height="50" /></a> </div>
                <div class="WB_detail">
                  <div class="WB_info"><a href="首页.html" target="_blank">艾米丽</a> 结交了新的好友</div>
                  <div class="WB_friend">
                    <dl>
                      <dt><a href="访问他人主页.html" target="_blank"><img src="images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                      <dd class="name"><a href="访问他人主页.html" target="_blank">小鱼儿</a></dd>
                      <dd class="address">辽宁鞍山</dd>
                      <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                      <dt><a href="访问他人主页.html" target="_blank"><img src="images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                      <dd class="name"><a href="访问他人主页.html" target="_blank">小鱼儿</a></dd>
                      <dd class="address">辽宁鞍山</dd>
                      <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                      <dt><a href="访问他人主页.html" target="_blank"><img src="images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                      <dd class="name"><a href="访问他人主页.html" target="_blank">小鱼儿</a></dd>
                      <dd class="address">辽宁鞍山</dd>
                      <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                  </div>
                  <div class="WB_time2"><a href="访问他人主页.html" target="_blank">11分钟前</a></div>
                </div>
              </div>
            </div>
 -->
  
            <div class="WB_feed_type" id="div_status_<s:property value="statusId"/>">
              <div class="WB_feed_datail">
                <div class="WB_face"> <a href="/u/<s:property value="userId"/>"><img class="USER_CARD" uid="<s:property value="userId"/>" src="<s:property value="headImgUrl"/>" width="50" height="50" /></a> </div>
                <div class="WB_detail">
                  <div class="WB_info">
                  	<a href="/u/<s:property value="userId"/>" class="USER_CARD" uid="<s:property value="userId"/>"><s:property value="name"/></a>
                  	<a target="_blank" href="/auth/">
                  		<s:if test="vFlag==1">
		              		<ins class="vip"></ins>
		              	</s:if>
		              	<s:if test="enterpriseFlag==1">
		              		<ins class="vip_agency"></ins>
		              	</s:if>
                  	</a>
                  </div>
                  <div class="WB_text"><s:property value="content" escape="false" /></div>
                  <!--一条记录开始-->
                   <s:if test="mediaUrl != null">
	                     <div class="rot_box"><img src="<s:property value="smallImgUrl"/>" class="simg"/></div>
		                  <div class="bigImage_box dis_none">
		                      <p class="jy_s_t"></p>
		                      <div class="jy_s_body">
		                          <p class="jy_s_btn"> <a href="javascript:void(0)" class="sq">收起</a> <a href="<s:property value="originalImgUrl"/>" class="seey" target="_blank">查看原图</a> <a href="javascript:void(0)" class="z_left">向左转</a> <a href="javascript:void(0)" class="z_right last">向右转</a> </p>
		                          <p class="js_s_bimg cursorHover"><img src="<s:property value="mediaUrl"/>" class="ximg" /></p>
		                      </div>
		                  </div>
                   </s:if>
                
                   <!--一条记录结束-->
                  <s:if test="forwardSrcId!=null">
                  	<div class="WB_media_expand Forward">
                  		<div class="WB_arrow"></div>
	                  	<s:if test="forwardSourceStatus==null">
	                  		该微博已删除
	                  	</s:if>
	                  	<s:else>
			                    <div class="clearfix">
			                      <div class="WB_info"><a class="USER_CARD" uid="<s:property value="forwardSourceStatus.userId"/>" href="/u/<s:property value="forwardSourceStatus.userId"/>">@<s:property value="forwardSourceStatus.name"/></a>
			                      	<a target="_blank" href="/auth/">
				                  		<s:if test="forwardSourceStatus.vFlag==1">
						              		<ins class="vip"></ins>
						              	</s:if>
						              	<s:if test="forwardSourceStatus.enterpriseFlag==1">
						              		<ins class="vip_agency"></ins>
						              	</s:if>
				                  	</a>
			                      </div>
			                      <div class="WB_text"><s:property value="forwardSourceStatus.content" escape="false" /></div>
			                    </div>
			                    <!--一条记录开始-->
			                     <s:if test="forwardSourceStatus.mediaUrl != null">
			                    <div class="rot_box"><img src="<s:property value="forwardSourceStatus.smallImgUrl"/>" class="simg"/></div>
			                  <div class="bigImage_box dis_none">
			                      <p class="jy_s_t"></p>
			                      <div class="jy_s_body">
			                          <p class="jy_s_btn"> <a href="javascript:void(0)" class="sq">收起</a> <a href="<s:property value="forwardSourceStatus.originalImgUrl"/>" class="seey" target="_blank">查看原图</a> <a href="javascript:void(0)" class="z_left">向左转</a> <a href="javascript:void(0)" class="z_right last">向右转</a> </p>
			                          <p class="js_s_bimg"><img src="<s:property value="forwardSourceStatus.mediaUrl"/>" class="ximg" /></p>
			                      </div>
			                  </div>
			                  </s:if>
			                   <!--一条记录结束-->
			                   	<div class="WB_func">
			                        <div class="WB_handle"><a href="/status/<s:property value="forwardSourceStatus.statusId"/>">转发(<s:property value="forwardSourceStatus.forwardNum"/>)</a> <i class="S_txt3">|</i> <a href="/status/<s:property value="forwardSourceStatus.statusId"/>">评论(<s:property value="forwardSourceStatus.commentNum"/>)</a> </div>
			                        <div class="WB_from"> 
			                        <a href="/status/<s:property value="forwardSourceStatus.statusId"/>" target="_blank" class="WB_time">
			                        	<s:if test="overOneDay">	
				                    		<s:date name="forwardSourceStatus.postTime" format="yyyy-MM-dd HH:mm:ss" />
				                    	</s:if>
				                    	<s:else>
				                    		<s:date name="forwardSourceStatus.postTime" nice="true" />
				                    	</s:else>
			                        </a> 
			                        <em class="S_txt2">来自</em> <a href="<s:property value="forwardSourceStatus.sourceUrl"/>" target="_blank" class="WB_where"><s:property value="forwardSourceStatus.sourceText"/></a> </div>
			                      </div>
	                  	</s:else>
	                  </div>
                  </s:if>
                  
                  
                  
                  <div class="WB_func">
                    <div class="WB_handle"> 
                    <s:if test="sessionUserInfo!=null && sessionUserInfo.userId==userId">
                    <span class="hover">
                    <a href="javascript:void(0);" class="del" statusId="<s:property value="statusId"/>" name="del_status">删除</a> <i class="S_txt3">|</i></span>
                    </s:if>
                    <a href="javascript:void(0);" class="zhuanfa" name="forward-btn" content="<s:property value="forwardContent"/>" statusId="<s:property value="statusId"/>">转发(<s:property value="forwardNum"/>)</a> <i class="S_txt3">|</i> 
                    <a href="javascript:void(0);" class="pinglun" name="comment-btn" statusId="<s:property value="statusId"/>">评论(<font id="comment_num_<s:property value="statusId"/>"><s:property value="commentNum"/></font>)</a> </div>
                    <div class="WB_from"> <a href="/status/<s:property value="statusId"/>" target="_blank" class="WB_time">
                    	<s:if test="overOneDay">	
                    		<s:date name="postTime" format="yyyy-MM-dd HH:mm:ss" />
                    	</s:if>
                    	<s:else>
                    		<s:date name="postTime" nice="true" />
                    	</s:else>
                    	</a> <em class="S_txt2">来自</em> <a href="<s:property value="sourceUrl"/>" target="_blank" class="WB_where"><s:property value="sourceText"/></a> </div>
                  </div>
                  <div class="WB_media_expand">
                    <div class="WB_arrow"></div>
                    <div>
                     <form id="forward-status-form-<s:property value="statusId"/>">
                      <input type="hidden" id="post_status_is_sync" name="isSync" />
             			 <input type="hidden" id="post_status_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
              			 <input type="hidden" id="post_status_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_STATUS" />
              			 <input type="hidden" id="post_status_forward_id" name="forwardId" value="<s:property value="statusId"/>" />
              			 <input type="hidden" id="post_status_status_id" name="statusId" value="<s:property value="statusId"/>" />
              			 <input type="hidden" id="groupId" name="groupId" value="<s:property value="groupInfo.groupId"/>" />
                      <div class="clearfix">
                        <div class="relative" id="publisherTop_box_<s:property value="statusId"/>">
                          <textarea id="comment_content_<s:property value="statusId"/>" name="content" rows="" cols=""  action-type="check" class="W_input" onfocus="WB_media_expand_textarea_focus()" onblur="WB_media_expand_textarea_blur()"></textarea>
                       	  <script>
                       		prepareAutoComplete($("#comment_content_<s:property value="statusId"/>"));
                       	  </script>
                        </div>
                        <p class="btn"><a href="javascript:void(0);" statusId="<s:property value="statusId"/>" class="inside" name="inside" >评论</a></p>
                        <div class="action">
                          <a class="ico_faces" name="face_comment" statusId="<s:property value="statusId"/>" href="javascript:void(0);" ></a>
                          <ul class="commoned_list">
                            <li>
                              <label id="label_comment_<s:property value="statusId"/>">
                                <input type="checkbox" name="comment" value="true" class="W_checkbox">同时评论给<s:property value="name"/>
                              </label>
                              <label id="label_forward_<s:property value="statusId"/>" style="display: none">
                              	 <input type="checkbox" name="forward" value="true" class="W_checkbox">转发到我的微博
                              </label>
                            </li>
                            <li>
	                            <s:if test="forwardSourceStatus!=null">
	                              <label>
	                                <input type="checkbox" name="commentOriginalAuthor" value="true" class="W_checkbox">同时评论给原文作者  <s:property value="forwardSourceStatus.name"/>
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
