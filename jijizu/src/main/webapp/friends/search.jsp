<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link href="${CSS_PATH}/local.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="../favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/friends.js${JS_EDITION}"></script>
<!--[if IE 6]>
<script type="text/javascript" src="../js/DD_belatedPNG_0.0.8a.js"></script>
<![endif]-->
</head>

<body>
<!--头部文件-->
<%@include file="/common/header.jsp" %>
<!--主体部分-->
<div class="W_main">
  <div class="W_main_bg clearfix"> 
    <!--内容部分-->
    <div class="plc_main">
      <div class="W_main_b">
        <%@include file="/friends/inc/friends-tap-inc.jsp" %>
        <div class="find_big_tab" id="company-user-div">
        	<%@include file="/friends/inc/company-user-inc.jsp" %>
        </div>
        <div class="find_big_tab" id="living-user-div">
        	<%@include file="/friends/inc/living-user-inc.jsp" %>
        </div>
        <div class="find_big_tab" id="school-user-div">
        	<%@include file="/friends/inc/school-user-inc.jsp" %>
        </div>
        <div class="find_big_tab" id="child-user-div">
        	<%@include file="/friends/inc/child-user-inc.jsp" %>
        </div>
        <div class="find_big_tab" style="display:none">
        	<form>
              <fieldset>
                <legend class="tit S_txt1">通过合作平台寻找好友</legend>
              </fieldset>
            </form>
      		<div class="tab_layer">
            	<ul>
                	<li id="msn_box" onclick="msn()"><a href="javascript:void(0);"><i class="msn"></i>MSN好友</a></li>
                    <li id="qq_box" onclick="qq()"><a href="javascript:void(0);"><i class="qq"></i>QQ好友</a></li>
                    <li id="email_box" onclick="email()"><a href="javascript:void(0);"><i class="email"></i>Email好友</a></li>
                    <li id="weibo_box" onclick="weibo()"><a href="javascript:void(0);"><i class="weibo"></i>微博好友</a></li>
                    <li id="kaixin_box" onclick="kaixin()"><a href="javascript:void(0);"><i class="kaixin"></i>开心网</a></li>
                    <li id="douban_box" onclick="douban()"><a href="javascript:void(0);"><i class="douban"></i>豆瓣好友</a></li>
                </ul>
                <div class="find_content_box">
       	    		<table width="620" border="0" cellspacing="0" cellpadding="0" id="msn_box_content">
                      <tr>
                        <td width="12%" align="right">MSN账号：</td>
                        <td width="88%"><input name="" type="text" /></td>
                      </tr>
                      <tr>
                        <td align="right">MSN密码：</td>
                        <td><input name="" type="text" /><div class="tips">集集组不会记录你的密码，请放心使用。</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a href="javascript:void(0);" class="friend_search_btn" onclick="find_people()">找人</a></td>
                      </tr>
                    </table>
       	    		<table width="620" border="0" cellspacing="0" cellpadding="0" id="qq_box_content">
                      <tr>
                        <td width="12%" align="right">QQ账号：</td>
                        <td width="88%"><input name="" type="text" /></td>
                      </tr>
                      <tr>
                        <td align="right">QQ密码：</td>
                        <td><input name="" type="text" /><div class="tips">集集组不会记录你的密码，请放心使用。</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a href="javascript:void(0);" class="friend_search_btn" onclick="find_people()">找人</a></td>
                      </tr>
                    </table>
       	    		<table width="620" border="0" cellspacing="0" cellpadding="0" id="email_box_content">
                      <tr>
                        <td width="12%" align="right">Email账号：</td>
                        <td width="88%"><input name="" type="text" /></td>
                      </tr>
                      <tr>
                        <td align="right">Email密码：</td>
                        <td><input name="" type="text" /><div class="tips">集集组不会记录你的密码，请放心使用。</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a href="javascript:void(0);" class="friend_search_btn" onclick="find_people()">找人</a></td>
                      </tr>
                    </table>
       	    		<table width="620" border="0" cellspacing="0" cellpadding="0" id="weibo_box_content">
                      <tr>
                        <td width="12%" align="right">微博账号：</td>
                        <td width="88%"><input name="" type="text" /></td>
                      </tr>
                      <tr>
                        <td align="right">微博密码：</td>
                        <td><input name="" type="text" /><div class="tips">集集组不会记录你的密码，请放心使用。</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a href="javascript:void(0);" class="friend_search_btn" onclick="find_people()">找人</a></td>
                      </tr>
                    </table>
       	    		<table width="620" border="0" cellspacing="0" cellpadding="0" id="kaixin_box_content">
                      <tr>
                        <td width="12%" align="right">开心账号：</td>
                        <td width="88%"><input name="" type="text" /></td>
                      </tr>
                      <tr>
                        <td align="right">开心密码：</td>
                        <td><input name="" type="text" /><div class="tips">集集组不会记录你的密码，请放心使用。</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a href="javascript:void(0);" class="friend_search_btn" onclick="find_people()">找人</a></td>
                      </tr>
                    </table>
       	    		<table width="620" border="0" cellspacing="0" cellpadding="0" id="douban_box_content">
                      <tr>
                        <td width="12%" align="right">豆瓣账号：</td>
                        <td width="88%"><input name="" type="text" /></td>
                      </tr>
                      <tr>
                        <td align="right">豆瓣密码：</td>
                        <td><input name="" type="text" /><div class="tips">集集组不会记录你的密码，请放心使用。</div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a href="javascript:void(0);" class="friend_search_btn" onclick="find_people()">找人</a></td>
                      </tr>
                    </table>
            	</div>
                <div class="find_content_box" style="display:block">
                    <div class="loading_box">
                        <img src="../images/loading2.gif" width="32" height="32" />正在读取好友信息
                    </div>
                    <div class="loading_tips">
                        <p>温馨提示</p>
                        <span>很抱歉，没有拉取到您的联系人列表</span>
                        <span>您可以尝试其他寻找好友的方式</span>
                    </div>
                </div>
                <div class="conResult_sam_sub" style="display:block">
                	<dl>
                        <dt><a href="javascript:void(0);"><img src="../images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                        <dd class="name"><a href="javascript:void(0);">李雯</a></dd>
                        <dd>现居 北京东城</dd>
                        <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                        <dt><a href="javascript:void(0);"><img src="../images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                        <dd class="name"><a href="javascript:void(0);">李雯</a></dd>
                        <dd>现居 北京东城</dd>
                        <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                        <dt><a href="javascript:void(0);"><img src="../images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                        <dd class="name"><a href="javascript:void(0);">李雯</a></dd>
                        <dd>现居 北京东城</dd>
                        <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                        <dt><a href="javascript:void(0);"><img src="../images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                        <dd class="name"><a href="javascript:void(0);">李雯</a></dd>
                        <dd>现居 北京东城</dd>
                        <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                        <dt><a href="javascript:void(0);"><img src="../images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                        <dd class="name"><a href="javascript:void(0);">李雯</a></dd>
                        <dd>现居 北京东城</dd>
                        <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <dl>
                        <dt><a href="javascript:void(0);"><img src="../images/people/pic_05.jpg" width="60" height="60" /></a></dt>
                        <dd class="name"><a href="javascript:void(0);">李雯</a></dd>
                        <dd>现居 北京东城</dd>
                        <dd class="btn"><a class="addFriend" href="javascript:void(0);"></a></dd>
                    </dl>
                    <div class="next_find"><a href="javascript:void(0);">下一组</a></div>
                </div>
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
<script language="javascript">

</script>
</body>
</html>
