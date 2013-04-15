<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基本信息-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="/favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.jSelectDate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/user.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/area.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/area2.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/city.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/select_util.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>

<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main bg3">
  <div class="W_main_bg clearfix"> 
    <!--菜单部分-->
    <%@include file="/user/set/inc/left-inc.jsp" %>
    <!--内容部分-->
    <div class="W_main_r3">
      <div class="MIB_setup">
        <div class="setup_main">
          <div class="index_title">
            <h3>基本信息<span class="W_textb"><i class="CH">(</i>&nbsp;<i style="color:red;" class="CH">*</i>&nbsp;必须填写项&nbsp;<i class="CH">)</i></span></h3>
            <h4>填写真实的资料，有助于好友找到你哦。</h4>
          </div>
          <%@include file="/user/set/inc/data-integrity-inc.jsp" %>
          <div id="information_box" class="info_tab01" style="display: block;">
          <form id="update-user-info-form" method="post">
            <table>
              <tbody>
                <tr>
                  <th class="gray6"><em class="error_color">*</em> 姓&nbsp;&nbsp;名：</th>
                  <td><input type="text" id="name" name="name" value="<s:property value="userInfo.name"/>" class="PY_input"/></td>
                </tr>
                <tr>
                  <th class="gray6"><em class="error_color">*</em> 昵&nbsp;&nbsp;称：</th>
                  <td><input type="text" id="nickName" name="nickName" value="<s:property value="userInfo.nickName"/>" class="PY_input"/></td>
                </tr>
                <tr>
                  <th class="gray6"><em class="error_color">*</em> 性&nbsp;&nbsp;别：</th>
                  <td class="info_tab_vm"><input type="radio" <s:if test='userInfo.gender=="m"'>checked="checked"</s:if> class="info_radio01" value="m" name="gender" id="male"/>
                    <label for="man">男</label>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" class="info_radio01"  <s:if test='userInfo.gender=="f"'>checked="checked"</s:if> value="f" name="gender" id="female"/>
                    <label for="woman">女</label>
                    </td>
                </tr>
                <tr>
                  <th class="gray6">生日：</th>
                  <td class="info_tab_vm"><input class="date" type="text" id="birthday"
												name="birthday"
												value="<s:date name="userInfo.birthday" format="yyyy-MM-dd" />" /></td>
                </tr>
                <tr>
                  <th class="gray6">血型：</th>
                  <td class="info_tab_vm"><select init_value="" name="blood">
                      <option value="">请选择</option>
                      <option value="A" <s:if test='userInfo.blood=="A"'>selected="selected"</s:if>>A型</option>
                      <option value="B" <s:if test='userInfo.blood=="B"'>selected="selected"</s:if>>B型</option>
                      <option value="AB" <s:if test='userInfo.blood=="AB"'>selected="selected"</s:if>>AB型</option>
                      <option value="O" <s:if test='userInfo.blood=="O"'>selected="selected"</s:if>>O型</option>
                    </select></td>
                </tr>
                <tr>
                  <th class="gray6"><em class="error_color">*</em> 现居地：</th>
                  <td class="info_tab_vm">
	                 <div class="address_edit">
	                          <select name="select">
					              <option>中国</option>
					            </select>
					            <select name="province" id="first" style="width:100px;">
					            </select>
					            <select name="city" id="second" style="width:100px;">
					            </select>
					            <select name="district" id="third" class="last" style="width:100px;">
					            </select>
	                          <input type="text" value="<s:property value="userInfo.livingCommunityName"/>" class="PY_input" id="living1"/>
	                          <input type="hidden" id="living_community_hidden" name="livingCommunity" value="<s:property value="userInfo.livingCommunity.id"/>"/>
	                      </div>
                    </td>
                </tr>
                <tr>
                  <th class="gray6">家乡：</th>
                  <td class="info_tab_vm">
                   <div class="address_edit">
	                          <select name="select">
					              <option>中国</option>
					            </select>
					            <select name="hometownProvince" id="first2" style="width:100px;">
					            </select>
					            <select name="hometownCity" id="second2" style="width:100px;">
					            </select>
					            <select name="hometownDistrict" id="third2" class="last" style="width:100px;">
					            </select>
	                          <input type="text" value="<s:property value="userInfo.hometownLivingCommunityName"/>" class="PY_input" id="living2"/>
	                          <input type="hidden" id="hometown_living_community_hidden" name="hometownLivingCommunity" value="<s:property value="userInfo.hometownLivingCommunity.id"/>"/>
	                      </div>
                    </td>
                </tr>
                <tr>
                  <th class="gray6">微博内容：</th>
                  <td class="info_tab_vm">
                  <input type="radio" <s:if test='userInfo.statusPermission=="PERMISSION_TYPE_ALL"'>checked="checked"</s:if>class="info_radio01" value="PERMISSION_TYPE_ALL" name="statusPermission" id="man"/>
                    <label for="man">所有人可见</label>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" <s:if test='userInfo.statusPermission=="PERMISSION_TYPE_FRIENDS"'>checked="checked"</s:if> class="info_radio01" value="PERMISSION_TYPE_FRIENDS" name="statusPermission" id="woman"/>
                    <label for="woman">仅好友可见</label>
                    </td>
                </tr>
                <tr>
                  <th class="gray6">相册信息：</th>
                  <td class="info_tab_vm">
                  <input type="radio" <s:if test='userInfo.albumPermission=="PERMISSION_TYPE_ALL"'>checked="checked"</s:if> class="info_radio01" value="PERMISSION_TYPE_ALL" name="albumPermission" />
                    <label for="man">所有人可见</label>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" <s:if test='userInfo.albumPermission=="PERMISSION_TYPE_FRIENDS"'>checked="checked"</s:if> class="info_radio01" value="PERMISSION_TYPE_FRIENDS" name="albumPermission" />
                    <label for="woman">仅好友可见</label>
                    </td>
                </tr>
                <tr>
                  <th class="gray6">一句话介绍：</th>
                  <td><textarea class="info_txtarea" id="user_desc" name="userDesc"><s:property value="userInfo.userDesc"/></textarea>
                    </td>
                </tr>
              </tbody>
            </table>
            </form>
          	<div class="add_btn"><a id="update-user-info-btn" href="javascript:void(0)">保存信息</a></div>
          </div>
          <div class="border_btm"></div>
          <div class="setting_box">
          		<h4>
          			您的宝宝
	          		<div class="setting_botton"><a href="javascript:void(0);" onclick="$('#add-child-div').show();">新增宝宝</a></div>
          		</h4>
          		<!-- 孩子信息 -->
          		<div id="child-info-div">
          		
          		</div>
            </div>
          <div  id="add-child-div" class="info_tab01" style="display: none;">
          		<form id="add-child-form" method="post">
          		<input type="hidden" name="childId" value=""/>
                <table>
                    <tbody>
                        <tr>
                      <th class="gray6"><em class="error_color">*</em> 孩子昵称：</th>
                      <td><input type="text" id="childNickName" name="nickName" value="" class="PY_input"/></td>
                    </tr>
                    <tr>
                      <th class="gray6"><em class="error_color">*</em>孩子姓名：</th>
                      <td><input type="text" id="childName" name="name" value="" class="PY_input"/></td>
                    </tr>
                    <tr>
                      <th class="gray6"><em class="error_color">*</em> 孩子性别：</th>
                      <td class="info_tab_vm"><input type="radio" checked="checked" class="info_radio01" value="m" name="gender" id="childMale"/>
                        <label for="man">男</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" class="info_radio01" value="f" name="gender" id="childFemale"/>
                        <label for="woman">女</label>
                        </td>
                    </tr>
                    <tr>
                      <th class="gray6"><em class="error_color">*</em> 孩子生日：</th>
                      <td id="birthday" class="info_tab_vm birthDay">
                      	<input class="date" type="text" id="childBirthday"
												name="birthday"
												value="" />
                      </td>
                    </tr>
                    <tr>
                      <th class="gray6">孩子学校类型：</th>
                      <td class="info_tab_vm"><select onchange="changeSchoolType();" id="school_type" name="type">
		                  	  <s:iterator value="schoolTypes">
		                     	 <option  value="<s:property value="dictCode"/>" label=""><s:property value="name"/></option>
		                  	  </s:iterator>
		                    </select></td>
                    </tr>
                    <tr>
                      <th class="gray6">孩子学校名称：</th>
                      <td>
                      	<input type="text" name="schoolName" id="schoolName" value="点击选择学校" class="PY_input school"/>
		                 <input type="hidden" name="schoolId" id="schoolId"/>
                      </td>
                    </tr>
                    <tr>
                      <th class="gray6">班级：</th>
                      <td><input type="text" id="department" name="department" value="" class="PY_input"/></td>
                    </tr>
                    </tbody>
                </table>
                </form>
                <div class="addChild" style="display:none"><a href="javascript:void(0)">添加孩子信息</a></div>
                <div class="add_btn"><a id="add-child-btn" href="javascript:addChild()">保存信息</a></div>
                <div class="clear"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 删除孩子信息弹出层 -->
