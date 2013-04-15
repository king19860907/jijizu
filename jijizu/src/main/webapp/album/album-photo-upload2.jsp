<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.form.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/album.js${JS_EDITION}"></script>
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
        	<div class="tab">
            	<a href="javascript:void(0);" class="hover">上传照片</a>
            </div>
          <div class="pl_content">
          	<div class="m_path_tit">
            	<p class="ok"><em></em>您已成功上传6张照片</p>
                <!--<span class="btn"><a href="相册详情.html">返回相册</a><a href="javascript:void(0);">继续上传</a></span>-->
            </div>
            <div class="m_add_tag">
            	<div class="title">统一添加照片信息</div>
                <div class="btn"><a href="javascript:void(0);" onclick="return false;" id="update-photo-unified-btn">保存</a></div>
            </div>
            <div class="tag_content">
                <dl>
                    <dt>照片名称：</dt>
                    <dd><input name="" id="unifiedName" type="text" /></dd>
                </dl>
                <dl>
                    <dt>照片描述：</dt>
                    <dd><textarea name="" id="unifiedContent" cols="" rows=""></textarea></dd>
                </dl>
            </div>
            <div class="uploadEditForm">
            	<form id="update-photo-list-form" method="post">
            	<input type="hidden" name="albumId" value="<s:property value="albumId"/>" />
            	<ul class="plst">
            		<s:iterator value="photoPage.result" status="stat">
            			<input type="hidden" name="photoList[<s:property value="#stat.index"/>].photoId" value="<s:property value="photoId"/>" />
            			<li>
	                    	<div class="m_editphoto">
	                        	<div class="parea"><img src="<s:property value="imgUrl"/>" alt=""/></div>
	                            <div class="editarea">
	                            	<input class="name" name="photoList[<s:property value="#stat.index"/>].name" type="text"  onblur="if(this.value=='') this.value='照片名称';" onfocus="if(this.value=='照片名称') this.value='';" value="照片名称" />
	                                <textarea name="photoList[<s:property value="#stat.index"/>].content"  onblur="if(this.value=='') this.value='照片描述';" onfocus="if(this.value=='照片描述') this.value='';"  cols="" rows="">照片描述</textarea>
	                                <label><input type="radio" value = "<s:property value="photoId"/>"  id="coverId" name="coverId" class="radio" />设为专辑封面</label>
	                            </div>
	                        </div>
	                    </li>
            		</s:iterator>
                </ul>
            	</form>
            </div>
            <div class="clear"></div>
            <div class="P_uppict_edit_save"><a href="javascript:void(0);" onclick="return false;" class="M_btn_a" id="update-photo-list-btn">保存并发布</a></div>
          </div>
        </div>
        
        <!--右边-->
        <%@include file="/status/inc/status-right-inc.jsp" %>
        <!--右边END-->
      </div>
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
