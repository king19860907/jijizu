<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box-delete-layer" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	 <input id="groupId" type="hidden" name="groupId" value="">
        	<h1>提示<a href="javascript:void(0);" class="span"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定删除该集集组？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="delete-group-btn" class="ok" onclick="hide_Cancel()">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_delete_layer()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
$("#box-delete-layer").overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_delete_layer(groupId){
	$("#box-delete-layer").overlay().load();
	$("#delete-group-btn").attr("groupId",groupId);
}
	
function hide_delete_layer(){
	$("#box-delete-layer").overlay().close();
}
</script>