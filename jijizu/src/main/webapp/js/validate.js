/*summary
String.prototype.trim					删除左边和右边空格
String.prototype.ltrim					删除左边空格
String.prototype.rtrim					删除右边空格

function isEmail(strEmail)  			是否电子邮件 
function isEmails(strEmail,flag)        是否电子邮件(多个,用flag分隔)
function isPost(str) 					是否邮编(1位至6位）
function isMoney(str) 					是否金额(例:19.53)
function isNumber(str) 					是否数字
function isChinese(str) 				判断是否中文
function isCountryNumber(str) 			是否国家区号(2位至5位数字)
function isCityNumber(str) 				是否城市区号(3位至4位数字)
function isPhoneNumber(str) 			是否固定电话号码(7位至8位数字)
function isCityPhoneNumber(str) 		是否固定电话号码(含城市区号,例:021-1111111)
function isCountryCityPhoneNumber(str) 	是否固定电话号码(含国际区号含城市区号:55555-021-1111111)
function isMobile(str) 					是否手机号码
function isDate(str) 					是否日期类型(例:2005-12-12)
function isLeapYear(year)   			判断是否闰年
function isValidDate(iY, iM, iD)		是否有效的日期
function isDateEx(oStartDate)			验证给定的日期是否合法   ,参数格式要求：yyyy-mm-dd 可以根据情况更改正则表达式
function isValidDate(iY, iM, iD) 		判断是否有效日期
function datevaluechang(dateobj)		判断日期输入格式
function isResumeid(str) 				判断是不是合法的resumeid
function isEqual(str1,str2) 			字符串1和字符串2是否相等
function isPicture(str) 				是否jpg,jpeg,swf,gif结尾的图片类型
function isMedia(str) 					是否rm,wav,wmv结尾的图片类型
function isSalary(str) 					是否工资类型(例：12.59)
function isManID(sNo) 					是否有效身份证号
function isInteger(str)                 是否整型
function isQQ(str)                      是否合法的QQ号码

*/

String.prototype.trim = function()
{
    return this.replace(/(^s*)|(s*$)/g, "");
}
String.prototype.ltrim = function()
{
    return this.replace(/(^s*)/g, "");
}
String.prototype.rtrim = function() {
    return this.replace(/(s*$)/g, "");
}

function isEmails(strEmail,flag){
	var email_arr=strEmail.split(flag);
	var res=true;
	for(var i=0;i<email_arr.length;i++){
		if(!isEmail(email_arr[i])){
			res=false;
			break;
		}
	}	
	return res;
}
//是否整型
function isInteger(str)
{
	var reg=/^[-\+]?\d+$/;
    if(reg.test(str))
        return true;
    else
        return false; 
}

//是否电子邮件
function isEmail(strEmail)
{
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/) != -1) {
		return true;
	} else {
		return false;
	}
}

//是否邮编
function isPost(str)
{
	var reg=/^\d{6,6}$/;
    if(reg.test(str))
        return true;
    else
        return false;
}

//是否金额
function isMoney(str)
{
	var objReg=/^(\d*[.]?\d*)$/;
	if(objReg.test(str))
		return true;else
		return false; 
}
//是否数字
function isNumber(str)
{
    var objReg = /^\d+$/;
    if (objReg.test(str))
        return true;
 	else
        return false;
}
//判断是否中文
function isChinese(str){
   var re1 = new RegExp("^[\u4E00-\uFA29]*$"); 
   var re2 = new RegExp("^[\uE7C7-\uE7F3]*$");
   var str = str.replace(/(^\s*)|(\s*$)/g,'');
   if (!(re1.test(str) && (! re2.test(str)))){
      return false;
   }
   return true;
}

//是否国家区号
function isCountryNumber(str)
{
  var reg=/^\d{2,5}$/;
  if(reg.test(str))
    return true;
  else
    return false;
}

//是否城市区号
function isCityNumber(str)
{
  var reg=/^\d{3,4}/;
  if(reg.test(str))
    return true;
  else
    return false;
}

//是否固定电话号码(不含区号)
function isPhoneNumber(str)
{
    var reg=/^\d{7,8}$/;
    if(reg.test(str))
      return true;
    else
      return false;
}
//是否固定电话号码(含城市区号)
function isCityPhoneNumber(str)
{	
    var reg=/^\d{3,4}(\-)+\d{7,8}$/;//  /(^\d{3,4}(\-)+\d{7,8}$)|(^\d{3,4}(\-)+\d{7,8}(\-)+\d{3,5}$)/;
    if(reg.test(str))
      return true;
    else
      return false;
}
function isCountryCityPhoneNumber(str) //是否固定电话号码(含国际区号含城市区号:55555-021-1111111)
{
    var reg=/^\d{2,5}(\-)+\d{3,4}(\-)+\d{7,8}$/;
    if(reg.test(str))
      return true;
    else
      return false;
}

