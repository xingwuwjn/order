package com.order.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
@Component
public class WechatOpenConfig {
    @Autowired
    WechatAccountConfig wechatAccountConfig;
    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService=new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxMpConfigStorage());//设置基础配置
        return wxOpenService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpMemoryConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpMemoryConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());//设置公众号appid
        wxMpMemoryConfigStorage.setSecret(wechatAccountConfig.getOpenSecret());//设置公众号secret
        return  wxMpMemoryConfigStorage;
    }
}
