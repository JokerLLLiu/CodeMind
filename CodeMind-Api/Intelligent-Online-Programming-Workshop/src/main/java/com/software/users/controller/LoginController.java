package com.software.users.controller;

import com.software.pojo.LoginInfo;
import com.software.pojo.Result;
import com.software.pojo.User;
import com.software.users.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LoginInfo login = loginService.login(user.getEmail(), user.getPassword());
        if(login != null){
            return Result.success(login);
        }else{
            return Result.error("用户名或密码错误");
        }
    }
}