//是否手机号码
function isMobile(str)
{
  var reg=/^1\d{10}$/;
  if(reg.test(str))
    return true;
  else
    return false;
}


//是否日期类型(例:2005-12-12)
function isDate(str)
{
    //var objReg=/(\d{4}\-{1}\d{1,2}\-{1}\d{1,2})+/;
    var objReg = /^((19|20){1}\d{2}\-{1}\d{1,2}\-{1}\d{1,2})+$/;
    if (objReg.test(str))
    {
        return true;
    }
    else
    {
        return false;
    }
}

//判断是否有效日期
function isValidDate(iY, iM, iD)
{
    var undefined;
    if (iY != undefined && !isNaN(iY) && iY >= 0 && iY <= 9999 && iM != undefined && !isNaN(iM) && iM >= 1 && iM <= 12 && iD != undefined && !isNaN(iD) && iD >= 1 && iD <= 31) {
        if (iY < 50)
            iY = 2000 + iY;
        else if (iY < 100)
            iY = 1900 + iY;
        if (iM == 2 && (isLeapYear(iY) && iD > 29 || !isLeapYear(iY) && iD > 28) || iD == 31 && (iM < 7 && iM % 2 == 0 || iM > 7 && iM % 2 == 1))
            return false; else
            return true;
    }
    else
        return false;
}

//判断是不是合法的QQ号码
function isQQ(str) {
	var objReg = /^\d{5,9}$/;
	
	if(objReg.test(str))
	{
		return true;
	}
	else
	{
		return false;
	}
}

//判断是不是合法的resumeid
function isResumeid(str) {
    var objReg = /^(\d{1,16})$/

    if (objReg.test(str))
    {
        return true;
    }
    else
    {
        return false;
    }
}
//字符串1和字符串2是否相等
function isEqual(str1,str2)
{	
    if(str1==str2)
    {
        return true;
    }
    return false;
}
//是否jpg,jpeg,swf,gif结尾的图片类型
function isPicture(str)
{
    var objReg = new RegExp("[.]+(jpg|jpeg|swf|gif)$", "gi");
    if (objReg.test(str))
        return true; else
        return false;
}
//是否rm,wav,wmv结尾的图片类型
function isMedia(str)
{
    var objReg = new RegExp("[.]+(rm|wav|wmv)$", "gi");

    if (objReg.test(str))
        return true; else
        return false;
}
//是否工资类型(例：12.59)
function isSalary(str)
{
     var objReg = /(^[0-9]+\.[0-9]{1,2}$)|(^[0-9]+)$/;
     if(objReg.test(str))
     {
        return true;
     }
     else
     {
        return false;
     }
}
//是否有效身份证号
function isManID(sNo)
{   
    if (sNo.length == 18) {
        var a,b,c
        if (!isNumber(sNo.substr(0, 17))) {
            return false;
        }

        a = parseInt(sNo.substr(0, 1)) * 7 + parseInt(sNo.substr(1, 1)) * 9 + parseInt(sNo.substr(2, 1)) * 10;
        a = a + parseInt(sNo.substr(3, 1)) * 5 + parseInt(sNo.substr(4, 1)) * 8 + parseInt(sNo.substr(5, 1)) * 4;
        a = a + parseInt(sNo.substr(6, 1)) * 2 + parseInt(sNo.substr(7, 1)) * 1 + parseInt(sNo.substr(8, 1)) * 6;
        a = a + parseInt(sNo.substr(9, 1)) * 3 + parseInt(sNo.substr(10, 1)) * 7 + parseInt(sNo.substr(11, 1)) * 9;
        a = a + parseInt(sNo.substr(12, 1)) * 10 + parseInt(sNo.substr(13, 1)) * 5 + parseInt(sNo.substr(14, 1)) * 8;
        a = a + parseInt(sNo.substr(15, 1)) * 4 + parseInt(sNo.substr(16, 1)) * 2;
        b = a % 11;

        if (b == 2) {
            c = sNo.substr(17, 1).toUpperCase();
        } else {
            c = parseInt(sNo.substr(17, 1));
        }

        switch (b) {
            case 0:
                if (c != 1) {
                    return false;
                }
                break;
            case 1:
                if (c != 0) {
                    return false;
                }
                break;
            case 2:
                if (c != "X") {
                    return false;
                }
                break;
            case 3:
                if (c != 9) {
                    return false;
                }
                break;
            case 4:
                if (c != 8) {
                    return false;
                }
                break;
            case 5:
                if (c != 7) {
                    return false;
                }
                break;
            case 6:
                if (c != 6) {
                    return false;
                }
                break;
            case 7:
                if (c != 5) {
                    return false;
                }
                break;
            case 8:
                if (c != 4) {
                    return false;
                }
                break;
            case 9:
                if (c != 3) {
                    return false;
                }
                break;
            case 10:
                if (c != 2) {
                    return false;
                }
                break;
        }
    } else {
        if (!isNumber(sNo)) {
            return false;
        }
    }

    switch (sNo.length) {
        case 15:
            if (isValidDate(sNo.substr(6, 2), sNo.substr(8, 2), sNo.substr(10, 2))) {
                return true;
            }
            break;
        case 18:
            if (isValidDate(sNo.substr(6, 4), sNo.substr(10, 2), sNo.substr(12, 2))) {
                return true;
            }
            break;
    }
    return false;
}


