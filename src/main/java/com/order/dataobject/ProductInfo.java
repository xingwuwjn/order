package com.order.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order.enums.ProductStatusEnum;
import com.order.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
    private Integer productStatus=ProductStatusEnum.DOWN.getCode();
    private Date createTime;
    private Date updateTime;
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

    public ProductInfo() {
    }
}
