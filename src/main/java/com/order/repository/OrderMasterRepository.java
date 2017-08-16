package com.order.repository;

import com.order.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 醒悟wjn on 2017/7/28.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    //根据买家微信的openid查询订单列表
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}
