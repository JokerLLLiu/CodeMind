package com.software.mapper;

import com.software.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);
}
