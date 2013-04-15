<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:iterator value="userSchoolList">
	<div class="setting_box" id="schoolId_info_<s:property value="id"/>">
          		<h4 style="display:none">大学经历</h4>
                <form>
                  <fieldset>
                    <legend class="tit S_txt1"><span class="name"><s:property value="schoolName"/></span>
                    	<s:if test="startYear != null">
	                    	<em class="CH">|</em><span class="birthday"><s:property value="startYear"/>年入学</span>
                    	</s:if>
                    	<s:if test="department != null">
	                    	<em class="CH">|</em><span class="birthday">就读<s:property value="department"/></span>
                    	</s:if>
                    </legend>
                    <div class="btns"> <a class="W_btn_round" href="javascript:eidtSchoolDiv('<s:property value="id"/>','<s:property value="schoolName"/>','<s:property value="startYear"/>','<s:property value="department"/>','<s:property value="type"/>','<s:property value="schoolId"/>');">编辑</a> <a title="删除" class="W_btn_round_ico" href="javascript:void(0);" onclick="showdel_lay(<s:property value="id"/>)"></a> </div>
                  </fieldset>
                </form>
            <s:if test="classmates.size>0">    
            	 <div class="roommate_title">你的同校同学在这儿呦<a href="javascript:location.href='/search/user.jspa?schoolName=<s:property value="schoolName"/>'">查看全部 >></a></div>
         	</s:if>
          </div>
          <div class="roommate_list">
          	<s:iterator value="classmates">
          		<dl>
	            	<dt><a class="USER_CARD" uid="<s:property value="userId"/>" href="/u/<s:property value="userId"/>" target="_blank"><img src="<s:property value="%{getHeadImgUrl(80)}"/>" width="80" height="80" /></a></dt>
	                <dd class="name"><a class="USER_CARD" uid="<s:property value="userId"/>" href="/u/<s:property value="userId"/>" target="_blank"><s:property value="name"/></a></dd>
	                <dd class="btn">
	                	<s:if test="%{@com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,userId)}">
	                		<div class="old_friend_btn"></div>
	                	</s:if>
	                	<s:else>
	                		<a class="addFriend" href="javascript:followUser(<s:property value="userId"/>);"></a>
	                	</s:else>
	                </dd>
	            </dl>
          	</s:iterator>
          </div>
</s:iterator>
<script type="text/javascript">
$(document).ready(function(){

	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
	
});

function eidtSchoolDiv(userSchoolId,schoolName,startYear,department,type,schoolId){
	var $editSchool = $("#edit-school-div");
	$("#edit-school-title").html("编辑教育信息");
	$("#add-school-btn").attr("href","javascript:updateUserSchool();");
	$("#userSchoolId-hidden").val(userSchoolId);
	$("#schoolId").val(schoolId);
	$("#schoolName").val(schoolName);
	$("#department").val(department);
	$("#school_start_year").val(startYear);
	$("#school_type").val(type);
	$("#schoolId_info_"+userSchoolId).append($editSchool);
}
</script>