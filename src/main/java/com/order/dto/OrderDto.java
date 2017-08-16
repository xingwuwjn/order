package com.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.order.dataobject.OrderDetail;
import com.order.enums.CodeEnum;
import com.order.enums.OrderStatusEnums;
import com.order.enums.PayStatusEnums;
import com.order.utils.EnumUtil;
import com.order.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Data
/*@JsonInclude(JsonInclude.Include.NON_NULL)*///返回的json对象不为null,还可以全局配置
public class OrderDto {
    private String orderId;
    private String buyerName;
    private  String buyerPhone;
    private String  buyerAddress;
    private String buyerOpenid;//微信的openid
    private BigDecimal orderAmount;//总价
    private Integer orderStatus;//订单状态
    private Integer  payStatus;//支付状态
    @JsonSerialize(using= Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using= Date2LongSerializer.class)
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnums.class);
    }
    @JsonIgnore
    public PayStatusEnums getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnums.class);
    }
}
