<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_album_del" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_album_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定删除该照片吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="delete-photo-batch-btn" class="ok" >确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_album_del()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var deletePhotoBatchBox= $("#box_album_del");
deletePhotoBatchBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_album_del(albumId){
	var photoIds = getPhotoIds();
	if(photoIds == ""){
		alert("请选择照片");
		return;
	}
	deletePhotoBatchBox.overlay().load();
	$("#delete-photo-batch-btn").attr("albumId",albumId);
}
	
function hide_album_del(){
	deletePhotoBatchBox.overlay().close();
}
</script>