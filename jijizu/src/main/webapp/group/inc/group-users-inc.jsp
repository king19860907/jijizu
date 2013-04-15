<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<form>
              <fieldset>
                <legend class="tit S_txt1">活动参与者<em>(<s:property value="userPage.recordCnt"/>)</em></legend>
              </fieldset>
            </form>
            <div class="person_list">
              <s:iterator value="userPage.result">
              	<dl class="name_pic width285">
	                <dt class="pic"> <a target="_blank" href="/u/<s:property value="userId"/>" title="<s:property value="name"/>"><img width="45" height="45" title="<s:property value="name"/>" alt="<s:property value="name"/>" src="<s:property value="%{getHeadImgUrl(150)}"/>"></a> </dt>
	                <dd class="con_name">
	                	<div class="btn">
	                		 <s:if test="sessionUserInfo != null && sessionUserInfo.userId != userId">
								<s:if test="%{@com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,userId)}">
					               <div class="old_friend_btn"></div>
					            </s:if>
					            <s:else>
					               <a href="javascript:followUser(<s:property value="userId"/>);" class="addFriend"></a>
					            </s:else>
							</s:if>
	                	</div>
	                    <p class="name"><a target="_blank" href="/u/<s:property value="userId"/>" title="<s:property value="name"/>"><s:property value="name"/></a></p>
	                </dd>
	                <dd class="change"><a class="icon_close" href="javascript:void(0);"></a></dd>
	              </dl>
              </s:iterator>
            </div>