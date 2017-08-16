package com.order.repository;

import com.order.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;
   @Test
    public void findByProductStatus() throws Exception {
       List<ProductInfo> list=productInfoRepository.findByProductStatus(0);
    }
    @Test
    public void saveTest() throws Exception {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("2344");
        productInfo.setCategoryType(3);
        productInfo.setProductDescription("南瓜粥超级好喝！");
        productInfo.setProductPrice(new BigDecimal(9.33));
        productInfo.setProductName("南瓜粥");
        productInfo.setProductStock(0);
        productInfo.setProductIcon("http://abc.jpg");
        productInfo.setProductStatus(0);
        productInfoRepository.save(productInfo);
    }

}