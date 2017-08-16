package com.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by 醒悟wjn on 2017/8/9.
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket>webSocketset=new CopyOnWriteArraySet<WebSocket>();

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketset.add(this);
        log.info("【websocket】,已连接，总数{}",webSocketset.size());
    }
    @OnClose
    public void onClose(){
        webSocketset.remove(this);
        log.info("【websocket】,连接断开，总数{}",webSocketset.size());
    }
    @OnMessage
    public void onMessage(String message){
        log.info("【websocket】,收到客户端发来的消息{}",message);
    }
    //发送消息
    public void sendMessage(String message){
        for(WebSocket webSocket:webSocketset){
            log.info("【websocket信息】，message={}",message);
            try{
                webSocket.session.getBasicRemote().sendText(message);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
