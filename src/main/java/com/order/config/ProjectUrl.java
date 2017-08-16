package com.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
@Component
@Data
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrl {
    //微信公众平台授权url
    private String wechatMpAuthorize;
    //微信开放平台授权url
    private String wechatOpenAutorize;
    //点餐系统
    public String sell;
    public String imageUrl;
}
