<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="cancel_friend_box" style="display:none;">
  <input type="hidden" id="cancelUserId" value="" />
  <div class="layer_point lay_box">
    <div class="bg">
      <h1>提示<a href="javascript:void(0);" class="span" onclick="hidecancel_friend_box()"></a></h1>
      <dl class="point clearfix">
        <dt><span class="icon_warnM"></span></dt>
        <dd>
          <p class="S_txt1">确认要取消对"<font id="cancelName"></font>"的关注吗？</p>
        </dd>
      </dl>
      <div class="btn"><a href="javascript:void(0)" class="ok" onclick="del_firendbox()">确认</a><a href="javascript:void(0)" class="cancel" onclick="hidecancel_friend_box()">取消</a></div>
    </div>
  </div>
</div>
<script type="text/javascript">
var cancelFollowBox= $("#cancel_friend_box");
cancelFollowBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function showcancel_friend_box(userId,name){
	$("#cancelName").text(name);
	$("#cancelUserId").val(userId);
	cancelFollowBox.overlay().load();
}
	
function hidecancel_friend_box(){
	var userId = $("#cancelUserId").val();
	followCancel(userId,true);
}
</script>