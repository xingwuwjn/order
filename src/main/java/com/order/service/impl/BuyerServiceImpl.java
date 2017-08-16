package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.service.BuyerService;
import com.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 醒悟wjn on 2017/7/30.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    OrderService orderService;
    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto=checkOrderOwner(openid,orderId);
        if(orderDto==null){
            log.error("【取消订单】，没有此订单");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId){
        OrderDto orderDto=orderService.findone(orderId);
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查订单】，不是本人");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
