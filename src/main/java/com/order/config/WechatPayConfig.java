package com.order.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 醒悟wjn on 2017/8/1.
 */
@Component
public class WechatPayConfig {
    @Autowired
    private WechatAccountConfig accountConfig;
    @Bean
    public BestPayServiceImpl bestPayService(){
        WxPayH5Config wxPayH5Config=new WxPayH5Config();
        wxPayH5Config.setAppId(accountConfig.getMpAppId());//设置公众号appid
        wxPayH5Config.setMchId(accountConfig.getMchId());//商户号
        wxPayH5Config.setMchKey(accountConfig.getMchKey());
        wxPayH5Config.setKeyPath(accountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(accountConfig.getNotifyUrl());//异步通知地址
        BestPayServiceImpl bestPayService=new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);//设置基础配置。。。
        return bestPayService;
    }
    //基础配置bean
    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config=new WxPayH5Config();
        wxPayH5Config.setAppId(accountConfig.getMpAppId());
        wxPayH5Config.setMchId(accountConfig.getMchId());
        wxPayH5Config.setMchKey(accountConfig.getMchKey());
        wxPayH5Config.setKeyPath(accountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(accountConfig.getNotifyUrl());
        return  wxPayH5Config;
    }
}
