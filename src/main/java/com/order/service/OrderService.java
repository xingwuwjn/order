package com.order.service;

import com.order.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
public interface OrderService {
    //创建订单
    OrderDto create(OrderDto orderDto);
    //查询单个订单
    OrderDto findone(String orderId);

    //查询订单列表
    Page<OrderDto> findList(String buyerOpenid, Pageable page);
    //取消订单
    OrderDto cancel(OrderDto orderDto);
    //完成的订单
    OrderDto finish(OrderDto orderDto);
    //支付订单
    OrderDto paid(OrderDto orderDto);
    Page<OrderDto> findList(Pageable pageable);
}
