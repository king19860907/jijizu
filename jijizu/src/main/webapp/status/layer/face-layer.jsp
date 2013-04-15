<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div class="layoutContent" type="">
	<div class="content">
    	<a href="javascript:void(0)" class="W_close" onclick="hide_faces()"></a>
        <div class="layer_faces">
        	<ul>
            	<s:iterator value="faces">
            		<li name="face_btn" text="<s:property value="name"/>"><a href="javascript:void(0);"><img width="22" height="22" title="<s:property value="name"/>" alt="<s:property value="name"/>" src="<s:property value="imgUrl"/>"/></a></li>
            	</s:iterator>
            </ul>
        </div>
        <div class="arrow"></div>
    </div>
</div>