package com.order.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 醒悟wjn on 2017/7/31.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}",code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbed43f5f2db25aa8&secret=64c225fd312ad34e691124050a7c87c5&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject(url,String.class);
        String st="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=oxeK-v6xZZTy78SnGWZ48q0UXubY&lang=zh_CN";
        log.info("response={}",response);
    }

}
