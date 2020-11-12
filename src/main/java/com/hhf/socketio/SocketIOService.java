package com.hhf.socketio;


import com.corundumstudio.socketio.SocketIOServer;
import com.hhf.socketio.dto.WebSocketDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @author: zyu
 * @description:
 * @date: 2019/4/23 10:41
 */

@Order(1)
@Slf4j
@Component
public class SocketIOService implements CommandLineRunner {

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... strings) {
        socketIOServer.start();
        log.info("socket.io启动成功！");
    }
}