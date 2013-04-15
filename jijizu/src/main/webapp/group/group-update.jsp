<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改集集组-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/group.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/date/WdatePicker.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/area.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/select_util.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/validate.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
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
        	<div class="title">发起集集组</div>
            <div class="ev_notice_area">
            	<input type="hidden" id="groupId" name="groupId" value="<s:property value="groupInfo.groupId"/>" />
            	<dl>
                	<dt>标题<font color="red">*</font></dt>
                    <dd>
                    	<input name="title" type="text" class="W_input" id="title" value="<s:property value="groupInfo.title"/>" />
                    	<div class="tipbox" id="tip1" style="display:none">
                            <div class="M_notice_del"><span class="icon_del"></span><span class="txt">请输入标题</span></div>
                            <div class="M_notice_succ"><span class="icon_succ"></span></div>
                            <div class="M_notice_warn"><span class="icon_warn"></span><span class="txt">给你的集集组起个好名字</span></div>
                        </div>
                    </dd>
            	</dl>
                <dl>
                	<dt>报名时间<font color="red">*</font></dt>
                    <dd>
                    	<div class="time_box">
                        	<label class="W_textb">截止</label>
                             <input id="applyEndDay" class="Wdate" type="text"
                                            name="applyEndDay" style="width:120px"
                                            onFocus="WdatePicker({maxDate:'#F{\'2020-10-01\'}'})" 
                                            value="<s:date name="groupInfo.applyEndDate" format="yyyy-MM-dd" />"/>
				                <select name="applyEndHour" id="applyEndHour">　
				                  <option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23" selected="true">23</option>
				                </select>
				                <select name="applyEndMinute" id="applyEndMinute">
				                  <option value="0" selected="true">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option>
				                </select>
                            <div id="timetip1" class="tipbox2">
                            	<div class="M_notice_del"><span class="icon_del"></span><span class="txt">请选择开始时间</span></div>
                                <div class="M_notice_succ"><span class="icon_succ"></span></div>
                            </div>
                        </div>
                    </dd>
            	</dl>
                <dl>
                	<dt>时间<font color="red">*</font></dt>
                    <dd class="select_area">
                    	<div class="time_box">
                        	<label class="W_textb">开始</label>
                             <input id="startDay" class="Wdate" type="text"
                                            name="startDay" style="width:120px"
                                            onFocus="WdatePicker({maxDate:'#F{\'2020-10-01\'}'})" 
                                            value="<s:date name="groupInfo.startDate" format="yyyy-MM-dd" />"/>
				                <select name="startHour" id="startHour">　
				                  <option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23" selected="true">23</option>
				                </select>
				                <select name="startMinute" id="startMinute">
				                  <option value="0" selected="true">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option>
				                </select>
                            <div id="timetip1" class="tipbox2">
                            	<div class="M_notice_del"><span class="icon_del"></span><span class="txt">请选择开始时间</span></div>
                                <div class="M_notice_succ"><span class="icon_succ"></span></div>
                            </div>
                        </div>
                        <div class="time_box">
                        	<label class="W_textb">结束</label>
                             <input id="endDay" class="Wdate" type="text"
                                            name="endDay" style="width:120px"
                                            onFocus="WdatePicker({maxDate:'#F{\'2020-10-01\'}'})" 
                                            value="<s:date name="groupInfo.endDate" format="yyyy-MM-dd" />"/>
				                <select name="endHour" id="endHour">　
				                  <option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23" selected="true">23</option>
				                </select>
				                <select name="endMinute" id="endMinute">
				                  <option value="0" selected="true">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option>
				                </select>
                            <div id="timetip1" class="tipbox2">
                            	<div class="M_notice_del"><span class="icon_del"></span><span class="txt">请选择开始时间</span></div>
                                <div class="M_notice_succ"><span class="icon_succ"></span></div>
                            </div>
                        </div>
                    </dd>
                </dl>
                <dl>
                	<dt>集集组描述<font color="red">*</font></dt>
                    <dd>
                    	<textarea name="groupDesc" id="groupDesc" cols="" rows="" class="editor_Cont"><s:property value="groupInfo.groupDesc"/></textarea>
                        <div class="notice_area">最多输入2000个字</div>
                        <div class="M_notice_del"><span class="icon_del"></span><span class="txt">已超过最大字数</span></div>
                    </dd>
                </dl>
                <dl>
                	<dt>上传海报<font color="red">*</font></dt>
                    <dd>
                    		<form id="status_upload_pic_form" method="post" enctype="multipart/form-data">
                    		<p id="poster_img_p" class="input_file">
                    			
	                    			<span class="file_span">
	                    			<input type="file" id="upload_img" onchange="uploadPosterImg();" name="myFile" /></span>
	                    			<span class="input_file_notice">请上传小于2MB的JPG、PNG和GIF格式图片</span>
                    		
                    		</p>
                    		</form>
                    		<input type="hidden" id="post_status_media_url" value="<s:property value="groupInfo.posterImgUrl"/>" name="posterImgUrl"/>
                    		<img width="60" height="80" id="post_status_media_img" src="<s:property value="groupInfo.posterImgUrl"/>"/>
                    </dd>
                    <dt></dt>
                    <dd></dd>
                </dl>
                <dl>
                	<dt>参与地址<font color="red">*</font></dt>
                    <dd class="select_area">
                    	<select name="province" 
									id="first" style="width:90px">
									<option value="">
										请选择
									</option>
								</select>
								  省
								<select name="city"
									 id="second" style="width:90px">
									<option value="">
										请选择
									</option>
								</select>
								  市　
								<select name="district" id="third" style="width:90px">
									<option value="">
										请选择
									</option>
								</select>
                        <input id="address" name="address" value="<s:property value="groupInfo.address"/>" type="text" class="editor_add" />
                    </dd>
                </dl>
                <dl>
                	<dt>参加人数</dt>
                    <dd><label><input type="radio" name="people" <s:if test="groupInfo.applyNum==-1">checked="checked"</s:if> value="0" class="W_radio"/>不限</label>
                    	<input id="ev_radio14" type="radio" <s:if test="groupInfo.applyNum>0">checked="checked"</s:if> name="people" value="1" class="W_radio"/>
                    	<s:if test="groupInfo.applyNum==-1">
                    		<s:set name="applyNum" value=""/>
                    	</s:if>
                    	<s:else>
                    		<s:set name="applyNum" value="groupInfo.applyNum"/>
                    	</s:else>
                    	<label for="ev_radio14">最多</label><input id="applyNum" value="<s:property value="#applyNum"/>" name="applyNum" class="input41" maxlength="9" /><label>人报名</label>
                        <label><input type="checkbox" <s:if test="groupInfo.parentsLimit!=null">checked="checked"</s:if> name="onlyOne" class="checkbox" value="1" id="parent_onlyone"/>仅限一位大人</label>
                        <select class="Parents" name="parentsLimit">
                          <option <s:if test='groupInfo.parentsLimit=="GROUP_PARENTS_LIMIT_MOTHER"'>selected="selected"</s:if> value="GROUP_PARENTS_LIMIT_MOTHER">妈妈</option>
                          <option <s:if test='groupInfo.parentsLimit=="GROUP_PARENTS_LIMIT_FATHER"'>selected="selected"</s:if> value="GROUP_PARENTS_LIMIT_FATHER">爸爸</option>
                        </select>
                    </dd>
                </dl>
                <dl>
                	<dt>年龄范围<font color="red">*</font></dt>
                    <dd>
                    	<div class="ctrl"><input type="text" id="startAge" name="startAge" value="<s:property value="groupInfo.startAge"/>" class="age one"/>-<input type="text" id="endAge" name="endAge" value="<s:property value="groupInfo.endAge"/>" class="age two"/>岁</div>
                    </dd>
                </dl>
                <dl>
                	<dt>人均费用<font color="red">*</font></dt>
                    <dd>
                    	<input type="radio" <s:if test="groupInfo.cost!=0">checked="checked"</s:if> id="ev_radio1" class="W_radio" value="1" name="fee"/>
                    	<s:if test="groupInfo.cost==0">
                    		<s:set name="cost" value=""/>
                    	</s:if>
                    	<s:else>
                    		<s:set name="cost" value="groupInfo.cost"/>
                    	</s:else>
                    	<label for="ev_radio1">费用为</label><input id="cost" name="cost" maxlength="9" class="input41" value="<s:property value="#cost"/>" id="feenum"/><label>元</label> 
                    	<input type="radio" <s:if test="groupInfo.cost==0">checked="checked"</s:if>id="ev_radio0" class="W_radio" value="0" name="fee"/><label for="ev_radio0">免费</label>
                    </dd>
                </dl>
                <dl>
                	<dt>参加权限<font color="red">*</font></dt>
                    <dd>
                    	<p><input type="radio" name="enterType" value="GROUP_ENTER_TYPE_PUBIC" <s:if test='groupInfo.enterType=="GROUP_ENTER_TYPE_PUBIC"'>checked="checked"</s:if>  id="ev_radio3" class="W_radio"/><label for="ev_radio3">公开招募</label></p>
                        <p><input type="radio" name="enterType" value="GROUP_ENTER_TYPE_FRIENDS" <s:if test='groupInfo.enterType=="GROUP_ENTER_TYPE_FRIENDS"'>checked="checked"</s:if> id="ev_radio5" class="W_radio"/><label for="ev_radio5">仅限于好友</label>
                    		<select name="enterTypeDetail">
                              <option <s:if test='groupInfo.enterTypeDetail=="GROUP_ENTER_TYPE_DETAIL_NOT_NEED_AUDIT"'>selected="selected"</s:if> value="GROUP_ENTER_TYPE_DETAIL_NOT_NEED_AUDIT">不需要审核</option>
                              <option <s:if test='groupInfo.enterTypeDetail=="GROUP_ENTER_TYPE_DETAIL_NEED_AUDIT"'>selected="selected"</s:if> value="GROUP_ENTER_TYPE_DETAIL_NEED_AUDIT">需要审核</option>
                            </select>
                    	</p>
                        <p><input type="radio" name="enterType" value="GROUP_ENTER_TYPE_AUDIT" <s:if test='groupInfo.enterType=="GROUP_ENTER_TYPE_AUDIT"'>checked="checked"</s:if> id="ev_radio2" class="W_radio"/><label for="ev_radio2">发起人审核</label></p>
                        <p><input type="radio" name="enterType" value="GROUP_ENTER_TYPE_INVITE" <s:if test='groupInfo.enterType=="GROUP_ENTER_TYPE_INVITE"'>checked="checked"</s:if> id="ev_radio4" class="W_radio"/><label for="ev_radio4">发起人秘密邀约</label></p>
                    </dd>
                </dl>
            </div>
            <div id="pl_ev_next" class="ev_next">
            	<a class="W_btn_d" id="update-group-btn" href="javascript:void(0);">修改集集组</a>
            </div>
        </div>
      </div>
      <!--右边-->
        <div class="W_main_sub_r">
        	<div class="jjz_right_box">
            	<dl>
                	<dt>什么是有效的集集组？</dt>
                    <dd>有能最终确定的活动起止日期</dd>
                    <dd>具备现实中能集体参与的活动地点</dd>
                    <dd>是多人在现实中能碰面的活动</dd>
                </dl>
                <dl>
                	<dt>如何才能让你的集集组更吸引人？</dt>
                    <dd>标题简单明了</dd>
                    <dd>活动内容和特点介绍详细</dd>
                    <dd>活动海报吸引人眼球</dd>
                    <dt>更重要的是，邀请好友们都来参与！</dt>
                </dl>
            </div>
        </div>
        <!--右边END-->
    </div>
  </div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<script language="javascript">
$(document).ready(function() {
	area.curFirst = <s:property value="groupInfo.province"/>;
	area.curSecond = <s:property value="groupInfo.city"/>;
	area.curThird = <s:property value="groupInfo.district"/>;
	try{
		area.init();
		area.firstChange();
	}catch(ex){}
	
	 $("#startHour").get(0).options[<s:property value="groupInfo.startDateHour"/>].selected = true;   
	 $("#startMinute").get(0).options[<s:property value="groupInfo.startDateMinute"/>].selected = true;   
	 $("#endHour").get(0).options[<s:property value="groupInfo.endDateHour"/>].selected = true;   
	 $("#endMinute").get(0).options[<s:property value="groupInfo.endDateMinute"/>].selected = true;  
	 $("#applyEndHour").get(0).options[<s:property value="groupInfo.applyEndDateHour"/>].selected = true;   
	 $("#applyEndMinute").get(0).options[<s:property value="groupInfo.applyEndDateMinute"/>].selected = true;   
});
</script>
<!--弹出框-->
</body>
</html>
