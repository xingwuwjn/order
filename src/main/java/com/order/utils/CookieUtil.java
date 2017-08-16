package com.order.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
public class CookieUtil {
    //设置cookie
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    //获取cookie
    public static Cookie get(HttpServletRequest request,String name){
        Map<String,Cookie> map=cookieMap(request);
        if(map.containsKey(name)){
            return map.get(name);
        }
        else{
            return null;
        }

    }
    /*将cookie封装成cookie*/
    private static Map<String,Cookie> cookieMap(HttpServletRequest request){
        Map<String,Cookie> map=new HashMap<>();
        Cookie [] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                map.put(cookie.getName(),cookie);
            }
        }
        return map;
    }
}
