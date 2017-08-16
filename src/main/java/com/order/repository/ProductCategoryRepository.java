package com.order.repository;

import com.order.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/26.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
         List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

}
