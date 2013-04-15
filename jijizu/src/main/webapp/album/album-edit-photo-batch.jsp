<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>照片批量管理-集集组是一个平台，方便家长组织好友，带着孩子们聚在一起，玩耍、交流、分享。</title>
<link href="${CSS_PATH}/global.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
<script type="text/javascript" src="${JS_PATH}/jquery-1.4.2.min.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/global.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/hl_all.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/common_layer.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery-1.2.6.pack.js${JS_EDITION}"></script>
<script type="text/javascript" src="${JS_PATH}/jquery.tools.min.js${JS_EDITION}"></script>
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
        	<div class="upload_title">
            	<span>照片批量管理</span>
            </div>
            <div class="m_browse_menu">
                <div class="menu_box">
                	<span class="back"><a href="/album/photo.jspa?albumId=<s:property value="albumInfo.albumId"/>">返回照片列表»</a></span>
                	<label class="manage_check">
                        <input type="checkbox" action-type="all_choose" class="M_checkbox"/> 当页全选
                    </label>
                	<span class="manage_count">已选<em>0</em>张</span>
                    <span class="manage_act">
                        <a href="javascript:void(0);" onclick="show_box_movePic(<s:property value="albumInfo.albumId"/>)">批量移动</a>
                        <a href="javascript:void(0);" onclick="show_album_del(<s:property value="albumInfo.albumId"/>)">批量删除</a>
                	</span>
                </div>
            </div>
          <div class="pl_content">
          	<div class="m_photo_list_a">
          		<s:iterator value="photoPage.result">
          			<dl class="photoList m_selectpic_list">
	                	<dt name="edit-photo-ids" photoid="<s:property value="photoId"/>"><a href="javascript:void(0);"><img src="<s:property value="imgUrl"/>" width="120" class="img"/><span class="M_ico"></span></a></dt>
	                </dl>
          		</s:iterator>
            </div>
          </div>
          <div class="m_browse_menu">
                <div class="menu_box">
                	<span class="back"><a href="/album/photo.jspa?albumId=<s:property value="albumInfo.albumId"/>">返回照片列表»</a></span>
                	<label class="manage_check">
                        <input type="checkbox" action-type="all_choose" class="M_checkbox"/> 当页全选
                    </label>
                	<span class="manage_count">已选<em>0</em>张</span>
                    <span class="manage_act">
                        <a href="javascript:void(0);" onclick="show_box_movePic()">批量移动</a>
                        <a href="javascript:void(0);" onclick="show_album_del()">批量删除</a>
                	</span>
                </div>
            </div>
             <s:if test="photoPage.pageCnt>1">
					<div class="W_pages_minibtn">
						<s:if test="photoPage.befPageNum>0 && photoPage.curPageNum!=1">      	
						   <a href="/album/editPhotoBatch.jspa?albumId=<s:property value="albumInfo.albumId"/>&page=<s:property value="photoPage.befPageNum" />" class="btn_page">上一页</a>
						</s:if>
						<s:iterator value="photoPage.pageList" id="curPage">
							<s:if test="#curPage==photoPage.curPageNum">
								 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
							</s:if>
							<s:else>
								<a href="/album/editPhotoBatch.jspa?albumId=<s:property value="albumInfo.albumId"/>&page=<s:property value="#curPage" />"><s:property value="#curPage" /></a>
							</s:else>
						</s:iterator>
						   <s:if test="photoPage.curPageNum!=photoPage.pageCnt">
						   <a href="/album/editPhotoBatch.jspa?albumId=<s:property value="albumInfo.albumId"/>&page=<s:property value="photoPage.aftPageNum" />" class="btn_page">下一页</a>
						   </s:if>
					</div>
			</s:if>
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
<!-- 批量移动照片弹出层 -->
<%@include file="/album/layer/move-photo-batch-layer.jsp" %>
<!-- 批量删除照片弹出层 -->
<%@include file="/album/layer/delete-photo-batch-layer.jsp" %>
<script language="javascript">
</script>
</body>
</html>
