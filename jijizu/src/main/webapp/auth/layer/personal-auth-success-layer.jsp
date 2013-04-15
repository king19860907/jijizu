<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="medal" style="display:none;">
  <div class="layer_point ly9 lay_box">
    <div class="bg">
      <h1>提示<a href="javascript:void(0);" class="span" onclick="hide_medal()"></a></h1>
      <dl class="apply_form_dl">
      	<dt>&nbsp;</dt>
        <dd>您已成功提交申请<br />我们将于5个工作日内以私信的方式给您回复</dd>
      </dl>
    </div>
  </div>
</div>
<script type="text/javascript">
var personalAuthSuccessBox= $("#medal");
personalAuthSuccessBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_medal(){
	personalAuthSuccessBox.overlay().load();
}
	
function hide_medal(){
	personalAuthSuccessBox.overlay().close();
	location.href="/home.jspa";
}

</script>