<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_upload_album2" style="display:none;">
	
</div>
<script type="text/javascript">
function show_upload_album2(page,albumId,groupId){
	$.ajax({
		type:'POST',
		url:'/group/getPhoto.jspa',
		 data:({"page":page,
			 	"albumId":albumId,
			 	"groupId":groupId
		      }),  
		success:function(json){
			$("#box_upload_img").html(json);
		}
	});
}
	
</script>