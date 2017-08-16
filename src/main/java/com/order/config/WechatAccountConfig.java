package com.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 醒悟wjn on 2017/7/31.
 */
@Data
@Component
@ConfigurationProperties(prefix="wechat")
public class WechatAccountConfig {
    //开放平台秘钥
    private String openAppId;
    private String openSecret;
    //公众平台秘钥
    private String mpAppId;
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;
    /**
     * 异步通知url
     */
    private String notifyUrl;
}
