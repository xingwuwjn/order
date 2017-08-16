package com.order.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/8/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ImageConfigTest {
    @Autowired
    private ProjectUrl projectUrl;
    @Autowired
    private ImageConfig imageConfig;
    @Test
    public void getMoudle() throws Exception {
        String a=imageConfig.getModule();
        log.info("【获取图片配置】，moudle={}",a);
        Assert.assertNotNull(a);
    }

    @Test
    public void getModuleKey() throws Exception {
        String a=imageConfig.getModuleKey();
        log.info("【获取图片配置】，moudle={}",a);
        Assert.assertNotNull(a);

    }

    @Test
    public void getDealSourceBasePath() throws Exception {
        String a=imageConfig.getDealSourceBasePath();
        log.info("【获取图片配置】，moudle={}",a);
        Assert.assertNotNull(a);

    }

}