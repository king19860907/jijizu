<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:if test="groupList!=null && groupList.size != 0">
<form>
              <fieldset>
                <legend class="tit S_txt1">热门集集组活动</legend>
                <div class="btns" style="display:none"> <a href="javascript:findHotGroup();" class="W_btn_round_ico" title="换一换"></a> </div>
              </fieldset>
            </form>
            <s:iterator value="groupList">
            <div class="activity_list">
              	<dl>
	                <dt><a href="/group/<s:property value="groupId"/>" target="_blank"><img src="<s:property value="smallPosterImgUrl"/>" width="60" height="80" /></a></dt>
	                <dd class="title"><a href="/group/<s:property value="groupId"/>" target="_blank"><s:property value="title"/></a></dd>
	                <dd class="time"><s:date name="startDate" format="MM月dd日 HH:mm" /></dd>
	                <dd class="number"><s:property value="enterNum"/>人参加</dd>
	                <dd class="btn"><a href="/group/<s:property value="groupId"/>" target="_blank"></a></dd>
	              </dl>
            </div>
             </s:iterator>
</s:if>