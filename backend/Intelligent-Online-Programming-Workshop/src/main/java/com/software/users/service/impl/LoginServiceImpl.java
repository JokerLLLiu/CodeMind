package com.software.users.service.impl;

import com.software.pojo.User;
import com.software.users.mapper.LoginMapper;
import com.software.users.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public User login(String email, String password) {
        User user = loginMapper.selectByEmailAndPassword(email, password);
        return user;
    }
}
