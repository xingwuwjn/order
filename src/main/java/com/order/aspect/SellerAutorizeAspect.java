package com.order.aspect;

import com.order.constant.CookieConstant;
import com.order.constant.RedisConstant;
import com.order.exception.SellerAuthorizeException;
import com.order.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by 醒悟wjn on 2017/8/8.
 */
/*@Aspect
@Component
@Slf4j
public class SellerAutorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * com.order.Controller.Seller*.*(..))"+"&&" +
            "!execution(public * com.order.Controller.SellerUserController.*(..))")
    public void verfy(){}

    @Before("verfy()")
    public void doVerfy(){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        //获取cookie
        Cookie cookie=CookieUtil.get(request, CookieConstant.token);
        //未登录情况
        if(cookie==null){
            log.warn("【登陆校验】，cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //去redis中查询
        String tokenvalue=redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
       if(StringUtils.isEmpty(tokenvalue)){
           log.warn("【登陆校验】，redis中查不到token");
           throw new SellerAuthorizeException();
       }
    }
}*/
