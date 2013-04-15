<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
  <div class="layer_point ly10 lay_box" id="join_event" style="display:none">
    <div class="bg">
      <h1>活动报名<a onclick="hide_join_event()" class="span" href="javascript:void(0);"></a></h1>
      <div class="layer1_table_title clearfix">
      	<span class="layer1_need">为了便于联系，发起人需要你填写报名信息</span>
      </div>
      <form id="enter-group-form" method="post">
      	  <input id="enterGroupId" type="hidden" name="groupId" value="">
      	  <input name="joinStatus" value="<s:property value="groupInfo.defaultJoinStatus"/>" type="hidden"/>
	      <div class="layer_table">
	          <dl class="clearfix apply">
	              <dt>您的真实姓名：</dt>	
	              <dd><s:property value="userInfo.name"/>
	              <input type="hidden" max="30" name="name" value="<s:property value="userInfo.name"/>" class="W_input" id="realname_event"  /></dd>
	          </dl>
	          <dl class="clearfix apply">
	              <dt>参与宝宝：</dt>	
	              <dd>
	              	<s:if test="childList!=null && childList.size>0">
		              	<s:iterator value="childList">
		              		<label><input checked="checked" type="checkbox" value="<s:property value="childId"/>" class="checkbbox" name="childIds"><s:property value="name"/></label>
		              	</s:iterator>
	              	</s:if>
	              	<s:else>
	              		<a href="/user/base.jspa" style="color:#0179B5">请完善孩子信息后进行报名</a>
	              	</s:else>
	              </dd>
	          </dl>
	          <dl class="clearfix apply">
	              <dt>参与大人人数：</dt>
	              <dd>
	              	<s:if test="groupInfo.parentsLimit==null">
		              	<input style="width:30px" max="15" id="adultNum" name="adultNum" value="1" class="W_input" id="phone_event"  />
	              	</s:if>
	              	<s:else>1<input type="hidden" name="adultNum" value="1"/></s:else>
	              	人
	              </dd>
	          </dl>
	          <dl class="clearfix apply">
	              <dt>联系手机：</dt>
	              <dd><input max="15" name="mobile" value="<s:property value="userInfo.mobile"/>" class="W_input" id="phone_event"  /></dd>
	          </dl>
	          <dl class="clearfix apply">
	              <dt>联系地址：</dt>
	              <dd><input max="80" name="address" value="<s:property value="userInfo.cityStr"/><s:property value="userInfo.districtStr"/>" class="W_input" id="address_event"  /></dd>
	          </dl>
	      </div>
      </form>
      <div class="layer1_commit clearfix">
          <p id="errorTip" class="layer1_error W_spetxt"><span class="icon_del"></span><span class="txt"></span></p>
          <a href="javascript:void(0);" class="W_btn_b" id="enter-group-btn">报名</a>
      </div>
    </div>
  </div>
<script type="text/javascript">
var enterGroupBox= $("#join_event");
enterGroupBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function show_join_event(groupId){
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/group/enterGroupCheck.jspa',
		data:({"groupId":groupId
		      }), 
		success:function(data){
			if(data==null){
	enterGroupBox.overlay().load();
	$("#enterGroupId").val(groupId);
	enterGroupBox.overlay().load();
			}else{
				alert(data.msg);
}
		}
	});
}
	
function hide_join_event(){
	enterGroupBox.overlay().close();
}

</script>