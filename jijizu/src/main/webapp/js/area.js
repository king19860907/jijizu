var area={
	areaData: null,
    first:document.getElementById("first"),  //一级下拉框
    second:document.getElementById("second"),//二级下拉框	
    third:document.getElementById("third"),  //三级下拉框
    curFirst:"",
	curSecond:"", 
	curThird:"",
	init:function(){
		$.ajax({
		   type: "POST",
		   url: "/user/getAreaData.jspa",
		   async: false,      //ajax同步
		   dataType : 'json',
		   success: function(json){
			   area.areaData = json;
			   area.first = document.getElementById("first");
			   area.second = document.getElementById("second");
			   area.third = document.getElementById("third");
			   area.onInit();
			   if(typeof(area2) != "undefined"){
					 area2.init();
					 area2.firstChange();
			   }
			   if(typeof(area3) != "undefined"){
					 area3.init();
					 area3.firstChange();
			   }
		   }
		})
	},
	onInit:function(){
		/****一级添加开始****/
		area.addFirst();
		area.addSecond();  //添加当前省份的城市
		area.addThird();  //添加当前城市的区域
		area.first.onchange=area.firstChange; //绑定一级变化事件
		area.second.onchange=area.secondChange;//绑定二级变化事件
	},
	
    addFirst:function(){
    	SelectUtil.deleteAll(area.first);//删除所有的省
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area.first.appendChild(opt);			
		 
		var province = area.areaData.PROVINCE;
		for(var _int = 0 ;_int< province.length; _int ++){
		   var opt=document.createElement("option");
			opt.setAttribute("value",province[_int].areaId);
			opt.appendChild(document.createTextNode(province[_int].areaName));
		    area.first.appendChild(opt);	
		}
		var flag=true;
		for(var i=0;i<area.first.options.length;i++){
	 		if(area.first.options[i].value == area.curFirst){
		 		area.first.options[i].setAttribute("selected",true);//selected=true;
		 		flag=false;
		 		break;			
		 	}
	 	}
		if(flag){
			for(var i=0;i<area.first.options.length;i++){
		 		if(area.first.options[i].text == area.curFirst){
			 		area.first.options[i].setAttribute("selected",true);//selected=true;
			 		break;			
			 	}
		 	}
		}
   },
	//一级变化
   firstChange:function(){	
        area.curFirst = getValueOfSelect("first");
		SelectUtil.deleteAll(area.second);//删除所有城市
		SelectUtil.deleteAll(area.third);//删除所有区域		 
		area.addSecond();   //增加选择省份下的城市 
		area.addThird(); //增加选择城市下的区域
	},
	//二级变化
	 secondChange:function(){	
	    area.curSecond = getValueOfSelect("second");
		SelectUtil.deleteAll(area.third);		
		area.addThird();
	},
	//二级添加
	 addSecond:function(){
		SelectUtil.deleteAll(area.second);//删除所有城市
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area.second.appendChild(opt);			
		var city = area.areaData.CITY;
		for(var _int = 0 ;_int< city.length; _int ++){
			if(area.curFirst == city[_int].fatherId && city[_int].areaName!='所有县市'){
				var opt=document.createElement("option");
				opt.setAttribute("value",city[_int].areaId);
				opt.appendChild(document.createTextNode(city[_int].areaName));
			    area.second.appendChild(opt);	
			}
		}
		
		var flag=true;
		for(var i=0;i<area.second.options.length;i++){
	 		if(area.second.options[i].value == area.curSecond){
		 		area.second.options[i].setAttribute("selected",true);//selected=true;
		 		flag=false;
		 		break;			
		 	}
	 	}
		if(flag){
			for(var i=0;i<area.second.options.length;i++){
		 		if(area.second.options[i].text == area.curSecond){
			 		area.second.options[i].setAttribute("selected",true);//selected=true;	 
			 		break;			
			 	}
		 	}
	 	}
	},
	
	addThird:function(){	
		SelectUtil.deleteAll(area.third);//删除所有区域	
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area.third.appendChild(opt);			
		var district = area.areaData.DISTRICT; 
		for(var _int = 0 ;_int< district.length; _int ++){
			if(area.curSecond == district[_int].fatherId){
			    var opt=document.createElement("option");
				opt.setAttribute("value",district[_int].areaId);
				opt.appendChild(document.createTextNode(district[_int].areaName));
			    area.third.appendChild(opt);	
			}
		}
		for(var i=0;i<area.third.options.length;i++){
	 		if(area.third.options[i].value == area.curThird){
		 		area.third.options[i].setAttribute("selected",true);//selected=true;	 
		 		break;			
		 	}
	 	} 
	},
	
	getThirdAreaId:function(){
		return document.getElementsByName('third')[0].value; 
	},
	
	//根据第三目录修改3级目录
	changeByThirdId:function(districtId){
	     if(districtId<=0){
		      area.curSecond= ""; 
			  area.curFirst = ""; 
			  area.curThird = "";
			  setValueOfSelect("first","");
			  setValueOfSelect("second","");
			  setValueOfSelect("third","");
			  return ;
	     }else{
	        area.curThird = districtId;
            area.curSecond= area.getSecondIdByThirdId(districtId); 
		    area.curFirst = area.getFirstIdBySecondId(area.curSecond); 
	     }
	 	 area.onInit();
	},
	//根据第3级别 找到第二级别的Id
	getSecondIdByThirdId:function(thirdId){
		var Third = area.areaData.DISTRICT;
		for(var _int = 0 ;_int< Third.length; _int ++){
			if(thirdId == Third[_int].areaId){
			    return Third[_int].fatherId;
			}
		}
		 
	} ,
	//根据第2级别 找到第一级别的
	getFirstIdBySecondId:function(secondId){
		var Second = area.areaData.CITY;
		for(var _int = 0 ;_int< Second.length; _int ++){
			if(secondId == Second[_int].areaId){
			    return Second[_int].fatherId;
			}
		}
	}
}
