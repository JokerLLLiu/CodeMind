package com.software.users.service;

import com.software.pojo.PageResult;
import com.software.pojo.Problem;
import com.software.pojo.ProblemQueryParam;
import com.software.users.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface ProblemService {

    // 获取题目列表
    PageResult<Problem> getAllProblems(ProblemQueryParam problemQueryParam);

    // 获取所有标签
    List<Map<Integer, String>> getTags();
}
