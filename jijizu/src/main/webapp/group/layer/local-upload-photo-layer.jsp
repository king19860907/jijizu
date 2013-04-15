<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_upload_album3" style="display:none;">
	<div class="layer_point_upload_img ly12 lay_box">
        <div class="bg">
        	<h1>上传照片<a href="javascript:void(0);" class="span" onclick="hide_upload_album3()"></a></h1>
            <div class="loading_icon"><img src="${IMG_PATH}/loading.gif" width="32" height="32" /></div>
            <div class="layer_event_upimg">
                <div class="up_stat clearfix">
                    <p class="main">请添加照片描述</p>
                </div>
                <div class="uploading up_modify">
                	<form id="group-upload-photo-form" method="post">
                		<input type="hidden" name="groupId" value="<s:property value="groupId"/>" />
                	</form>
                    <div class="opt_area clearfix" style="height:35px;"/>
                      <form id="group_upload_pic_form2" method="post"  enctype="multipart/form-data">
                        <div class="add_zone">
                            <a href="javascript:void(0)" class="btn_album_small" title="添加照片">
                            	 <input type="file" name="myFile" id="group_upload_img2" style="left: 0px;position:absolute;width:78px;height:30px;visibility: visible;background: none repeat scroll 0 0 transparent; border: medium none; cursor: pointer; filter:alpha(opacity=0); -moz-opacity:0; opacity:0;"/>
                            </a>
                            <span class="up_num">已添加<span id="upload_num">0</span>张，还可以添加<span id="max_num">0</span>张</span>
                        </div>
                        </form>
                        <div class="add_ok"><span class="share_wb"></span><a href="javascript:void(0)" groupid="<s:property value="groupId"/>" id="group-upload-photo-btn" class="send">上传</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	uploadImg("group_upload_img2","group_upload_pic_form2");

	$("#group-upload-photo-btn").click(function(){
		var length = $(".modify_mod").length;
		for(var i=0;i<length;i++){
			var content = $("[name='photoList["+i+"].content']");
			if(content.val()=="添加描述"){
				content.val("");
			}
		}
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					  alert("保存成功");
					  location.reload();
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			$("#group-upload-photo-form").attr('action','/group/uploadPhotoBatch.jspa');
			$("#group-upload-photo-form").ajaxSubmit(options);
		
	});
});
var localUploadBox= $("#box_upload_album3");
localUploadBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_upload_album3(){
	//setTimeout('loading_upimg()', 0 );
	localUploadBox.overlay().load();
}
	
function hide_upload_album3(){
	localUploadBox.overlay().close();
}
function upload_focus(e){
	if($(e).html()=="添加描述"){
		$(e).html("").css("color","#333");
	}
};
function upload_blur(e){
	if($(e).html()==""){
		$(e).html("添加描述").css("color","#B8B8B8");
	}
};
</script>