package com.order.enums;

import lombok.Getter;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_ERROR(15,"订单取消失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情无商品"),
    ORDER_PAY_ERROR(17,"订单支付状态不正确"),
    ORDER_PAY_FAIL(18,"修改支付成功失败"),
    CART_EMPTY(19,"购物车为空"),
    ORDER_OWNER_ERROR(19,"该订单不属此主人"),
    WECHAT_MP_ERROR(20,"微信公众账号方面错误"),
    WECHAT_NOTIFY_MONEY_ERROR(21,"微信支付异步通知，校验金额不正确"),
    PRODUCT_UP_ERROR(22,"商品上架状态异常"),
    PRODUCT_DOWN_ERROR(23,"商品下架状态异常"),
    PRODUCT_IMAGE_ERROR(26,"商品图片没上传"),
    LOGIN_ERROR(24,"登陆失败"),
    QUIT_SUCCESS(25,"退出成功"),

    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
