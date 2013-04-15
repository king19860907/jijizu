<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>

<div id="advanced_search" style="display:none;">
  <div class="layer_point ly5 lay_box">
    <div class="bg">
      <h1>高级搜索<a href="javascript:void(0);" class="span" onclick="hide_advanced_search()"></a></h1>
      <div class="search_adv_detail form_table clearfix">
      	<div class="adv_form">
        	<dl class="adv_input clearfix"><dt>昵称：</dt><dd class="conbox"><input type="text" id="nickName" class="W_inputStp" value="<s:property value="nickName"/>" name="nickname"></dd></dl>
            <dl class="adv_input clearfix"><dt>学校：</dt>
            	<dd class="conbox">
            		<input type="text" class="W_inputStp" id="school_name" value="<s:property value="schoolName"/>" name="school" suggest="true" node-type="school">
            	</dd></dl>
            <dl class="adv_input clearfix"><dt>公司：</dt>
            	<dd class="conbox">
            		<input id="companyName" type="text" class="W_inputStp" value="<s:property value="companyName"/>" name="work" suggest="true" node-type="work">
            	</dd></dl>
            <dl class="adv_add clearfix"><dt>地点：</dt>
            	<dd class="conbox">
            					<select name="province" id="first" style="width:100px;">
					            </select>
					            <select name="city" id="second" style="width:100px;">
					            </select>
					            <select name="district" id="third" class="last" style="width:100px;">
					            </select>
            	</dd>
            </dl>
            <dl class="adv_age clearfix"><dt>年龄：</dt><dd class="conbox">
            	<select class="select2" name="age" id="ageType" hidefocus="true">
            		<option value="">不限</option>
            		<option value="1" <s:if test='ageType==1'>selected="selected"</s:if>>18岁以下</option>
            		<option value="2" <s:if test='ageType==2'>selected="selected"</s:if>>19~22岁</option>
            		<option value="3" <s:if test='ageType==3'>selected="selected"</s:if>>23~29岁</option>
            		<option value="4" <s:if test='ageType==4'>selected="selected"</s:if>>30~39岁</option>
            		<option value="5" <s:if test='ageType==5'>selected="selected"</s:if>>40岁以上</option>
            	</select></dd></dl>
            <dl class="adv_sex clearfix"><dt>性别：</dt><dd class="conbox">
            	<select value="all" id="gender" name="gender" class="select2" hidefocus="true">
            		<option value="">不限</option>
            		<option value="m" <s:if test='gender=="m"'>selected="selected"</s:if>>男</option>
            		<option value="f" <s:if test='gender=="f"'>selected="selected"</s:if>>女</option>
            	</select></dd></dl>
        </div>
        <div class="adv_btn"><dl class="p_btn clearfix"><dt>&nbsp;</dt><dd><a class="ok" href="javascript:void(0);" onclick="advanceSearch();return false;"><span>搜索</span></a><a class="cancel" href="javascript:void(0);" onclick="hide_advanced_search()">取消</a></dd></dl></div>
      </div>
      
    </div>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#school_name").autocomplete("/user/findSchoolNames.jspa");
	$("#companyName").autocomplete("/user/findCompanyNames.jspa");

	area.curFirst = '<s:property value="province"/>';
	area.curSecond = '<s:property value="city"/>';
	area.curThird = '<s:property value="district"/>';

	try{
		area.init();
		area.firstChange();
	}catch(ex){}
	
});

var advancedSearchBox=$("#advanced_search");
advancedSearchBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_advanced_search(){
	advancedSearchBox.overlay().load();
}
	
function hide_advanced_search(){
	advancedSearchBox.overlay().close();
}


function advanceSearch(){

	var nickName = encodeURIComponent($("#nickName").val());
	var schoolName = encodeURIComponent($("#school_name").val());
	var companyName = encodeURIComponent($("#companyName").val());
	var province = $("#first").val();
	var city = $("#second").val();
	var district = $("#third").val();
	var gender = $("#gender").val();
	var ageType = $("#ageType").val();

	var url = "/search/user.jspa?nickName="+nickName+"&schoolName="+schoolName+"&companyName="+companyName+"&province="+province+"&city="+city+"&district="+district+"&gender="+gender+"&ageType="+ageType;

	location.href = url;
}

</script>