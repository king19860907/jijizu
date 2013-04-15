<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>

<s:iterator value="children">
<form id="child-info-<s:property value="childId"/>">
                  <fieldset>
                    <legend class="tit S_txt1"><span class="name"><s:property value="nickName"/></span><em class="CH">|</em>
                    <s:if test='gender=="m"'>
                   	 <span class="sex">男</span>
                    </s:if>
                    <s:if test='gender=="f"'>
                     <span class="sex">女</span>
                    </s:if>
                    <em class="CH">|</em>
                    <span class="birthday"><s:date name="birthday" format="yyyy-MM-dd" /></span></legend>
                    <div class="btns"> <a class="W_btn_round" href="javascript:editChild(<s:property value="childId"/>,'<s:property value="name"/>','<s:property value="nickName"/>','<s:property value="gender"/>','<s:date name="birthday" format="yyyy-MM-dd" />','<s:property value="schoolInfo.type"/>','<s:property value="schoolInfo.schoolName"/>','<s:property value="department"/>','<s:property value="schoolInfo.schoolId"/>');">编辑</a> <a title="删除" class="W_btn_round_ico" href="javascript:void(0);" onclick="showdel_lay(<s:property value="childId"/>)"></a> </div>
                  </fieldset>
                </form>
</s:iterator>

<script type="text/javascript">

/**
 * 编辑孩子信息
 */
function editChild(childId,name,nickName,gender,birthday,type,schoolName,department,schoolId){
	var $addChildDiv = $("#add-child-div");
	$("#child-info-"+childId).after($addChildDiv);
	
	$("[name='childId']").val(childId);
	$("#childName").val(name);
	$("#childNickName").val(nickName);
	$("#department").val(department);
	if(gender == 'f'){
		$("#childFemale").attr("checked",true);	
	}else{
		$("#childMale").attr("checked",true);	
	}
	$(".jSelectDateBorder").each(function(i){
		if(i == 1){
			$(this).remove();
		}
	});
	$("#childBirthday").val(birthday);
	jSelectDate.init($("#childBirthday"));
	$("#school_type").val(type);
	$("#schoolName").val(schoolName);
	$("#schoolId").val(schoolId);

	$("#add-child-btn").attr("href","javascript:updateChild();");
	$('#add-child-div').show();
	
	changeSchoolType();
}

</script>