<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<form>
              <fieldset>
                <legend class="tit S_txt1">找同学</legend>
              </fieldset>
            </form>
            <ul class="find_nav">
            	<s:set name="paramSchoolId" value="schoolId"></s:set>
            	<s:iterator value="schools">
	            	<li onclick="changeSchool(<s:property value="schoolId"/>,0);" <s:if test="schoolId==#paramSchoolId">class="on"</s:if>>
	            	<s:if test="schoolId==#paramSchoolId">
            			<i></i>
            		</s:if>
	            	<s:property value="schoolName"/></li>
            	</s:iterator>
                <li class="edit_find"><a href="/user/edu.jspa">编辑教育信息</a></li>
            </ul>
            <div class="conResult_sam">
            	<s:iterator value="schoolUserPage.result">
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
           <s:if test="schoolUserPage.pageCnt>1">
            	
	            <div class="next_find">
	            	<s:if test="schoolUserPage.curPageNum!=schoolUserPage.pageCnt">
		            	<a href="javascript:changeSchool(<s:property value="schoolId"/>,<s:property value="schoolUserPage.aftPageNum"/>);">下一组</a>
	            	</s:if>
	            	<s:else>
		            	<a href="javascript:changeSchool(<s:property value="schoolId"/>,<s:property value="schoolUserPage.befPageNum"/>);">上一组</a>
	            	</s:else>
	            </div>
            </s:if>