package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.service.OrderService;
import com.order.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 醒悟wjn on 2017/8/1.
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class PayServiceImplTest {
    @Autowired
    OrderService orderService;
    @Autowired
    private PayServiceImpl payService;
    @Test
    public void create() throws Exception {
        OrderDto orderDto=orderService.findone("1501414933283709134");
        payService.create(orderDto);
    }

}