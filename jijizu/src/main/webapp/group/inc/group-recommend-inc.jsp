<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<s:if test="groupList!=null && groupList.size != 0">
 			<form>
              <fieldset>
                <legend class="tit S_txt1">可能感兴趣的集集组</legend>
                <div class="btns"><a title="换一换" class="W_btn_round_ico" href="javascript:findRecommendGroup();"></a> </div>
              </fieldset>
            </form>
            <div class="event_details">
			  <s:iterator value="groupList">
            	<dl>
              	  <dt><a target="_blank" href="/group/<s:property value="groupId"/>"><img width="60" height="80" src="<s:property value="smallPosterImgUrl"/>"></a></dt>
                  <dd><a target="_blank" href="/group/<s:property value="groupId"/>"><s:property value="title"/></a></dd>
                  <dd><s:date name="startDate" format="MM月dd日 HH:mm" /> - <s:date name="endDate" format="MM月dd日 HH:mm" /></dd>
                  <dd><s:property value="enterNum"/>人参加</dd>
                  <dd class="btn"><a target="_blank" href="/group/<s:property value="groupId"/>" class="addEvent"></a></dd>
                </dl>
              </s:iterator>
            </div>
</s:if>