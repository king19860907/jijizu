
$(document).ready(function(){
	
	$("#update-photo-unified-btn").click(function(){
		var length = $(".plst > li").length;
		var unifiedName = $("#unifiedName").val();
		var unifiedContent = $("#unifiedContent").val();
		var albumId = $("[name='albumId']").val();
		for(var i=0;i<length;i++){
			var name = $("[name='photoList["+i+"].name']");
			name.val(unifiedName);
			var content = $("[name='photoList["+i+"].content']");
			content.val(unifiedContent);
		}
		var options = { 
				cache : false,
				dataType:"json",	
				type :"post",
				async:false,
				success: function(data) { 
				  if(data.flag == 0){
					alert("保存成功");
					location.href="/album/photo.jspa?albumId="+albumId;
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			$("#update-photo-list-form").attr('action','/album/updatePhotoList.jspa');
			$("#update-photo-list-form").ajaxSubmit(options);
	});
	
	$("#update-photo-list-btn").click(function(){
		var length = $(".plst > li").length;
		var albumId = $("[name='albumId']").val();
		for(var i=0;i<length;i++){
			var name = $("[name='photoList["+i+"].name']");
			if(name.val()=="照片名称"){
				name.val("");
			}
			var content = $("[name='photoList["+i+"].content']");
			if(content.val()=="照片描述"){
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
					  location.href="/album/photo.jspa?albumId="+albumId;
				  }else{
				  	alert(data.msg);
				  }
				}
			};
			$("#update-photo-list-form").attr('action','/album/updatePhotoList.jspa');
			$("#update-photo-list-form").ajaxSubmit(options);
		
	});
	
	/**
	 * 上一张照片
	 */
	$("#pre-photo-btn").click(function(){
		var photoId = $(this).attr("photoid");
		if(photoId == ""){
			alert("没有上一张照片");
			return;
		}
		location.href="/album/photoDetail.jspa?photoId="+photoId;
	});
	
	/**
	 * 下一张照片
	 */
	$("#next-photo-btn").click(function(){
		var photoId = $(this).attr("photoid");
		if(photoId == ""){
			alert("没有下一张照片");
			return;
		}
		location.href="/album/photoDetail.jspa?photoId="+photoId;
	});
	
	/**
	 * 批量删除照片
	 */
	$("#delete-photo-batch-btn").click(function(){
		var photoIds = getPhotoIds();
		var albumId = $(this).attr("albumId");
		$.ajax({
			type:'POST',
			url:'/album/deletePhotoBatch.jspa',
			dataType:'json',
			 data:({"photoIds":photoIds,
				 	"albumId":albumId
			      }),  
			success:function(json){
				if(json.flag == 0){
					location.reload();
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
	/**
	 * 批量移动照片
	 */
	$("#move-photo-batch-btn").click(function(){
		var photoIds = getPhotoIds();
		var albumId = $("#move-photo-album").val();
		var sourceAlbumId = $(this).attr("sourceAlbumId");
		//alert(photoIds);
		///alert(albumId);
		//alert(sourceAlbumId);
		$.ajax({
			type:'POST',
			url:'/album/movePhotoBatch.jspa',
			dataType:'json',
			 data:({"photoIds":photoIds,
				 	"albumId":albumId,
				 	"sourceAlbumId":sourceAlbumId
			      }),  
			success:function(json){
				if(json.flag == 0){
					location.reload();
				}else{
					alert(json.msg);
				}
			}
		});
	});
	
	/**
	 * 编辑照片
	 */
	$("#update-photo-btn").click(function(){
		var photoId = $(this).attr("photoId");
		var name = $("#updatePhotoName").val();
		var content = $("#updatePhotoContent").val();
		var cover = $("#cover").attr("checked");
		if(name.length>30){
			alert("照片名称不能大于15个字！");
			$("#updatePhotoName").focus();
			return;
		}
		if(content.length>150){
			alert("照片描述不能大于150个字！");
			$("#updatePhotoContent").focus();
			return;
		}
		updatePhoto(photoId,name,content,cover);
	});
	
	/**
	 * 删除照片
	 */
	$("#delete-photo-btn").click(function(){
		var photoId = $(this).attr("photoId");
		var returnUrl = $(this).attr("return-url");
		deletePhoto(photoId,returnUrl);
	});
	
	/**
	 * 删除相册
	 */
	$("#del-album-btn").click(function(){
		var albumId = $(this).attr("albumId");
		deleteAlbum(albumId);
	});
	
	/**
	 * 编辑相册
	 */
	$("#update-album-btn").click(function(){
		var albumId = $(this).attr("albumId");
		var name = $("#updateAlbumName").val();
		var content = $("#updateAlbumContent").val();
		if(name==""){
			alert("请输入专辑名称！");
			$("#updateAlbumName").focus();
			return;
		}
		if(name.length>15){
			alert("专辑名称不能大于15个字！");
			$("#updateAlbumName").focus();
			return;
		}
		if(content.length>150){
			alert("专辑描述不能大于150个字！");
			$("#updateAlbumContent").focus();
			return;
		}
		updateAlbum(albumId,name,content);
	});
	
});

function getPhotoIds(){
	var photoIds = "";
	$("[name='edit-photo-ids']").each(function(){
		if($(this).attr("class")=="selected"){
			var photoId = $(this).attr("photoid");
			photoIds = photoIds+photoId+"-";
		}
	});
	if(photoIds == ""){
		return photoIds;
	}
	return photoIds.substring(0,photoIds.length-1);
}

/**
 * 编辑照片
 * @param photoId
 * @param name
 * @param content
 */
function updatePhoto(photoId,name,content,isCover){
	$.ajax({
		type:'POST',
		url:'/album/updatePhoto.jspa',
		dataType:'json',
		 data:({"photoId":photoId,
			 	"name":name,
			 	"content":content,
			 	"cover":isCover
		      }),  
		success:function(json){
			if(json.flag == 0){
				location.reload();
			}else{
				alert(json.msg);
			}
		}
	});
}

/**
 * 删除照片
 * @param photoId
 */
function deletePhoto(photoId,returnUrl){
	$.ajax({
		type:'POST',
		url:'/album/deletePhoto.jspa',
		dataType:'json',
		 data:({"photoId":photoId
		      }),  
		success:function(json){
			if(json.flag == 0){
				if(typeof(returnUrl) != "undefined" && returnUrl!=""){
					location.href = returnUrl;
				}else{
					location.reload();
				}
			}else{
				alert(json.msg);
			}
		}
	});
}

/**
 * 编辑相册
 */
function updateAlbum(albumId,name,content){
	$.ajax({
		type:'POST',
		url:'/album/updateAlbum.jspa',
		dataType:'json',
		 data:({"albumId":albumId,
			 	"name":name,
			 	"content":content
		      }),  
		success:function(json){
			if(json.flag == 0){
				location.reload();
			}else{
				alert(json.msg);
			}
		}
	});
}

/**
 * 删除相册
 */
function deleteAlbum(albumId){
	$.ajax({
		type:'POST',
		url:'/album/deleteAlbum.jspa',
		dataType:'json',
		 data:({"albumId":albumId
		      }),  
		success:function(json){
			if(json.flag == 0){
				location.reload();
			}else{
				alert(json.msg);
			}
		}
	});
}

/**
 * 创建相册
 */
function createAlbum(){
	var name = $("#albumName").val();
	var content = $("#albumContent").val();
	if(name==""){
		alert("请输入专辑名称！");
		$("#albumName").focus();
		return;
	}
	if(name.length>15){
		alert("专辑名称不能大于15个字！");
		$("#albumName").focus();
		return;
	}
	if(content.length>150){
		alert("专辑描述不能大于150个字！");
		$("#albumContent").focus();
		return;
	}
	$.ajax({
		type:'POST',
		url:'/album/createAlbum.jspa',
		dataType:'json',
		 data:({"name":name,
			 	"content":content
		      }),  
		success:function(json){
			if(json.flag == 0){
				$("#albumSuccessName").html(json.data.name);
				hide_CreateAlbums();
				show_CreateAlbums_ok();
			}else{
				alert(json.msg);
			}
		}
	});
}