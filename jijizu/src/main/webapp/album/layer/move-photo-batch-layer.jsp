<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_movePic" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>批量移动<a href="javascript:void(0);" class="span" onclick="hide_box_movePic()"></a></h1>
            <dl class="chose_slt clearfix">
                <dt>将照片移动到</dt>
                <dd><select id="move-photo-album">
                		<s:iterator value="albumPage.result">
                			<s:if test="albumId!=albumInfo.albumId">
		                		<option value="<s:property value="albumId"/>"><s:property value="name"/></option>
                			</s:if>
                		</s:iterator>
                	</select>
                </dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="move-photo-batch-btn" class="ok" >确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_box_movePic()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var movePhotoBatch= $("#box_movePic");
movePhotoBatch.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});

function show_box_movePic(albumId){
	var photoIds = getPhotoIds();
	if(photoIds == ""){
		alert("请选择照片");
		return;
	}
	movePhotoBatch.overlay().load();
	$("#move-photo-batch-btn").attr("sourceAlbumId",albumId)
}
	
function hide_box_movePic(){
	movePhotoBatch.overlay().close();
}

</script>