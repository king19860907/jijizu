<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理成员-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${JS_PATH}/global.js"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js"></script>
<script type="text/javascript" src="${JS_PATH}/group.js"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
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
          <div class="title">成员管理</div>
          <div class="ev_notice_area">
            <table class="ev_table_materia">
              <colgroup>
              <col width="170">
              <col width="140">
              <col width="">
              <col width="75">
              <col width="75">
              </colgroup>
              <tbody>
                <tr>
                  <th>姓名</th>
                  <th>参与孩子信息</th>
                  <th>参与大人人数</th>
                  <th>报名时间</th>
                  <th>状态</th>
                </tr>
                <s:iterator value="userPage.result">
	                <tr>
	                  <td class="ev_menu_list1"><a href="/u/<s:property value="userId"/>"><img width="30" height="30" title="alicerg" alt="<s:property value="name"/>" src="<s:property value="headImgUrl"/>"/></a> <a class="ev_table_name" title="<s:property value="name"/>" href="/u/<s:property value="userId"/>"><s:property value="name"/></a></td>
	                  <td><s:property value="childName"/></td>
	                  <td><s:property value="adultNum"/></td>
	                  <td><s:date name="createDate" format="yyyy年MM月dd日 HH:mm" /></td>
	                  <td>
	                  	<s:if test='joinStatus=="GROUP_JOIN_STATUS_AUDIT"'>
		                  	<a href="javascript:passGroupUser(<s:property value="groupId"/>,<s:property value="userId"/>);">批准</a><br />
		                  	<a href="javascript:show_refuse_layer(<s:property value="groupId"/>,<s:property value="userId"/>);">不批准</a>
	                  	</s:if>
	                  	<s:elseif test='joinStatus=="GROUP_JOIN_STATUS_PASS"'>
	                  		已通过
	                  	</s:elseif>
	                  	<s:elseif test='joinStatus=="GROUP_JOIN_STATUS_WAIT"'>
	                  		处于等待区域
	                  	</s:elseif>
	                  </td>
	                </tr>
                </s:iterator>
              </tbody>
            </table>
             <s:if test="userPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="userPage.befPageNum>0 && userPage.curPageNum!=1">      	
						   <a href="/group/manageUser.jspa?groupId=<s:property value="groupId"/>&page=<s:property value="userPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="userPage.pageList" id="curPage">
							<s:if test="#curPage==userPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/group/manageUser.jspa?groupId=<s:property value="groupId"/>&page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="userPage.curPageNum!=userPage.pageCnt">
						   <a href="/group/manageUser.jspa?groupId=<s:property value="groupId"/>&page=<s:property value="userPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
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
<!--弹出框-->
<!-- 拒绝用户参加集集组弹层 -->
<%@include file="/group/layer/refuse-group-user-layer.jsp" %>
</body>
</html>
