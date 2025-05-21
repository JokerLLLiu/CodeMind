package com.software.controller.user;

import com.software.entity.User;
import com.software.result.Result;
import com.software.service.UserService;
import com.software.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LoginVO loginVO = userService.login(user.getEmail(), user.getPassword());
        return Result.success(loginVO);
    }
}
