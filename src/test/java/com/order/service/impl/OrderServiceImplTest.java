package com.order.service.impl;

import com.order.dataobject.OrderDetail;
import com.order.dto.OrderDto;
import com.order.enums.OrderStatusEnums;
import com.order.enums.PayStatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/7/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private String BUYEROPENID="134";
    private String ORDERID="1501299782277638692";
    @Test
    public void create() throws Exception {
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerAddress("宁夏贺兰一品尚都");
        orderDto.setBuyerName("王佳楠");
        orderDto.setBuyerOpenid("134");
        orderDto.setBuyerPhone("177795150469");
        List<OrderDetail> list=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductQuantity(1);
        orderDetail.setProductId("123");
        list.add(orderDetail);
        orderDto.setOrderDetailList(list);
        OrderDto result=orderService.create(orderDto);
        log.info("创建订单result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findone() throws Exception {
        OrderDto orderDto=orderService.findone(ORDERID);
        log.info("【查询单个订单】result={}",orderDto);
        Assert.assertEquals(ORDERID,orderDto.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<OrderDto> list=orderService.findList(BUYEROPENID,request);
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDto orderDto=orderService.findone(ORDERID);
        OrderDto result=orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnums.CANCEL.getCode(),result.getOrderStatus());
    }
   /*完成订单测试*/
    @Test
    public void finish() throws Exception {
        OrderDto orderDto=orderService.findone(ORDERID);
        OrderDto result=orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnums.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDto orderDto=orderService.findone(ORDERID);
        OrderDto result=orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnums.SUCCESS.getCode(),result.getPayStatus());
    }
    @Test
    public void list(){
        PageRequest request=new PageRequest(0,2);
        Page<OrderDto> list=orderService.findList(request);
        Assert.assertNotEquals(0,list.getTotalElements());
    }

}