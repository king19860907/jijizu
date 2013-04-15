<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_upload_img" style="display:none;">
    
</div>
<div id="box_upload_img_content" style="display:none">
<div class="layer_point_upload_img lay_box">
        <div class="bg">
        	<h1>上传照片<a href="javascript:void(0);" class="span" onclick="hide_box_upload_img()"></a></h1>
            <div class="layer_point_upload_img">
                <div class="layer_event_uppic">
                    <p class="up_des"></p>
                    <div class="up_con">
                    <form id="group_upload_pic_form" method="post"  enctype="multipart/form-data">
                        <p class="opt_area clearfix">
                            <a class="btn_up" href="javascript:void(0)">
	                            
	                            	  <input type="file" name="myFile" id="group_upload_img" style="left: 0px;position:absolute;width:78px;height:30px;visibility: visible;background: none repeat scroll 0 0 transparent; border: medium none; cursor: pointer; filter:alpha(opacity=0); -moz-opacity:0; opacity:0;"/>
								
                            </a>
                            <a class="btn_album" href="javascript:void(0)" onclick="show_box_upload_album(0,<s:property value="groupId"/>)"></a>
                        </p>
                        </form>
                    </div>
                 </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var uploadPhotoBox= $("#box_upload_img");
uploadPhotoBox.overlay({closeOnClick:false,speed:"fast", mask:{color:'#000', loadSpeed:"fast",opacity:0.5}});
function show_box_upload_img(){
	$("#box_upload_img").html($("#box_upload_img_content"));
	$("#box_upload_img_content").show();
	uploadPhotoBox.overlay().load();
}
	
function hide_box_upload_img(){
	location.reload();
}

function uploadImg(fileId,formId){
	$("#"+fileId).change(function(){
		var filepath=$(this).val(); 
		var extStart=filepath.lastIndexOf(".");
		var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			alert("图片限于bmp,png,gif,jpeg,jpg格式");
			return ;
		} 
		//show_upload_album3();
		var options = { 
				cache : false,
				dataType:"data",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.indexOf(':') != -1){
					  var length=$(".modify_mod").length;
					  var maxNum = 4;
					  var html ="<dl class='modify_mod clearfix'>";
					  html+="<input type='hidden' value='"+data+"' name='photoList["+length+"].imgUrl' />";
				  	  html+="<dt class='thumb'><img width='80' height='80' alt='' src='"+data+"'></dt>";
				  	  html+="<dd class='img_des'><textarea onblur='upload_blur(this)' onfocus='upload_focus(this)' class='des_words' rows='' cols='' name='photoList["+(length)+"].content'>添加描述</textarea></dd>";
				  	  html+="<dd class='picmdf_area'><a class='del' onclick='delUpload(this);' href='javascript:void(0)'></a></dd>";
				  	  html+="</dl>";
				 	  $("#group-upload-photo-form").prepend(html);
				 	  $("#upload_num").html(length+1);
				 	  $("#max_num").html(maxNum-length-1);
				 	  if((length+1)==maxNum){
				 		  $(".btn_album_small").hide();
				 	  }
				 	  if($("#box_upload_album3").is(":visible")==false){
				 		 $("#box_upload_album3").show();
						  loading_upimg();
						  $("#box_upload_img").html($("#box_upload_album3"));
					  }
				  }else{
				  	alert(data);
				  }
				}
			};
			$("#"+formId).attr('action','/status/fileUploadStatusAjax.jspa');
			$("#"+formId).ajaxSubmit(options);
	});
}

function delUpload(e){
	$(e).parent().parent().remove();
	var maxNum = 4;
	var length=$(".modify_mod").length;
	$("#upload_num").html(length);
	$("#max_num").html(maxNum-length);
	if(maxNum > length){
		 $(".btn_album_small").show();
	}
}

$(document).ready(function(){
	uploadImg("group_upload_img","group_upload_pic_form");
});

</script>