<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/notice.js${JS_EDITION}"></script>

<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main">
  <div class="W_main_bg clearfix"> 
    <!--菜单部分-->
    <%@include file="/status/inc/left-tab-inc.jsp" %>
    <!--内容部分-->
    <div class="plc_main">
      <div class="W_main_a">
        <div class="W_main_c">
        	<div class="tab">
            	<h3>系统通知<span class="plus_txt">（共<s:property value="noticePage.recordCnt"/>个）</span></h3>
            </div>
            
          <div class="pl_content">
          	<s:iterator value="noticePage.result">
          		<div class="WB_feed_type" id="notice_<s:property value="noticeId"/>">
	              <div class="WB_feed_datail">
	                <div class="WB_face"> <a href="/u/<s:property value="fromUserId"/>" target="_blank"><img src="<s:property value="fromUserHeadImgUrl"/>" width="50" height="50" /></a> </div>
	                <div class="WB_detail">
	                  <div class="WB_func">
	                  	<div class="Comment_detail" noticeid="<s:property value="noticeId"/>"><a href="/u/<s:property value="fromUserId"/>" target="_blank"><s:property value="fromUserName"/></a>
	                  		<a target="_blank" href="/auth/">
		                  		<s:if test="fromUserVFlag==1">
				              		<ins class="vip"></ins>
				              	</s:if>
				              	<s:if test="fromUserEnterpriseFlag==1">
				              		<ins class="vip_agency"></ins>
				              	</s:if>
		                  	</a>：
	                  		<s:property value="content" escape="false"/></div>
	                    <div class="WB_handle"><span class="hover"><a class="del_sixin" noticeid="<s:property value="noticeId"/>" href="javascript:showdel_notice_box(<s:property value="noticeId"/>);">删除</a> </span></div>
	                    <div class="WB_from" style="display:none"><em class="S_txt2">来自</em> <a href="首页.html" target="_blank" class="WB_where">集集组微博</a> </div>
	                  </div>
	                </div>
	              </div>
	            </div>
          	</s:iterator>
          </div>
          <s:if test="noticePage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="noticePage.befPageNum>0 && noticePage.curPageNum!=1">      	
						   <a href="/notice.jspa<s:property value="%{getParamsUrl('page',noticePage.befPageNum,true)}"/>" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="noticePage.pageList" id="curPage">
							<s:if test="#curPage==noticePage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/notice.jspa<s:property value="%{getParamsUrl('page',#curPage,true)}"/>"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="noticePage.curPageNum!=noticePage.pageCnt">
						   <a href="/notice.jspa<s:property value="%{getParamsUrl('page',noticePage.aftPageNum,true)}"/>" class="btn_page">下一页</a>
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
<!-- 删除通知弹出层 -->
<%@include file="/notice/layer/del-notice-layer.jsp" %>
<script language="javascript">
$(document).ready(function(){
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
});
</script>
</body>
</html>
