<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_sixin_del" style="display:none;">
	<div class="layer_point lay_box">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_sixin_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1">确认要删除这条微博吗？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" class="ok" id="confirm_del_status">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_sixin_del()">取消</a></div>
        </div>
    </div>
</div>
<script language="javascript">
var del_status_box= $("#box_sixin_del");
del_status_box.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
	function show_sixin_del(){
		del_status_box.overlay().load();
	}
		
	function hide_sixin_del(){
		del_status_box.overlay().close();
	}
</script>