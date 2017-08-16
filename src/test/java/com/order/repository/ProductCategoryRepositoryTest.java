package com.order.repository;

import com.order.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
@Autowired
    private ProductCategoryRepository repository;
@Test
    public void findOneTest(){
    ProductCategory productCategory=repository.findOne(1);
    System.out.println(productCategory.toString());
}
    @Transactional  /*测试之后没有向数据库中实际插入数据*/
    @Test
    public void saveOneTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("午餐");
        productCategory.setCategoryType(3);
        ProductCategory result=repository.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list=Arrays.asList(1,2,3);
        List<ProductCategory> CategoryList=repository.findByCategoryTypeIn(list);
       Assert.assertNotEquals(0,CategoryList.size());
    }
}