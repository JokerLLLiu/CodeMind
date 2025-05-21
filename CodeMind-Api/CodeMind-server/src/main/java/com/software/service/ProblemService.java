package com.software.service;

import com.software.dto.ProblemQueryDTO;
import com.software.entity.Problem;
import com.software.result.PageResult;

import java.util.List;
import java.util.Map;

public interface ProblemService {

    // 获取题目列表
    PageResult<Problem> getAllProblems(ProblemQueryDTO problemQueryDTO);

    // 获取所有标签
    List<Map<Integer, String>> getTags();

    // 根据id获取题目
    Problem getProblemById(Integer id);
}
