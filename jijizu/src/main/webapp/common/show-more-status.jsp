<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:iterator value="statusList">
       <%@include file="/common/status-inc.jsp" %>
</s:iterator>
<s:if test="statusList.size==20">
	            <div class="WB_feed" id="showmore_btn" page="2" onclick="showmore()">
	            	<a href="javascript:void(0);" class="notes">更多好友动态</a>
	            </div>
            </s:if>