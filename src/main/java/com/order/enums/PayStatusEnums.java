package com.order.enums;

import lombok.Getter;
import org.aopalliance.reflect.Code;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
@Getter
public enum  PayStatusEnums implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),

    ;
    private Integer code;
    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
