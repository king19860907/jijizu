//定义 城市 数据数组
var cityArray = new Array();

function initArea(type){
	$.get("/user/getAreaData.jspa", function (data) {
		if(data != null){
			var p = data.PROVINCE;
			var c = data.CITY;
			for(var i=0;i<p.length;i++){
				var cs = "";s
				for(var j=0;j<c.length;j++){
					if(p[i].areaId == c[j].fatherId){
						cityArray[i] = new Array(p[i].areaName);
						cs += c[j].areaName + "|";
					}
				}
				cityArray[i].push(cs.substring(cs.lastIndexOf("|"),""));				
			}
			initProvince(type);
		}
	},"json");
}

function initProvince(type){
	//alert(document.getElementById("m_selProvince"));
	//var p = eval('document.all.'+type+'_selProvince');
	var p = document.getElementById(type+"_selProvince");
	for (var i = 0 ;i <=cityArray.length;i++){  
		if(i == 0){
			p.options[0] = new Option("请选择","");
		}else{
			p.options[i] = new Option(cityArray[i-1][0],cityArray[i-1][0]);
		}
    }
}

function getCity(type,currProvince){
	//var c = eval('document.all.'+type+'_selCity');
	var c = document.getElementById(type+"_selCity");
	//当前 所选择 的 省
    var currProvince = currProvince;
    var i,j,k;
    //清空 城市 下拉选单
    c.length = 0 ;
    for (var i = 0 ;i <cityArray.length;i++){  
    	//得到 当前省 在 城市数组中的位置
        if(cityArray[i][0]==currProvince){  
        	//得到 当前省 所辖制的 地市
            tmpcityArray = cityArray[i][1].split("|")
            for(j=0;j<tmpcityArray.length;j++){
            	//填充 城市 下拉选单
                c.options[c.length] = new Option(tmpcityArray[j],tmpcityArray[j]);
            }
        }
    }
}

function setCity(type,p,c){
//	var po = eval('document.all.'+type+'_selProvince');
//	var co = eval('document.all.'+type+'_selCity');
	var po = document.getElementById(type+"_selProvince");
	var co = document.getElementById(type+"_selCity");
	po.value=p;
	getCity(type,p);
	co.value=c;
}


