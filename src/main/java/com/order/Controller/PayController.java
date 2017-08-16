package com.order.Controller;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.order.dto.OrderDto;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.service.OrderService;
import com.order.service.PayService;
import com.order.utils.JsonUtil;
import com.order.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 醒悟wjn on 2017/8/1.
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
    @Autowired
    PayService payService;
    @Autowired
    OrderService orderService;
    @Autowired
    BestPayServiceImpl bestPsyService;
    /*@GetMapping("/pay")
    public ModelAndView create(@RequestParam("openid") String Openid){
        log.info("pay进来了");
        PayRequest payRequest=new PayRequest();//设置传入参数
        payRequest.setOpenid(Openid);//设置用户的openid
        payRequest.setOrderAmount(0.01);//设置总价
        payRequest.setOrderId("324234234234");//设置订单总价
        payRequest.setOrderName("springboot点餐");//设置订单名称
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);//设置支付方式
        PayResponse payResponsepay=bestPsyService.pay(payRequest);
        Map<String,Object> map=new HashMap<>();
        map.put("payResponse",payResponsepay);
        return new ModelAndView("pay/create",map);
    }*/

    /**
     * 创建订单
     * @param orderId
     * @param returnUrl
     * @return
     */
    @GetMapping("/create")
   public ModelAndView create(@RequestParam("orderId")String orderId,
                         @RequestParam("returnUrl") String returnUrl){
        OrderDto orderDto=orderService.findone(orderId);
        if(orderDto==null){
            log.error("【订单支付】，订单不存在");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponsepay=payService.create(orderDto);//统一下单，返回预支付内容
        //构建预支付内容
        Map<String,Object> map=new HashMap<>();
        map.put("payResponse",payResponsepay);
        map.put("returnUrl",returnUrl);//支付成功返回地址
        return new ModelAndView("pay/create",map);

        }

    /**
     * 支付成功异步通知
     * @param notifyData
     * @return
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
