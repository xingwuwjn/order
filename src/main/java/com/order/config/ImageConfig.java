package com.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by 醒悟wjn on 2017/8/12.
 */
@Component
@Data
@ConfigurationProperties(prefix = "image")
public class ImageConfig {
    public  String module;//图片的moudle
    public  String moduleKey;//图片的moudleKey
    public  String dealSourceBasePath;//图片生成的路径

}
