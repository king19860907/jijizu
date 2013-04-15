$(document).ready(function(){
	
	/**
	 * 搜索好友
	 */
	$("#gn_search_btn").click(function(){
		var name = $("#search_friend_name").val();
		if(name == "" || name=="搜索好友"){
			alert("请输入好友姓名！");
			return;
		}
		location.href="/friends/?name="+encodeURIComponent(name);
	});
	
});

function changeChildSchool(schoolId,page){
	$.ajax({
		type:'POST',
		url:'/friends/fndUserByChildSchool.jspa',
		data:({"schoolId":schoolId,"page":page
	      }), 
		success:function(json){
			var html = $(json);
			$("#child-user-div").html(html);
		}
	});
}

function changeSchool(schoolId,page){
	$.ajax({
		type:'POST',
		url:'/friends/findUserBySchool.jspa',
		data:({"schoolId":schoolId,"page":page
	      }), 
		success:function(json){
			var html = $(json);
			$("#school-user-div").html(html);
		}
	});
}

function changeCompany(companyId,page){
	$.ajax({
		type:'POST',
		url:'/friends/findUserByCompany.jspa',
		data:({"companyId":companyId,"page":page
	      }), 
		success:function(json){
			var html = $(json);
			$("#company-user-div").html(html);
		}
	});
}

function changeLivingCommunity(livingCommunityId,page){
	$.ajax({
		type:'POST',
		url:'/friends/findUserByLivingCommunity.jspa',
		data:({"livingCommunityId":livingCommunityId,"page":page
	      }), 
		success:function(json){
			var html = $(json);
			$("#living-user-div").html(html);
		}
	});
}