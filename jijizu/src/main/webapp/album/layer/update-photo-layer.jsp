<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="ChangeAlbums2" style="display:none;">
	<div class="layer_point ly11 lay_box">
        <div class="bg">
        	<h1>编辑照片<a href="javascript:void(0);" class="span" onclick="hide_ChangeAlbums2()"></a></h1>
            <div class="L_editalbum">
                <div class="album_info">
                   <dl class="m_Layform clearfix">
                        <dt>照片名称：</dt>
                        <dd class="contBox"><input id="updatePhotoName" type="text" class="M_inpt" name="caption" value=""/></dd>
                        <dd class="tipBox"><span node-type="caption_w" style="color:red" class="M_txte"></span></dd>
                    </dl>
                    <dl class="m_Layform clearfix">
                        <dt>照片描述：</dt>
                        <dd class="contBox"><textarea id="updatePhotoContent" class="M_txtarea" title="" name="description"></textarea></dd>
                        <dd class="tipBox"><span node-type="description_w" style="display:none" class="M_txte">16/140</span></dd>
                    </dl>
                    <div class="setcover"><label><input type="checkbox" class="M_checkbox" id="cover" value="1" name="cover">设为专辑封面</label></div>
                    <div class="clear"></div>
                </div>
                <div class="save_cal">
                    <div class="btn"><a href="javascript:void(0)" class="ok" id="update-photo-btn">保存</a><a href="javascript:void(0)" class="cancel" onclick="hide_ChangeAlbums2()">取消</a></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var updatePhotoBox= $("#ChangeAlbums2");
updatePhotoBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_ChangeAlbums2(photoId,name,content){
	updatePhotoBox.overlay().load();
	$("#update-photo-btn").attr("photoId",photoId);
	$("#updatePhotoName").val(name);
	$("#updatePhotoContent").val(content);
}
	
function hide_ChangeAlbums2(){
	updatePhotoBox.overlay().close();
}
</script>