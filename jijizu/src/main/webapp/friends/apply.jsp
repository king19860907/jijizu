<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${JS_PATH}/global.js"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js"></script>
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
        <div class="conResult_sam">
        		<s:iterator value="friendsPage.result">
        			<dl>
	                    <dt><a href="/u/<s:property value="userId"/>" target="_blank"><img src="<s:property value="%{getHeadImgUrl(80)}"/>" width="80" height="80" /></a></dt>
	                    <dd class="name"><a class="Fl" href="/u/<s:property value="userId"/>" target="_blank"><s:property value="name"/></a>
	                    	<a target="_blank" href="/auth/">
		                  		<s:if test="vFlag==1">
				              		<ins class="vip"></ins>
				              	</s:if>
				              	<s:if test="enterpriseFlag==1">
				              		<ins class="vip_agency"></ins>
				              	</s:if>
		                  	</a>
	                    </dd>
	                    <dd><s:property value="cityStr"/><s:property value="districtStr"/></dd>
	                    <dd class="btn"><a class="addFriend" onclick="followUser(<s:property value="userId"/>);" href="javascript:void(0);"></a></dd>
	                </dl>
        		</s:iterator>
            </div>
        <s:if test="friendsPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="friendsPage.befPageNum>0 && friendsPage.curPageNum!=1">      	
						   <a href="/friends/apply.jspa<s:property value="%{getParamsUrl('page',friendsPage.befPageNum,true)}"/>" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="friendsPage.pageList" id="curPage">
							<s:if test="#curPage==friendsPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/friends/apply.jspa<s:property value="%{getParamsUrl('page',#curPage,true)}"/>"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="friendsPage.curPageNum!=friendsPage.pageCnt">
						   <a href="/friends/apply.jspa<s:property value="%{getParamsUrl('page',friendsPage.aftPageNum,true)}"/>" class="btn_page">下一页</a>
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
<!--弹出框-->

<script language="javascript">

</script>
</body>
</html>
