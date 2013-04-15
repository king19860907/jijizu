<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看私信-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/jquery.autocomplete.css"/>
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
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
            	<a href="javascript:void(0);" class="hover">我的私信</a>
            </div>
            <div class="letter_btn">
            	<a href="javascript:void(0);" id="new_sixin" onclick="show_box_sixin_new()"></a><span>共<s:property value="mailPage.recordCnt"/>个联系人</span>
            </div>
          <div class="pl_content">
          	<s:iterator value="mailPage.result">
          		<div class="WB_feed_type">
	              <div class="WB_feed_datail">
	                <div class="WB_face"> <a class="USER_CARD" uid="<s:property value="mailUserId"/>" href="/u/<s:property value="mailUserId"/>" target="_blank"><img src="<s:property value="mailUserInfo.headImgUrl"/>" width="50" height="50" /></a> </div>
	                <div class="WB_detail">
	                  <div class="WB_func">
	                  	<div class="Comment_detail" mailShowId="<s:property value="mailShowId"/>"><a class="USER_CARD" uid="<s:property value="mailUserId"/>" href="/u/<s:property value="mailUserId"/>" target="_blank"><s:property value="mailUserInfo.name"/></a>
	                  	<a target="_blank" href="/auth/">
	                  		<s:if test="mailUserInfo.vFlag==1">
			              		<ins class="vip"></ins>
			              	</s:if>
			              	<s:if test="mailUserInfo.enterpriseFlag==1">
			              		<ins class="vip_agency"></ins>
			              	</s:if>
	                  	</a>
	                  	：<s:property value="lastMailContent" escape="false"/></div>
	                    <div class="WB_handle"><span class="hover"><a class="del_sixin" onclick="show_sixin_del('<s:property value="mailUserInfo.name"/>',<s:property value="mailShowId"/>)" href="javascript:void(0);">删除</a> <i class="S_txt3">|</i> </span> <a href="/mail/mailDetail.jspa?mailShowId=<s:property value="mailShowId"/>">共<s:property value="mailNum"/>条私信</a> <i class="S_txt3">|</i> <a href="javascript:void(0);" class="Reply_sixin" onclick="show_box_sixin_new('<s:property value="mailUserInfo.name"/>(<s:property value="mailUserInfo.nickName"/>)')">回复</a></div>
	                    <div class="WB_from">
	                    	<div class="WB_time">
		                    	<s:if test="overOneDay">	
					                    <s:date name="lastMailDate" format="yyyy-MM-dd HH:mm:ss" />
					            </s:if>
					            <s:else>
					                  	<s:date name="lastMailDate" nice="true" />
					            </s:else>
				            </div>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
          	</s:iterator>
          </div>
          <s:if test="mailPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="mailPage.befPageNum>0 && mailPage.curPageNum!=1">      	
						   <a href="/mail.jspa?page=<s:property value="mailPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="mailPage.pageList" id="curPage">
							<s:if test="#curPage==mailPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/mail.jspa?page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="mailPage.curPageNum!=mailPage.pageCnt">
						   <a href="/mail.jspa?page=<s:property value="mailPage.aftPageNum" />" class="btn_page">下一页</a>
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
<!-- 删除私信弹层 -->
<%@include file="/mail/layer/del-mail-layer.jsp" %>
<!-- 创建私信弹层 -->
<%@include file="/mail/layer/create-mail-layer.jsp" %>
<script language="javascript">
$(document).ready(function(){
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
});
</script>
</body>
</html>