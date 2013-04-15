<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="del_lay" style="display:none;">
  <div class="layer_point lay_box">
  	<div class="bg">
    <h1>提示<a href="javascript:void(0);" class="span" onclick="hidedel_lay()"></a></h1>
    <dl class="point clearfix">
      <dt><span class="icon_warnM"></span></dt>
      <dd>
        <p class="S_txt1">您确定要删除这条记录吗？</p>
        <input id="userSchoolIdHidden" type="hidden" value=""/>
      </dd>
    </dl>
    <div class="btn"><a class="W_btn_d" href="javascript:void(0)" onclick="del_user_school()">确定</a><a class="cancel" href="javascript:void(0)" onclick="hidedel_lay()">取消</a></div>
    </div>
  </div>
</div>
<script type="text/javascript">

var delUserSchoolBox= $("#del_lay");
delUserSchoolBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function del_user_school(){
	var userSchoolId = $("#userSchoolIdHidden").val();
	hidedel_lay();
	deleteUserSchool(userSchoolId);
}

function showdel_lay(userSchoolId){
	delUserSchoolBox.overlay().load();
	$("#userSchoolIdHidden").val(userSchoolId);
}
	
function hidedel_lay(){
	delUserSchoolBox.overlay().close();
}
</script>