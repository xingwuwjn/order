package com.order.dataobject;

import com.order.enums.OrderStatusEnums;
import com.order.enums.PayStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
     private String orderId;
     private String buyerName;
     private  String buyerPhone;
     private String  buyerAddress;
    private String buyerOpenid;//微信的openid
    private BigDecimal orderAmount;//总价
    private Integer orderStatus= OrderStatusEnums.NEW.getCode();//订单状态
    private Integer  payStatus= PayStatusEnums.WAIT.getCode();//支付状态
    private Date createTime;
    private Date updateTime;
    public OrderMaster() {
    }

}
