<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_upload_album" style="display:none;">
	
</div>
<script type="text/javascript">
function show_box_upload_album(page,groupId){
	$.ajax({
		type:'POST',
		url:'/group/getUserAlbum.jspa',
		 data:({"page":page,
			 	"groupId":groupId
		      }),  
		success:function(json){
			//hide_box_upload_img();
			//hide_upload_album2();
			$("#box_upload_img").html(json);
			//show_box_upload_img();
		}
	});
}
	
</script>