<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="CreateAlbums" style="display:none;">
	<div class="layer_point ly6 lay_box">
        <div class="bg">
        	<h1>创建相册<a href="javascript:void(0);" class="span" onclick="hide_CreateAlbums()"></a></h1>
            <div class="L_editalbum">
                <div class="album_info">
                    <dl class="m_Layform clearfix">
                        <dt>专辑名称：</dt>
                        <dd class="contBox"><input id="albumName" type="text" class="M_inpt" name="caption" value=""></dd>
                        <dd class="tipBox"><span node-type="caption_w" style="color:red" class="M_txte">*</span></dd>
                    </dl>
                    <dl class="m_Layform clearfix">
                        <dt>专辑描述：</dt>
                        <dd class="contBox"><textarea id="albumContent" class="M_txtarea" title="" name="description"></textarea></dd>
                        <dd class="tipBox"><span node-type="description_w" style="display:none" class="M_txte">16/140</span></dd>
                    </dl>
                </div>
                <div class="save_cal">
                    <div class="btn"><a href="javascript:void(0)" class="ok" onclick="createAlbum();">创建</a><a href="javascript:void(0)" class="cancel" onclick="hide_CreateAlbums()">取消</a></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function show_CreateAlbums(){
	$("#CreateAlbums").overlay().load();
}
	
function hide_CreateAlbums(){
	$("#CreateAlbums").overlay().close();
}
$(document).ready(function(){
	$("#CreateAlbums").overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});	
});
</script>