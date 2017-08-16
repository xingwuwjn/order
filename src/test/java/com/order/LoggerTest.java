package com.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 醒悟wjn on 2017/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
   /* private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);*/
    @Test
    public void test1(){
        String name="wjn";
        String password="123456";
        /*log.info("name:"+name+"password:"+password);*/
        log.info("name:{},password:{}",name,password);
        log.debug("debug..");
        log.info("info..");
        log.error("error..");
    }

}
