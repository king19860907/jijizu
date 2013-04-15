package com.jijizu.web.action;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.jijizu.base.util.JijizuUtil;
import com.jijizu.base.util.StringUtil;
import com.jijizu.core.cache.service.CacheService;
import com.jijizu.core.user.dto.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected static final String NOT_LOGIN = "not_login";
	
	protected static final String INDEX = "index";
	
	protected static final String TARGET = "target";
	
	protected HttpServletResponse response;
	
    protected HttpServletRequest request;
    
    /**
	 * 系统默认编码方式
	 */
	public static final String DEFAULT_CHARSET="UTF-8";
	
	 /**
     * 目标跳转页
     */
    protected String target;
    
    /**
     * json传输
     */
    protected JSONObject jsonObject=new JSONObject();
    
    protected CacheService cacheService;//子类可能用到

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7010380056476871040L;

	@Override
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    @Override
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public HttpServletResponse getResponse()
    {
        return response;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }
    
    /**
     * 输出LIST
     * 
     * @param list
     */
    public void outListToJson(OutputStream out, List list)
    {
        // JSONArray.fromObject(object)
        try
        {
            String str = JSONArray.fromObject(list).toString();
            outString(out, str);
        } catch (Exception e)
        {
            log.error("outListToJson" + e);
        }
    }
    
    /**
     * 输出DTO
     * 
     * @param list
     */
    public void outObjectToJson(OutputStream out, Object obj)
    {
        try
        {
            String str = JSONObject.fromObject(obj).toString();
            outString(out, str);
        } catch (Exception e)
        {
            log.error("outObjectToJson:" + e);
        }
    }
    
    /**
     * 输出LIST
     * 
     * @param list
     */
    public void outListToJson(List list)
    {
        try
        {
            String str = JSONArray.fromObject(list).toString();
            outString(str);
        } catch (Exception e)
        {
            log.error("outListToJson" + e);
        }
    }
    
    /**
     * 输出DTO
     * 
     * @param list
     */
    /*
     * public void outObjectToJson(Object obj) { try{ String str =
     * JSONObject.fromObject(obj).toString(); outString(str); }catch(Exception
     * e){ log.error("outObjectToJson:" + e); e.printStackTrace(); }
     * 
     * }
     */
    public void outString(String str)
    {
        try
        {
            HttpServletResponse resp = getResponse();
            // add by liuxb start
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html; charset=utf-8");
            // add by liuxb end
            if (resp != null)
            {
                PrintWriter out = resp.getWriter();
                out.write(str);
                out.close();
            }
        } catch (IOException e)
        {
            log.error("outString" + e);
        }
    }
    
    /**
     * 输出文本
     * @param str
     */
    public void outTextString(String str)
    {
        try
        {
            HttpServletResponse resp = getResponse();
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/plain; charset=utf-8");
            if (resp != null)
            {
                PrintWriter out = resp.getWriter();
                out.write(str);
                out.close();
            }
        } catch (IOException e)
        {
            log.error("outString" + e);
        }
    }
    
    public void outString(OutputStream out, String str)
    {
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        try
        {
            bos = new BufferedOutputStream(out);
            dos = new DataOutputStream(bos);
            dos.writeUTF(str);
            dos.flush();
            bos.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (bos != null)
            {
                try
                {
                    if (dos != null)
                    {
                        dos.close();
                    }
                    bos.close();
                    // getResponse().getOutputStream().close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                dos = null;
                bos = null;
            }
        }
    }
    
    /**
     * 取用户的IP
     * 
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request)
    {
        String ip = request.getHeader("X-Forwarded-For");
        // System.out.println("X-Forwarded-For"+ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
		String[] ips = ip.trim().split(",");
		String firstIP = ip;

		if (ips.length > 2) {
			firstIP = ips[1];
		}else{
			firstIP = ips[0];
    }
    
		return firstIP;
    }
    
    /**
     * 
     ******************************************************************************* 
     * @function : 获取request参数
     * @return
     ******************************************************************************* 
     * @creator ：Administrator
     * @date ：2010-12-15
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> getParams()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        Map requestParams = getRequest().getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();)
        {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++)
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
    
    public String getParamsUrl(String setKey,String setValue,boolean needPage){
    	String url = "?";
    	Map<String,Object> params = this.getParams();
    	boolean setKeyExist = false;
    	for(String key : params.keySet()){
    		Object value = params.get(key);
    		if(!"page".equals(key) || needPage){
    			if(key.equals(setKey)){
        			url += key+"="+setValue + "&";
        			setKeyExist = true;
        		}else{
        			url += key+"="+value + "&";
        		}
    		}
    	}
    	if(!setKeyExist){
    		url += setKey+"="+setValue + "&";
    	}
    	return url.substring(0, url.length()-1);
    }
    
    /**
     * 
     ******************************************************************************* 
     * @function : 输出XML
     * @param xmlStr
     ******************************************************************************* 
     * @creator ：liuxb
     * @date ：2011-4-18
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    public void outXMLString(String xmlStr)
    {
        getResponse().setContentType("application/xml;charset=UTF-8");
        outString(xmlStr);
    }

    
    /**   
     *******************************************************************************
     * @function : 将对象输出为json格式
     * @param obj
     *******************************************************************************
     * @creator ：majun   
     * @date ：2012-12-18   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void outObjectToJson(Object obj)
    {
        try
        {
            String str = JSONObject.fromObject(obj).toString();
            outJson(str);
        } catch (Exception e)
        {
            log.error("outObjectToJson:" + e);
            e.printStackTrace();
        }

    }

    public void outJson(String str)
    {
        try
        {
            HttpServletResponse resp = getResponse();
            if (resp != null)
            {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = resp.getWriter();
                out.write(str);
                out.close();
            }
        } catch (IOException e)
        {
            log.error("outJson" + e);
        }
    }
    
    /**   
     *******************************************************************************
     * @function : 将对象输出为jsonp格式
     * @param obj
     * @param callback
     *******************************************************************************
     * @creator ：majun   
     * @date ：2012-12-18   
     *******************************************************************************
     * @revisor ：   
     * @date ：   
     * @memo ：   
     *******************************************************************************
     */
    public void outObjectToJsonp(Object obj, String callback){
		if ((callback==null) || (callback.length()==0)){
			callback = "_callback";
		}
		this.outJson(callback + "(" + JSONObject.fromObject(obj).toString() + ")");
	}
    
    public UserInfo getSessionUserInfo(){
    	
    	UserInfo userInfo = JijizuUtil.getSessionUserInfo();
    	
    	return userInfo;
    }
    
    public String getBaseUrl(){
    	String baseUrl = JijizuUtil.getAbsolutePath();
    	return baseUrl;
    }
    
    public String getTarget() {
    	if(!StringUtil.isNotNullOrEmpty(target)){
    		target = request.getRequestURI();
    		if(StringUtil.isNotNullOrEmpty(target) && target.endsWith(".jsp")){
    			return null;
    		}
    		Map<String,Object> params = getParams();
    		if(params != null && params.size() > 0){
    			target+="?";
    			Set<String> keys = params.keySet();
    			for(String key : keys){
    				target+=key+"="+params.get(key)+"&";
    			}
    			target = target.substring(0,target.length()-1);
    		}
    	}
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    
    public  CacheService getCacheService() {
		return cacheService;
	}
	public  void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}
	
	public boolean isDiaryAction(String action) {
		if(StringUtil.isNotNullOrEmpty(action) && action.contains("/diary/")) {
			return true;
		}
		return false;
	}

}
