<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="ChangeAlbums" style="display:none;">
	<div class="layer_point ly6 lay_box">
        <div class="bg">
        	<h1>编辑相册<a href="javascript:void(0);" class="span" onclick="hide_ChangeAlbums()"></a></h1>
            <div class="L_editalbum">
                <div class="album_info">
                    <dl class="m_Layform clearfix">
                        <dt>专辑名称：</dt>
                        <dd class="contBox"><input id="updateAlbumName" type="text" class="M_inpt" name="caption" value=""/></dd>
                        <dd class="tipBox"><span node-type="caption_w" style="color:red" class="M_txte">*</span></dd>
                    </dl>
                    <dl class="m_Layform clearfix">
                        <dt>专辑描述：</dt>
                        <dd class="contBox"><textarea id="updateAlbumContent" class="M_txtarea" title="" name="description"></textarea></dd>
                        <dd class="tipBox"><span node-type="description_w" style="display:none" class="M_txte">16/140</span></dd>
                    </dl>
                </div>
                <div class="save_cal">
                    <div class="btn"><a href="javascript:void(0)" class="ok" id="update-album-btn">保存</a><a href="javascript:void(0)" class="cancel" onclick="hide_ChangeAlbums()">取消</a></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var updateAlbumBox= $("#ChangeAlbums");
updateAlbumBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_ChangeAlbums(albumId,name,content){
	updateAlbumBox.overlay().load();
	$("#update-album-btn").attr("albumId",albumId);
	$("#updateAlbumName").val(name);
	$("#updateAlbumContent").val(content);
}
	
function hide_ChangeAlbums(){
	updateAlbumBox.overlay().close();
}
</script>