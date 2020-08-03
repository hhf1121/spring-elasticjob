package com.hhf.controller;


import com.hhf.entity.User;
import com.hhf.service.IUserService;
import com.hhf.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("test/{info}")
    public Map<String,Object> test(@PathVariable("info") String info ){
        User byId = userService.getById(1);
        log.info(byId.toString());
        return ResultUtils.getSuccessResult(info);
    }

}
