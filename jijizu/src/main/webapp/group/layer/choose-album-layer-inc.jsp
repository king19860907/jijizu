<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div class="layer_point_upload_img ly12 lay_box">
        <div class="bg">
        	<h1>上传照片<a href="javascript:void(0);" class="span" onclick="hide_box_upload_img();"></a></h1>
                <div class="up_stat clearfix">选择相册</div>
                <div class="up_mainContainer">
                    <ul class="grp_list clearfix">
                    	<s:set name="paraGroupId" value="groupId"></s:set>
                    	<s:iterator value="albumPage.result">
                    		<li>
	                            <div class="album_cover_bg">
	                            	<a href="javascript:void(0)" class="album_grp_con" onclick="show_upload_album2(0,<s:property value="albumId"/>,<s:property value="#paraGroupId"/>)"><span class="inner"><span class="album_cover"><img alt="" src="<s:property value="defaultPhotoImgUrl"/>"></span></span></a>
	                            </div>
	                            <p class="album_des">
	                            	<a href="javascript:void(0)" onclick="show_upload_album2(0,<s:property value="albumId"/>,<s:property value="#paraGroupId"/>)"><s:property value="name"/></a><em class="a_num">(<s:property value="photoNum"/>)</em>
	                            </p>
                        	</li>
                    	</s:iterator>
                    </ul>
                     <s:if test="albumPage.pageCnt>1">
						<div class="W_pages_minibtn" style="height:35px;">
							<s:if test="albumPage.befPageNum>0 && albumPage.curPageNum!=1">      	
							   <a href="javascript:show_box_upload_album(<s:property value="albumPage.befPageNum" />,<s:property value="groupId"/>);" class="btn_page">上一页</a>
							</s:if>
							<s:iterator value="albumPage.pageList" id="curPage">
								<s:if test="#curPage==albumPage.curPageNum">
									 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
								</s:if>
								<s:else>
									<a href="javascript:show_box_upload_album(<s:property value="#curPage" />,<s:property value="groupId"/>);"><s:property value="#curPage" /></a>
								</s:else>
							</s:iterator>
							   <s:if test="albumPage.curPageNum!=albumPage.pageCnt">
							   <a href="javascript:show_box_upload_album(<s:property value="albumPage.aftPageNum" />,<s:property value="groupId"/>);" class="btn_page">下一页</a>
							   </s:if>
						</div>
				</s:if>
                </div>
            </div>
        </div>