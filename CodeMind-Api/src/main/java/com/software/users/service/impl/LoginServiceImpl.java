package com.software.users.service.impl;

import com.software.pojo.LoginInfo;
import com.software.pojo.User;
import com.software.users.mapper.LoginMapper;
import com.software.users.service.LoginService;
import com.software.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public LoginInfo login(String email, String password) {
        User user = loginMapper.selectByEmailAndPassword(email, password);
        if(user != null){
            Map<String,Object> claims = Map.of("id",user.getId(),"username",user.getUsername(),"role",user.getRole());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(user.getId(),user.getUsername(),user.getRole(),jwt);
        }
        return null;
    }
}
