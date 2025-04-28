package com.software.users.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.software.pojo.PageResult;
import com.software.pojo.Problem;
import com.software.pojo.ProblemQueryParam;
import com.software.users.mapper.ProblemMapper;
import com.software.users.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<Map<Integer, String>> getTags() {
        return problemMapper.getAllTags();
    }

    @Override
    public Problem getProblemById(Integer id) {
        return problemMapper.getProblemById(id);
    }

    @Override
    public PageResult<Problem> getAllProblems(ProblemQueryParam problemQueryParam) {
        PageHelper.startPage(problemQueryParam.getPage(),problemQueryParam.getPageSize());

        List<String> tagList = problemQueryParam.getTagList();  // 获得全部tagId
        List<Problem> allProblems = problemMapper.getAllProblems(problemQueryParam.getTitle(), problemQueryParam.getDifficulty(), tagList);

        Page<Problem> page = (Page<Problem>) allProblems;
        return new PageResult<Problem>(page.getTotal(),page.getResult());
    }
}
