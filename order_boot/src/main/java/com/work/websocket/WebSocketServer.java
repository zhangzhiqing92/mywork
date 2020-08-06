package com.work.websocket;

/**
 * Simple to Introduction
 *
 * @ClassName: [WebSocketServer]
 * @Description: [一句话描述该类的功能]
 * @Author: [zhangzhiqing]
 * @CreateDate: [2020/8/5 17:40:58]
 * @Version: [v0.5]
 */
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import javax.websocket.*;

import lombok.extern.slf4j.Slf4j;
/**
 * @Description:
 * @Author: zhangzhiqing
 * @Date: 2020/8/5 17:43
 * @Param:
 * @Return:
 */
@Slf4j
// @ServerEndpoint("/websocket/{username}")
@Component // 此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
@ServerEndpoint("/websocket")
public class WebSocketServer {
    //注意@ServerEndpoint("/websocket")是你连接时的url，
    // 如果后端为127.0.0.1:8080，那么前端websocket连接url写为： 127.0.0.1:8080/websocket
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("有新连接加入！当前在线人数为" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }
    //	//连接打开时执行
    //	@OnOpen
    //	public void onOpen(@PathParam("user") String user, Session session) {
    //		currentUser = user;
    //		System.out.println("Connected ... " + session.getId());
    //	}

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息:" + message);

        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误
     * @param session
     * @param
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        log.info(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}