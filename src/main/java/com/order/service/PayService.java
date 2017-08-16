package com.order.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.order.dto.OrderDto;

/**
 * Created by 醒悟wjn on 2017/8/1.
 */
public interface PayService {
    PayResponse create(OrderDto orderDto);
    PayResponse notify(String notifyData);
    RefundResponse refund(OrderDto orderDto);
}
