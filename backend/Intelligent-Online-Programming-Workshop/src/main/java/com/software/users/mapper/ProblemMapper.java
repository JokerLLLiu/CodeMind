package com.software.users.mapper;

import com.software.pojo.Problem;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProblemMapper {

    List<Problem> getAllProblems(String title,Integer difficulty,List<String> tags);

    @MapKey("id")
    List<Map<Integer, String>> getAllTags();

    Problem getProblemById(Integer id);
}
