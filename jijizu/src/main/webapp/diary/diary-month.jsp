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
<script type="text/javascript" src="${JS_PATH}/diary.js${JS_EDITION}"></script>
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
<div class="littlebook_topnav">
	<div class="left"></div>
	<ul>
    	<li><a href="/diary/create.jspa?diaryType=DIARY_TYPE_HEIGHT">身高</a></li>
        <li><a href="/diary/create.jspa?diaryType=DIARY_TYPE_WEIGHT">体重</a></li>
        <li><a href="/diary/create.jspa?diaryType=DIARY_TYPE_MILK_POWDER">奶粉</a></li>
        <li><a href="/diary/create.jspa?diaryType=DIARY_TYPE_FIRST_TIME">第一次</a></li>
    </ul>
    <div class="right"></div>
</div>
<!--主体部分-->
<div class="little_book_main">
	<div class="top"></div>
    <div class="center clearfix">
    	<div class="calendar_box">
        	<div class="calendar_title clearfix">
                <div class="previous"><a href="/diary/month/<s:date name="monthCalendar.preMonthDate" format="yyyyMM" />"></a></div>
                <div class="calendar"><s:property value="monthCalendar.year"/>年<s:property value="monthCalendar.monthOfYear"/>月</div>
                <div class="next"><a href="/diary/month/<s:date name="monthCalendar.nextMonthDate" format="yyyyMM" />"></a></div>
            </div>
            <div class="week_content clearfix">
            	<table width="900" class="month_table" id="month_table">
                    <colgroup>
                        <col class="col1">
                        <col class="col1">
                        <col class="col1">
                        <col class="col1">
                        <col class="col1">
                        <col class="col1">
                        <col class="col2">
                    </colgroup>
                  <tr>
                    <th>星期一</th>
                    <th>星期二</th>
                    <th>星期三</th>
                    <th>星期四</th>
                    <th>星期五</th>
                    <th>星期六</th>
                    <th>星期日</th>
                  </tr>
                  
                  <!-- 上月 -->
                  <s:iterator value="monthCalendar.preMonthDay" id="diaryDate">
                  	<s:if test="firstDayOfWeek">
                  		<tr class="last_month">
                  	</s:if>
                  			<td class="last_month">
		                    	<div class="month_box" day="<s:date name="diaryDate" format="yyyy-MM-dd" />">
		                    		<s:iterator value="%{diaryDataMap.get(dayStr)}">
		                    			<a href="/diary/<s:property value="diaryId"/>"><p><s:property value="title"/></p></a>
		                    		</s:iterator>
		                            <div class="day"><s:property value="dayOfMonth"/></div>
		                        </div>
		                    </td>
		            <s:if test="lastDayOfWeek">
                  		<tr>
                  	</s:if>
                  </s:iterator>
                  
                  <!-- 当前月份 -->
                  <s:iterator value="monthCalendar.currentMonthDay" id="diaryDate">
                  	<s:if test="firstDayOfWeek">
                  		<tr>
                  	</s:if>
                  			<td name="create_td">
		                    	<div class="month_box" day="<s:date name="diaryDate" format="yyyy-MM-dd" />">
		                    		<s:iterator value="%{diaryDataMap.get(dayStr)}">
		                    			<a href="/diary/<s:property value="diaryId"/>"><p><s:property value="title"/></p></a>
		                    		</s:iterator>
		                            <div class="day"><s:property value="dayOfMonth"/></div>
		                        </div>
		                    </td>
		            <s:if test="lastDayOfWeek">
                  		<tr>
                  	</s:if>
                  </s:iterator>
                  
                  <!-- 下月 -->
                  <s:iterator value="monthCalendar.nextMonthDay" id="diaryDate">
                  	<s:if test="firstDayOfWeek">
                  		<tr class="last_month">
                  	</s:if>
                  			<td class="last_month">
		                    	<div class="month_box" day="<s:date name="diaryDate" format="yyyy-MM-dd" />">
		                    		<s:iterator value="%{diaryDataMap.get(dayStr)}">
		                    			<a href="/diary/<s:property value="diaryId"/>"><p><s:property value="title"/></p></a>
		                    		</s:iterator>
		                            <div class="day"><s:property value="dayOfMonth"/></div>
		                        </div>
		                    </td>
		            <s:if test="lastDayOfWeek">
                  		<tr>
                  	</s:if>
                  </s:iterator>
                </table>
            </div>
        </div>
        <div class="footer_control">
        	<div class="view_way clearfix">
            	<dl class="view_day">
                	<a href="/diary/day/"><dt></dt><dd>日</dd></a>
                </dl>
                <dl class="view_week">
                	<a href="/diary/week/"><dt></dt><dd>周</dd></a>
                </dl>
                <dl class="view_month on">
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
var tb=document.getElementById("month_table");    //获取table对像
var rows=tb.rows;
for(var i=1;i<rows.length;i++){    //--循环所有的行
var cells=rows[i].cells;
var cnt=cells[0].firstElementChild.children.length;
	for(var j=1;j<cells.length;j++){   //--循环所有的列
		var curCnt=cells[j].firstElementChild.children.length;
		if(curCnt>cnt){
			cnt = curCnt;
		}
	}	
	if(cnt!=0){
		cnt=cnt-1;
		for(var j=0;j<cells.length;j++){   //--循环所有的列
				  
			var d=cells[j].firstElementChild;
			var h=d.style.height=cnt*23 + 30 + "px"
		}
	}
 }
</script>
</body>
</html>
