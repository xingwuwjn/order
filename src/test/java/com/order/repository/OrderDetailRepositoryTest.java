package com.order.repository;

import com.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Test
    public void findByOrOrderId() throws Exception {
         List<OrderDetail> list=orderDetailRepository.findByOrOrderId("123456");
         Assert.assertNotEquals(0,list.size());

    }
     @Test
    public void saveTest(){
         OrderDetail orderDetail=new OrderDetail();
         orderDetail.setDetailId("123");
         orderDetail.setOrderId("123456");
         orderDetail.setProductIcon("http://abc.jpg");
         orderDetail.setProductId("123");
         orderDetail.setProductName("皮皮粥");
         orderDetail.setProductPrice(new BigDecimal(10.23));
         orderDetail.setProductQuantity(10);
         OrderDetail orderDetail1=orderDetailRepository.save(orderDetail);
         Assert.assertNotNull(orderDetail);

     }
}