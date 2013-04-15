<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/jquery.autocomplete.css"/>
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.rotate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/wineFriends.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main_sub">
  <div class="W_main_sub_bg clearfix"> 
    <!--内容部分-->
    <div class="plc_main_sub">
     <!--左边-->
      <div class="W_main_sub_b">
      	<div class="jjz_left_box">
            <%@ include file="/status/inc/user-detail-inc.jsp" %> 
            <div class="pl_content_account">
            	<div class="infoblock">
                	<form class="info_title">
                        <fieldset>
                            <legend>基本信息</legend>
                            <s:if test="%{canEdit(sessionUserInfo,userInfo.userId)}">
                          	  <div class="btns"><a href="/user/base.jspa" class="W_btn_round"><span>编辑</span></a></div>
                            </s:if>
                        </fieldset>
                    </form>
                    <div class="base_view">
                    	<div class="pf_item clearfix">
                            <div class="con_name">姓名：</div>
                            <div class="con_txt"><s:property value="userInfo.name"/></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">性别：</div>
                            <div class="con_txt">
                            	<s:if test='userInfo.gender=="m"'>
                            		男	
                            	</s:if>
                            	<s:elseif test='userInfo.gender=="f"'>
                            		女
                            	</s:elseif>
                            </div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">生日：</div>
                            <div class="con_txt"><s:date name="userInfo.birthday" format="yyyy-MM-dd" /></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">血型：</div>
                            <div class="con_txt"><s:property value="userInfo.blood"/></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">现居地：</div>
                            <div class="con_txt"><s:property value="userInfo.cityStr"/> <s:property value="userInfo.districtStr"/></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">家乡：</div>
                            <div class="con_txt"><s:property value="userInfo.hometownCityStr"/> <s:property value="userInfo.hometownDistrictStr"/></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">简介：</div>
                            <div class="con_txt"><s:property value="userInfo.userDesc"/></div>
                        </div>
                        <s:if test="%{sessionUserInfo.userId == userInfo.userId || @com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,userInfo.userId)}">
                        <s:iterator value="children">
                        <div class="pf_item clearfix">
                            <div class="con_name">孩子姓名：</div>
                            <div class="con_txt"><s:property value="name"/></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">孩子昵称：</div>
                            <div class="con_txt"><s:property value="nickName"/></div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">孩子性别：</div>
                            <div class="con_txt">
                            	<s:if test='gender=="m"'>
                            		男
                            	</s:if>
                            	<s:elseif test='gender=="f"'>
	                            	女
                            	</s:elseif>
                            </div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">孩子生日：</div>
                            <div class="con_txt">
                            	<s:date name="birthday" format="yyyy-MM-dd" />
                            </div>
                        </div>
                        <s:if test="schoolInfo!=null">
                        	<div class="pf_item clearfix">
	                            <div class="con_name">孩子学校类型：</div>
	                            <div class="con_txt"><s:property value="schoolInfo.typeStr"/></div>
	                        </div>
	                        <div class="pf_item clearfix">
	                            <div class="con_name">孩子学校名称：</div>
	                            <div class="line_hidden"><a href="javascript:findUser('childSchoolName','<s:property value="schoolInfo.schoolName"/>');">找孩子同学 &gt;&gt;</a></div>
	                            <div class="con_txt cut"><a href="javascript:findUser('childSchoolName','<s:property value="schoolInfo.schoolName"/>');"><s:property value="schoolInfo.schoolName"/></a></div>
	                        </div>
	                        <div class="pf_item clearfix">
	                            <div class="con_name">班级：</div>
	                            <div class="con_txt"><s:property value="department"/></div>
	                        </div>
                        </s:if>
                        </s:iterator>
                       </s:if>
                    </div>
                    <div class="base">
                    	<div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>姓名：</div>
                            <div class="con_txt"><input value="玮玮-Enthen" class="W_input">&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>性别：</div>
                            <div class="con_txt"><input value="女" class="W_input">&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>生日：</div>
                            <div class="con_txt"><select><option value="2013" label="2013">2013</option></select><span class="input_sel_text">年</span><select><option value="01" label="01">01</option></select><span class="input_sel_text">月</span><select><option value="01" label="01">01</option></select><span class="input_sel_text">日</span>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">血型：</div>
                            <div class="con_txt"><select><option value="A型" label="A型">A型</option></select>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>现居地：</div>
                            <div class="con_txt"><select><option value="上海" label="上海">上海</option></select> <select><option value="浦东新区" label="浦东新区">浦东新区</option></select>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">家乡：</div>
                            <div class="con_txt"><select><option value="河北" label="河北">河北</option></select> <select><option value="石家庄" label="石家庄">石家庄</option></select>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">简介：</div>
                            <div class="con_txt"><input value="每朵花都有绽放的姿态，每个人都是唯一的精彩" class="W_input">&nbsp;</div>
                        </div>
                        
                        <!-- 
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>孩子昵称：</div>
                            <div class="con_txt"><input value="程瑶迦" class="W_input">&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>孩子性别：</div>
                            <div class="con_txt"><span class="rsp"><label for="man_radio"><input type="radio" checked="checked" class="W_radio" value="m" name="gender" id="man_radio">男</label></span><span class="rsp"><label for="woman_radio"><input type="radio" class="W_radio" value="f" name="gender" id="woman_radio">女</label></span>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">孩子生日：</div>
                            <div class="con_txt"><select><option value="2013" label="2013">2013</option></select><span class="input_sel_text">年</span><select><option value="01" label="01">01</option></select><span class="input_sel_text">月</span><select><option value="01" label="01">01</option></select><span class="input_sel_text">日</span>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>孩子学校类型：</div>
                            <div class="con_txt"><select><option value="小学" label="小学">小学</option></select>&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>孩子学校名称：</div>
                            <div class="con_txt"><input value="上海实验小学" class="W_input">&nbsp;</div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name"><span class="W_error">*</span>班级：</div>
                            <div class="con_txt"><input value="1年级2班" class="W_input">&nbsp;</div>
                        </div>
                         -->
                    </div>
                </div>
                <div class="infoblock">
                	<form class="info_title">
                        <fieldset>
                            <legend>教育信息</legend>
                            <s:if test="%{canEdit(sessionUserInfo,userInfo.userId)}">
	                            <div class="btns"><a href="/user/edu.jspa" class="W_btn_round"><span>编辑</span></a></div>
                            </s:if>
                        </fieldset>
                    </form>
                    <div class="base_view">
                    	<s:iterator value="userSchoolList">
                    		 <div class="pf_item clearfix">
	                            <div class="con_name"><s:property value="typeStr"/>：</div>
	                            <div class="line_hidden"><a href="javascript:findUser('schoolName','<s:property value="schoolName"/>');">找同学 &gt;&gt;</a></div>
	                            <div class="con_txt cut">
	                            	<div class="edulist_item">
	                                    <p><a href="javascript:findUser('schoolName','<s:property value="schoolName"/>');"><s:property value="schoolName"/></a>
	                                    	<s:if test="startYear != null">
	                                    		(<s:property value="startYear"/>年)
	                                    	</s:if>
	                                    	</p>
	                                    <p><s:property value="department"/></p>
	                                </div>
	                            </div>
	                        </div>
                    	</s:iterator>
                    </div>
                    <div class="base" style="display:none">
                    	<div class="editing">
                        	<a class="pf_add" href="javascript:void(0)"><em class="addicon">+</em>添加教育信息</a>
                            <div class="edit_area S_line2">
                            	<div class="pf_item clearfix">
                                	<div class="con_name">学校类型</div>
                                    <div class="con_txt">
                                    	<div class="input_sel">
                                            <span class="rsp">
                                                <select name="school_type" node-type="school_type">
                                                    <option value="1">大学</option>
                                                    <option value="2">高中</option>
                                                    <option value="3">中专技校</option>
                                                    <option value="4">初中</option>
                                                    <option value="5">小学</option>
                                                </select>
                                            </span>&nbsp;
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name"><span class="W_error">*</span>学校名称</div>
                                    <div class="con_txt"><input value="点击选择学校" name="name" class="W_input school" onclick="showschSearchLayer()">&nbsp;</div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name">入学时间</div>
                                    <div class="con_txt"><select><option value="请选择" label="请选择">请选择</option></select>&nbsp;</div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name">院系</div>
                                    <div class="con_txt"><select><option value="请选择院系" label="请选择院系">请选择院系</option></select>&nbsp;</div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name">&nbsp;</div>
                                    <div class="con_txt">
                                    	<div class="btn"><a onclick="show_send_success()" class="save" href="javascript:void(0)">确定</a><a onclick="hide_box_sixin_new()" class="cancel" href="javascript:void(0)">取消</a></div>
                                    </div>
                                </div>
                                
                                
                                
                                
                            </div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">大学：</div>
                            <div class="con_txt cut">
                            	<div class="edulist_item">
                                    <p><a href="搜索结果_找人.html" target="_blank">上海水产大学</a>(2004年)</p>
                                    <p>计算机</p>
                                    <div class="action">
                                        <a class="icon_edit" href="javascript:void(0)"></a>
                                        <a class="icon_close" href="javascript:void(0)" onclick="show_msg_del()"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">高中：</div>
                            <div class="con_txt cut">
                            	<div class="edulist_item">
                                    <p><a href="搜索结果_找人.html" target="_blank">上海师大附属中学</a>(2003年)</p>
                                    <p>高三5班</p>
                                    <div class="action">
                                        <a class="icon_edit" href="javascript:void(0)"></a>
                                        <a class="icon_close" href="javascript:void(0)" onclick="show_msg_del()"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="infoblock">
                	<form class="info_title">
                        <fieldset>
                            <legend>工作信息</legend>
                            <s:if test="%{canEdit(sessionUserInfo,userInfo.userId)}">
	                            <div class="btns"><a href="/user/job.jspa" class="W_btn_round"><span>编辑</span></a></div>
                            </s:if>
                        </fieldset>
                    </form>
                    <div class="base_view">
                        <s:iterator value="jobs">
                        	<div class="pf_item clearfix">
	                            <div class="con_name">工作单位：</div>
	                            <div class="line_hidden"><a href="javascript:findUser('companyName','<s:property value="companyName"/>');">找同事 &gt;&gt;</a></div>
	                            <div class="con_txt cut">
	                            	<div class="edulist_item">
	                                    <p><a href="javascript:findUser('companyName','<s:property value="companyName"/>');"><s:property value="companyName"/></a></p>
	                                    <s:if test="province != null">
		                                    <p>地点：<s:property value="provinceStr"/> <s:property value="cityStr"/></p>
	                                    </s:if>
	                                    <s:if test="department != null">
		                                    <p>职位：<s:property value="department"/></p>
	                                    </s:if>
	                                </div>
	                            </div>
	                        </div>
                        </s:iterator>
                    </div>
                    <div class="base" style="display:none"/>
                    	<div class="editing">
                        	<a class="pf_add" href="javascript:void(0)"><em class="addicon">+</em>添加职业信息</a>
                            <div class="edit_area S_line2">
                            	<div class="pf_item clearfix">
                                	<div class="con_name">公司所在地</div>
                                    <div class="con_txt">
                                    	<div class="input_sel">
                                            <span class="rsp">
                                                <select name="province" node-type="province">
                                                    <option value="1">上海</option>
                                                </select>
                                            </span>
                                            <span>
                                                <select node-type="city" name="city">
                                                    <option value="1">黄浦区</option>
                                                </select>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name"><span class="W_error">*</span>单位名称</div>
                                    <div class="con_txt"><input value="上海外国语学院" name="name" class="W_input">&nbsp;</div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name">工作时间</div>
                                    <div class="con_txt">
                                    	<span class="rsp">
                                        	<select><option value="请选择" label="请选择">请选择</option></select>至
                                        </span>
                                        <span class="rsp">
                                            <select><option value="请选择" label="请选择">请选择</option></select>&nbsp;
                                        </span>
                                    </div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name">院系</div>
                                    <div class="con_txt"><input value="语文老师" name="name" class="W_input">&nbsp;</div>
                                </div>
                                
                                <div class="pf_item clearfix">
                                	<div class="con_name">&nbsp;</div>
                                    <div class="con_txt">
                                    	<div class="btn"><a onclick="show_send_success()" class="save" href="javascript:void(0)">确定</a><a onclick="hide_box_sixin_new()" class="cancel" href="javascript:void(0)">取消</a></div>
                                    </div>
                                </div>
                                
                                
                                
                                
                            </div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">公司：</div>
                            <div class="con_txt cut">
                            	<div class="edulist_item">
                                    <p><a href="搜索结果_找人.html" target="_blank">上海外国语学院</a></p>
                                    <p>地点：上海 闸北区</p>
                                    <p>职位：语文老师</p>
                                    <div class="action">
                                        <a class="icon_edit" href="javascript:void(0)"></a>
                                        <a class="icon_close" href="javascript:void(0)" onclick="show_msg_del()"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="pf_item clearfix">
                            <div class="con_name">&nbsp;</div>
                            <div class="con_txt cut">
                            	<div class="edulist_item">
                                    <p><a href="搜索结果_找人.html" target="_blank">上海水产大学</a></p>
                                    <p>地点：上海 杨浦区</p>
                                    <p>职位：数学老师</p>
                                    <div class="action">
                                        <a class="icon_edit" href="javascript:void(0)"></a>
                                        <a class="icon_close" href="javascript:void(0)" onclick="show_msg_del()"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
      <!--右边-->
       <!-- 共同好友 -->        	
          <div class="WB_right_module" id="common_friends">
          </div>
          <!-- 他的好友 -->
          <div class="WB_right_module" id="friends">
          </div>
          <!-- 他的集集组 -->
          <div class="WB_right_module" id="user-group">
          </div>
        </div>
        <!--右边END-->
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 取消好友弹层 -->
<%@include file="/user/layer/cancel-follow-layer.jsp"%>
<!-- 发送私信弹层 -->
<%@include file="/mail/layer/create-mail-layer.jsp"%>
<script language="javascript">
$(document).ready(function(){
	prepareUserCardTip($(this).find(".USER_CARD"));
	findCommonFirends(<s:property value="userInfo.userId"/>);
	findFirends(<s:property value="userInfo.userId"/>);
	findUserGroup(<s:property value="userInfo.userId"/>);
});

function findUser(param,value){
	var url = "/search/user.jspa?"+param+"="+encodeURIComponent(value);
	window.open(url);
}

</script>
</body>
</html>
