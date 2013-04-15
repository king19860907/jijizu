<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<form>
              <fieldset>
                <legend class="tit S_txt1">找邻居</legend>
              </fieldset>
            </form>
            <ul class="find_nav">
            	<s:set name="paramLivingCommunityId" value="livingCommunityId"></s:set>
            	<s:if test="userInfo.livingCommunity!=null">
	            	<li <s:if test="userInfo.livingCommunity.id==#paramLivingCommunityId">class="on"</s:if> onclick="changeLivingCommunity(<s:property value="userInfo.livingCommunity.id"/>,0);">
	            	<s:if test="userInfo.livingCommunity.id==#paramLivingCommunityId">
	            		<i></i>
	            	</s:if>
	            	<s:property value="userInfo.livingCommunity.name"/></li>
            	</s:if>
                <s:if test="userInfo.hometownLivingCommunity!=null">
	                <li <s:if test="userInfo.hometownLivingCommunity.id==#paramLivingCommunityId">class="on"</s:if> onclick="changeLivingCommunity(<s:property value="userInfo.hometownLivingCommunity.id"/>,0);">
	                <s:if test="userInfo.hometownLivingCommunity.id==#paramLivingCommunityId">
	            		<i></i>
	            	</s:if>
	                <s:property value="userInfo.hometownLivingCommunity.name"/></li>
                </s:if>
                <li class="edit_find"><a href="/user/base.jspa">编辑居住信息</a></li>
            </ul>
            <div class="conResult_sam">
            	<s:iterator value="livingUserPage.result">
            		<dl>
	                    <dt><a href="/u/<s:property value="userId"/>" target="_blank"><img src="<s:property value="%{getHeadImgUrl(80)}"/>" width="80" height="80" /></a></dt>
	                    <dd class="name"><a class="Fl" href="/u/<s:property value="userId"/>" target="_blank"><s:property value="name"/></a>
	                    	<a target="_blank" href="/auth/">
		                  		<s:if test="vFlag==1">
				              		<ins class="vip"></ins>
				              	</s:if>
				              	<s:if test="enterpriseFlag==1">
				              		<ins class="vip_agency"></ins>
				              	</s:if>
		                  	</a>
	                    </dd>
	                    <dd><s:property value="cityStr"/><s:property value="districtStr"/></dd>
	                    <dd class="btn"><a class="addFriend" href="javascript:followUser(<s:property value="userId"/>);"></a></dd>
	                </dl>
            	</s:iterator>
            </div>
            <s:if test="livingUserPage.pageCnt>1">
            	
	            <div class="next_find">
	            	<s:if test="livingUserPage.curPageNum!=livingUserPage.pageCnt">
		            	<a href="javascript:changeLivingCommunity(<s:property value="livingCommunityId"/>,<s:property value="livingUserPage.aftPageNum"/>);">下一组</a>
	            	</s:if>
	            	<s:else>
		            	<a href="javascript:changeLivingCommunity(<s:property value="livingCommunityId"/>,<s:property value="livingUserPage.befPageNum"/>);">上一组</a>
	            	</s:else>
	            </div>
            </s:if>