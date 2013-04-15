<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<form>
	<fieldset>
		<legend class="tit S_txt1">
			他的好友<span class="S_txt2">(<s:property value="friendsPage.recordCnt"/>)</span>
		</legend>
		<s:if test="friendsPage.recordCnt>10">
			<div class="btns">
				<a class="W_btn_more" href="/u/<s:property value="userId"/>/friends?type=his">更多 &gt;&gt;</a>
			</div>
		</s:if>
	</fieldset>
</form>
<div class="person_list">
	<s:iterator value="friendsPage.result">
	    <dl class="name_pic">
	                <dt class="pic"> <a target="_blank" href="/u/<s:property value="userId"/>" title="<s:property value="name"/>"><img width="45" height="45" title="<s:property value="name"/>" alt="<s:property value="name"/>" src="<s:property value="headImgUrl"/>"></a> </dt>
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
	                    <p class="name"><a target="_blank" href="/u/<s:property value="userId"/>" title="<s:property value="name"/>"><s:property value="name"/></a>
	                    	<a target="_blank" href="/auth/">
		                  		<s:if test="vFlag==1">
				              		<ins class="vip"></ins>
				              	</s:if>
				              	<s:if test="enterpriseFlag==1">
				              		<ins class="vip_agency"></ins>
				              	</s:if>
		                  	</a>
	                    </p>
	                    <p class="info"><s:property value="recommendTypeStr"/></p>
	                </dd>
	                <dd class="change"><a class="icon_close" href="javascript:void(0);"></a></dd>
	              </dl>
	</s:iterator>
</div>