<%@page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<div id="schSearchLayer" style="display:none;">
  <div class="schSearchLayer lay_box">
  	<div class="bg">
        <h1>提示<a href="javascript:void(0);" class="span" onclick="hideschSearchLayer()"></a></h1>
        <div class="sch_letter clearFix" node-type="sch_nameArea">
            <p style="position:relative">
                快速搜索<input type="text" id="school_name"  class="txt" style="width: 180px; background: rgb(249, 251, 240);"/><span style="display:none;" class="intromsg" node-type="searchTitle">请输入并点选你要找的学校信息</span>
            </p>
            <div class="sch_form">学校所在地
            <select name="province" id="first3">
            </select>
            <select name="city" id="second3">
            </select>
            <select name="district" id="third3" onchange="$('.on').removeClass('on');findSchool();" class="last">
            </select>
            </div>
        </div>
        <div class="sch_letter clearFix">
            <ul>
                <li><a title="a" name="first_letter" href="javascript:void(0);">A</a></li>
                <li><a title="b" name="first_letter" href="javascript:void(0);">B</a></li>
                <li><a title="c" name="first_letter" href="javascript:void(0);">C</a></li>
                <li><a title="d" name="first_letter" href="javascript:void(0);">D</a></li>
                <li><a title="e" name="first_letter" href="javascript:void(0);">E</a></li>
                <li><a title="f" name="first_letter" href="javascript:void(0);">F</a></li>
                <li><a title="g" name="first_letter" href="javascript:void(0);">G</a></li>
                <li><a title="h" name="first_letter" href="javascript:void(0);">H</a></li>
                <li><a title="i" name="first_letter" href="javascript:void(0);">I</a></li>
                <li><a title="j" name="first_letter" href="javascript:void(0);">J</a></li>
                <li><a title="k" name="first_letter" href="javascript:void(0);">K</a></li>
                <li><a title="l" name="first_letter" href="javascript:void(0);">L</a></li>
                <li><a title="m" name="first_letter" href="javascript:void(0);">M</a></li>
                <li><a title="n" name="first_letter" href="javascript:void(0);">N</a></li>
                <li><a title="o" name="first_letter" href="javascript:void(0);">O</a></li>
                <li><a title="p" name="first_letter" href="javascript:void(0);">P</a></li>
                <li><a title="q" name="first_letter" href="javascript:void(0);">Q</a></li>
                <li><a title="r" name="first_letter" href="javascript:void(0);">R</a></li>
                <li><a title="s" name="first_letter" href="javascript:void(0);">S</a></li>
                <li><a title="t" name="first_letter" href="javascript:void(0);">T</a></li>
                <li><a title="u" name="first_letter" href="javascript:void(0);">U</a></li>
                <li><a title="v" name="first_letter" href="javascript:void(0);">V</a></li>
                <li><a title="w" name="first_letter" href="javascript:void(0);">W</a></li>
                <li><a title="x" name="first_letter" href="javascript:void(0);">X</a></li>
                <li><a title="y" name="first_letter" href="javascript:void(0);">Y</a></li>
                <li><a title="z" name="first_letter" href="javascript:void(0);">Z</a></li>
            </ul>
        </div>
        <div class="sch_list clearFix" id="school-list-div">
            
        </div>
        <div class="btn"><a href="javascript:void(0)" class="cancel" onclick="hideschSearchLayer()">关闭</a></div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${JS_PATH}/area3.js${JS_EDITION}"></script>
<script type="text/javascript">
$(document).ready(function() {
	area3.curFirst = 1;
	area3.curSecond = 2;
	area3.curThird = 204;

	$("#school_name").autocomplete("/user/findSchoolNames.jspa",
			{
				extraParams: { type: function() { return $("#school_type").find("option:selected").val();}}
			})
	.result(function(event, row, formatted) {
		chooseSchool(row[1],row[0]);
	});
	
	$("#school_type").change(function(){
		clearSchooInfo();
	});
	

	$("#schoolName").autocomplete("/user/findSchoolNames.jspa",
			{
				extraParams: { type: function() { return $("#school_type").find("option:selected").val();}}
			})
	.result(function(event, row, formatted) {
		$("#schoolId").val(row[1]);
	});
	
	$("#schoolName").keydown(function(){
		$("#schoolId").val("");
	});
});
var findSchoolBox= $("#schSearchLayer");
findSchoolBox.overlay({closeOnClick:false, mask:{color:'#000', opacity:0.5}});
function showschSearchLayer(){
	findSchoolBox.overlay().load();
	findSchool();
}
	
function hideschSearchLayer(){
	findSchoolBox.overlay().close();
}

function changeSchoolType(){
	var schoolType = $("#school_type").find("option:selected").val();
	if(schoolType=='SCHOOL_TYPE_UNIVERSITY'){
		$("#first3").change(function(){
			$('.on').removeClass('on');findSchool();
		});
	}else{
		$("#first3").unbind("change");
		
	}
	
	if(schoolType == 'SCHOOL_TYPE_PERSCHOOL' ||
			schoolType == 'SCHOOL_TYPE_KINDERGARTEN' ||
			schoolType == 'SCHOOL_TYPE_CRECHE'){
		$("#schoolName").unbind("click");
	}else{
		$("#schoolName").unbind("click");
		$("#schoolName").click(function(){
			showschSearchLayer();
		});
	}
	
}

function clearSchooInfo(){
	$("#schoolId").val("");
	$("#schoolName").val("");
	$("#department").val("");
}

function findSchool(){
	var district=$("#third3").find("option:selected").text();
	var schoolType = $("#school_type").find("option:selected").val();
	var firstLetter = $(".on").attr("title");
	if(typeof(firstLetter) == "undefined"){
		firstLetter = "";
	}
	if(schoolType=='SCHOOL_TYPE_UNIVERSITY'){
		district=$("#first3").find("option:selected").text();
		$("#second3").hide();
		$("#third3").hide();
	}else{
		$("#second3").show();
		$("#third3").show();
	}
	$.ajax({
		type:'POST',
		url:'/user/findSchoolInfo.jspa',
		data:({"areaName":district,"type":schoolType,"firstLetter":firstLetter
	      }),
		success:function(data){
			$("#school-list-div").html(data);
		}
	});
}

</script>