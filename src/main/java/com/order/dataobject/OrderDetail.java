package com.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Entity
@Data
public class OrderDetail {
    @Id
      private String detailId;
      private String  orderId;
      private String productId;
      private String  productName;
      private BigDecimal productPrice;
      private Integer productQuantity;//商品总数
      private String  productIcon;//商品图片路径

    public OrderDetail() {
    }
}
