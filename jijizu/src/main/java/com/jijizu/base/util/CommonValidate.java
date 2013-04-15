package com.jijizu.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidate {
public CommonValidate(){
        
    }
    /**
     * 验证字符长度，不含边际 
     * @author liuxb
     * @param str
     * @param min 最小个数 -1为不验证
     * @param max 最大个数 -1为不验证
     * @param isByte 是否按字节数来比较
     * @return
     */
    public static boolean validateLength(String str,int min,int max,boolean isByte){
        if(null == str || "".equals(str)){
            return false;
        }
        
        int len = 0;
        if(isByte==true){
            len = str.replaceAll("[^x00-xff]", "**").length();
        }else{
            len = str.length();
        }
        if(min==-1 && max!=-1){//只验证最大
            if(len<max){
                return true;
            }
        }else if(min!=-1 && max==-1){//只验证最小
            if(len>min){
                return true;
            }
        }else if(min==-1 && max==-1){//两个都部验证
          return true;
        }else{ //两个都验证
            if(len>min && len < max){
                return true;
            }
        }
        return false;
    }
    
    //是否手机号码
    public static boolean isMobile(String str){
        if(null == str || "".equals(str)){
            return false;
        }
        return Pattern.matches("^1\\d{10}$", str);
    }
    //是否email
    public static boolean isEmail(String str){  
        if(null == str || "".equals(str)){
            return false;
        }
        return Pattern.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$",str);      
    }
    //是否整型
    public static boolean isInteger(String str){    
        if(null == str || "".equals(str)){
            return false;
        }
        return Pattern.matches("^[-\\+]?\\d+$",str);        
    }
    //是否为正整数
    public static boolean isSignlessInteger(String str) {
    	if(isInteger(str)) {
    		return Integer.parseInt(str)>0;
    	}
    	return false;
    }
    
    //是否为数字
    public static boolean isNumeric(String str)
    {  
        if(null == str || "".equals(str)){
            return false;
        }
        
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
    //是否邮编
    public static boolean isPost(String str){   
        if(null == str || "".equals(str)){
            return false;
        }
        return Pattern.matches("^\\d{6}$",str);     
    }
    //是否金额
    public static boolean isMoney(String str){  
        if(null == str || "".equals(str)){
            return false;
        }
        return Pattern.matches("^(\\d*[.]?\\d*)$",str);     
    }
    //是否金额
    public static boolean isNumber(String str){ 
        if(null == str || "".equals(str)){
            return false;
        }
        return Pattern.matches("^\\d+$",str);       
    }
    //判断是否中文
    public static boolean isChinese(String str){
        if(null == str || "".equals(str)){
            return false;
        }
        
        String re1="^[\u4E00-\uFA29]*$";
        String re2="^[\uE7C7-\uE7F3]*$";        
        return (Pattern.matches(re1,str) && Pattern.matches(re2,str));  
    }   
    //判断是否国家区号
    public static boolean isCountryNumber(String str){
        
        if(null == str || "".equals(str)){
            return false;
        }
        
        return Pattern.matches("^\\d{2,5}$",str);           
    }
    //是否城市区号
    public static boolean isCityNumber(String str){
        
        if(null == str || "".equals(str)){
            return false;
        }
        
        return Pattern.matches("^\\d{3,4}$",str);           
    }
    //是否固定电话号码(不含区号)
    public static boolean isPhoneNumber(String str){
        
        if(null == str || "".equals(str)){
            return false;
        }
        
        return Pattern.matches("^\\d{7,8}$",str);           
    }
    //是否固定电话号码(含城市区号)
    public static boolean isCityPhoneNumber(String str){
        
        if(null == str || "".equals(str)){
            return false;
        }
        
        return Pattern.matches("^\\d{3,4}(\\-)+\\d{7,8}$",str);         
    }
}
