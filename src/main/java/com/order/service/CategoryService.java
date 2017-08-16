package com.order.service;

import com.order.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
public interface CategoryService {
    public ProductCategory findone(Integer categoryId);
    List<ProductCategory> findall();
    List<ProductCategory> findByCategoryTypeIn(List<Integer>list);
    ProductCategory save(ProductCategory productCategory);
}
