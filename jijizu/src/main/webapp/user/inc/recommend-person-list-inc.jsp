<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:if test="userList != null && userList.size!=0">
<form>
              <fieldset>
                <legend class="tit S_txt1">可能感兴趣的人</legend>
                <div class="btns"> <a href="/friends/search.jspa" class="W_btn_round">找人</a> <a href="javascript:findRecommendPerson();" class="W_btn_round_ico" title="换一换"></a> </div>
              </fieldset>
            </form>
            <div class="person_list">
              <s:iterator value="userList">
            	<dl class="name_pic">
	                <dt class="pic"> <a target="_blank" href="/u/<s:property value="userId"/>" title="<s:property value="name"/>"><img width="45" height="45" title="<s:property value="name"/>" alt="<s:property value="name"/>" src="<s:property value="headImgUrl"/>"></a> </dt>
	                <dd class="con_name">
	                	<div class="btn"><a class="addFriend" href="javascript:followUser(<s:property value="userId"/>);"></a></div>
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
</s:if>