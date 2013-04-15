<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="box_sixin_new" style="display:none;">
	<div class="W_private_letter_box lay_box">
        <div class="bg">
        	<h1>发私信<a href="javascript:void(0);" class="span" onclick="hide_box_sixin_new()"></a></h1>
            <div class="W_private_letter">
                <dl>
                    <dt>发  给：</dt>
                    <dd><input type="text" class="text" name="mail-nickName" id="nickName" value="请输入对方昵称" onfocus="input_focus()" onblur="input_blur()"></dd>
                </dl>
                <dl>
                    <dt>内  容：</dt>
                    <dd><div  class="relative sixin_lay">
                        <textarea class="W_no_outline" onfocus="textarea_focus()" onblur="textarea_blur()" id="content">请输入内容，最多300个字</textarea></div><div id="publisherTop_list2" class="publisherTop_list">
                      </div></dd>
                </dl>
                <div class="btn"><a href="javascript:void(0)" class="send">发送</a><a href="javascript:void(0)" class="cancel" onclick="hide_box_sixin_new()">取消</a></div>
            </div>
        </div>
    </div>
</div>
<script language="javascript">
$(document).ready(function(){
	prepareMailAutoComplete($(this).find("[name='mail-nickName']"));
});
function show_box_sixin_new(nickName){
	$('#nickName').val(nickName);
	create_mail_box.overlay().load();
}
	
function hide_box_sixin_new(){
	create_mail_box.overlay().close();
}
var create_mail_box=$("#box_sixin_new");
create_mail_box.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
$(document).ready(function(){
	$(".send").click(function(){
		var nickName = $("#nickName").val();
		var content = $("#content").val();
		if(nickName == '请输入对方昵称'){
			alert('请输入对方昵称');
			$('#nickName').focus();
			return;
		}
		if(content == '请输入内容，最多300个字'){
			alert('请输入内容');
			$('#content').focus();
			return;
		}
		if(content.length>300){
			alert('内容大于300字');
			$('#content').focus();
			return;
		}
		$(".send").unbind("click");
		sendMail(nickName,content);
	});
});
</script>