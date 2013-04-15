<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_Cancel" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	 <input id="groupId" type="hidden" name="groupId" value="">
        	<h1>提示<a href="javascript:void(0);" class="span"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定不去该活动了？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="cancel-enter-group-btn" class="ok" onclick="hide_Cancel()">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_Cancel()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
var cancelEnterGroupBox= $("#box_Cancel");
cancelEnterGroupBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_Cancel(groupId){
	cancelEnterGroupBox.overlay().load();
	$("#cancel-enter-group-btn").attr("groupId",groupId);
}
	
function hide_Cancel(){
	cancelEnterGroupBox.overlay().close();
}
</script>