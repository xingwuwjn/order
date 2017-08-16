package com.order.service;

import com.order.dataobject.SellerInfo;
import com.order.repository.SellserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 醒悟wjn on 2017/8/7.
 */
@Service
public class SellerLoginServiceImpl implements SellerLoginService {
    @Autowired
    SellserInfoRepository repository;
    @Override
    public SellerInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
