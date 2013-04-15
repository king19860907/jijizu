<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

	<div class="name_card">
	    <dl class="people">
	      <dt><a href="/u/<s:property value="userInfo.userId"/>" target="_blank"><img src="<s:property value="%{userInfo.getHeadImgUrl(80)}"/>" width="80" height="80" /></a></dt>
	      <dd>
	        <p><a href="/u/<s:property value="userInfo.userId"/>" target="_blank" class="name"><s:property value="userInfo.name"/>
	        <s:if test="userInfo.vFlag==1">
		              		<ins class="vip"></ins>
		              	</s:if>
		              	<s:if test="userInfo.enterpriseFlag==1">
		              		<ins class="vip_agency"></ins>
		              	</s:if>
	        </a>
	        <s:if test="sessionUserInfo.userId!=null && sessionUserInfo.userId != userId">
	        	<s:if test="!followEachOther">
	        		<a href="javascript:followUser(<s:property value="userInfo.userId"/>);" class="addFriend"></a>
	        	</s:if>
	        	<s:else>
	        		<span class="cancelFriend"><b>互为好友</b><em>|</em>
	        		<a href="javascript:void(0);" onclick="followCancel(<s:property value="userInfo.userId"/>)" id="cancel_friend">取消</a></span>
	        	</s:else>
	        </s:if>
	        </p>
	        <p>
	        	<s:if test='userInfo.gender!=null && userInfo.gender!=""'> 
	        	<i
	        	<s:if test='userInfo.gender=="m"'> 
	        	class="boy"
	        	</s:if>
	        	<s:elseif test='userInfo.gender=="f"'>
	        	class="girl"
	        	</s:elseif>
	        	></i>
	        	</s:if>
	        	<span><s:property value="userInfo.cityStr"/>&nbsp;&nbsp;<s:property value="userInfo.districtStr"/></span></p>
	        <p style="display:none">广西百色职业学院</p>
	        <ul class="userdata">
	            <li><a href="好友/好友管理.html" target="_blank">好友</a><s:property value="userInfo.friendNum"/></li>
	            <li class="W_vline">|</li>
	            <li><a href="访问他人主页.html" target="_blank">微博</a><s:property value="userInfo.statusNum"/></li>
	            <li class="W_vline">|</li>
	            <li><a href="全部集集组.html" target="_blank">集集组</a><s:property value="userInfo.groupNum"/></li>
	          </ul>
	      </dd>
	    </dl>
	    <dl class="info">
	      <dd>简介：<s:property value="userInfo.userDesc"/></dd>
	      <s:if test="commonFriendsPage!=null && commonFriendsPage.recordCnt!=0">
	      <dd>有<strong><s:property value="commonFriendsPage.recordCnt"/></strong>位共同好友</dd>
	      <!--[if IE 6]><div class="clear"></div><![endif]-->
	      <dd>
	        <ul class="thisFriend">
	          <s:iterator value="commonFriendsPage.result">
		          <li><a title="<s:property value="name"/>" href="/u/<s:property value="userId"/>" target="_blank"><img src="<s:property value="headImgUrl"/>" width="30" height="30" /></a></li>
	          </s:iterator>
	        </ul>
	        <!--[if IE 6]><div class="clear"></div><![endif]-->
	      </dd>
	      </s:if>
	      <!--[if IE 6]><div class="clear"></div><![endif]-->
	    </dl>
	    <!--[if IE 6]><div class="clear"></div><![endif]-->
	    <div class="arrow" style="display:none"></div>
	  </div>
