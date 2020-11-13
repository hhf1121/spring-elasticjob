package com.hhf.socketio.redisConfig;


import com.corundumstudio.socketio.SocketIOClient;
import com.hhf.socketio.dto.WebSocketDto;
import com.hhf.socketio.service.MessageEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author litong
 */
@Service(value = "redisMessageListener")
@Slf4j
public class redisSub implements MessageListener {

    @Autowired
    private MessageEventHandler messageEventHandler;

    @Value("${server.port}")
    private String port;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("redis消费,服务端口:"+port);
        WebSocketDto entity = JSONObject.parseObject(message.getBody(), WebSocketDto.class);
        String channel = (String) redisTemplate.getValueSerializer().deserialize(message.getChannel());
        List<String> agentCodes = entity.getAgentCodes();
        if (!StringUtils.isEmpty(channel) && !agentCodes.isEmpty()) {
            for (String agentCode : agentCodes) {
                // 向客户端推送消息
                messageEventHandler.sendMessageOne(agentCode,entity.getType());
            }
//            if (userClient != null & !userClient.isEmpty()) {
//                userClient.forEach((uuid, socketIOClient) -> {
//                    socketIOClient.sendEvent("chatevent", pushMsgEntity.getMessage());
//                });
//            }
        }

    }
}
