package com.software.users.mapper;

import com.software.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProblemMapper {

    List<Problem> getAllProblems(String title,Integer difficulty,List<String> tags);
}
