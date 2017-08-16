package com.order.utils;

import com.order.enums.CodeEnum;
import com.order.enums.OrderStatusEnums;

/**
 * Created by 醒悟wjn on 2017/8/3.
 */
public class EnumUtil {
    //获取指定的枚举
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
          for(T each:enumClass.getEnumConstants()){
              if(code.equals(each.getCode()))
              return each;
          }
          return  null;
    }
}
