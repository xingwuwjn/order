package com.order.handler;

import com.order.config.ProjectUrl;
import com.order.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 醒悟wjn on 2017/8/8.
 */
@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    ProjectUrl projectUrl;
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                        .concat(projectUrl.getSell())
                        .concat("/sell/wechat/openAuthorize")
                        .concat("?returnUrl=")
                        .concat(projectUrl.getSell())
                        .concat("/sell/seller/login"));
    }
}
