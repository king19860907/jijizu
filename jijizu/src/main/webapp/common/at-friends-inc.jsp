<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<ul>
	<li class="first">选择最近@的人或直接输入</li>
	<s:iterator value="friendsPage.result">
		<li class="hove"><div class="pic"><img src="<s:property value="headImgUrl"/>" width="30" height="30" /></div><div class="name"><s:property value="name"/></div></li>
	</s:iterator>
</ul>
<script>
$(document).ready(function() {
	var id = $('#<s:property value="attr"/>').parent().attr("id");
});
</script>