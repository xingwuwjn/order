package com.order.Controller;

import com.order.dataobject.ProductCategory;
import com.order.dataobject.ProductInfo;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.form.ProductForm;
import com.order.service.impl.Categoryimpl;
import com.order.service.impl.ProductImpl;
import com.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.core.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by 醒悟wjn on 2017/8/4.
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private Categoryimpl categoryservice;
    @Autowired
    private ProductImpl productservice;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1") Integer page,
                             @RequestParam(value="size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<ProductInfo> pagelist=productservice.findAll(pageRequest);
        map.put("ProductPage",pagelist);
        map.put("currentPage",page);//当前页码
        map.put("size",size);//每页个数
        return new ModelAndView("product/list",map);

    }
    //上架
    @GetMapping("up")
    public ModelAndView onsale(@RequestParam("productId") String productId,Map<String,Object>map){
        try{
        productservice.onSale(productId);}
        catch(SellException e){
            log.error("【卖家端商品上架】，发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("order/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("order/success",map);
    }
//下架
    @GetMapping("down")
    public ModelAndView downsale(@RequestParam("productId") String productId,Map<String,Object>map){
        try{
            productservice.down(productId);}
        catch(SellException e){
            log.error("【卖家端商品下架】，发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("order/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("order/success",map);
    }
    //修改商品或添加商品
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value="productId",required = false) String productId,
                              Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo=productservice.findone(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> list=categoryservice.findall();//将所有的类目传入
        map.put("categoryList",list);
        return new ModelAndView("product/index",map);
    }

    /**
     * 新建商品或修改商品
     * @param
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,BindingResult bindingResult,
                             Map<String,Object> map, MultipartFile dealImgFile){
        if(bindingResult.hasErrors()){
            log.error("【修改商品】,参数不正确，productForm={}",productForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        ProductInfo productInfo=new ProductInfo();
        //新增
        if(StringUtils.isEmpty(productForm.getProductId())){
            productForm.setProductId(KeyUtil.getUniqueKey());
            if(dealImgFile.isEmpty()){
                log.error("【卖家端保存商品】，商品图片未上传{}",dealImgFile);
                map.put("msg", "商品图片未上传");
                map.put("url","/sell/seller/product/list");
                return new ModelAndView("order/error",map);
            }
        }
        //修改
        else{
         productInfo=productservice.findone(productForm.getProductId());
        }
         BeanUtils.copyProperties(productForm,productInfo);
        try{
            productservice.save(productInfo,dealImgFile);
        }
        catch(SellException e){
            log.error("【卖家端保存商品】，发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("order/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("order/success",map);
    }
}
