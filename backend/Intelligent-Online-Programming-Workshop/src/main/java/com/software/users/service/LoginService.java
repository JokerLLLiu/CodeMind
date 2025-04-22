package com.software.users.service;

import com.software.pojo.User;

public interface LoginService {
    User login(String email, String password);
}
