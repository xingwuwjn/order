package com.order.service.impl;

import com.order.dataobject.ProductCategory;
import com.order.repository.ProductCategoryRepository;
import com.order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@Service
public class Categoryimpl implements CategoryService{
    @Autowired
    ProductCategoryRepository repository;
    @Override
    public ProductCategory findone(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findall() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return repository.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
