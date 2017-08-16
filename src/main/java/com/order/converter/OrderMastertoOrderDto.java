package com.order.converter;

import com.order.dataobject.OrderMaster;
import com.order.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 醒悟wjn on 2017/7/29.
 */
public class OrderMastertoOrderDto {
    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }
    public static List<OrderDto>convert(List<OrderMaster> orderMaster){
        return orderMaster.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
