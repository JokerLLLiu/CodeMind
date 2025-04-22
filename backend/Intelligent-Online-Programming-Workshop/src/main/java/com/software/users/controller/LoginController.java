package com.software.users.controller;

import com.software.pojo.Result;
import com.software.pojo.User;
import com.software.users.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(String email, String password) {
        User user = loginService.login(email, password);
        if(user != null){
            return Result.success(user);
        }else{
            return Result.error("用户名或密码错误");
        }
    }
}
