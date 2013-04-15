package com.jijizu.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil
{

	private static final String UUID_COOKIE_NAME = "mc_uuid";
	
	public static final int MAX_AGE_YEAR = 60 * 60 * 24 * 365;
	
	public static final int MAX_AGE_WEEK = 60 * 60 * 24 * 7;

    // 得到一个cookie
    public static String getCookieValue(Cookie[] cookies, String cookieName,
            String defaultValue)
    {
        for (int i = 0; i < cookies.length; i++)
        {
            Cookie cookie = cookies[i];
            if (cookieName.equals(cookie.getName()))
            {
                return (cookie.getValue());
            }
        }
        return (defaultValue);
    }

    // 得到一个cookie
    public static String getCookieValue(HttpServletRequest request,
            String cookieName, String defaultValue)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
        {
            return (defaultValue);
        }
        for (int i = 0; i < cookies.length; i++)
        {
            Cookie cookie = cookies[i];
            if (cookieName.equals(cookie.getName()))
            {
                String d = "";
                try
                {
                    d = URLDecoder.decode(cookie.getValue(), "utf-8");
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                return (d);
            }
        }
        return (defaultValue);
    }
    
    
    public static Map<String,String> getCookieMap(HttpServletRequest request)
    {
        Map<String,String> map = new HashMap<String,String>();
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
        {
            return map;
        }
        for (int i = 0; i < cookies.length; i++)
        {
            Cookie cookie = cookies[i];
            String d = "";
            try
            {
                d = URLDecoder.decode(cookie.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            map.put(cookie.getName(), d);
        }
        return map;
    }

    // 写一个cookie
    /**
     * 转public static void setCookie(HttpServletRequest request, HttpServletResponse response,
            String coiokieName, String cookieValue)
     * @param response
     * @param coiokieName
     * @param cookieValue
     */
    @Deprecated
    public static void setCookie(HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,
            String coiokieName, String cookieValue, int maxAgeBySecond)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(maxAgeBySecond);
        cookie.setPath("/");
        cookie.setDomain(getDomain(request));
        response.addCookie(cookie);
        if (maxAgeBySecond==0) { // 如果是删除， 则也要删其它分支
            Cookie cookie2 = new Cookie(coiokieName, cookieValue);
            cookie2.setMaxAge(0);
            cookie2.setPath("/");
            response.addCookie(cookie2);
            Cookie cookie3 = new Cookie(coiokieName, cookieValue);
            cookie3.setMaxAge(0);
            response.addCookie(cookie3);
        }
    }
	protected static String getDomain(HttpServletRequest request) {
		String serverName = request.getServerName(); 
		if (serverName.contains(".jijizu.com")) { 
		return ".jijizu.com"; 
		} 
		if(serverName.equals("jijizu.com")){
			return ".jijizu.com";
		}
		int index = serverName.indexOf("."); 
		if (index>=0 && serverName.endsWith(".com")) { 
		return serverName.substring(index+1); 
		} 
		return serverName; 
	}
    // 写保存一个月的cookie
	@Deprecated
    public static void writeMonthCookie(HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 10);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 写保存一个月的cookie
	@Deprecated
    public static void writeCookieCycle(HttpServletResponse response,
            String coiokieName, String cookieValue, int day)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * day);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 写保存一天月的cookie
	@Deprecated
    public static void writeDayCookie(HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 删一个cookie
	@Deprecated
    public static void deleteCookie(HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    public static void deleteCookie(HttpServletRequest requets, HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        Cookie cookie2 = new Cookie(coiokieName, cookieValue);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
        cookie2.setDomain(getDomain(requets));
        response.addCookie(cookie2);
    }
    // 写一个cookie
    @Deprecated
    public static void setCookie(HttpServletResponse response,
            String coiokieName, String cookieValue, int minute)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setMaxAge(60 * minute);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 写一个cookie
    @Deprecated
    public static void setCookieNotSave(HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    public static void setCookieNotSave(HttpServletRequest request, HttpServletResponse response,
            String coiokieName, String cookieValue)
    {
        try
        {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            cookieValue = "";
        }
        Cookie cookie = new Cookie(coiokieName, cookieValue);
        cookie.setPath("/");
        cookie.setDomain(getDomain(request));
        response.addCookie(cookie);
    }
    @Deprecated
    public static void setMemberIdToCookie(HttpServletResponse response,
            Long memberId, boolean saveFlag)
    {
        // String encString = des.getEncString(String.valueOf(memberId));
        String encString = String.valueOf(memberId);

        /*
         * START: added by luke huang, 2011-05-13 16:12, for remember-me when
         * login
         */
        encString = CryptoUtil.desEncryptToHex(encString, null);
        /*
         * END: added by luke huang, 2011-05-13 16:12, for remember-me when
         * login
         */

        if (saveFlag)
        {
            setCookie(response, Constant.LOGIN_USERNAME, encString);
            setCookie(response, "encFlag", "1");
        } else
        {
            setCookieNotSave(response, Constant.LOGIN_USERNAME, encString);
            setCookieNotSave(response, "encFlag", "1");
        }
    }
    public static void setUserIdToCookie(HttpServletRequest request, HttpServletResponse response,
            Long userId, boolean saveFlag)
    {
        // String encString = des.getEncString(String.valueOf(memberId));
        String encString = String.valueOf(userId);

        /*
         * START: added by luke huang, 2011-05-13 16:12, for remember-me when
         * login
         */
        encString = CryptoUtil.desEncryptToHex(encString, null);
        /*
         * END: added by luke huang, 2011-05-13 16:12, for remember-me when
         * login
         */

        setCookie(request, response, Constant.LOGIN_USERNAME, encString, saveFlag ? 3600*24*365 : -1);
        setCookie(request, response, "encFlag", "1", saveFlag ? 3600*24*365 : -1);
    }
    public static Long getUserIdFromCookie(HttpServletRequest request)
    {
        Long userId = -1L;
        try
        {
            String strMemberId = CookieUtil.getCookieValue(request,
                    Constant.LOGIN_USERNAME, "");
            String encFlag = CookieUtil.getCookieValue(request,
            		"encFlag", "");

            /*
             * START: added by luke huang, 2011-05-13 16:16, for remember-me
             * when login
             */
			if ("1".equals(encFlag)) {
				strMemberId = CryptoUtil.desDecryptFromHex(strMemberId, null);
			}
            /*
             * END: added by luke huang, 2011-05-13 16:16, , for remember-me
             * when login
             */

            // if (!StringUtil.isBlank(strMemberId)) {
            // System.out.println("1=====>"+strMemberId);
            // strMemberId = des.getDesString(strMemberId);
            // System.out.println("2=====>"+strMemberId);
            if (strMemberId != null && strMemberId.matches("\\d+"))
            {
                userId = Long.valueOf(strMemberId, 10);
            }
            // }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return userId;
    }
    @Deprecated
    public static void deleteMemberIdCookie(HttpServletResponse response)
    {
        deleteCookie(response, Constant.LOGIN_USERNAME, null);
    }
    public static void deleteUserIdCookie(HttpServletRequest request, HttpServletResponse response)
    {
        deleteCookie(request, response, Constant.LOGIN_USERNAME, null);
    }
	/**
	 ******************************************************************************* 
	 * @function : 从COOKIE获取UUID
	 * @return UUID
	 ******************************************************************************* 
	 * @creator ：nelson
	 * @date ：2011-6-15
	 ******************************************************************************* 
	 * @revisor ：
	 * @date ：
	 * @memo ：
	 ******************************************************************************* 
	 */
	public static String getUUIDfromCookie(HttpServletRequest request,
			HttpServletResponse response) {
		String uuid = getCookieValue(request, UUID_COOKIE_NAME, null);
		if (uuid == null) {
			uuid = StringUtil.shortUUIDString();
			// 保存到COOKIE
			setCookieNotSave(request, response, UUID_COOKIE_NAME, uuid);
		}
		return uuid;
	}
	@Deprecated
	public static void removeUUIDfromCookie(HttpServletResponse response) {
		deleteCookie(response, UUID_COOKIE_NAME, null);
	}
	public static void removeUUIDfromCookie(HttpServletRequest request, HttpServletResponse response) {
		deleteCookie(request, response, UUID_COOKIE_NAME, null);
	}
}