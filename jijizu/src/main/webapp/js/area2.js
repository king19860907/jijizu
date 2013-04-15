var area2={
	areaData: null,
    first:document.getElementById("first2"),  //一级下拉框
    second:document.getElementById("second2"),//二级下拉框	
    third:document.getElementById("third2"),  //三级下拉框
    curFirst:"",
	curSecond:"", 
	curThird:"",
	init:function(){
			   area2.areaData = area.areaData;
			   area2.first = document.getElementById("first2");
			   area2.second = document.getElementById("second2");
			   area2.third = document.getElementById("third2");
			   area2.onInit();
		
	},
	onInit:function(){
		/****一级添加开始****/
		area2.addFirst();
		area2.addSecond();  //添加当前省份的城市
		area2.addThird();  //添加当前城市的区域
		area2.first.onchange=area2.firstChange; //绑定一级变化事件
		area2.second.onchange=area2.secondChange;//绑定二级变化事件
	},
	
    addFirst:function(){
    	SelectUtil.deleteAll(area2.first);//删除所有的省
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area2.first.appendChild(opt);			
		 
		var province = area2.areaData.PROVINCE;
		for(var _int = 0 ;_int< province.length; _int ++){
		   var opt=document.createElement("option");
			opt.setAttribute("value",province[_int].areaId);
			opt.appendChild(document.createTextNode(province[_int].areaName));
		    area2.first.appendChild(opt);	
		}
		var flag=true;
		for(var i=0;i<area2.first.options.length;i++){
	 		if(area2.first.options[i].value == area2.curFirst){
		 		area2.first.options[i].setAttribute("selected",true);//selected=true;
		 		flag=false;
		 		break;			
		 	}
	 	}
		if(flag){
			for(var i=0;i<area2.first.options.length;i++){
		 		if(area2.first.options[i].text == area2.curFirst){
			 		area2.first.options[i].setAttribute("selected",true);//selected=true;
			 		break;			
			 	}
		 	}
		}
   },
	//一级变化
   firstChange:function(){	
        area2.curFirst = getValueOfSelect("first2");
		SelectUtil.deleteAll(area2.second);//删除所有城市
		SelectUtil.deleteAll(area2.third);//删除所有区域		 
		area2.addSecond();   //增加选择省份下的城市 
		area2.addThird(); //增加选择城市下的区域
	},
	//二级变化
	 secondChange:function(){	
	    area2.curSecond = getValueOfSelect("second2");
		SelectUtil.deleteAll(area2.third);		
		area2.addThird();
	},
	//二级添加
	 addSecond:function(){
		SelectUtil.deleteAll(area2.second);//删除所有城市
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area2.second.appendChild(opt);			
		var city = area2.areaData.CITY;
		for(var _int = 0 ;_int< city.length; _int ++){
			if(area2.curFirst == city[_int].fatherId && city[_int].areaName!='所有县市'){
				var opt=document.createElement("option");
				opt.setAttribute("value",city[_int].areaId);
				opt.appendChild(document.createTextNode(city[_int].areaName));
			    area2.second.appendChild(opt);	
			}
		}
		
		var flag=true;
		for(var i=0;i<area2.second.options.length;i++){
	 		if(area2.second.options[i].value == area2.curSecond){
		 		area2.second.options[i].setAttribute("selected",true);//selected=true;
		 		flag=false;
		 		break;			
		 	}
	 	}
		if(flag){
			for(var i=0;i<area2.second.options.length;i++){
		 		if(area2.second.options[i].text == area2.curSecond){
			 		area2.second.options[i].setAttribute("selected",true);//selected=true;	 
			 		break;			
			 	}
		 	}
	 	}
	},
	
	addThird:function(){	
		SelectUtil.deleteAll(area2.third);//删除所有区域	
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area2.third.appendChild(opt);			
		var district = area2.areaData.DISTRICT; 
		for(var _int = 0 ;_int< district.length; _int ++){
			if(area2.curSecond == district[_int].fatherId){
			    var opt=document.createElement("option");
				opt.setAttribute("value",district[_int].areaId);
				opt.appendChild(document.createTextNode(district[_int].areaName));
			    area2.third.appendChild(opt);	
			}
		}
		for(var i=0;i<area2.third.options.length;i++){
	 		if(area2.third.options[i].value == area2.curThird){
		 		area2.third.options[i].setAttribute("selected",true);//selected=true;	 
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
		      area2.curSecond= ""; 
			  area2.curFirst = ""; 
			  area2.curThird = "";
			  setValueOfSelect("first2","");
			  setValueOfSelect("second2","");
			  setValueOfSelect("third2","");
			  return ;
	     }else{
	        area2.curThird = districtId;
            area2.curSecond= area2.getSecondIdByThirdId(districtId); 
		    area2.curFirst = area2.getFirstIdBySecondId(area2.curSecond); 
	     }
	 	 area2.onInit();
	},
	//根据第3级别 找到第二级别的Id
	getSecondIdByThirdId:function(thirdId){
		var Third = area2.areaData.DISTRICT;
		for(var _int = 0 ;_int< Third.length; _int ++){
			if(thirdId == Third[_int].areaId){
			    return Third[_int].fatherId;
			}
		}
		 
	} ,
	//根据第2级别 找到第一级别的
	getFirstIdBySecondId:function(secondId){
		var Second = area2.areaData.CITY;
		for(var _int = 0 ;_int< Second.length; _int ++){
			if(secondId == Second[_int].areaId){
			    return Second[_int].fatherId;
			}
		}
	}
}
