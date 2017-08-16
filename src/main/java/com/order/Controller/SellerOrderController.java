package com.order.Controller;

import com.order.dto.OrderDto;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by 醒悟wjn on 2017/8/3.
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    OrderServiceImpl orderService;
    //订单列表
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1") Integer page,
                             @RequestParam(value="size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<OrderDto> pageList=orderService.findList(pageRequest);//查询出所有的订单
        map.put("orderDtoPage",pageList);
        map.put("currentPage",page);//当前页码
        map.put("size",size);//每页个数
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam ("orderId") String orderId,
                               Map<String,Object> map){
        try{
            OrderDto orderDto=orderService.findone(orderId);
            orderService.cancel(orderDto);}
        catch(SellException e){
            log.error("【卖家端取消订单】，发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("order/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("order/success",map);
    }
//订单详情
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,Map<String,Object> map){
        OrderDto orderDto;
        try{
            orderDto=orderService.findone(orderId);

           }
        catch(SellException e){
            log.error("【卖家端查询订单详情】，发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("order/error",map);
        }
        map.put("orderDto",orderDto);
        return new ModelAndView("order/detail",map);


    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,Map<String,Object> map){
        OrderDto orderDto;
        try{
            orderDto=orderService.findone(orderId);
            orderService.finish(orderDto);
        }
        catch(SellException e){
            log.error("【卖家端完结订单】，发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("order/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("order/success",map);
    }
}
