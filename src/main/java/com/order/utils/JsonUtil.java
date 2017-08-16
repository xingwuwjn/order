package com.order.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by 醒悟wjn on 2017/8/1.
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);

    }
}
