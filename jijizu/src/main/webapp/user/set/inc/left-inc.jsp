<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:set name="actionName" value="#request['struts.request_uri']"></s:set>  
<div class="W_main_l">
      <div class="W_left_nav">
        <h3>个人资料</h3>
        <div class="level_1_Box">
          <div class="lev"> <a <s:if test="#actionName=='/user/base.jspa'">class="lev_curr"</s:if> href="/user/base.jspa"><i class="ico_basics"></i><span>基本信息</span></a> </div>
          <div class="lev"> <a <s:if test="#actionName=='/user/edu.jspa'">class="lev_curr"</s:if> href="/user/edu.jspa"><i class="ico_edu"></i><span>教育信息</span></a> </div>
          <div class="lev"> <a <s:if test="#actionName=='/user/job.jspa'">class="lev_curr"</s:if> href="/user/job.jspa"><i class="ico_work"></i><span>工作信息</span></a> </div>
          <div class="lev"> <a <s:if test="#actionName=='/user/photo.jspa'">class="lev_curr"</s:if> href="/user/photo.jspa"><i class="ico_pic"></i><span>修改头像</span></a> </div>
        </div>
      </div>
    </div>