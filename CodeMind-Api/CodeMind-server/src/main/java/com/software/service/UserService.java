package com.software.service;

import com.software.vo.LoginVO;

public interface UserService {
    LoginVO login(String email, String password);
}
