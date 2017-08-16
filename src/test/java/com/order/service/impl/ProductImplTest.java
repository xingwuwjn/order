package com.order.service.impl;

import com.order.dataobject.ProductInfo;
import com.order.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImplTest {
    @Autowired
    ProductImpl productimpl;
    @Test
    public void findone() throws Exception {
        ProductInfo productInfo=productimpl.findone("234");
        Assert.assertNotNull(productInfo);

    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList=productimpl.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<ProductInfo> productInfoList=productimpl.findAll(request);
        Assert.assertNotEquals(0,productInfoList.getTotalElements());
    }

    /*@Test
    public void save() throws Exception {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setCategoryType(3);
        productInfo.setProductDescription("皮皮虾超级好喝！");
        productInfo.setProductPrice(new BigDecimal(9.33));
        productInfo.setProductName("皮皮虾");
        productInfo.setProductStock(0);
        productInfo.setProductIcon("http://abc.jpg");
        productInfo.setProductStatus(0);
        ProductInfo product=productimpl.save(productInfo);
        Assert.assertNotNull(product);
    }*/

}