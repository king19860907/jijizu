<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:iterator value="jobs" status="s">
	<div class="setting_box" id="job_info_<s:property value="jobId"/>">
          		<s:if test="#s.index==0"><h4>工作单位</h4></s:if>
                <form>
                  <fieldset>
                    <legend class="tit S_txt1"><span class="name"><s:property value="companyName"/></span></legend>
                    <div class="btns"> <a class="W_btn_round" href="javascript:eidtCompanyDiv(<s:property value="jobId"/>,'<s:property value="province"/>','<s:property value="city"/>','<s:property value="companyName"/>','<s:property value="startYear"/>','<s:property value="endYear"/>','<s:property value="department"/>');">编辑</a> <a title="删除" class="W_btn_round_ico" href="javascript:void(0);" onclick="showdel_lay(<s:property value="jobId"/>)"></a> </div>
                  </fieldset>
                </form>
            <s:if test="colleagues.size>0">    
            	<div class="roommate_title">你的同事也在这儿呦<a href="javascript:location.href='/search/user.jspa?companyName=<s:property value="companyName"/>'">查看全部 >></a></div>
         	</s:if>
          </div>
          <div class="roommate_list">
          	<s:iterator value="colleagues">
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
	try{
		area.init();
		area.firstChange();
	}catch(ex){}
	initJobYear();
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
});

function eidtCompanyDiv(jobId,province,city,companyName,startYear,endYear,department){
	var $editJob = $("#edit_job_div");
	area.curFirst = province;
	area.curSecond = city;
	area.init();
	area.firstChange();
	//$("#first").val(province);
	//$("#second").val(city);
	$("#edit-job-title").html("编辑工作信息");
	$("#add-job-btn").attr("href","javascript:updateUserJob();");
	$("#jobid-hidden").val(jobId);
	$("#companyName").val(companyName);
	$("#department").val(department);
	$("#job_start_year").val(startYear);
	$("#job_end_year").val(endYear);
	$("#job_info_"+jobId).append($editJob);
}
</script>
