<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="del_notice_box" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hidedel_notice_box()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确认要删除该条系统通知吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="del-notice-btn" class="ok" onclick="return false;">确认</a><a href="javascript:void(0)" class="cancel" onclick="hidedel_notice_box()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var  delNoticeBox= $("#del_notice_box");
delNoticeBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function showdel_notice_box(noticeId){
	$("#del-notice-btn").attr("noticeId",noticeId);
	delNoticeBox.overlay().load();
}
	
function hidedel_notice_box(){
	delNoticeBox.overlay().close();
}

$(document).ready(function(){
	
	$("#del-notice-btn").click(function(){
		var noticeId = $(this).attr("noticeid");
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/notice/deleteNotice.jspa',
			data:({"noticeId":noticeId
			      }), 
			success:function(data){
				if(data.flag==0){
					hidedel_notice_box();
					$("#notice_"+noticeId).remove();
				}else{
					alert(data.msg);
				}
			}
		});
	});
	
});
</script>