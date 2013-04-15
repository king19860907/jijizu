<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<form>
<fieldset><legend class="tit S_txt1">她/他的集集组</legend>
<div class="btns"><a class="W_btn_more" style="display:none" href="集集组/全部集集组.html">更多
&gt;&gt;</a></div>
</fieldset>
</form>
<div class="activity_list">
	<s:iterator value="groupPage.result">
		<dl>
			<dt><a target="_blank" href="/group/<s:property value="groupId"/>"><img width="60"
				height="80" src="<s:property value="smallPosterImgUrl"/>"></a></dt>
			<dd class="title"><a target="_blank" href="/group/<s:property value="groupId"/>"><s:property value="title"/></a></dd>
			<dd class="time"><s:date name="startDate" format="MM月dd日 HH:mm" /> - <s:date name="endDate" format="MM月dd日 HH:mm" /></dd>
			<dd class="number"><s:property value="enterNum"/>人参加</dd>
			<dd class="btn"><a target="_blank" href="/group/<s:property value="groupId"/>"></a></dd>
		</dl>
	</s:iterator>
</div>