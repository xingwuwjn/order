package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
@Service
@Slf4j
public class PushMessageImpl implements PushMessage {
    @Autowired
    private WxMpService wxMpService;
    @Override
    public void orderStatus(OrderDto orderDto) {
        WxMpTemplateMessage templateMessage=new WxMpTemplateMessage();
        templateMessage.setTemplateId("fdsafds");//模板id
        templateMessage.setToUser("sdf");//发送给谁
        List<WxMpTemplateData> data= Arrays.asList(
                new WxMpTemplateData("first","亲，请记得收货。"),
                new WxMpTemplateData("keyword1",""),
                new WxMpTemplateData("keyword1",""),
                new WxMpTemplateData("keyword1",""),
                new WxMpTemplateData("keyword1",""),
                new WxMpTemplateData("keyword1","")
        );
        templateMessage.setData(data);//模板信息
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
