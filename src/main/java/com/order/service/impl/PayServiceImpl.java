package com.order.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
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
import org.springframework.stereotype.Service;

/**
 * Created by 醒悟wjn on 2017/8/1.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static  final String ORDER_NAME="微信点餐订单";
    @Autowired
    BestPayServiceImpl bestPsyService;
    @Autowired
    OrderService orderService;
    //支付
    @Override
    public PayResponse create(OrderDto orderDto) {
        PayRequest payRequest=new PayRequest();//设置传入参数
        payRequest.setOpenid(orderDto.getBuyerOpenid());//设置用户的openid
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());//设置总价
        payRequest.setOrderId(orderDto.getOrderId());//设置订单总价
        payRequest.setOrderName(ORDER_NAME);//设置订单名称
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);//设置支付方式
        log.info("【微信支付】 payrequest={}",payRequest);
        PayResponse payResponse=bestPsyService.pay(payRequest);//进行支付并且返回预支付信息
        log.info("【微信支付】reponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }
    //下单异步通知
    @Override
    public PayResponse notify(String notifyData){
        //1.验证签名
        //2.支付的状态
        PayResponse payResponse=bestPsyService.asyncNotify(notifyData);//规则或异步通知数据
        log.info("【微信支付】异步通知，payResponse={}", JsonUtil.toJson(payResponse));
        OrderDto orderDto=orderService.findone(payResponse.getOrderId());//依据异步通知信息查询有没有此订单
        //此订单是否存在
        if(orderDto==null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //3.验证支付金额
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDto.getOrderAmount().doubleValue())){
            log.error("【微信支付】异步通知，订单金额不一致，orderId={},微信通知金额={}，系统金额={}",
                    orderDto.getOrderId(),payResponse.getOrderAmount(),orderDto.getOrderAmount());
            throw new SellException(ResultEnum.WECHAT_NOTIFY_MONEY_ERROR);

        }
        //修改订单支付状态(已支付)
        orderService.paid(orderDto);
        return payResponse;
    }
    //退款
    @Override
    public RefundResponse refund(OrderDto orderDto){
        RefundRequest refundRequest=new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",refundRequest);
        RefundResponse refundResponse=bestPsyService.refund(refundRequest);//退款
        log.info("【微信退款】refundResponse={}",refundResponse);
        return  refundResponse;
    }
}
