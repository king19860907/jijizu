<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="diary-delete-layer" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	 <input id="groupId" type="hidden" name="groupId" value="">
        	<h1>提示<a href="javascript:void(0);" class="span"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确定删除该条记录？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" id="delete-diary-btn" class="ok">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_delete_diary_layer()">取消</a></div>
        </div>
    </div>
</div>
<script type="text/javascript">
$("#diary-delete-layer").overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_delete_diary_layer(diaryId){
	$("#diary-delete-layer").overlay().load();
	$("#delete-diary-btn").attr("diaryId",diaryId);
}
	
function hide_delete_diary_layer(){
	$("#diary-delete-layer").overlay().close();
}
</script>