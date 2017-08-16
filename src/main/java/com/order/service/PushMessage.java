package com.order.service;

import com.order.dto.OrderDto;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
public interface PushMessage {
    void orderStatus(OrderDto orderDto);
}
