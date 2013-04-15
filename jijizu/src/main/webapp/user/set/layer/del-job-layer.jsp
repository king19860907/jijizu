<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="del_lay" style="display:none;">
  <div class="layer_point lay_box">
  	<input type="hidden" id="jobIdHidden" value=""/>
  	<div class="bg">
    <h1>提示<a href="javascript:void(0);" class="span" onclick="hidedel_lay()"></a></h1>
    <dl class="point clearfix">
      <dt><span class="icon_warnM"></span></dt>
      <dd>
        <p class="S_txt1">您确定要删除这条记录吗？</p>
      </dd>
    </dl>
    <div class="btn"><a class="W_btn_d" href="javascript:void(0)" onclick="del_job()">确定</a><a class="cancel" href="javascript:void(0)" onclick="hidedel_lay()">取消</a></div>
    </div>
  </div>
</div>
<script type="text/javascript">
var del_job_box= $("#del_lay");
del_job_box.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function del_job(){
	var jobId = $("#jobIdHidden").val();
	hidedel_lay();
	deleteUserJob(jobId);
}
function showdel_lay(jobId){
	del_job_box.overlay().load();
	$("#jobIdHidden").val(jobId);
}
	
function hidedel_lay(){
	del_job_box.overlay().close();
}

</script>