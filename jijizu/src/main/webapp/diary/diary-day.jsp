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
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
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
	<div class="top"></div>
    <div class="center clearfix">
    	<div class="calendar_box">
            <div class="day_content clearfix">
            	<div class="leftBar">
                	<div class="calendar_title clearfix">
                        <div class="previous"><a href="/diary/day/<s:date name="dayCalendar.preTwoDay" format="yyyyMMdd" />"></a></div>
                        <s:if test="dayCalendar.currentDay.today">
	                        <div class="calendar">今天</div>
                        </s:if>
                        <s:else>
                        	<div class="calendar"><s:date name="dayCalendar.currentDay" format="yyyy年MM月dd日" /></div>
                        </s:else>
                    </div>
                    <s:if test="dayCalendar.currentDay.today">
	                    <div class="Calendar_view today">
	                    		<s:date name="dayCalendar.currentDay" format="yyyy年MM月dd日" />
	                    </div>
                    </s:if>
                    <div class="day_txt">
                    	<s:iterator value="%{diaryDataMap.get(dayCalendar.currentDay.dayStr)}">
		                    	<a href="/diary/<s:property value="diaryId"/>"><p><s:property value="title"/></p></a>
		                </s:iterator>
                    </div>
                </div>
                <div class="rightBar">
                	<div class="calendar_title clearfix">
                		<s:if test="dayCalendar.currentDay.today">
	                        <div class="calendar">明天</div>
                        </s:if>
                        <s:else>
                        	<div class="calendar"><s:date name="dayCalendar.nextDay" format="yyyy年MM月dd日" /></div>
                        </s:else>
                        <div class="next"><a href="/diary/day/<s:date name="dayCalendar.nextTwoDay" format="yyyyMMdd" />"></a></div>
                    </div>
                    	<s:if test="dayCalendar.currentDay.today">
		                    <div class="Calendar_view">
		                    		<s:date name="dayCalendar.nextDay" format="yyyy年MM月dd日" />
		                    </div>
                    	</s:if>
                     <div class="day_txt">
                    	<s:iterator value="%{diaryDataMap.get(dayCalendar.nextDay.dayStr)}">
		                    	<a href="/diary/<s:property value="diaryId"/>"><p><s:property value="title"/></p></a>
		                </s:iterator>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer_control">
        	<div class="view_way clearfix">
            	<dl class="view_day on">
                	<a href="/diary/day/"><dt></dt><dd>日</dd></a>
                </dl>
                <dl class="view_week">
                	<a href="/diary/week/"><dt></dt><dd>周</dd></a>
                </dl>
                <dl class="view_month">
                	<a href="/diary/"><dt></dt><dd>月</dd></a>
                </dl>
                <div class="new_btn"><a href="/diary/create.jspa">新建</a></div>
            </div>
            
        </div>
    </div>
    <div class="bottom"></div>
</div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>
