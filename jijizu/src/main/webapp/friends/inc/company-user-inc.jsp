<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<form>
              <fieldset>
                <legend class="tit S_txt1">找同事</legend>
              </fieldset>
            </form>
            <ul class="find_nav">
            	<s:set name="paramCompanyId" value="companyId"></s:set>
            	<s:iterator value="jobs" status="s" >
            		<li <s:if test="companyId==#paramCompanyId">class="on"</s:if> onclick="changeCompany(<s:property value="companyId"/>,<s:property value="page"/>);">
            		<s:if test="companyId==#paramCompanyId">
            			<i></i>
            		</s:if>
            		<s:property value="companyName"/></li>
            	</s:iterator>
                <li class="edit_find"><a href="/user/job.jspa">编辑工作信息</a></li>
            </ul>
            <div class="conResult_sam">
            	<s:iterator value="companyUserPage.result">
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
            <s:if test="companyUserPage.pageCnt>1">
            	
	            <div class="next_find">
	            	<s:if test="companyUserPage.curPageNum!=companyUserPage.pageCnt">
		            	<a href="javascript:changeCompany(<s:property value="companyId"/>,<s:property value="companyUserPage.aftPageNum"/>);">下一组</a>
	            	</s:if>
	            	<s:else>
		            	<a href="javascript:changeCompany(<s:property value="companyId"/>,<s:property value="companyUserPage.befPageNum"/>);">上一组</a>
	            	</s:else>
	            </div>
            </s:if>