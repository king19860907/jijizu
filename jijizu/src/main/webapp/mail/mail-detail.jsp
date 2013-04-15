<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看私信-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/userCard.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${CSS_PATH }/jquery.autocomplete.css"/>
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<%@ include file="/common/common-js-inc.jsp" %>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/at.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.autocomplete.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jijizu.jquery.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.poshytip.min.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main">
  <div class="W_main_bg clearfix"> 
    <!--菜单部分-->
    <%@include file="/status/inc/left-tab-inc.jsp" %>
    <!--内容部分-->
    <div class="plc_main">
      <div class="W_main_a">
        <div class="W_main_c">
        	<div class="group_read_sixin">我和 <s:property value="mailShow.mailUserInfo.name"/> 的对话</div>
          	<div class="message_wrap">
                <div class="search_title">
                	<div class="operation_btn" style="display:none"><a href="javascript:void(0);" class="W_btn_b" onclick="delMessageList()">批量删除</a></div>
                    <div class="bread_crumbs">
                    	<i><a href="/mail.jspa">返回所有私信</a></i>
                        <span class="CH">&gt;</span>
                        <em>共<s:property value="mailDetailPage.recordCnt"/>条私信</em>
                    </div>
                    <div class="del_operation">
                        <div class="operation_btn">
                        	<a class="W_btn_a" href="javascript:void(0);" onclick="delMessageList_confirm()">确定</a>
                            <a class="W_btn_b" href="javascript:void(0);" onclick="cancelMessageList()">取消</a>
                        </div>
                    	<h5 class="title">请选择需要删除的私信</h5>
                    </div>
                </div>
                <div class="send_private_msg">
                	<dl class="private_SRLr">
                    	<div class="title"><em class="icon_mes"></em>发私信给：<span class="name"><s:property value="mailShow.mailUserInfo.name"/></span></div>
                        <div class="num" id="spare_num">还可以输入300字</div>
                        <dd class="W_border_content">
                        	<div class="private_operate">
                                <div id="publisherTop_box2" class="relative sixin">
                                  <textarea  onkeydown="displaySpareNumber(this,300,'spare_num');" onkeyup="displaySpareNumber(this,300,'spare_num');" name="" id="mail_content" class="W_input"></textarea>
                                </div>
                                <input type="hidden" value="<s:property value="mailShow.mailUserInfo.name"/>(<s:property value="mailShow.mailUserInfo.nickName"/>)" id="nickName"/>
                                <div id="publisherTop_list2" class="publisherTop_list">
                      </div>
                                <div class="fbcg"></div>
                      		</div>
                            <div class="btnf"><a class="btn" href="javascript:void(0);" id="send-mail-btn" name='send-mail-btn'>发送</a>
                                <div class="icon_detail"> 
                                <a type="mail" name="face_mail" href="javascript:void(0);"><i class="icon_sw_face"></i>表情</a> 
                                <a class="icon_sw_img" style="display:none" href="javascript:void(0);">图片</a></div>
                            </div>
                        </dd>
                    </dl>
                </div>
                <div class="clear"></div>
                <div class="private_dialogue">
                	<s:iterator value="mailDetailPage.result">
                		<s:if test="direction==2">
                			<dl class="private_SRLr">
		                    	<dt class="face"><a href="/u/<s:property value="mailShow.userId"/>" class="USER_CARD" uid="<s:property value="mailShow.userId"/>" title=""><img width="50" height="50" src="<s:property value="mailShow.userInfo.headImgUrl"/>"></a></dt>
		                        <dd class="content">
		                        	<div class="close"><a href="javascript:void(0);" onclick="show_sixin_detail_del(<s:property value="mailDetailId"/>)"></a></div>
		                        	<div class="txts" maildetailid=<s:property value="mailDetailId"/>><s:property value="mailContent" escape="false"/></div>
		                            <div class="time">
		                            	<s:if test="overOneDay">	
							                    <s:date name="mailDate" format="yyyy-MM-dd HH:mm:ss" />
							            </s:if>
							            <s:else>
							                  	<s:date name="mailDate" nice="true" />
							            </s:else>
		                            </div>
		                            <div class="arrow"></div>
		                        </dd>
		                    </dl>
                		</s:if>
                		<s:else>
                			<dl class="private_SRLl">
		                    	<dt class="face"><a href="/u/<s:property value="mailShow.mailUserId"/>" class="USER_CARD" uid="<s:property value="mailShow.mailUserId"/>" title=""><img width="50" height="50" src="<s:property value="mailShow.mailUserInfo.headImgUrl"/>"></a></dt>
		                        <dd class="content">
		                        	<div class="close"><a href="javascript:void(0);" onclick="show_sixin_detail_del(<s:property value="mailDetailId"/>)"></a></div>
		                        	<div class="txts" maildetailid=<s:property value="mailDetailId"/>><s:property value="mailContent" escape="false"/></div>
		                            <div class="time">
		                            	<s:if test="overOneDay">	
							                    <s:date name="mailDate" format="yyyy-MM-dd HH:mm:ss" />
							            </s:if>
							            <s:else>
							                  	<s:date name="mailDate" nice="true" />
							            </s:else>
		                            </div>
		                            <div class="arrow"></div>
		                        </dd>
		                    </dl>
                		</s:else>
                	</s:iterator>
               <s:if test="mailDetailPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="mailDetailPage.befPageNum>0 && mailDetailPage.curPageNum!=1">      	
						   <a href="/mail/mailDetail.jspa<s:property value="%{getParamsUrl('page',mailDetailPage.befPageNum,true)}"/>" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="mailDetailPage.pageList" id="curPage">
							<s:if test="#curPage==mailDetailPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/mail/mailDetail.jspa<s:property value="%{getParamsUrl('page',#curPage,true)}"/>"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="mailDetailPage.curPageNum!=mailDetailPage.pageCnt">
						   <a href="/mail/mailDetail.jspa<s:property value="%{getParamsUrl('page',mailDetailPage.aftPageNum,true)}"/>" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
                </div>
          </div>
        </div>
        
        <!--右边-->
        <%@include file="/status/inc/status-right-inc.jsp" %>
        <!--右边END-->
      </div>
    </div>
  </div>
<!--尾部文件-->
<%@include file="/common/footer.jsp" %>
<!--弹出框-->
<!-- 表情弹层 -->
<%@include file="/status/layer/face-layer.jsp"%>
<!-- 删除私信弹层 -->
<%@include file="/mail/layer/del-mail-detail-layer.jsp" %>
<!-- 创建私信弹层 -->
<%@include file="/mail/layer/create-mail-layer.jsp" %>
<script language="javascript">
$(document).ready(function(){
	prepareMailAutoComplete($(this).find("[name='mail-nickName']"));
	prepareUserCardTip($(this).find(".USER_CARD[uid],.USER_CARD[nick-name]"));
	prepareMailFace($(this).find("[name='face_mail']"));
	$("#send-mail-btn").click(function(){
		var content = $("#mail_content").val();
		var nickName = $("#nickName").val();
		if(content == ""){
			alert('请输入内容');
			$('#content').focus();
			return;
		}
		if(content.length>300){
			alert('内容大于300字');
			$('#content').focus();
			return;
		}
		$("#send-mail-btn").unbind("click");
		sendMail(nickName,content);
	});
});
</script>
</body>
</html>