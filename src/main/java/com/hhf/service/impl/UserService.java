package com.hhf.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhf.entity.User;
import com.hhf.mapper.UserMapper;
import com.hhf.service.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
