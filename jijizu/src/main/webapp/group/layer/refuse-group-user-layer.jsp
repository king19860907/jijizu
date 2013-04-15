<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box-refuse-group-user-layer" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	 <input id="groupId" type="hidden" name="groupId" value="">
        	<h1>提示<a href="javascript:void(0);" class="span"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定拒绝该用户的参加申请？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="refuse-group-user-btn" class="ok">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_refuse_layer()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var refuseGroupUserBox= $("#box-refuse-group-user-layer");
refuseGroupUserBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_refuse_layer(groupId,userId){
	refuseGroupUserBox.overlay().load();
	$("#refuse-group-user-btn").attr("groupId",groupId);
	$("#refuse-group-user-btn").attr("userId",userId);
}
	
function hide_refuse_layer(){
	refuseGroupUserBox.overlay().close();
}
</script>