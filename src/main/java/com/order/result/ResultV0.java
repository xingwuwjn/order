package com.order.result;

import lombok.Data;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@Data
public class ResultV0 <T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultV0() {
    }
}
