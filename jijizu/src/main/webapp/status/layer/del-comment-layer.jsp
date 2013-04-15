<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_comment_del" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_comment_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确认要删除这条评论吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" class="ok" id="confirm_del_comment">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_comment_del()">取消</a></div>
        </div>
    </div>
</div>
<script language="javascript">
var del_comment_box= $("#box_comment_del");
del_comment_box.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
	$("#confirm_del_comment").click(function(){
		var commentId = $(this).attr("commentId");
		deleteComment(commentId,"#comment_list_"+commentId);
	});
	function show_comment_del(){
		del_comment_box.overlay().load();
	}
		
	function hide_comment_del(){
		del_comment_box.overlay().close();
	}
</script>