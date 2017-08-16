package com.order.utils;

import com.order.result.ProductV0;
import com.order.result.ResultV0;

import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
public class ResultV0Util {
    //设置返回的数据类型（查看文档）
    public  static ResultV0 success(Object object){
        ResultV0 resultV0=new ResultV0();
        resultV0.setMsg("成功");
        resultV0.setCode(0);
        resultV0.setData(object);
        return resultV0;
    }
    //直接返回成功
    public  static ResultV0 success(){
        return success(null);
    }
    //
    public  static ResultV0 error(String msg,Integer code){
        ResultV0 resultV0=new ResultV0();
        resultV0.setMsg(msg);
        resultV0.setCode(code);
        return resultV0;
    }
}
