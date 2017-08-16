package com.order.dto;

import lombok.Data;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Data
public class CartDto {
    private String productId;
    private  Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
