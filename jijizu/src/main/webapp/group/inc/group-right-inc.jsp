<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div class="W_main_sub_r">
			<s:if test="%{groupInfo.isCreator(sessionUserInfo)}">
	        	<div class="ev_infoMbox">
	        		<s:if test="%{groupInfo.canUpdate(sessionUserInfo)}">
		            	<a href="/group/update.jspa?groupId=<s:property value="groupInfo.groupId"/>">&gt; 修改活动</a>
	        		</s:if>
	        		<s:if test="%{groupInfo.canManageUsers(sessionUserInfo)}">
	                	<a href="/group/manageUser.jspa?groupId=<s:property value="groupInfo.groupId"/>">&gt; 管理成员</a>
	                </s:if>
	                <s:if test="%{groupInfo.canDelete(sessionUserInfo)}">
	                	<a href="javascript:show_delete_layer(<s:property value="groupInfo.groupId"/>);">&gt; 删除活动</a>
	                </s:if>
	            </div>
            </s:if>
            <div class="WB_right_module">
            <form>
              <fieldset>
                <legend class="tit S_txt1">活动组织者</legend>
              </fieldset>
            </form>
            <div class="person_list">
              <dl class="name_pic width285">
                <dt class="pic"> <a target="_blank" href="/u/<s:property value="createUser.userId"/>" title="<s:property value="createUser.name"/>"><img width="45" height="45" title="<s:property value="createUser.name"/>" alt="<s:property value="createUser.name"/>" src="<s:property value="%{createUser.getHeadImgUrl(150)}"/>"></a> </dt>
                <dd class="con_name">
                	<div class="btn">
                		 <s:if test="sessionUserInfo != null && sessionUserInfo.userId != createUser.userId">
							<s:if test="%{@com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,createUser.userId)}">
				               <div class="old_friend_btn"></div>
				            </s:if>
				            <s:else>
				               <a href="javascript:followUser(<s:property value="createUser.userId"/>);" class="addFriend"></a>
				            </s:else>
						</s:if>
                	</div>
                    <p class="name"><a target="_blank" href="/u/<s:property value="createUser.userId"/>" title="<s:property value="createUser.name"/>"><s:property value="createUser.name"/></a></p>
                </dd>
                <dd class="change"><a class="icon_close" href="javascript:void(0);"></a></dd>
              </dl>
            </div>
          </div>
          <!-- 集集组参与人 -->
          <div class="WB_right_module" id="group-users-div">
            
          </div>
          <!-- 可能感兴趣的集集组 -->
          <div class="WB_right_module" id="group-recommend-div">
           
          </div>
        </div>
<script type="text/javascript">

$(document).ready(function(){
	findGroupUsers(<s:property value="groupInfo.groupId"/>);
	findRecommendGroup();
});

</script>