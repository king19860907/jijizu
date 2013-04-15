<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/littleBook.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/example.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/raphael-min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/prettify.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/example.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/diary.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.jSelectDate.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/highcharts.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/home.js${JS_EDITION}"></script>


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
	<input id="diary-type-hidden" type="hidden" name="diaryType" value="<s:property value="diary.diaryType"/>" />
	<input id="child-id-hidden" type="hidden" name="childId" value="<s:property value="diary.childId"/>"/>
	<div class="top"></div>
    <div class="center clearfix">
    	<div class="little_book_nav">
        	<h3>详情</h3>
            <dl class="date">
            	<dt>日期</dt>
            	<dd><s:date name="diary.diaryDate" format="yyyy年MM月dd日" /> &nbsp;<s:property value="diary.diaryDateOfWeek"/></dd>
            </dl>
            <dl class="lable">
            	<dt>选择</dt>
		    	<dd class="clearfix">
		    		<s:if test="diary.heightDiaryType">
	                	<li><span><a id="Height_tag" href="javascript:void(0)">身高</a></span></li>
		    		</s:if>
		    		<s:elseif test="diary.weightDiaryType">
	                	<li><span><a id="Height_tag" href="javascript:void(0)">体重</a></span></li>
		    		</s:elseif>
		    		<s:elseif test="diary.milkPowerDiaryType">
		    			<li><span><a id="Height_tag" href="javascript:void(0)">奶粉</a></span></li>
		    		</s:elseif>
		    		<s:elseif test="diary.fisrtTimeDiaryType">
		    			<li><span><a id="Height_tag" href="javascript:void(0)">第一次</a></span></li>
                	</s:elseif>
                	<s:elseif test="diary.otherDiaryType">
		    			<li><span><a id="Height_tag" href="javascript:void(0)">其他</a></span></li>
                	</s:elseif>
                	<s:elseif test="diary.sickDiaryType">
                		<li><span><a id="Height_tag" href="javascript:void(0)">生病</a></span></li>
                	</s:elseif>
              </dd>
            </dl>
            <dl class="tag">
            	<dt>详细资料</dt>
		    	<dd class="clearfix">
                	<li><span class="details_title">姓名：</span><span><s:property value="diary.child.name"/></span></li>
                	<li><span class="details_title">孩子出生：</span><span>第<s:property value="diary.childMonth"/>个月</span></li>
                	<s:if test="diary.heightDiaryType">
	                    <li><span class="details_title">身高：</span><span><s:property value="diary.height"/>公分</span></li>
                	</s:if>
                	<s:elseif test="diary.weightDiaryType">
	                    <li><span class="details_title">体重：</span><span><s:property value="diary.weight"/>公斤</span></li>
                	</s:elseif>
                	<s:elseif test="diary.milkPowderDiaryType">
	                    <li><span class="details_title">奶粉品牌：</span><span><s:property value="diary.milkName"/></span></li>
	                    <li><span class="details_title">今天共吃：</span><span><s:property value="diary.milkMl"/>ml</span></li>
                	</s:elseif>
                	<s:elseif test="diary.fisrtTimeDiaryType">
	                    <li><span class="details_title">出生后：</span><span>第<s:property value="diary.firstDay"/>天第一次<s:property value="diary.firstTimeTypeStr"/></span></li>
	                    <li><span class="details_title">平均值：</span><span>是<b><s:property value="firstDayAvg"/></b>天</span></li>
                	</s:elseif>
                	<s:elseif test="diary.sickDiaryType">
                		<li><span class="details_title">症状：</span><span><s:property value="diary.symptomStr"/></span></li>
	                    <s:if test="diary.sick != null">
		                    <li><span class="details_title">确诊：</span><span><s:property value="diary.sick.sickName"/></span></li>
	                    </s:if>
	                    <li><span class="details_title">是否痊愈：</span><span>
	                    	<s:if test="diary.recovery">是</s:if>
	                    	<s:else>否</s:else>
	                    </span></li>
                	</s:elseif>
              </dd>
            </dl>
        </div>
        <div class="little_book_right">
        	<div class="little_book_content">
            	<div class="little_book_title">
                	<h3><s:property value="diary.title"/></h3>
                    <div class="btns">
                    	<a id="del_btn" title="删除" href="javascript:show_delete_diary_layer(<s:property value="diary.diaryId"/>);"><img alt="删除" src="${IMG_PATH}/little_book_icon01.jpg" /></a>
                    	<a id="edit_btn" title="编辑" href="/diary/edit.jspa?diaryId=<s:property value="diaryId"/>"><img alt="编辑" src="${IMG_PATH}/little_book_icon04.jpg" /></a>
                    	<a id="cancel_btn" title="关闭" href="<s:property value="target"/>"><img alt="关闭" src="${IMG_PATH}/little_book_icon03.jpg" /></a>
                    </div>
                </div>
                <div class="textarea">
                	<p>
                		<s:property value="diary.status.content" escape="false"/>
                	</p>
                	 <s:if test="diary.status.mediaUrl != null">
	                    <p><img src="<s:property value="diary.status.mediaUrl"/>" /></p>
                	 </s:if>
                </div>
                <div id="graph"></div>
            </div>
        </div>
    </div>
    <div class="bottom"></div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 删除小本本弹出层 -->
<%@include file="/diary/layer/diary-delete-layer.jsp"%>

</body>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test="!diary.sickDiaryType&&!diary.fisrtTimeDiaryType">
		getDate();
	</s:if>
	<s:if test="diary.sickDiaryType">
		getSickData(<s:property value="diary.diaryId"/>);
	</s:if>
});
</script>
</html>
