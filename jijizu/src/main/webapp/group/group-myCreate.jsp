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
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
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
        <%@include file="/group/inc/group-tab-inc.jsp" %>
        <div class="evlist_main">
        	<ul>
        		<s:iterator value="groupPage.result">
        			<li>
	                	<div class="evlist_img"><a href="/group/<s:property value="groupId"/>" target="_blank"><img src="<s:property value="smallPosterImgUrl"/>" width="60" height="80" /></a></div>
	                    <div class="event_action">
	                    	<s:if test="groupStatus==0">
	                    		<i class="State">即将开始</i>
	                    	</s:if>
	                    	<s:elseif test="groupStatus==1">
	                    		<i class="State">正在进行</i>
	                    	</s:elseif>
	                    	<s:elseif test="groupStatus==2">
	                    		<i class="State over">已经结束</i>
	                    	</s:elseif>
	                    	<p class="event_comm"></p>
	                    </div>
	                    <div class="evlist_cont width500">
	                    	<p class="evlist_title width500"><a href="/group/<s:property value="groupId"/>" target="_blank"><s:property value="title"/></a></p>
	                        <p class="evlist_infor width500">时　间：<s:date name="startDate" format="yyyy年MM月dd日 HH:mm" /> - <s:date name="endDate" format="yyyy年MM月dd日 HH:mm" /></p>
	                        <p class="evlist_infor width500">地　点：<s:property value="cityStr"/><s:property value="districtStr"/><s:property value="address"/></p>
	                        <p class="evlist_infor width500">发　起：<a href="/u/<s:property value="userId"/>" target="_blank"><s:property value="userName"/></a>
	                        <a target="_blank" href="/auth/">
			                  		<s:if test="userInfo.vFlag==1">
					              		<ins class="vip"></ins>
					              	</s:if>
					              	<s:if test="userInfo.enterpriseFlag==1">
					              		<ins class="vip_agency"></ins>
					              	</s:if>
			                  	</a>
	                        </p>
	                    </div>
	                </li>
        		</s:iterator>
            </ul>
        </div>
       <s:if test="groupPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="groupPage.befPageNum>0 && groupPage.curPageNum!=1">      	
						   <a href="/group/myCreate.jspa?page=<s:property value="groupPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="groupPage.pageList" id="curPage">
							<s:if test="#curPage==groupPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/group/myCreate.jspa?page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="groupPage.curPageNum!=groupPage.pageCnt">
						   <a href="/group/myCreate.jspa?page=<s:property value="groupPage.aftPageNum" />" class="btn_page">下一页</a>
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
