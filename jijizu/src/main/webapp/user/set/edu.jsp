<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教育信息-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="/favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/area.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/city.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/select_util.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/user.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
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
            <h3>教育信息<span class="W_textb"><i class="CH">(</i>&nbsp;<i style="color:red;" class="CH">*</i>&nbsp;必须填写项&nbsp;<i class="CH">)</i></span></h3>
            <h4>完善教育信息，找到同窗好友，分享生活趣事。</h4>
          </div>
         <%@include file="/user/set/inc/data-integrity-inc.jsp" %>
         <div id="user-school-div">
         
         </div>
          <div id="edit-school-div">
          	 <h5 id="edit-school-title" class="edu_title">添加教育信息</h5>
	          <form id="add-user-school-form" method="post">
	          	  <input type="hidden" id="userSchoolId-hidden" name="userSchoolId" value="" />
		          <div id="information_box" class="info_tab01" style="display: block;">
		            <table>
		              <tbody>
		                <tr>
		                  <th class="gray6"><em class="error_color">*</em> 学校类型：</th>
		                  <td class="info_tab_vm"><select onchange="changeSchoolType();" id="school_type" name="type">
		                  	  <s:iterator value="schoolTypes">
		                     	 <option  value="<s:property value="dictCode"/>" label=""><s:property value="name"/></option>
		                  	  </s:iterator>
		                    </select></td>
		                </tr>
		                <tr>
		                  <th class="gray6"><em class="error_color">*</em> 学校名称：</th>
		                  <td>
		                  	<input type="text" name="schoolName" id="schoolName" value="点击选择学校" class="PY_input school"/>
		                  	<input type="hidden" name="schoolId" id="schoolId"/>
		                  </td>
		                </tr>
		                <tr>
		                  <th class="gray6">班级/院系：</th>
		                  <td><input type="text" id="department" name="department" value="请输入完整的班级名称或/院系" onfocus="if(value=='请输入完整的班级名称或/院系') {value=''}" onblur="if (value=='') {value='请输入完整的班级名称或/院系'}" class="PY_input"/></td>
		                </tr>
		                <tr>
		                  <th class="gray6">入学年份：</th>
		                  <td>
		                  	 <select id="school_start_year" name="startYear" defvalue="31"><option value="34">请选择</option></select>
		                  </td>
		                </tr>
		              </tbody>
		            </table>
		          </div>
	          </form>
	          <div id="information_box2" class="info_tab01" style="display: block;">
	                <div class="add_btn"><a id="add-school-btn" href="javascript:addUserSchool();">保存信息</a></div>
	          </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 删除用户学校信息弹出层 -->
<%@include file="/user/set/layer/del-user-school-layer.jsp" %>
<!-- 查询学校弹出层 -->
<%@include file="/user/set/layer/find-school-layer.jsp" %>

<script language="javascript">
$(document).ready(function(){
	initSchoolYear();
	findUserSchool();
	changeSchoolType();

	/* var china = allUnivList[0];
	var provs = china.provs;
	for(var i=0;i<provs.length;i++){
		var s = "";
		var prov = provs[i];
		var provName = prov.name;
		s+=provName+":";
		var univs = prov.univs;
		for(var j=0;j<univs.length;j++){
			var univ = univs[j];
			var univName = univ.name;
			//alert(provName+":"+univName);
			s+=univName+"-";
		}		
		s=s.substring(0,s.length-1)
		saveSchoolInfo(s,"SCHOOL_TYPE_UNIVERSITY");
	} */
});

function initSchoolYear(){
	try{
		var pleaseSelect="请选择";
		var toNowSelect = "请选择";
		var ys = document.getElementById('school_start_year');
		
		var minYear = 100;
		var now = new Date();
		var nowYsNum = now.getFullYear()+1; 
		
		var nowYdNum = now.getFullYear()+1; 
		
		ys.innerHTML = "";
		for(var yeNum=nowYsNum;yeNum>=nowYsNum-minYear;yeNum--){
			if(nowYsNum-yeNum == 0){
				ys.options[0] = new Option(pleaseSelect,"");  
			}else{
				ys.options[nowYsNum-yeNum] = new Option(yeNum+"",yeNum);  
			}
		}
		
	}catch(ex){}
	
	
}
</script>
</body>
</html>
