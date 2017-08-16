package com.order.service.impl;

import com.order.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryimplTest {
    @Autowired
    private Categoryimpl categoryimpl;
    @Test
    public void findone() throws Exception {
        ProductCategory productCategory=categoryimpl.findone(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findall() throws Exception {
        List<ProductCategory> productCategoryList=categoryimpl.findall();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList=categoryimpl.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory category=new ProductCategory("晚餐",4);
        ProductCategory productCategory=categoryimpl.save(category);
        Assert.assertNotNull(productCategory);

    }

}