<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<script type="text/javascript" src="${JS_PATH}/jquery.cookie.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/mail.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<s:set name="headActionName" value="#request['struts.request_uri']"></s:set>  
<script>
	<s:if test="sessionUserInfo != null">
		LOGIN_USER_ID = <s:property value="sessionUserInfo.userId"/>;
	</s:if>
</script>
<div class="topBar">
  <div <s:if test='#headActionName=="/search/user.jspa" || #headActionName=="/auth/index.jspa"||#headActionName=="/auth/authPersonal.jspa"||#headActionName=="/auth/authEnterprise.jspa"'>class="wrap2"</s:if><s:else>class="wrap"</s:else>>
    <div class="gn_logo"><a href="/"></a></div>
    <div class="gn_nav">
      <div class="home<s:if test='#headActionName=="/home.jspa"'>current</s:if>"><a onclick="return show_login(this);" href="/home.jspa"><i class="icon"></i><span>首页</span></a></div>
      <div class="jjz <s:if test='#headActionName=="/group/view.jspa" || #headActionName=="/group/index.jspa" || #headActionName=="/group/myCreate.jspa"'>current</s:if>"><a href="/group/"><i class="icon"></i><span>我的集集组</span></a></div>
      <div class="friend <s:if test='#headActionName=="/friends/manage.jspa" || #headActionName=="/friends/apply.jspa" || #headActionName=="/friends/search.jspa"'>current</s:if>"><a onclick="return show_login(this);" href="/friends/"><i class="icon"></i><span>好友</span></a></div>
      <div class="littlebook <s:if test='%{isDiaryAction(#headActionName)}'>current</s:if>"><a href="/diary/"><i class="icon"></i><span>小本本</span></a></div>
      <div class="badge" style="display:none"><a href="展示墙/我的展示墙.html"><i class="icon"></i><span>展示墙</span></a></div>
    </div>
    <div class="gn_search">
      <input name="" id="searchName" type="text" onblur="gn_search_focus()" onfocus="gn_search_blur()" value="找人"  />
      <a href="javascript:findUser();" class="gn_search_btn"></a> </div>
    <div class="gn_person">
    	<div class="gn_setting"><a title="<s:property value="sessionUserInfo.name"/>" target="_top" class="gn_name" href="/u/<s:property value="sessionUserInfo.userId"/>"><s:property value="sessionUserInfo.name"/></a></div>
     <s:if test='sessionUserInfo!=null'>
	      <div class="gn_setting"> 
	      	<a href="/mail.jspa" class="gn_mymail"><i></i></a>
	      	<div style="display: none;" class="gn_topmenulist_set">
	            <ul class="gn_text_list">
	            	<li><a href="/commentIn.jspa" target="_top">查看评论</a></li>
	            	<li><a href="/atMeStatus.jspa" target="_top">查看@我</a></li>
	                <li><a href="/mail.jspa" target="_top">查看私信</a></li>
	                <li><a href="/notice.jspa" target="_top">查看通知</a></li>
	            </ul>
	        </div>	
	      </div>
	      <div class="gn_setting">
	      	 <a href="/user/base.jspa" class="gn_myset"><i></i></a> 
	      	<div style="display: none;" class="gn_topmenulist_set">
	            <ul class="gn_text_list">
	                <li><a href="/user/base.jspa" target="_top">基本信息</a></li>
	                <li><a href="/user/edu.jspa" target="_top">教育信息</a></li>
	                <li><a href="/user/job.jspa" target="_top">工作信息</a></li>
	                <li><a href="/user/photo.jspa" target="_top">修改头像</a></li>
	            </ul>
	            <div class="gn_func"><a href="javascript:logOut();" target="_top">退出</a></div>
	        </div>
	      </div>
      </s:if>
      <div class="msg_tips" style="display:none" id="popMsg">
      	<div class="gn_tips">
        	<a href="javascript:popClose();" class="close"></a>
            <ul>
            	<li id="comment"><font id="commentNum">0</font>条新评论，<a href="/commentIn.jspa">查看评论</a></li>
            	<li id="atMe"><font id="atMeNum">0</font>条@我的，<a href="/atMeStatus.jspa">查看@我</a></li>
            	<li id="message"><font id="messageNum">0</font>条新私信，<a href="/mail.jspa">查看我的私信</a></li>
            	<li id="notice"><font id="noticeNum">0</font>条新通知，<a href="/notice.jspa">查看我的通知</a></li>
            </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<s:if test='sessionUserInfo==null&&#headActionName!="/"'>
<div class="invite_register">
	<div class="nologin">
    	<span class="img_lang"></span>
        <div class="txt_info">Hi<s:if test="userInfo!=null">，我是<s:property value="userInfo.name"/></s:if>! 赶快注册微博粉我吧，随时分享我的最新动态！</div>
        <div class="reg_box">
            <a class="btn_reg" href="/user/sign.jspa?target=<s:property value="#request['javax.servlet.forward.request_uri']"/>" target="_top"></a>已有帐号，<a href="/?target=<s:property value="#request['javax.servlet.forward.request_uri']"/>" target="_top">直接登录»</a>
        </div>
    </div>
</div>
</s:if>
<s:else>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test="sessionUserInfo != null">
	getNewMessage();
	setInterval(getNewMessage, 1 * 60 * 1000);//每30秒刷新一次
	</s:if>
});
function findUser(){
	var name = $("#searchName").val();
	if(name=="找人"){
		name = "";
	}
	location.href = "/search/user.jspa?name="+encodeURIComponent(name);
}
</script>
</s:else>
