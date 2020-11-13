package com.hhf.controller;


import com.alibaba.fastjson.JSON;
import com.hhf.entity.User;
import com.hhf.service.IUserService;
import com.hhf.socketio.dto.WebSocketDto;
import com.hhf.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IUserService userService;

    @RequestMapping("test/{info}")
    public Map<String,Object> test(@PathVariable("info") String info ){
        User byId = userService.getById(1);
        log.info(byId.toString());
        return ResultUtils.getSuccessResult(info);
    }

    /**
     * redis-发布订阅、解决socketio集群问题
     * @param dto
     * @return
     */
    @PostMapping("/sendMessageOne")
    public Map<String,Object> sendMessageOne(@RequestBody  WebSocketDto dto){
        redisTemplate.convertAndSend("ws:message", JSON.toJSONString(dto));
        return ResultUtils.getSuccessResult("发送成功！");
    }

}
