package com.software.users.service;

import com.software.pojo.LoginInfo;
import com.software.pojo.User;

public interface LoginService {
    LoginInfo login(String email, String password);
}
