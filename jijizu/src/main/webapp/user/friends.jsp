<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="userInfo.name"/>的好友-第<s:property value="friendsPage.curPageNum"/>页-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
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
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
<script type="text/javascript">
$(document).ready(function(){
	prepareUserCardTip($(this).find(".USER_CARD"));
	findCommonFirends(<s:property value="userInfo.userId"/>);
	findFirends(<s:property value="userInfo.userId"/>);
	findUserGroup(<s:property value="userInfo.userId"/>);
});
</script>
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
            <div class="tab_friend">
	            <a href="/u/<s:property value="userId"/>/friends?type=his" <s:if test='type=="his"'>class="on"</s:if>>他的好友</a>
	            <s:if test="sessionUserInfo.userId != userId">
	           	 <a href="/u/<s:property value="userId"/>/friends?type=common" <s:if test='type=="common"'>class="on"</s:if>>共同好友</a>
	            </s:if>
            </div>
            <div class="conResult_sam">
            	<s:iterator value="friendsPage.result">
            		<dl>
	                    <dt><a target="_blank" href="/u/<s:property value="userId"/>"><img width="80" height="80" src="<s:property value="%{getHeadImgUrl(80)}"/>"/></a></dt>
	                    <dd class="name"><a target="_blank" title="<s:property value="name"/>" href="/u/<s:property value="userId"/>"><s:property value="%{getName(10,'...')}"/></a>
	                    	<a target="_blank" href="/auth/">
		                  		<s:if test="vFlag==1">
				              		<ins class="vip"></ins>
				              	</s:if>
				              	<s:if test="enterpriseFlag==1">
				              		<ins class="vip_agency"></ins>
				              	</s:if>
		                  	</a>
	                    </dd>
	                    <dd><s:property value="cityStr"/><s:property value="districtStr"/></dd>
	                    <dd class="btn">
	                    	<s:if test="sessionUserInfo != null && sessionUserInfo.userId != userId">
		                    	<s:if test="%{@com.jijizu.base.util.JijizuUtil@isFollowEachOther(sessionUserInfo.userId,userId)}">
		                    		<div class="focusLink"><b>互为好友</b></div>
		                    	</s:if>
		                    	<s:else>
		                    		<a href="javascript:followUser(<s:property value="userId"/>);" class="addFriend"></a>
		                    	</s:else>
	                    	</s:if>
	                    </dd>
	                </dl>
            	</s:iterator>
            </div>
            
             <s:if test="friendsPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="friendsPage.befPageNum>0 && friendsPage.curPageNum!=1">      	
						   <a href="/u/<s:property value="userId"/>/friends?type=<s:property value="type"/>&page=<s:property value="friendsPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="friendsPage.pageList" id="curPage">
							<s:if test="#curPage==friendsPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/u/<s:property value="userId"/>/friends?type=<s:property value="type"/>&page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="friendsPage.curPageNum!=friendsPage.pageCnt">
						   <a href="/u/<s:property value="userId"/>/friends?type=<s:property value="type"/>&page=<s:property value="friendsPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
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
<div id="cancel_friend_box" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hidecancel_friend_box()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确认要取消对"陈琳"的关注吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" class="ok" onclick="del_firendbox()">确认</a><a href="javascript:void(0)" class="cancel" onclick="hidecancel_friend_box()">取消</a></div>
        </div>
    </div>
</div>
<div id="box_sixin_new" style="display:none;">
	<div class="W_private_letter_box lay_box">
        <div class="bg">
        	<h1>发私信<a href="javascript:void(0);" class="span" onclick="hide_box_sixin_new()"></a></h1>
            <div class="W_private_letter">
                <dl>
                    <dt>发  给：</dt>
                    <dd><input type="text" class="text" value="请输入对方昵称" onfocus="input_focus()" onblur="input_blur()"></dd>
                </dl>
                <dl>
                    <dt>内  容：</dt>
                    <dd><textarea class="W_no_outline" onfocus="textarea_focus()" onblur="textarea_blur()">请输入分组描述，最多70个字</textarea></dd>
                </dl>
                <div class="btn"><a href="javascript:void(0)" class="send" onclick="show_send_success()">发送</a><a href="javascript:void(0)" class="cancel" onclick="hide_box_sixin_new()">取消</a></div>
            </div>
        </div>
    </div>
</div>
<div id="create_success" style="display:none;">
	<div class="layer_point2 ly4 lay_box">
        <div class="bg">
        	<h1>提示</h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnOK"></span></dt>
                <dd><p class="S_txt1">发布成功！</p></dd>
            </dl>
        </div>
    </div>
</div>
<div id="box_at" style="display:none;">
	<div class="layer_point ly6 lay_box">
        <div class="bg">
        	<h1>有什么话想对玛利亚 说<a href="javascript:void(0);" class="span" onclick="hide_at_box()"></a></h1>
            <div class="send_weibo clearfix">
                <div class="title_area clearfix"><div class="num">发言请遵守社区公约，还可以输入<span>131</span>字</div><div class="title"></div></div>
                <div class="input"><textarea name="" cols="" rows="" class="input_detail">对@玛利亚 说：</textarea></div>
                <div class="btnf clearfix"><a href="javascript:void(0);" class="btn_weibo" onclick="show_save_group()"></a></div>
            </div>
        </div>
    </div>
</div>

<script language="javascript">
function showcancel_friend_box(){
	box.Center="true";
	box.Fixed="true";	
	box.Show();
}
	
function hidecancel_friend_box(){
	box.Close();
}

function show_box_sixin_new(){
	box2.Center="true";
	box2.Fixed="true";	
	box2.Show();
}
	
function hide_box_sixin_new(){
	box2.Close();
}
function show_at_box(){
	box3.Center="true";
	box3.Fixed="true";	
	box3.Show();
}
	
function hide_at_box(){
	box3.Close();
}
function show_save_group(){
	box4.Center="true";
	box4.Fixed="true";	
	box4.Show();
	box3.Close();
	setTimeout('box4.Close();', 1000 );
}


var box=new LightBox("cancel_friend_box");
var box2=new LightBox("box_sixin_new");
var box3=new LightBox("box_at");
var box4=new LightBox("create_success");
</script>
</body>
</html>
