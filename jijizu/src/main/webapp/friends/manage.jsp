<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>好友管理-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/friends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main">
  <div class="W_main_bg clearfix"> 
    <!--内容部分-->
    <div class="plc_main">
      <div class="W_main_b">
        <%@include file="/friends/inc/friends-tap-inc.jsp" %>
        <div class="tab_normal">
        	<div class="bar_right">
            	<div class="gn_search"><input name="" id="search_friend_name" type="text" onblur="if (value=='') {value='搜索好友'}" onfocus="if(value=='搜索好友') {value=''}" value="搜索好友"  /> <a href="javascript:void(0);" onclick="return false;" class="gn_search_btn" id="gn_search_btn"></a></div>
            </div>
            <div class="bar_left"><a style="display:none" href="javascript:void(0);" class="W_btn_c_disable" id="add_group_btn" onclick="show_myfollow_select(event)"><em>添加到分组</em><span></span></a><a href="javascript:void(0);" class="W_btn_c_disable" id="cancel_friend" onclick="showcancel_friend_box()">解除好友关系</a><p class="chosen">已经选择<span id="select_num">0</span>人</p><a href="javascript:void(0);" class="cancel_select" id="cancel_select" onclick="cancel_select()">取消选择</a></div>
        </div>
       	<div class="mylistBox">
        <s:iterator value="friendsPage.result">
	        	<div class="myfollow_list" userid="<s:property value="userId"/>">
	            	<div class="face"><a href="/u/<s:property value="userId"/>" target="_blank"><img src="<s:property value="headImgUrl"/>" width="50" height="50" /></a></div>
	                <ul class="info">
	                	<li><a href="/u/<s:property value="userId"/>" class="S_func1 Fl" target="_blank"><s:property value="name"/></a>
	                		<a target="_blank" href="/auth/">
		                  		<s:if test="vFlag==1">
				              		<ins class="vip"></ins>
				              	</s:if>
				              	<s:if test="enterpriseFlag==1">
				              		<ins class="vip_agency"></ins>
				              	</s:if>
		                  	</a>
	                	</li>
	                    <li><s:property value="cityStr"/><s:property value="districtStr"/></li>
	                    <li style="display:none"><a href="javascript:void(0);" class="set_group" onclick="show_myfollow_select(event)"><span class="group_txt">未分组</span><span class="W_arrow"></span></a></li>
	                </ul>
	            </div>
        </s:iterator>
	    </div>
         <s:if test="friendsPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="friendsPage.befPageNum>0 && friendsPage.curPageNum!=1">      	
						   <a href="/friends/<s:property value="%{getParamsUrl('page',friendsPage.befPageNum,true)}"/>" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="friendsPage.pageList" id="curPage">
							<s:if test="#curPage==friendsPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/friends/<s:property value="%{getParamsUrl('page',#curPage,true)}"/>"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="friendsPage.curPageNum!=friendsPage.pageCnt">
						   <a href="/friends/<s:property value="%{getParamsUrl('page',friendsPage.aftPageNum,true)}"/>" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
      </div>
      <!--右边-->
        <%@include file="/status/inc/status-right-inc.jsp" %>
        <!--右边END-->
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!-- 弹出层 -->
<!-- 批量取消关注弹出层 -->
<%@include file="/friends/layer/follow-cancel-batch-layer.jsp" %>
<script language="javascript">

</script>

</body>
</html>
