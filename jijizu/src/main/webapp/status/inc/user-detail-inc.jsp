<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<div class="plc_profile_header">
            <div class="pl_profile_photo"><img src="<s:property value="%{userInfo.getHeadImgUrl(150)}"/>" width="150" height="150" /></div>
            <div class="pl_profile_hisInfo">
              <div class="pf_name"> <span class="name"><s:property value="userInfo.name"/>
              	<a target="_blank" href="/auth/">
                  		<s:if test="userInfo.vFlag==1">
		              		<ins class="vip"></ins>
		              	</s:if>
		              	<s:if test="userInfo.enterpriseFlag==1">
		              		<ins class="vip_agency"></ins>
		              	</s:if>
                  	</a>
              </span> 
              <span class="link"><a href="/u/<s:property value="userInfo.userId"/>"><s:property value="baseUrl"/>/u/<s:property value="userInfo.userId"/></a></span> </div>
              <div class="pf_intro"> <span><s:property value="userInfo.userDesc"/></span> </div>
              <div class="pf_tags">
                <div class="tags"> 
                	<s:if test='userInfo.gender=="f"'><em title="女" class="women"></em></s:if>
                	<s:elseif test='userInfo.gender=="m"'><em title="男" class="man"></em></s:elseif>
               <s:if test="userInfo.constellation != null">
                <span class="W_vline">|</span> <span class="S_txt3"><s:property value="userInfo.constellation"/></span> 
               </s:if>
                <span class="W_vline">|</span> <span class="S_txt3"><s:property value="userInfo.cityStr"/>&nbsp;<s:property value="userInfo.districtStr"/></span> 
               <s:if test="userInfo.lastestUserSchool.schoolName != null">
                <span class="W_vline">|</span> <span class="S_txt3"><s:property value="userInfo.lastestUserSchool.schoolName"/></span> 
               </s:if> 
               <s:if test="userInfo.lastestUserJob.companyName != null">
                <span class="W_vline">|</span> <span class="S_txt3"><s:property value="userInfo.lastestUserJob.companyName"/></span>
               </s:if>
                </div>
              </div>
              <div class="pf_do">
              	<s:if test="sessionUserInfo.userId==userInfo.userId">
                <div class="btn_bed"><a href="/user/base.jspa" class="editInformation">编辑个人资料</a></div>
                </s:if>
                <s:if test="sessionUserInfo.userId!=userInfo.userId">
	                <s:if test="%{userInfo.isFollowEachOther(sessionUserInfo)}">
		                <div class="focusLink"><b>互为好友</b><em>|</em><a onclick="showcancel_friend_box(<s:property value="userInfo.userId"/>,'<s:property value="userInfo.name"/>')" href="javascript:void(0);">取消</a></div>
	                </s:if>
	                <s:else>
	                	<div class="btn_bed"><a href="javascript:followUser(<s:property value="userInfo.userId"/>);" class="addFriend"></a></div>
	                </s:else>
	                <s:if test="%{userInfo.isFollowEachOther(sessionUserInfo)}">
		                <div class="btn_bed btn_links">
		                	<a href="javascript:void(0);" class="letter" onclick="show_box_sixin_new('<s:property value="userInfo.name"/>(<s:property value="userInfo.nickName"/>)')">发私信</a>
		                	<!-- <span class="S_line1_c">|</span><a href="javascript:void(0);" class="at" onclick="show_at_box()">@他</a>  -->
		                </div>
	                </s:if>
              	</s:if>
              </div>
            </div>
          </div>
          <s:if test="%{userInfo.canViewAlbum(sessionUserInfo)}">
          	 <s:if test="photoPage != null && photoPage.recordCnt!=0">
			 	 <div class="slide_panel">
				            <ul>
				              <s:iterator value="photoPage.result" status="stat">
				              	<li <s:if test="#stat.index==0">class="first"</s:if>><a href="/album/photoDetail.jspa?photoId=<s:property value="photoId"/>"><img src="<s:property value="imgUrl"/>" width="166" /></a></li>
				              </s:iterator> 
				            </ul>
				            <a href="/album/?userId=<s:property value="userId"/>" target="_blank" class="slide_panel_pager"> <i></i> </a>
				 </div>
			 </s:if>
          </s:if>
 <s:set name="actionName" value="#request['struts.request_uri']"></s:set>          
 <div class="pl_profile_nav">
            <ul 
            	<s:if test="#actionName=='/status-detail.jspa' || #actionName=='/user/view.jspa'">
            	class="pftb_ul1"
            	</s:if>
            	<s:elseif test="#actionName=='/user/friends.jspa'">
            	class="pftb_ul2"
            	</s:elseif>
            	<s:else>
            	class="pftb_ul3"
            	</s:else>
            >
              <li class="one"><a href="/u/<s:property value="userInfo.userId"/>">主页</a></li>
              <li class="two"><a href="/u/<s:property value="userInfo.userId"/>/friends?type=his">好友</a></li>
              <li class="three"><a href="/u/<s:property value="userInfo.userId"/>/info">个人资料</a></li>
            </ul>
          </div>