package com.order.exception;

import com.order.enums.ResultEnum;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
public class SellException extends RuntimeException{
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SellException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
}
