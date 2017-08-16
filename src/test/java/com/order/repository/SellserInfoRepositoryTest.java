package com.order.repository;

import com.order.dataobject.SellerInfo;
import com.order.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/8/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellserInfoRepositoryTest {
    @Autowired
    SellserInfoRepository repository;
    @Test
    public void findByOpenid() throws Exception{
        SellerInfo sellerInfo=repository.findByOpenid("sdf");
        Assert.assertNotNull("seller",sellerInfo);
    }
    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setUsername("醒悟wjn");
        sellerInfo.setPassword("123456");
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setOpenid("sdf");
        repository.save(sellerInfo);
    }

}