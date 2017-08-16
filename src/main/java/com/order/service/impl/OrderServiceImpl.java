package com.order.service.impl;

import com.order.converter.OrderMastertoOrderDto;
import com.order.dataobject.OrderDetail;
import com.order.dataobject.OrderMaster;
import com.order.dataobject.ProductInfo;
import com.order.dto.CartDto;
import com.order.dto.OrderDto;
import com.order.enums.OrderStatusEnums;
import com.order.enums.PayStatusEnums;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.repository.OrderDetailRepository;
import com.order.repository.OrderMasterRepository;
import com.order.service.OrderService;
import com.order.service.PayService;
import com.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    PayService payService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductImpl productService;
//创建订单
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId= KeyUtil.getUniqueKey();//随机生产订单id
        BigDecimal orderamout=new BigDecimal(0);
        /*List<CartDto> list=new ArrayList<>();*/
        //查询商品（数量，价格）
       for(OrderDetail orderDetail:orderDto.getOrderDetailList()) {
             ProductInfo productInfo=productService.findone(orderDetail.getProductId());//获得实际的商品
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价
           orderamout=
                   productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                   .add(orderamout);
            //订单详情入库
             orderDetail.setDetailId(KeyUtil.getUniqueKey());
             orderDetail.setOrderId(orderId);
             BeanUtils.copyProperties(productInfo,orderDetail);
             orderDetailRepository.save(orderDetail);
            /* CartDto cartDto=new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
             list.add(cartDto);*/
       }
        //订单写入数据库
        OrderMaster orderMaster=new OrderMaster();
        orderDto.setOrderId(orderId);//设置随机产生的订单
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderamout);//设置订单总价
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderMasterRepository.save(orderMaster);//保存订单
        //批量减库存
        List<CartDto> list=//构造商品id+商品数量的集合
        orderDto.getOrderDetailList().stream().map(
                orderDetail ->new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(list);
        return orderDto;
    }
//根据订单id查找订单
    @Override
    public OrderDto findone(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> list=orderDetailRepository.findByOrOrderId(orderId);
        if(CollectionUtils.isEmpty(list)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(list);
        return orderDto;
    }
//查询订单列表
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable page) {
        Page<OrderMaster>orderMasterPage= orderMasterRepository.findByBuyerOpenid(buyerOpenid,page);
        List<OrderDto>list=OrderMastertoOrderDto.convert(orderMasterPage.getContent());//将List<orderMaster>转换为orderdto
        Page<OrderDto> orderDtoPage=new PageImpl<OrderDto>(list,page,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }
//取消订单
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster=new OrderMaster();

        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
          log.error("取消订单，订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
          throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDto.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);//将orderDto的属性复制到orderMaster中
        OrderMaster orderMaster1=orderMasterRepository.save(orderMaster);
        if(orderMaster1==null){
            log.error("订单取消失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】订单中无商品，orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList=orderDto.getOrderDetailList().stream().map(e->new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.increateStock(cartDtoList);
        //如果已经支付需要退款
        if(orderDto.getPayStatus().equals(PayStatusEnums.SUCCESS.getCode())){
            //TODO
            payService.refund(orderDto);//退款
        }
        return orderDto;
    }

 //订单完成
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("完结订单，订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnums.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);//将orderDto的属性复制到orderMaster中
        OrderMaster orderMaster1=orderMasterRepository.save(orderMaster);
        if(orderMaster1==null){
            log.error("订单完成失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }
//修改支付状态
    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【修改订单支付状态】，订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDto.getPayStatus().equals(PayStatusEnums.WAIT.getCode())){
            log.error("【订单支付状态不正确】，payStatus={}",orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);//将orderDto的属性复制到orderMaster中
        OrderMaster orderMaster1=orderMasterRepository.save(orderMaster);
        if(orderMaster1==null){
            log.error("【订单支付完成】，更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_FAIL);
        }
        return orderDto;
    }


    /*----------------------------------------------------------网站后台-----------------------------------------------*/

    //网站后台获得所有的订单
    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster>orderMasterPage=orderMasterRepository.findAll(pageable);
        List<OrderDto>list=OrderMastertoOrderDto.convert(orderMasterPage.getContent());//将List<orderMaster>转换为orderdto
        Page<OrderDto> orderDtoPage=new PageImpl<OrderDto>(list,pageable,orderMasterPage.getTotalElements());
        Assert.assertTrue("查询所有订单列表",list.size()>0);
        return orderDtoPage;
    }
}
