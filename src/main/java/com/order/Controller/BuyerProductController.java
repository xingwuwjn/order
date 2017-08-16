package com.order.Controller;

import com.order.dataobject.ProductCategory;
import com.order.dataobject.ProductInfo;
import com.order.result.ProductInfoV0;
import com.order.result.ProductV0;
import com.order.result.ResultV0;
import com.order.service.impl.Categoryimpl;
import com.order.service.impl.ProductImpl;
import com.order.utils.ResultV0Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductImpl productService;
    @Autowired
    private Categoryimpl categoryService;
    @GetMapping("/list")
    public ResultV0 list(){
        //数据库查询所有上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //查询类目
        List<Integer> categoryid=productInfoList.stream().map(productInfo -> productInfo.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> ProductCategoryList=categoryService.findByCategoryTypeIn(categoryid);
        List <ProductV0> productV0List=new ArrayList<>();//存放所有的类目
        for(ProductCategory productCategory:ProductCategoryList){//遍历所有类目，构造类目实体
            ProductV0 productV0=new ProductV0();
            productV0.setCategoryName(productCategory.getCategoryName());
            productV0.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoV0>productInfoList1=new ArrayList<>();
            //找到此类目下的所有商品
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoV0 productInfoV0=new ProductInfoV0();
                    BeanUtils.copyProperties(productInfo,productInfoV0);
                    productInfoList1.add(productInfoV0);
                }
            }
            productV0.setList(productInfoList1);
            productV0List.add(productV0);
        }
        return ResultV0Util.success(productV0List);
    }
}
