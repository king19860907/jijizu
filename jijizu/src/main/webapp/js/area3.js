var area3={
	areaData: null,
    first:document.getElementById("first3"),  //一级下拉框
    second:document.getElementById("second3"),//二级下拉框	
    third:document.getElementById("third3"),  //三级下拉框
    curFirst:"",
	curSecond:"", 
	curThird:"",
	init:function(){
		if(area.areaData != null){
			   area3.areaData = area.areaData;
			   area3.first = document.getElementById("first3");
			   area3.second = document.getElementById("second3");
			   area3.third = document.getElementById("third3");
			   area3.onInit();
		}else{
			$.ajax({
				   type: "POST",
				   url: "/user/getAreaData.jspa",
				   async: false,      //ajax同步
				   dataType : 'json',
				   success: function(json){
					   area3.areaData = json;
					   area3.first = document.getElementById("first3");
					   area3.second = document.getElementById("second3");
					   area3.third = document.getElementById("third3");
					   area3.onInit();
				   }
				})
		}
	},
	onInit:function(){
		/****一级添加开始****/
		area3.addFirst();
		area3.addSecond();  //添加当前省份的城市
		area3.addThird();  //添加当前城市的区域
		area3.first.onchange=area3.firstChange; //绑定一级变化事件
		area3.second.onchange=area3.secondChange;//绑定二级变化事件
	},
	
    addFirst:function(){
    	SelectUtil.deleteAll(area3.first);//删除所有的省
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area3.first.appendChild(opt);			
		 
		var province = area3.areaData.PROVINCE;
		for(var _int = 0 ;_int< province.length; _int ++){
		   var opt=document.createElement("option");
			opt.setAttribute("value",province[_int].areaId);
			opt.appendChild(document.createTextNode(province[_int].areaName));
		    area3.first.appendChild(opt);	
		}
		var flag=true;
		for(var i=0;i<area3.first.options.length;i++){
	 		if(area3.first.options[i].value == area3.curFirst){
		 		area3.first.options[i].setAttribute("selected",true);//selected=true;
		 		flag=false;
		 		break;			
		 	}
	 	}
		if(flag){
			for(var i=0;i<area3.first.options.length;i++){
		 		if(area3.first.options[i].text == area3.curFirst){
			 		area3.first.options[i].setAttribute("selected",true);//selected=true;
			 		break;			
			 	}
		 	}
		}
   },
	//一级变化
   firstChange:function(){	
        area3.curFirst = getValueOfSelect("first3");
		SelectUtil.deleteAll(area3.second);//删除所有城市
		SelectUtil.deleteAll(area3.third);//删除所有区域		 
		area3.addSecond();   //增加选择省份下的城市 
		area3.addThird(); //增加选择城市下的区域
	},
	//二级变化
	 secondChange:function(){	
	    area3.curSecond = getValueOfSelect("second3");
		SelectUtil.deleteAll(area3.third);		
		area3.addThird();
	},
	//二级添加
	 addSecond:function(){
		SelectUtil.deleteAll(area3.second);//删除所有城市
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area3.second.appendChild(opt);			
		var city = area3.areaData.CITY;
		for(var _int = 0 ;_int< city.length; _int ++){
			if(area3.curFirst == city[_int].fatherId && city[_int].areaName!='所有县市'){
				var opt=document.createElement("option");
				opt.setAttribute("value",city[_int].areaId);
				opt.appendChild(document.createTextNode(city[_int].areaName));
			    area3.second.appendChild(opt);	
			}
		}
		
		var flag=true;
		for(var i=0;i<area3.second.options.length;i++){
	 		if(area3.second.options[i].value == area3.curSecond){
		 		area3.second.options[i].setAttribute("selected",true);//selected=true;
		 		flag=false;
		 		break;			
		 	}
	 	}
		if(flag){
			for(var i=0;i<area3.second.options.length;i++){
		 		if(area3.second.options[i].text == area3.curSecond){
			 		area3.second.options[i].setAttribute("selected",true);//selected=true;	 
			 		break;			
			 	}
		 	}
	 	}
	},
	
	addThird:function(){	
		SelectUtil.deleteAll(area3.third);//删除所有区域	
		var opt=document.createElement("option");
		opt.setAttribute("value","");
		opt.appendChild(document.createTextNode("请选择..."));
	    area3.third.appendChild(opt);			
		var district = area3.areaData.DISTRICT; 
		for(var _int = 0 ;_int< district.length; _int ++){
			if(area3.curSecond == district[_int].fatherId){
			    var opt=document.createElement("option");
				opt.setAttribute("value",district[_int].areaId);
				opt.appendChild(document.createTextNode(district[_int].areaName));
			    area3.third.appendChild(opt);	
			}
		}
		for(var i=0;i<area3.third.options.length;i++){
	 		if(area3.third.options[i].value == area3.curThird){
		 		area3.third.options[i].setAttribute("selected",true);//selected=true;	 
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
		      area3.curSecond= ""; 
			  area3.curFirst = ""; 
			  area3.curThird = "";
			  setValueOfSelect("first3","");
			  setValueOfSelect("second3","");
			  setValueOfSelect("third3","");
			  return ;
	     }else{
	        area3.curThird = districtId;
            area3.curSecond= area3.getSecondIdByThirdId(districtId); 
		    area3.curFirst = area3.getFirstIdBySecondId(area3.curSecond); 
	     }
	 	 area3.onInit();
	},
	//根据第3级别 找到第二级别的Id
	getSecondIdByThirdId:function(thirdId){
		var Third = area3.areaData.DISTRICT;
		for(var _int = 0 ;_int< Third.length; _int ++){
			if(thirdId == Third[_int].areaId){
			    return Third[_int].fatherId;
			}
		}
		 
	} ,
	//根据第2级别 找到第一级别的
	getFirstIdBySecondId:function(secondId){
		var Second = area3.areaData.CITY;
		for(var _int = 0 ;_int< Second.length; _int ++){
			if(secondId == Second[_int].areaId){
			    return Second[_int].fatherId;
			}
		}
	}
}
