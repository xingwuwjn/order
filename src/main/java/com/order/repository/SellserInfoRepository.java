package com.order.repository;

import com.order.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 醒悟wjn on 2017/8/7.
 */
public interface SellserInfoRepository extends JpaRepository<SellerInfo,String>
{
       SellerInfo findByOpenid(String openid);
}
