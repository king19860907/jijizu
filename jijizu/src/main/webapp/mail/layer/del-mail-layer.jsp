<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_sixin_del" style="display:none;">
	<div class="layer_point lay_box">
		<input type="hidden" id="mailShowId" value="">
        <div class="bg">
        	<h1>提示<a href="javascript:void(0);" class="span" onclick="hide_sixin_del()"></a></h1>
            <dl class="point clearfix">
                <dt><span class="icon_warnM"></span></dt>
                <dd><p class="S_txt1" id="del-content">确定要删除所有和 陈平 之间的所有私信？</p></dd>
            </dl>
            <div class="btn"><a href="javascript:void(0)" class="ok" onclick="sixin_del()">确认</a><a href="javascript:void(0)" class="cancel" onclick="hide_sixin_del()">取消</a></div>
        </div>
    </div>
</div>
<script language="javascript">
var del_mail_box= $("#box_sixin_del");
del_mail_box.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_sixin_del(name,mailShowId){
	$("#mailShowId").val(mailShowId);
	$("#del-content").html("确定要删除所有和 "+name+"之间的所有私信？");
	del_mail_box.overlay().load();
}

function sixin_del(){
	var mailShowId = $("#mailShowId").val();
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/mail/deleteMailShow.jspa',
		data:({"mailShowId":mailShowId
		      }), 
		success:function(data){
			if(data.flag==0){
				window.location.reload();
			}else{
				alert(data.msg);
			}
		}
	});
}
	
function hide_sixin_del(){
	del_mail_box.overlay().close();
}
</script>