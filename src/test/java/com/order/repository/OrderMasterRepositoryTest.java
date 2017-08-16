package com.order.repository;

import com.order.dataobject.OrderMaster;
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
 * Created by 醒悟wjn on 2017/7/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request=new PageRequest(0,1);
        Page<OrderMaster> list=orderMasterRepository.findByBuyerOpenid("1324",request);
        System.out.println(list.getTotalElements());
        Assert.assertNotEquals(0,list.getTotalElements());
    }
    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerAddress("宁夏贺兰县一品尚都");
         orderMaster.setBuyerName("王佳楠");
         orderMaster.setBuyerOpenid("1324");
         orderMaster.setBuyerPhone("17795150469");
         orderMaster.setOrderAmount(new BigDecimal(100.0));
         orderMaster.setOrderId("123456");
         OrderMaster order=orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(order);
    }

}