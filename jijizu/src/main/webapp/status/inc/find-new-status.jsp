<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:iterator value="statusList">
       <%@include file="/common/status-inc.jsp" %>
</s:iterator>