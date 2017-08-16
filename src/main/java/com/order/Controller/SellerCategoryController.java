package com.order.Controller;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.order.dataobject.ProductCategory;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.form.CategoryForm;
import com.order.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by 醒悟wjn on 2017/8/6.
 */
@Controller
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品类别列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> list=categoryService.findall();
        map.put("CategoryList",list);
        return new ModelAndView("/category/list",map);
    }

    /**
     * 跳转到修改/添加页面
     * @param categoryId
     * @param map
     * @return
     */
        @GetMapping("index")
        public ModelAndView list(@RequestParam(value="categoryId",required = false) Integer categoryId,Map<String,Object>map){
            ProductCategory productCategory;
              //修改
             if(categoryId!=null){
                 productCategory=categoryService.findone(categoryId);
                 map.put("category",productCategory);
             }
             return new ModelAndView("/category/index",map);
        }
    /**
     * 添加/修改分类
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/add")
    public ModelAndView add(@Valid CategoryForm categoryForm,
                            BindingResult bindingResult,
                            Map<String,Object> map){
         if(bindingResult.hasErrors()){
             log.error("【添加分类】，参数不正确={}",categoryForm);
             map.put("msg",bindingResult.getFieldError().getDefaultMessage());
             map.put("url","/sell/seller/category/index");
             return  new ModelAndView("order/error",map);
         }
        ProductCategory productCategory=new ProductCategory();
        BeanUtils.copyProperties(categoryForm,productCategory);
        try{
            categoryService.save(productCategory);
        }
        catch(Exception e){
            log.error("【卖家端保存商品分类】，发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/list");
            return new ModelAndView("order/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("order/success",map);
    }

}
