package com.chengqj.study.websocket.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author chengqj
 * @Date 2020/11/12 20:33
 * @Desc
 */
@Component
@Slf4j
@ServerEndpoint(value = "/ws/asset")
public class WSWebSocketServer {
    AtomicInteger onlineCount = new AtomicInteger();
    // concurrent包的线程安全list,用来存放每个客户端对应的Session对象
    private static CopyOnWriteArrayList<Session> sessionList = new CopyOnWriteArrayList<>();

    /**
     * 发送消息
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionList.add(session);
        int cnt = onlineCount.incrementAndGet(); // 在线数+1
        log.info("有连接加入,当前连接数为:{}", cnt);
    }

    /**
     * 接收消息
     *
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("来自客户端的消息:{}", message);
        // sendMessage(session, "Echo消息内容: " + message);
        batchSendMessage(message);
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        sessionList.remove(session);
        int cnt = onlineCount.decrementAndGet();
        log.info("有连接关闭,当前连接数为:{}", cnt);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误:{},Session ID:{}", error.getMessage(), session.getId());
    }

    private static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(String.format("%s (From Server. Session ID=%s)", message, session.getId()));
    }

    private static void batchSendMessage(String message) throws IOException {
        for (Session session : sessionList) {
            sendMessage(session, message);
        }
    }


}
