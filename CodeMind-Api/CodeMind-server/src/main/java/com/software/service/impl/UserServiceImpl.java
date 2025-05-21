package com.software.service.impl;

import com.software.constant.MessageConstant;
import com.software.entity.User;
import com.software.exception.LoginFailedException;
import com.software.properties.JwtProperties;
import com.software.mapper.UserMapper;
import com.software.service.UserService;
import com.software.utils.JwtUtils;
import com.software.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public LoginVO login(String email, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("email:{},password:{}",email,password);

        User user = userMapper.selectByEmail(email);
        if(user != null && user.getPassword().equals(password)){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            String jwt = JwtUtils.generateJwt(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
            log.info("jwt:{}",jwt);
            return new LoginVO(user.getId(),user.getUsername(),user.getRole(),jwt);
        }else {
            throw new LoginFailedException(MessageConstant.ACCOUNT_OR_PASSWORD_ERROR);
        }
    }
}
