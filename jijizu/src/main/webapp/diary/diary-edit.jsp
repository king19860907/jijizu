<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小本本-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/littleBook.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/example.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/validate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/diary.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.jSelectDate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/diary-symptom.js${JS_EDITION}"></script>

<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body class="little_book_body">
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<div class="little_book_topBar">
	<div class="wrap">
        <img src="${IMG_PATH}/little_book_titlePic.jpg" />
    </div>
</div>
<!--主体部分-->
<div class="little_book_main">
<form action="" id="update-diary-form">
	<input id="child-id-hidden" type="hidden" name="childId" value="<s:property value="diary.childId"/>"/>
	<input id="diary-type-hidden" type="hidden" name="diaryType" value="<s:property value="diary.diaryType"/>" />
	<input id="diary-id-hidden" type="hidden" name="diaryId" value="<s:property value="diary.diaryId"/>" />
	<input id="status-id-hidden" type="hidden" name="statusId" value="<s:property value="diary.statusId"/>" />
	<div class="top"></div>
    <div class="center clearfix">
    	<div class="little_book_nav">
        	<h3>详情</h3>
            <dl class="date">
            	<dt>日期</dt>
            	<dd>
                	<input class="date" type="text" id="diaryDate"
												name="diaryDate"
												value="<s:date name="diary.diaryDate" format="yyyy-MM-dd" />" />
              	</dd>
            </dl>
            <dl class="lable">
            	<dt>选择标签</dt>
		    	<dd class="clearfix">
                	<li><span><a id="Height_tag" value="DIARY_TYPE_HEIGHT" name="tag" href="javascript:void(0)">身高</a></span></li>
                    <li><span><a id="Weight_tag" value="DIARY_TYPE_WEIGHT" name="tag" href="javascript:void(0)">体重</a></span></li>
                    <li><span><a id="MilkPowder_tag" value="DIARY_TYPE_MILK_POWDER" name="tag" href="javascript:void(0)">奶粉</a></span></li>
                    <li><span><a id="Sick_tag" value="DIARY_TYPE_SICK" name="tag" href="javascript:void(0)">生病</a></span></li>
                    <li><span><a id="First_tag" value="DIARY_TYPE_FIRST_TIME" name="tag" href="javascript:void(0)">第一次</a></span></li>
              		<li><span><a id="Other_tag" value="DIARY_TYPE_OTHER" name="tag" href="javascript:void(0)">其他</a></span></li>
              </dd>
            </dl>
            <s:if test="childList!= null && childList.size > 0">
            	<div id="name_hidden" style="display:none">
	            	<span class="details_title">姓名：</span>
	                <span>
	                			<select id="child-select">
	                				<s:iterator value="childList">
		                				<option <s:if test="diary.childId==childId">selected="selected"</s:if> value="<s:property value="childId"/>"><s:property value="name"/></option>
	                				</s:iterator>
	                			</select>
	                </span>
	            </div>
	            <dl class="tag" id="Height_content" name="tag_content">
	            	<dt>详细资料</dt>
			    	<dd class="clearfix">
	                	<li id="Height_name">
	                	</li>
	                    <li><span class="details_title">身高：</span><span><input name="height" type="text" class="height_unit" value="<s:property value="diary.height"/>" />公分</span></li>
	              	</dd>
	            </dl>
	            <dl class="tag" id="Weight_content" name="tag_content">
	            	<dt>详细资料</dt>
			    	<dd class="clearfix">
	                	<li id="Weight_name">
	                	</li>
	                    <li><span class="details_title">体重：</span><span><input name="weight" type="text" class="height_unit" value="<s:property value="diary.weight"/>" />公斤</span></li>
	              </dd>
	            </dl>
	            <dl class="tag" id="MilkPowder_content" name="tag_content">
	            	<dt>详细资料</dt>
			    	<dd class="clearfix">
	                	<li id="MilkPowder_name">
	                	</li>
	                    <li><span class="details_title">奶粉品牌：</span><span><input type="text" class="txt" value="<s:property value="diary.milkName"/>" id="milkName" name="milkName" /></span></li>
	                    <li><span class="details_title">今天共吃：</span><span><input type="text" class="txt" value="<s:property value="diary.milkMl"/>" name="milkMl" />ml</span></li>
	              </dd>
	            </dl>
	           <dl class="tag" id="Sick_content" name="tag_content">
	            	<dt>详细资料</dt>
	              <dd class="clearfix">
	                	<li id="Sick_name">
	                	</li>
	                    <li class="symptom clearfix">
	                    	<span class="details_title">症状：</span>
	                    	<span style="line-height:22px;float:left;">限九个字，空格分开</span>
	                    </li>
	                    <li class="symptom clearfix">
	                   		 <span class="details_title">&nbsp</span>
	                    	<div class="symptom_input">
	                    		<s:iterator value="diary.symptomList">
	                    			<div class="tag"><span><s:property value="sickName"/><a class="icon_close" href="javascript:void(0);"></a><input type="hidden" value="<s:property value="sickName"/>" name="symptoms"/></span></div>
	                    		</s:iterator>
	                        	<span><input type="text" name="symptoms" id="symptom_input" class="txt" value="" /></span>
	                        </div>
	                    </li>
	                    <li>
	                        <span class="details_title">确诊：</span>
	                        <span class="prompt">
                            <input type="text" value="<s:property value="diary.sick.sickName"/>" id="sickName" name="sickName" class="txt">
                        </span>
	                    </li>
	                    <li><span class="details_title">是否痊愈：</span>
	                    	<span>
	                    		<input name="recoveryFlag" type="radio" <s:if test="diary.recovery">checked="checked"</s:if> class="radio" value="1" /><em>是</em>
	                    		<input name="recoveryFlag" type="radio" <s:if test="!diary.recovery">checked="checked"</s:if> class="radio" value="0" checked="checked" /><em>否</em>
	                    	</span>
	                    </li>
	              </dd>
	            </dl>
	            <dl class="tag" id="First_content" name="tag_content">
	            	<dt>详细资料</dt>
			    	<dd class="clearfix">
			    		<li id="First_name">
	                	</li>
	                	<li><span class="details_title">第一次：</span><span>
	                		<select name="firstTimeType" id="firstTimeType">
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_TRUN_OVER"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_TRUN_OVER">翻身</option>
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_SIT_UP"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_SIT_UP">坐起</option>
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_CRAWL"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_CRAWL">爬行</option>
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_CALL_MOM"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_CALL_MOM">叫妈妈</option>
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_CALL_DAD"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_CALL_DAD">叫爸爸</option>
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_STAND"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_STAND">站立</option>
	                			<option <s:if test='diary.firstTimeType=="FIRST_TIME_TYPE_WALK"'>selected="selected"</s:if> value="FIRST_TIME_TYPE_WALK">行走</option>
	                		</select></span></li>
	                    <li><span class="details_title">出生后：</span><span>第<input type="text" value="<s:property value="diary.firstDay"/>" name="firstDay" class="height_unit"/>天第一次<span id="first-time-str">翻身</span></span></li>
	              </dd>
	            </dl>
	             <dl class="tag" id="Other_content" name="tag_content">
	            	<dt>详细资料</dt>
			    	<dd class="clearfix">
	                	<li id="Other_name">
	                	</li>
	              </dd>
	            </dl>
            </s:if>
            <s:else>
            	<dl class="tag" id="Height_content" name="tag_content">
	            		<font color="#666666">请</font><a href="/user/base.jspa" style="color:#0179B5;">点击这里</a><font color="#666666">完善孩子信息后方能进行操作</font>
	            </dl>
	            <dl class="tag" id="Weight_content" name="tag_content">
	            		<font color="#666666">请</font><a href="/user/base.jspa" style="color:#0179B5;">点击这里</a><font color="#666666">完善孩子信息后方能进行操作</font>
	            </dl>
	            <dl class="tag" id="MilkPowder_content" name="tag_content">
	            		<font color="#666666">请</font><a href="/user/base.jspa" style="color:#0179B5;">点击这里</a><font color="#666666">完善孩子信息后方能进行操作</font>
	            </dl>
	            <dl class="tag" id="Sick_content" name="tag_content">
	            		<font color="#666666">请</font><a href="/user/base.jspa" style="color:#0179B5;">点击这里</a><font color="#666666">完善孩子信息后方能进行操作</font>
	            </dl>
	            <dl class="tag" id="First_content" name="tag_content">
	            		<font color="#666666">请</font><a href="/user/base.jspa" style="color:#0179B5;">点击这里</a><font color="#666666">完善孩子信息后方能进行操作</font>
	            </dl>
	            <dl class="tag" id="Other_content" name="tag_content">
	            	<font color="#666666">请</font><a href="/user/base.jspa" style="color:#0179B5;">点击这里</a><font color="#666666">完善孩子信息后方能进行操作</font>
	            </dl>
            </s:else>
            
            <dl class="congratulation" id="congratulation">
       	    <dt><img src="${IMG_PATH}/little_book_Congratulation.jpg" /></dt>
                <dd>恭喜<span>胖胖</span>康复！</dd>
            </dl>
        </div>
        <div class="little_book_right">
        	<div class="little_book_content">
            	<div class="little_book_title">
                	<h3><input type="text" name="title" value="<s:property value="diary.title"/>" id="title" onfocus="if(value=='添加标题') {value=''}" onblur="if (value=='') {value='添加标题'}" /></h3>
                    <div class="btns">
                        <a id="update-diary-btn" title="保存" href="javascript:void(0)"><img alt="保存" src="${IMG_PATH}/little_book_icon02.jpg" /></a>
                        <a id="cancel_btn" title="关闭" href="<s:property value="target"/>"><img alt="关闭" src="${IMG_PATH}/little_book_icon03.jpg" /></a>
                    </div>
                </div>
                <div class="textarea">
			        <input type="hidden" id="post_status_media_type" name="mediaType" value="<s:property value="diary.status.mediaType"/>" />
			        <input type="hidden" id="post_status_media_url" name="mediaUrl" value="<s:property value="diary.status.mediaUrl"/>" />
			        <input type="hidden" id="post_status_is_sync" name="isSync" />
			        <input type="hidden" id="post_status_source_type" name="sourceType" value="SOURCE_TYPE_WEB" />
			        <input type="hidden" id="post_status_source_type_detail" name="sourceTypeDetail" value="SOURCE_TYPE_DETAIL_DIARY" />
                	<textarea id="text" cols="" rows="5" name="content"><s:property value="%{diary.status.getContentWithOutHtmlTag(true)}" escape="false"/></textarea>
                </div>
                <div class="btnf clearfix">
                    <div class="icon_detail">
                    	 <a href="javascript:void(0);" type="status" name="face_status"><i class="icon_sw_face"></i>表情</a>
                    	 <a name="icon_sw_img" href="javascript:void(0);"><i class="icon_sw_img"></i>图片</a>
                    	 <a href="javascript:void(0);" name="icon_sw_movie" style="display:none"><i class="icon_sw_movie"></i>视频</a>
                    </div>
                </div>
                <div id="graph"></div>
            </div>
        </div>
    </div>
    <div class="bottom"></div>
   </form>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
<!--上传图片-->
<%@include file="/layer/img-upload-layer.jsp"%>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("input.date").jSelectDate({
		css:"date",
		yearBeign: 1900,
		disabled : false,
		showLabel : false
	}); 
	
	$("#milkName").autocomplete("/diary/findMilkNames.jspa");

	var diaryType = $("#diary-type-hidden").val();
	$("[name='tag']").each(function(){
		if(diaryType == $(this).attr("value")){
			$(this).click();
		}
	});

	<s:if test="diary.status.haveImage">
		$("#laPic").show();
		$("#imgUrl").attr("src",'<s:property value="diary.status.mediaUrl"/>');
		$("[name='icon_sw_img']").click();
	</s:if>
});
</script>
</html>
