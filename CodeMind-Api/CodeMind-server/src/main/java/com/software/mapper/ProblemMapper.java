package com.software.mapper;

import com.software.entity.Problem;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProblemMapper {

    List<Problem> getAllProblems(String title, Integer difficulty, List<String> tags);

    @MapKey("id")
    @Select("select * from tag")
    List<Map<Integer, String>> getAllTags();

    @Select("select * from problem where id=#{id}")
    Problem selectById(Integer id);
}
