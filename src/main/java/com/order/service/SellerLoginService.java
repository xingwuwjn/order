package com.order.service;

import com.order.dataobject.SellerInfo;

/**
 * Created by 醒悟wjn on 2017/8/7.
 */
public interface SellerLoginService {
    SellerInfo findByOpenid(String openid);
}
