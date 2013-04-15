<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:set name="actionName" value="#request['struts.request_uri']"></s:set> 
<div class="group_read">
        	<h3>我的集集组</h3>
            <ul class="tab">
            	<li <s:if test='#actionName=="/group/index.jspa"'>class="on"</s:if>><a href="/group/">全部集集组</a></li>
            	<s:if test="sessionUserInfo != null">
                	<li <s:if test='#actionName=="/group/myEnter.jspa"'>class="on"</s:if>><a href="/group/myEnter.jspa">我参加的集集组</a></li>
                	<li <s:if test='#actionName=="/group/myCreate.jspa"'>class="on"</s:if>><a href="/group/myCreate.jspa">我发起的集集组</a></li>
               		<div class="W_EvCt"><a href="/group/create.jspa">发起集集组</a></div>
                </s:if>
            </ul>
        </div>