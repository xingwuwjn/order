package com.order.service;

import com.order.dataobject.ProductInfo;
import com.order.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
public interface ProductService {
    //查询一件商品
    ProductInfo findone(String id);
    //查询上架的商品
    List<ProductInfo> findUpAll();
    //查询所有的商品
    Page<ProductInfo> findAll(Pageable pageable);
    //添加商品
    ProductInfo save(ProductInfo productInfo, MultipartFile file);
    //加库存
    void increateStock(List<CartDto> cartDTOList);
    //减库存
    void decreaseStock(List<CartDto> cartDTOList);
    //上架
    ProductInfo onSale(String productId);
    //下架
    ProductInfo down(String productId);

}
