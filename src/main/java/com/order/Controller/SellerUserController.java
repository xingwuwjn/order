package com.order.Controller;

import com.order.config.ProjectUrl;
import com.order.constant.CookieConstant;
import com.order.constant.RedisConstant;
import com.order.dataobject.SellerInfo;
import com.order.enums.ResultEnum;
import com.order.service.SellerLoginService;
import com.order.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private ProjectUrl projectUrl;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SellerLoginService sellerLoginService;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){
        //openid和数据库里的数据匹配
        SellerInfo sellerInfo=sellerLoginService.findByOpenid(openid);
        if(sellerInfo==null){
            map.put("msg", ResultEnum.LOGIN_ERROR.getMessage());
            map.put("url","/sell/seller/order/list");
            /*return new ModelAndView("order/error",map);*/
        }
        //设置token至redis
        String token= UUID.randomUUID().toString();
        Integer expire= RedisConstant.EXPIRE;//过期时间
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),
                openid,expire, TimeUnit.SECONDS);
        //设置token至cookie
        CookieUtil.set(response, CookieConstant.token,token,expire);//设置cookie
        return new ModelAndView("redirect:"+ projectUrl.getSell()+"/seller/order/list");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response,
                       HttpServletRequest request,
                       Map<String ,Object> map){
        //从cookie里查询
         Cookie cookie=CookieUtil.get(request,CookieConstant.token);
              //清除redis
         if(cookie!=null){
             stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
         }
        //清除cookie
         CookieUtil.set(response,CookieConstant.token,null,0);
         map.put("msg",ResultEnum.QUIT_SUCCESS.getMessage());
         map.put("url","/sell/seller/order/list");
         return  new ModelAndView("order/success",map);
    }
}
