<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_photo_del" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_album_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定删除该照片吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" class="ok" id="delete-photo-btn">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_photo_del()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var deletePhotoBox= $("#box_photo_del");
deletePhotoBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_photo_del(photoId,returnUrl){
	deletePhotoBox.overlay().load();
	$("#delete-photo-btn").attr("photoId",photoId).attr("return-url",returnUrl);
}
	
function hide_photo_del(){
	deletePhotoBox.overlay().close();
}
</script>