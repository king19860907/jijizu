<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="CreateAlbums_ok" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>创建相册成功<a href="javascript:void(0);" class="span" onclick="hide_sixin_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">相册<font id="albumSuccessName"></font>创建成功，<br />是否马上 上传照片到这个相册？</p></dd>
            </dl>
            <div class="btn"><a href="/album/upload.jspa" class="ok">确认</a><a href="javascript:void(0)" class="cancel" onclick="location.reload();">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var createAlbumSuccessBox= $("#CreateAlbums_ok");
createAlbumSuccessBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_CreateAlbums_ok(){
	createAlbumSuccessBox.overlay().load();
}
	
function hide_CreateAlbums_ok(){
	createAlbumSuccessBox.overlay().close();
}
</script>