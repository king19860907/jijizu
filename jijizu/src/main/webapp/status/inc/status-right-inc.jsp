<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:set name="actionName" value="#request['struts.request_uri']"></s:set> 
<div <s:if test='#headActionName=="/search/user.jspa"'>class="W_main_sub_r"</s:if><s:else>class="W_main_r"</s:else>>
		<s:if test="userInfo != null">
          <div class="pl_rightmod_myinfo">
            <dl class="W_person_info clearfix">
              <dt><a href="/u/<s:property value="userInfo.userId"/>"><img src="<s:property value="%{userInfo.getHeadImgUrl(80)}"/>" width="80" height="80" /></a></dt>
              <dd class="nameBox"> <a title="<s:property value="userInfo.name"/>" href="/u/<s:property value="userInfo.userId"/>"><span><s:property value="userInfo.name"/></span>
              	<s:if test="userInfo.vFlag==1">
              		<ins class="vip"></ins>
              	</s:if>
              	<s:if test="userInfo.enterpriseFlag==1">
              		<ins class="vip_agency"></ins>
              	</s:if>
              	<s:if test='userInfo.gender=="f"'>
	              	<i class="girl"></i>
              	</s:if>
              	<s:elseif test='userInfo.gender=="m"'>
    	          	<i class="boy"></i>
              	</s:elseif>
              	</a>
                <div class="address"><s:property value="userInfo.cityStr"/>&nbsp;<s:property value="userInfo.districtStr"/></div>
                <div class="icon_bed" id="Information" style="display:none"> <a href="javascript:void(0);" onclick="Information()"><i></i><span style="display:none">30%</span></a>
                  <div class="tips">
                    <div class="txt">完善个人资料，有助于好友找到你哦<del><a href="javascript:void(0);" onclick="InformationHide()"></a></del></div>
                  </div>
                </div>
              </dd>
            </dl>
            <ul class="user_atten clearfix">
              <li class="S_line2"><a class="S_func1" href="/u/<s:property value="userInfo.userId"/>/friends?type=his"><strong><s:property value="userInfo.friendNum"/></strong><span>好友</span></a></li>
              <li class="S_line2"><a class="S_func1" href="/u/<s:property value="userInfo.userId"/>"><strong><s:property value="userInfo.statusNum"/></strong><span>微博</span></a></li>
              <li class="W_no_border"><a class="S_func1" href="/group/"><strong><s:property value="userInfo.groupNum"/></strong><span>集集组</span></a></li>
            </ul>
          </div>
          </s:if>
          <!-- 用户推荐 -->
          <div class="WB_right_module" id="recommend_person_list">
            
       	 </div>
        <s:if test='#actionName!="/group/index.jspa" && #actionName!="/group/myEnter.jspa" && #actionName!="/group/myCreate.jspa" '>
          <div class="WB_right_module" style="display:none">
            <form>
              <fieldset>
                <legend class="tit S_txt1">集集组活动推荐</legend>
              </fieldset>
            </form>
            <div class="recommend">
              <dl>
                <dt><a href="集集组/集集组详情页.html" target="_blank"><img src="${IMG_PATH}/activity_01.jpg" width="190" height="190" /></a></dt>
                <dd class="title"><a href="集集组/集集组详情页.html" target="_blank">薛妙音等人已参加</a></dd>
                <dd class="btn"><a href="集集组/集集组详情页.html" target="_blank"></a></dd>
              </dl>
            </div>
          </div>
         </s:if>
         <!-- 热门集集组 -->
          <div class="WB_right_module" id="group-recommend-div">
            
          </div>
        </div>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test="sessionUserInfo != null">
		findRecommendPerson();
	</s:if>
	findHotGroup();
});

function findHotGroup(){
	$.ajax({
		type:'POST',
		url:'/group/findHotGroup.jspa',
		success:function(data){
			$("#group-recommend-div").html(data);
		}
	});
}
</script>