$(document).ready(function(){
	$("[name='followUserMail']").click(function(){
		var userId = $(this).attr("userid");
		var mailShowId = $(this).parent().attr("mailshowid");
		var mailDetailId = $(this).parent().attr("maildetailid");
		if(userId == null){
			return ;
		}
		if(mailShowId == null && mailDetailId == null){
			return;
		}
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
						url:'/mail/updateMailContentAfterFollow.jspa',
						data:({"userId":userId,"mailShowId":mailShowId,"mailDetailId":mailDetailId
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


function prepareMailAutoComplete(selector){
	selector.autocomplete("/follow/findAtFirendsJson.jspa", {formatItem: function(row, i, max) {
		return "<img width='30' height='30' src='"+row[1]+"'/><font name='at-user-name'>"+row[0]+"</font>";
	}});
}

function sendMail(nickName,content){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/mail/sendMail.jspa',
		data:({"nickName":nickName,"content":content
		      }), 
		success:function(data){
			if(data.flag==0){
				window.location.reload();
			}else{
				alert(data.msg);
			}
		}
	});
}

/**
 * 加载回复表情
 * @param selector
 * @return
 */
function prepareMailFace(selector){
	selector.click(function(){
		var p=$(this).offset();
		var $left = p.left - 10 + "px";
		var $top = p.top + 30 + "px";
		if($(".layoutContent").is(":visible")==true && ($(".layoutContent").attr("type") == "mail")){
			$(".layoutContent").hide();
		}else{
			$(".layoutContent").show();
		}
		$(".layoutContent").attr("type","mail").css("left",$left).css("top",$top);
		//var commentId = $(this).attr("commentId");
		$("[name='face_btn']").attr("areaType","mail");
	});
}