package com.jijizu.base.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
public class RequestUtil
{
    public static Map<String, String> getParaForAction(ServletRequest request)
    {
        Map<String, String> map = new HashMap<String, String>();
        try
        {
            // map = request.getParameterMap();
            if (null == request)
            {
                return map;
            }
            HttpServletRequest req = (HttpServletRequest) request;
            map.put("WEB_METHOD", getString(req.getMethod(), 8));
            map.put("REQUEST_URI", getString(req.getRequestURI(), 256));
            map.put("REMOTE_ADDR", getString(getIp(req), 256));
            map.put("SERVER_NAME", getString(req.getServerName(), 256));
            map.put("USER_AGENT", getString(req.getHeader("user-agent"), 1000));

            // 地址栏URL
            map.put("URL",
                    getString(CookieUtil.getCookieValue(req, "cookieUrl", ""),
                            256));
            // 前一个URL
            map.put("preURL",
                    getString(
                            CookieUtil.getCookieValue(req, "preCookieUrl", ""),
                            256));
            map.put("PRE_URL", getString(req.getHeader("Referer"), 256));

            //setUserInfo(req, map);
            setSource(req, map);
            Object tmpName = null;
            Enumeration enumeration = req.getParameterNames();
            while (enumeration != null && enumeration.hasMoreElements())
            {
                tmpName = enumeration.nextElement();
                if (tmpName != null)
                {
                    map.put(getString(tmpName, 128),
                            getString(req.getParameter(tmpName.toString()), 512));
                }
            }
        } catch (Exception e)
        {
            DebugTools.error(RequestUtil.class, e);
        }
        return map;
    }

    public static Map getPara(ServletRequest request)
    {
        Map map = new HashMap();
        try
        {
            HttpServletRequest req = (HttpServletRequest) request;
            map.put("WEB_METHOD", getString(req.getMethod(), 8));
            map.put("REQUEST_URI", getString(req.getRequestURI(), 256));
            map.put("REMOTE_ADDR", getString(getIp(req), 256));
            map.put("SERVER_NAME", getString(req.getServerName(), 256));
            map.put("USER_AGENT", getString(req.getHeader("user-agent"), 1000));
            // 地址栏URL
            map.put("URL",
                    getString(CookieUtil.getCookieValue(req, "cookieUrl", ""),
                            256));
            /*
             * map.put("PRE_URL", getString(CookieUtil.getCookieValue(req,
             * "preCookieUrl", ""), 256));
             */
            // 前一个URL
            map.put("PRE_URL", getString(req.getHeader("Referer"), 256));

            // DebugTools.debug(map);

            //setUserInfo(req, map);
            setSource(req, map);

            List list = new ArrayList();
            String tmpName = null;
            Map tempMap = null;
            Enumeration<String> enumeration = req.getParameterNames();
            while (enumeration != null && enumeration.hasMoreElements())
            {
                tmpName = enumeration.nextElement();
                tempMap = new HashMap();
                tempMap.put("paraName", getString(tmpName, 128));
                tempMap.put("paraValue",
                        getString(req.getParameter(tmpName), 512));
                list.add(tempMap);
            }
            map.put("list", list);
        } catch (Exception e)
        {
            DebugTools.error(RequestUtil.class, e);
        }
        return map;
    }

    /**
     * 
     * @函数说明：设置来源
     * @创建人：weishen
     * @创建时间：2010-12-9 下午05:48:14
     * @修改人：
     * @修改时间：
     * @修改备注：
     * 
     * @param request
     * @param map
     */
    private static void setSource(HttpServletRequest request, Map map)
    {
        /*
         * StringBuffer sb = new StringBuffer(); String getSource =
         * request.getSession().getAttribute("getsource") == null ? null :
         * (String) request.getSession().getAttribute("getsource"); if
         * (getSource != null && !getSource.equals("")) { map.put("getsource",
         * getSource); sb.append(getSource); } String keyname =
         * CookieUtil.getCookieValue(request, Constant.KEYNAME, ""); if (keyname
         * != null && !keyname.equals("")) { map.put("keyname", keyname);
         * sb.append(","); sb.append(keyname); } String keysource =
         * CookieUtil.getCookieValue(request, Constant.KEYSOURCE, ""); if
         * (keysource != null && !keysource.equals("")) { map.put("keysource",
         * keysource); sb.append(","); sb.append(keysource); } keyname =
         * (String) request.getSession().getAttribute("keyname"); if (keyname !=
         * null && !keyname.equals("")) { map.put("keyname", keyname);
         * sb.append(","); sb.append(keyname); } keysource = (String)
         * request.getSession().getAttribute("keysource"); if (keysource != null
         * && !keysource.equals("")) { map.put("keysource", keysource);
         * sb.append(","); sb.append(keysource); } map.put("USER_SOURCE",
         * getString(sb.toString(), 128));
         */
        map.put("USER_SOURCE", "");
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
     * @function : 截取指定长度的IP地址
     * @param request
     * @param length
     * @return
     ******************************************************************************* 
     * @creator ：Administrator
     * @date ：Dec 9, 2010
     ******************************************************************************* 
     * @revisor ：
     * @date ：
     * @memo ：
     ******************************************************************************* 
     */
    public static String getIp(HttpServletRequest request, int length)
    {
        return getString(getIp(request), length);
    }

    /**
     * 返回限定长度的字符串
     * 
     * @param str
     * @param maxLength
     * @return
     */
    private static String getString(String str, int maxLength)
    {
        if (str != null && str.length() > maxLength)
        {
            return str.substring(0, maxLength);
        }
        return str;
    }

    /**
     * 返回限定长度的字符串
     * 
     * @param str
     * @param maxLength
     * @return
     */
    private static String getString(Object str, int maxLength)
    {
        if (str != null)
        {
            return getString(str.toString(), maxLength);
        }
        return "";
    }

}
