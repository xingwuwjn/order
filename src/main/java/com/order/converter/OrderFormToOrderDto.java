package com.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.order.dataobject.OrderDetail;
import com.order.dto.OrderDto;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/30.
 */
@Slf4j
public class OrderFormToOrderDto {
    public static OrderDto convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        orderDto.setBuyerName(orderForm.getName());
        List<OrderDetail> list=new ArrayList<>();
        try {
            list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());/*将json转换为list*/
        }
        catch(Exception e){
             log.error("【对象转换出错】orderForm={}",orderForm.getItems());
             throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(list);
        return orderDto;
        }
}