<%@include file="/user/set/layer/del-child-layer.jsp" %>
<!-- 查询学校弹出层 -->
<%@include file="/user/set/layer/find-school-layer.jsp" %>
<script language="javascript">
$(document).ready(function() {
	changeSchoolType();
	findChildInfo();
	//时间日期
	$("input.date").jSelectDate({
		css:"date",
		yearBeign: 1900,
		disabled : false,
		showLabel : false
	}); 
	
	$("#selYear1").prepend("<option value=''>请选择</option>");
	$("#selMonth1").prepend("<option value=''>请选择</option>");
	$("#selDay1").prepend("<option value=''>请选择</option>");
	
	if($("#birthday").val()==""){
		$("#selYear1").val("");
		$("#selMonth1").val("");
		$("#selDay1").val("");
	}
	
	
	area.curFirst = '<s:property value="userInfo.province"/>';
	area.curSecond = '<s:property value="userInfo.city"/>';
	area.curThird = '<s:property value="userInfo.district"/>';
	area2.curFirst = '<s:property value="userInfo.hometownProvince"/>';
	area2.curSecond = '<s:property value="userInfo.hometownCity"/>';
	area2.curThird = '<s:property value="userInfo.hometownDistrict"/>';
	try{
		area.init();
		area.firstChange();
	}catch(ex){}
	
	prepareLivingCommunity($("#living1"),$("#living_community_hidden"));
	prepareLivingCommunity($("#living2"),$("#hometown_living_community_hidden"));
	
});

</script>
</body>
</html>
