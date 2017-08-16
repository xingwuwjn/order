package com.order.enums;

import lombok.Getter;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Getter
public enum OrderStatusEnums implements  CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),;

    private Integer code;
    private  String msg;

    OrderStatusEnums(Integer code,String msg) {
        this.msg = msg;
        this.code = code;
    }
   /* public OrderStatusEnums getOrderStausEnums(Integer code){
        for(OrderStatusEnums orderStatusEnums:OrderStatusEnums.values()){
            if(orderStatusEnums.getCode()==code){
                return orderStatusEnums;
            }
        }
        return null;
    }*/
}
