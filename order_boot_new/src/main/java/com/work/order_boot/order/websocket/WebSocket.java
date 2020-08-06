package com.work.order_boot.order.websocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description:websocket配置文件
 * @Author: zhangzhiqing
 * @Date: 2020/8/5 17:39
 * @Param:
 * @Return:
 */

@Configuration
public class WebSocket {

    /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}