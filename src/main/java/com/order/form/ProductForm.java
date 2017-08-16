package com.order.form;

import com.order.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 醒悟wjn on 2017/8/6.
 */
@Data
public class ProductForm {
    private String productId;
    @NotEmpty(message="商品姓名必填")
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    @NotEmpty(message="商品描述必填")
    private String productDescription;
    private Integer categoryType;
}
