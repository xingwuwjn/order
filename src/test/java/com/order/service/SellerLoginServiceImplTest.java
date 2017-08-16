package com.order.service;

import com.order.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/8/7.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerLoginServiceImplTest {
    @Autowired
    SellerLoginServiceImpl sellerLoginService;
    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo=sellerLoginService.findByOpenid("sdf");
        Assert.assertNotNull(sellerInfo);
    }

}