//验证给定的日期是否合法   ,参数格式要求：yyyy-mm-dd 可以根据情况更改正则表达式
function isDateEx(oStartDate)
{
    //对日期格式进行验证 要求为1900-2099年  格式为 yyyy-mm-dd 并且可以正常转换成正确的日期
    var pat_hd=/^(19|20)\d{2}-((0[1-9]{1})|(1[0-2]{1}))-((0[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))$/;
  
 	try{
     	if(!pat_hd.test(oStartDate)){throw "日期非法！";}
  		var arr_hd=oStartDate.split("-");
  		var dateTmp;
  		dateTmp= new Date(arr_hd[0],parseFloat(arr_hd[1])-1,parseFloat(arr_hd[2]));
  		if(dateTmp.getFullYear()!=parseFloat(arr_hd[0]) || dateTmp.getMonth()!=parseFloat(arr_hd[1]) -1 || dateTmp.getDate()!=parseFloat(arr_hd[2])){
   			throw "日期非法！";
  		}
 	}catch(ex){
  		if(ex.description){
  			return false;
  		}
   		else{
   			return false;
   		}
 	}
 	return true;
}

//判断是否闰年
function isLeapYear(year) {
    // is it leap year ? returns a boolean
    return ( (0 == (year % 4)) && ( (0 != (year % 100)) || (0 == (year % 400))));
    // ie, if the year divides by 4, but not by 100 except when it divides by
    // 400, it is leap year
}


/**判断日期输入格式**/
function datevaluechang(dateobj) {
    var str = dateobj.value;
    var noline = "";
    str = str.trim();
    if (str.length == 0)
        return true;

    noline = str.indexOf("-");
    while (noline > -1) {
        str = str.replace("-", "");
        noline = str.search("-");
    }
    /*if(str.length != 8) {
        dateobj.focus();
        alert("日期长度不正确!");
        return false;
    }*/
    yearstr = str.substring(0, 4);
    matchstr = str.substring(4, 6);
    if (matchstr > '12' || matchstr < '01') {
        dateobj.focus();
        alert("月份数据越界!");
        return false;
    }
    daystr = str.substring(6, 8);
    if (daystr > '31' || daystr < '01') {
        dateobj.focus();
        alert("日期数据越界!");
        return false;
    }
    //dateobj.value = yearstr+"-"+matchstr+"-"+daystr;
    return true;
}


/*
function isEmail(ChkStr)
{
   var reg=/[^\._][\w\._]+@[\w\d\-]+\.[\w]+[\.]?[\w]*?[\.]?[\w]*$/;
  if(reg.test(ChkStr))
    return true;
  else
    return false;
}

function isMail(str)
{
    //var objReg=new RegExp("^[a-z,0-9,_,\\-,\.]+@([a-z,0-9,_,\\-,\.]+.)+(.cc|.hk|.uk|.tw|.jp|.com|.net|.bta|.net|.org|.edu|.biz|.tv|.mil|.cn)$","gi");
    var objReg = new RegExp("^[a-z,0-9,_,\\-,\.]+@([a-z,0-9,_,\\-,\.]+.)+([.]+(cc|hk|uk|tw|jp|com|net|bta|net|org|edu|biz|tv|mil|cn))$", "gi");
    if (objReg.test(str))
    {
        return true;
    }
    else
    {
        return false;
    }
}
*/
