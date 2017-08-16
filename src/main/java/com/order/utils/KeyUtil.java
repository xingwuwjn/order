package com.order.utils;

import java.util.Random;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
public class KeyUtil {
    /**
     * 生产唯一主键
     * 格式：时间加随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        System.out.println(System.currentTimeMillis());
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
