package com.software.users.mapper;

import com.software.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from user where email=#{email} and password=#{password}")
    User selectByEmailAndPassword(String email, String password);
}
