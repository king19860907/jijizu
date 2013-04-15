<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div class="sch_listBrd">
                <ul>
                	<s:iterator value="schoolList">
	                    <li><a href="javascript:chooseSchool(<s:property value="schoolId"/>,'<s:property value="schoolName"/>');" id="school_id_<s:property value="schoolId"/>" title="<s:property value="schoolName"/>"><s:property value="schoolName"/></a></li>
                	</s:iterator>
                </ul>
            </div>
<script type="text/javascript">

function chooseSchool(schoolId,schoolName){
	$("#schoolName").val(schoolName);
	$("#schoolId").val(schoolId);
	hideschSearchLayer();
}

</script>