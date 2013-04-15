<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/search.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/search.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script> 
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/area.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/select_util.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body class="sign">
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_signbg">
  <div class="wrap2">
    <div class="search_head" style="display:none"> <a href="../搜索/搜索首页.html" class="logo_img">
      <div class="search_logo"></div>
      </a>
      <div class="search_head_formbox">
        <ul class="formbox_tab clearfix">
          <li> <a class="" href="搜索_微博.html">微博</a> <a class="cur" href="搜索_人.html">找人</a> <a class="" href="搜索_图片.html">图片</a> <a class="" href="搜索_集集组.html">集集组</a> </li>
        </ul>
        <div class="search_input_b">
          <div class="search_input_wrap">
            <div class="searchBtn_box"><a class="searchBtn" href="javascript:void(0);">搜索</a></div>
            <div class="searchInp_box">
              <div class="searchInp_auto">
                <input type="text" autocomplete="off" class="searchInp_form" node-type="text" maxlength="40"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="W_main_sub">
    <div class="W_main_sub_bg clearfix"> 
      <!--内容部分-->
      <div class="plc_main_sub"> 
        <!--左边-->
        <div class="W_main_sub_b">
          <div class="pl_event_content">
          	<div class="search_tab more_search_on clearfix">
            	<div class="basic_search clearfix">
                	<div class="tab_l">
                    	<ul class="tab_list">
                            <li style="display:none">所有地区<a class="change" href="javascript:void(0)" onclick="change(this)">[切换]</a></li>
                            <li style="display:none"><em class="W_vline W_textb">|</em></li>
                            <li>
                                <a class="W_moredownb show_gender" href="javascript:void(0)">
                                <span>
                                	<s:if test='gender == "" || gender == null'>
	                                	不限性别
                                	</s:if>
                                	<s:elseif  test='gender == "m"'>
                                		男
                                	</s:elseif>
                                	<s:elseif  test='gender == "f"'>
                                		女
                                	</s:elseif>
                                </span>
                                <span class="more"></span></a>
                            </li>
                            <li><em class="W_vline">|</em></li>
                            <li>
                                <a class="W_moredownb showAgeLayer" href="javascript:void(0)">
                                <span>
                                	<s:if test='ageType == "" || ageType == null'>
	                                	不限年龄
                                	</s:if>
                                	<s:elseif test="ageType == 1">
                                		18岁以下
                                	</s:elseif>
                                	<s:elseif test="ageType == 2">
                                		19~22岁
                                	</s:elseif>
                                	<s:elseif test="ageType == 3">
                                		23~29岁
                                	</s:elseif>
                                	<s:elseif test="ageType == 4">
                                		30~39岁
                                	</s:elseif>
                                	<s:elseif test="ageType == 5">
                                		40岁以上
                                	</s:elseif>
                                </span>
                                <span class="more"></span></a>
                            </li>  
                        </ul>
                    </div>
                    <div class="tab_r">
                        <a class="more_factor" href="javascript:void(0)" onclick="show_advanced_search()">更多条件</a>  
                    </div>
                </div>
                <div>
                	<s:property value="%{getParamsUrl('gender','m',false)"/>
                    <ul class="search_more_ul search_more_ul_03 clearfix">
                        <li><a <s:if test='gender == "" || gender == null'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('gender','',false)}"/>"><span class="txt">不限</span></a></li>
                        <li><a <s:if test='gender == "m"'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('gender','m',false)}"/>"><span class="txt">男</span></a></li>
                        <li><a <s:if test='gender == "f"'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('gender','f',false)}"/>"><span class="txt">女</span></a></li> 
                    </ul>
                    <ul class="search_more_ul search_more_ul_04 clearfix">
                        <li><a <s:if test='ageType == "" || ageType == null'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('ageType','',false)}"/>"><span class="txt">不限</span></a></li>
                        <li><a <s:if test='ageType == 1'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('ageType','1',false)}"/>"><span class="txt">18岁以下</span></a></li>
                        <li><a <s:if test='ageType == 2'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('ageType','2',false)}"/>"><span class="txt">19~22岁</span></a></li> 
                        <li><a <s:if test='ageType == 3'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('ageType','3',false)}"/>"><span class="txt">23~29岁</span></a></li> 
                        <li><a <s:if test='ageType == 4'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('ageType','4',false)}"/>"><span class="txt">30~39岁</span></a></li>
                        <li><a <s:if test='ageType == 5'>class="current"</s:if> href="/search/user.jspa<s:property value="%{getParamsUrl('ageType','5',false)}"/>"><span class="txt">40岁以上</span></a></li> 
                    </ul>
                </div>
                
            </div>
          	<div class="person_list_feed clearfix">
            	<div class="pl_personlist">
                	<s:iterator value="userPage.result">
                		<div class="list_person clearfix">
	                    	<div class="person_pic"><a href="/u/<s:property value="userId"/>" target="_blank"><img width="80" height="80" src="<s:property value="%{getHeadImgUrl(80)}"/>"/></a></div>
	                        <div class="person_adbtn">
	                             <s:if test="sessionUserInfo != null && sessionUserInfo.userId != userId">
									<s:if test="%{@com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,userId)}">
						               <div class="focusLink"><b>互为好友</b></div>
						            </s:if>
						            <s:else>
						               <a href="javascript:followUser(<s:property value="userId"/>);" class="addFriend"></a>
						            </s:else>
								</s:if>
	                        </div>
	                        <div class="person_detail">
	                            <p class="person_name">
	                                <a href="/u/<s:property value="userId"/>" target="_blank"><s:property value="name"/></a>
	                                	<a target="_blank" href="/auth/">
					                  		<s:if test="vFlag==1">
							              		<ins class="vip"></ins>
							              	</s:if>
							              	<s:if test="enterpriseFlag==1">
							              		<ins class="vip_agency"></ins>
							              	</s:if>
					                  	</a>
	                            </p>
	                            <p class="person_addr">
	                                <s:if test='gender=="m"'>
		                                <i class="boy"></i>
	                                </s:if>
	                                <s:elseif test='gender=="f"'>
		                                <i class="girl"></i>
	                                </s:elseif>
	                                <span><s:property value="cityStr"/></span>
	                                <a class="wb_url" href="/u/<s:property value="userId"/>" target="_blank"><s:property value="baseUrl"/>/u/<s:property value="userId"/></a>
	                            </p>
	                            <p class="person_num">
	                                <span>好友<a target="_blank" href="/u/<s:property value="userId"/>/friends?type=his"><s:property value="friendNum"/></a></span>
	                                <span>微博<a target="_blank" href="/u/<s:property value="userId"/>"><s:property value="statusNum"/></a></span>
	                                <span>集集组<a target="_blank" href="/group/"><s:property value="groupNum"/></a></span>
	                            </p>
	                            <div class="person_info">
	                                <p><s:property value="userDesc"/></p>
	                            </div>
	                         </div>
	                    </div>
                	</s:iterator>
                </div>
            </div>
            <s:if test="userPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="userPage.befPageNum>0 && userPage.curPageNum!=1">      	
						   <a href="/search/user.jspa<s:property value="%{getParamsUrl('page',userPage.befPageNum,true)}"/>" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="userPage.pageList" id="curPage">
							<s:if test="#curPage==userPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/search/user.jspa<s:property value="%{getParamsUrl('page',#curPage,true)}"/>"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="userPage.curPageNum!=userPage.pageCnt">
						   <a href="/search/user.jspa<s:property value="%{getParamsUrl('page',userPage.aftPageNum,true)}"/>" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
          </div>
        </div>
        <!--右边-->
        <%@include file="/status/inc/status-right-inc.jsp" %>
        <!--右边END--> 
      </div>
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 高级搜索弹出框 -->
<%@include file="/search/layer/search-condition-layer.jsp" %>

<script language="javascript">

</script> 
</body>
</html>