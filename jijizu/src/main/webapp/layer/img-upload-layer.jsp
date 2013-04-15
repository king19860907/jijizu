<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>


<div class="layer_send_box">
<div class="content"><a href="javascript:void(0)" class="W_close" id="img_upload_layer_close"></a>
<div class="layer_send_pic">
<div class="laPic_btnmore">
<form id="status_upload_pic_form" method="post"  enctype="multipart/form-data">
		<input type="file" class="input_f" name="myFile" id="upload_img"/>
	</form>
<p>仅支持Png，Jpg，Gif，文件大小小于5M</p></div>
<div class="loading" id="loading" style="display:none">图片正在上传，请等待...</div>
<div class="laPic" id="laPic" style="display:none"><img id="imgUrl" src="" /></div>
</div>

<div class="arrow"></div>
</div>
</div>