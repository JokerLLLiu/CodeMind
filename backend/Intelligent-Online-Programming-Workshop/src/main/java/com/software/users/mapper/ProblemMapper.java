package com.software.users.mapper;

import com.software.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProblemMapper {

    @Select("select * from problem")
    List<Problem> getAllProblems();
}
