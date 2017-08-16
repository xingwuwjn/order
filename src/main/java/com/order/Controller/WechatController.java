package com.order.Controller;

import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * Created by 醒悟wjn on 2017/7/31.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController
{
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpService wxOpenService;
    //构造获取获取code的页面，并跳转

    /**
     * 官方文档https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
     * 发送请求（此方法在构造此请求），如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。
     * @param returnUrl  授权同意后跳转的页面
     * @return
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url="http://tsjcate.natapp1.cc/sell/wechat/userInfo";//获取code后跳转的地址 redirect_uri/?code=CODE&state=STATE。
        String result=wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权第一步】获取code的url,result={}",result);
        return "redirect:"+result;

        //调用方法
    }

    /**
     * 通过code换取网页授权access_token
     * @param code
     * @param returnUrl
     * @return
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken= wxMpService.oauth2getAccessToken(code);}
        catch(WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openId;//得到openid后跳转的页面
    }

    @GetMapping("/openAuthorize")
    public String Openauthorize(@RequestParam("returnUrl") String returnUrl){
        String url="http://tsjcate.natapp1.cc/sell/wechat/openUserInfo";//获取code后跳转的地址 redirect_uri/?code=CODE&state=STATE。
        String result=wxOpenService.buildQrConnectUrl(url,WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权第一步】获取code的url,result={}",result);
        return "redirect:"+result;

        //调用方法
    }
    @GetMapping("/openUserInfo")
    public String openUserInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken= wxOpenService.oauth2getAccessToken(code);}
        catch(WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openId;//得到openid后跳转的页面
    }
}
