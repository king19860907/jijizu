$(document).ready(function(){
	
	$("[name='followUserMail']").click(function(){
		var userId = $(this).attr("userId");
		var noticeId= $(this).parent().attr("noticeId");
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/follow/followUser.jspa',
			data:({"userId":userId
			      }), 
			success:function(data){
				if(data.flag==0){
					alert(data.msg);
					$.ajax({
						type:'POST',
						dataType:'json',
						url:'/notice/updateNoticeContentAfterFollow.jspa',
						data:({"userId":userId,"noticeId":noticeId
						      }), 
						success:function(data){
							if(data.flag==0){
								window.location.reload();
							}else{
								alert(data.msg);
							}
						}
					});
				}else{
					alert(data.msg);
				}
			}
		});
	});
	
});