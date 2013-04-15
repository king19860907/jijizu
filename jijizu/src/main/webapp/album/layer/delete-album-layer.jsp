<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_album_del" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_album_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定删除该专辑吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" class="ok" id="del-album-btn">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_album_del()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var deleteAlbumBox= $("#box_album_del");
deleteAlbumBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_album_del(albumId){
	deleteAlbumBox.overlay().load();
	$("#del-album-btn").attr("albumId",albumId);
}
	
function hide_album_del(){
	deleteAlbumBox.overlay().close();
}
</script>