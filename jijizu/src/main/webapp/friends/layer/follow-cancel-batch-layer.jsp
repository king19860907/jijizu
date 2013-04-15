<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="cancel_friend_box" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hidecancel_friend_box()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确认要取消对这些用户的关注吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="follow-cancel-batch-btn" class="ok" onclick="return false;">确认</a><a href="javascript:void(0)" class="cancel" onclick="hidecancel_friend_box()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var  followCancelBatchBox= $("#cancel_friend_box");
followCancelBatchBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function showcancel_friend_box(){
	if($('#cancel_friend').hasClass("on")){
		followCancelBatchBox.overlay().load();
	}
	else{}
}
	
function hidecancel_friend_box(){
	followCancelBatchBox.overlay().close();
}

$(document).ready(function(){
	$("#follow-cancel-batch-btn").click(function(){
		var userIds = "";
		$(".mylistBox").children().each(function(){
			if($(this).hasClass("selected")){
				var userId = $(this).attr("userId");
				userIds += userId+"-";
			}
		});	
		if(userIds != ""){
			userIds = userIds.substring(0,userIds.length-1);
		}
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/follow/followCancelBatch.jspa',
			data:({"userIds":userIds
			      }), 
			success:function(data){
				if(data.flag==0){
					alert("取消关注成功");
					location.reload();
				}else{
					alert(data.msg);
				}
			}
		});
	});
});
</script>