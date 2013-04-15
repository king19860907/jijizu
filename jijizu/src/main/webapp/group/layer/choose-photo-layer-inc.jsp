<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div class="layer_point_upload_img ly12 lay_box">
        <div class="bg">
        	<h1>上传照片<a href="javascript:void(0);" class="span" onclick="hide_box_upload_img()"></a></h1>
            <div class="up_stat clearfix">
                <p class="main">选择照片</p>
                <p class="plus">
                    <a href="javascript:void(0)" onclick="show_box_upload_album(0,<s:property value="groupId"/>);">«返回选择相册</a>
                </p>
            </div>
            <div class="mainContainer">
                <ul class="photo_list clearfix">
                	<s:iterator value="photoPage.result">
	                    <li><a class="list_img" name="choose-photo" photoid="<s:property value="photoId"/>" href="javascript:void(0)"><span class="imgc"><img alt="" src="<s:property value="smallImgUrl"/>"></span><span class="chked"></span></a></li>
                	</s:iterator>
                </ul>
            </div>
            <div class="sl_opt_area" <s:if test="photoPage == null || photoPage.recordCnt==0">style="height:35px;"</s:if>>
                <div class="btn">
                    <a href="javascript:void(0)" class="send" groupid="<s:property value="groupId"/>" albumid="<s:property value="albumId"/>" id="copty-photo-to-group-btn"><span>确定</span></a>
                    <a href="javascript:void(0)" class="cancel" onclick="hide_box_upload_img()"><span>取消</span></a>
                </div>
            </div>
            <s:if test="photoPage.pageCnt>1">
						<div class="W_pages_minibtn" style="height:35px;">
							<s:if test="photoPage.befPageNum>0 && photoPage.curPageNum!=1">      	
							   <a href="javascript:show_upload_album2(<s:property value="photoPage.befPageNum" />,<s:property value="albumId"/>,<s:property value="groupId"/>);" class="btn_page">上一页</a>
							</s:if>
							<s:iterator value="photoPage.pageList" id="curPage">
								<s:if test="#curPage==photoPage.curPageNum">
									 <a href="javascript:void(0);" class="on"><s:property value="#curPage" /></a>
								</s:if>
								<s:else>
									<a href="javascript:show_upload_album2(<s:property value="#curPage" />,<s:property value="albumId"/>,<s:property value="groupId"/>);"><s:property value="#curPage" /></a>
								</s:else>
							</s:iterator>
							   <s:if test="photoPage.curPageNum!=photoPage.pageCnt">
							   <a href="javascript:show_upload_album2(<s:property value="photoPage.aftPageNum" />,<s:property value="albumId"/>,<s:property value="groupId"/>);" class="btn_page">下一页</a>
							   </s:if>
						</div>
				</s:if>
        </div>
    </div>
<script type="text/javascript">
$(document).ready(function(){
	/**
	 * 选择照片
	 */
	$("[name='choose-photo']").click(function(){
		if($(this).hasClass("selected")){
			$(this).removeClass("selected");
		}else{
			$(this).addClass("selected");
		}
		
	});

	$("#copty-photo-to-group-btn").click(function(){
		var photoIds = getPhotoIds();
		if(photoIds == ""){
			alert("请选择照片");
			return;
		}
		var albumId = $(this).attr("albumid");
		var groupId = $(this).attr("groupid");
		$.ajax({
			type:'POST',
			url:'/group/copyPhotoToGroup.jspa',
			dataType:'json',
			 data:({"photoIds":photoIds,
				 	"albumId":albumId,
				 	"groupId":groupId
			      }),  
			success:function(json){
				if(json.flag == 0){
					location.reload();
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
});

function getPhotoIds(){
	var photoIds = "";
	$("[name='choose-photo']").each(function(){
		if($(this).hasClass("selected")){
			var photoId = $(this).attr("photoid");
			photoIds = photoIds+photoId+"-";
		}
	});
	if(photoIds != ""){
		photoIds = photoIds.substring(0,photoIds.length-1);
	}
	return photoIds;
}
</script>