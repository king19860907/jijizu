<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:set name="actionName" value="#request['struts.request_uri']"></s:set>  
<div class="group_read">
        	<h3>好友</h3>
            <ul class="tab">
            	<li <s:if test='#actionName=="/friends/search.jspa"'>class="on"</s:if>><a href="/friends/search.jspa">寻找好友</a></li>
                <li <s:if test='#actionName=="/friends/manage.jspa"'>class="on"</s:if>><a href="/friends/">好友管理</a></li>
                <li <s:if test='#actionName=="/friends/apply.jspa"'>class="on"</s:if>><a href="/friends/apply.jspa">已经申请</a></li>
            </ul>
        </div>