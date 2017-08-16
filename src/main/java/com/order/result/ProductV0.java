package com.order.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@Data
public class ProductV0 {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    public List<ProductInfoV0> list;
    public ProductV0() {
    }
}
