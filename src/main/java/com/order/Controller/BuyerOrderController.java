package com.order.Controller;

import com.order.converter.OrderFormToOrderDto;
import com.order.dataobject.OrderMaster;
import com.order.dto.OrderDto;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.form.OrderForm;
import com.order.result.ResultV0;
import com.order.service.WebSocket;
import com.order.service.impl.BuyerServiceImpl;
import com.order.service.impl.OrderServiceImpl;
import com.order.utils.ResultV0Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 醒悟wjn on 2017/7/30.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    WebSocket webSocket;
    @Autowired
    BuyerServiceImpl buyerService;
    @Autowired
    OrderServiceImpl orderService;
    //创建订单
    @PostMapping(value="/createOrder")
    public ResultV0 <Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
       //如果参数不正确
        if(bindingResult.hasErrors()){
           log.error("【创建订单】,参数不正确，orderForm={}",orderForm);
           throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());

       }
        OrderDto orderDto=OrderFormToOrderDto.convert(orderForm);//将从前端传入过来的参数转换为要查询的类
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】,购物车为空orderDtoDetail={}",orderDto.getOrderDetailList());
            throw new SellException(ResultEnum.CART_EMPTY);
        }
       OrderDto result=orderService.create(orderDto);
        Map<String ,String> map=new HashMap<>();
        map.put("orderId",result.getOrderId());
        webSocket.sendMessage("你有新订单");
        return ResultV0Util.success(map);
    }
    //订单列表
    @GetMapping(value="OrderList")
    public ResultV0<List<OrderDto>> orderlist(@RequestParam("openid") String openid,
                                                 @RequestParam(value = "page",defaultValue = "0") int page,
                                                 @RequestParam(value="size",defaultValue = "10")  int size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=new PageRequest(page,size);
        Page<OrderDto> orderDtoPage=orderService.findList(openid,request);
        return ResultV0Util.success(orderDtoPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultV0<OrderDto> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        //todo 不安全的要改进
        return ResultV0Util.success(buyerService.findOrderOne(openid,orderId));
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultV0 cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        //todo 不安全的要改进
        buyerService.cancelOrder(openid,orderId);
        return ResultV0Util.success();
    }
}
