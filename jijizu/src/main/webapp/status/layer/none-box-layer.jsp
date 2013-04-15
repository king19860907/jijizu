<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_none" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_box_none()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">写点东西吧，评论内容不能为空哦。</p></dd>
            </dl>
            <div class="btn"><a class="W_btn_d" href="javascript:void(0)" onclick="hide_box_none()">确定</a></div>
        </div>
    </div>
</div>
<script language="javascript">
var box_none= $("#box_none");
box_none.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
	function show_box_none(){
		box_none.overlay().load();
	}
		
	function hide_box_none(){
		box_none.overlay().close();
	}
</script>