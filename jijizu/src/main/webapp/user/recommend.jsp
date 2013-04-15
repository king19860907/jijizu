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
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.cookie.js${JS_EDITION}"></script>
</head>
<body>
<div class="Recommend_main">
	<!--头部-->
	<div class="top_bar">
    	<div class="logo"><a href="/"><img src="${IMG_PATH}/logo.png" /></a></div>
        <div class="user_name">您好，<a href="javascript:void(0);"><s:property value="sessionUserInfo.name"/></a></div>
    </div>
    <div class="Recommend_box">
    	<div class="Recommend_tab">
        	<ul>
              <li id="tab1" onclick="setTab('tab',1,2)" class="hover"><a href="javascript:void(0);">同一城市</a></li>
              <li id="tab2" onclick="setTab('tab',2,2)"><a href="javascript:void(0);">其他</a></li>
            </ul>
        </div>
        <!--END-->
    </div>
    <div id="con_tab_1">
        <div class="Recommend_content mylistBox">
        	<s:iterator value="userPage.result">
        		<div class="myfollow_list" userid="<s:property value="userId"/>">
	                <div class="face"><a href="javascript:void(0);"><img src="<s:property value="headImgUrl"/>" width="50" height="50" /></a></div>
	                <ul class="info">
	                    <li><a href="javascript:void(0);" class="S_func1 Fl"><s:property value="name"/></a>
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
	                </ul>
	            </div>
        	</s:iterator>
        </div>
    </div>
    <div id="con_tab_2" style="display:none;">
    	<div class="Recommend_content mylistBox">
           <s:iterator value="otherUserPage.result">
        		<div class="myfollow_list" userid="<s:property value="userId"/>">
	                <div class="face"><a href="javascript:void(0);"><img src="<s:property value="headImgUrl"/>" width="50" height="50" /></a></div>
	                <ul class="info">
	                    <li><a href="javascript:void(0);" class="S_func1 Fl"><s:property value="name"/></a>
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
	                </ul>
	            </div>
        	</s:iterator>
        </div>
    </div>
    <div class="clear"></div>
    <div class="Recommend_btn">
    	<a href="javascript:void(0);" onclick="return false;" id="follow-batch-btn">关注他们</a>
    	<a href="/home.jspa">跳过</a>
    </div>
</div>
<%@include file="/common/footer.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#follow-batch-btn").click(function(){
			var userIds = "";
			$(".selected").each(function(){
				var userId = $(this).attr("userid");
				userIds = userIds+userId+"-";
			});
			if(userIds == ""){
				location.href="/home.jspa";
				return;
			}
			userIds = userIds.substring(0,userIds.length-1);
			$.ajax({
				type:'POST',
				dataType:'json',
				url:'/follow/followUserBatch.jspa',
				data:({"userIds":userIds
				      }), 
				success:function(data){
				}
			});
			alert("已发送好友申请");
			location.href="/home.jspa";
		});
	});
</script>
</body>
</html>
