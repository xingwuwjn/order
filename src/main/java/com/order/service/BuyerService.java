package com.order.service;

import com.order.dto.OrderDto;

/**
 * Created by 醒悟wjn on 2017/7/30.
 */
public interface BuyerService {
    //查询一个订单
    OrderDto findOrderOne(String openid, String orderId);
    OrderDto cancelOrder(String openid, String orderId);
    //取消订单
}
