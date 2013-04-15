<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:set name="actionName" value="#request['struts.request_uri']"></s:set>    
<div class="W_main_l">
      <div class="W_left_nav">
        <div class="level_1_Box">
          <div class="lev"> <a href="/home.jspa" <s:if test="#actionName=='/home.jspa'"> class="lev_curr" </s:if>><i class="ico_myhomepage"></i><span>首页</span></a> </div>
          <div class="lev"> <a href="diary/"><i class="ico_basics"></i><span>小本本</span></a> </div>
          <div class="lev"> <a href="/atMeStatus.jspa" <s:if test="#actionName=='/atMeStatus.jspa' || #actionName=='/atMeComment.jspa'"> class="lev_curr" </s:if>><i class="ico_mentionedmy"></i><span>提到我的</span></a> </div>
          <div class="lev"> <a href="/commentIn.jspa"<s:if test="#actionName=='/commentIn.jspa'||#actionName=='/commentOut.jspa'"> class="lev_curr" </s:if>><i class="ico_mycomments"></i><span>评论</span></a> </div>
          <div class="lev"> <a href="/album/?userId=<s:property value="sessionUserInfo.userId"/>" <s:if test="#actionName=='/album/album.jspa' || #actionName=='/album/photo.jspa' || #actionName=='/album/editPhotoBatch.jspa'"> class="lev_curr" </s:if>><i class="ico_pic"></i><span>相册</span></a> </div>
          <div class="lev"> <a href="/mail.jspa" <s:if test="#actionName=='/mail.jspa'"> class="lev_curr" </s:if>><i class="ico_personletter"></i><span>私信</span></a> </div>
          <div class="lev"> <a href="/notice.jspa" <s:if test="#actionName=='/notice.jspa'"> class="lev_curr" </s:if>><i class="ico_notice"></i><span>通知</span></a> </div>
        </div>
      </div>
    </div>