<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
   <div class="period">
   				<!-- 
                	<div class="title">
                        <span class="start">2013.2.5</span>
                        <span class="end">2013.2.11</span>
                    </div> 
                    <p class="txt">生病症状：<span><s:property value="diary.symptomStr"/></span></p>-->
                </div>
		<div class="little_book_tab">
                	<ul>
                      <li class="hover" onclick="setTab('tab',1,2)" id="tab1"><a href="javascript:void(0);">有相同症状的小朋友的家长</a></li>
                      <!-- 
                      <li onclick="setTab('tab',2,2)" id="tab2" class=""><a href="javascript:void(0);">以前有相同症状的小朋友</a></li> -->
                    </ul>
                </div>
		<div class="clearfix" id="con_tab_1" style="display: block;">
                    <div class="conResult_sam">
                    	<s:iterator value="userList">
                    		<dl>
	                            <dt><a href="/u/<s:property value="userId"/>" target="_blank"><img width="80" height="80" src="<s:property value="%{getHeadImgUrl(80)}"/>"></a></dt>
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
	                             <dd class="btn">
			                    	<s:if test="sessionUserInfo != null && sessionUserInfo.userId != userId">
				                    	<s:if test="%{@com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,userId)}">
				                    		<div class="focusLink"><b>互为好友</b></div>
				                    	</s:if>
				                    	<s:else>
				                    		<a href="javascript:followUser(<s:property value="userId"/>);" class="addFriend"></a>
				                    	</s:else>
			                    	</s:if>
			                    </dd>
	                        </dl>
                    	</s:iterator>
                    </div>
                </div>