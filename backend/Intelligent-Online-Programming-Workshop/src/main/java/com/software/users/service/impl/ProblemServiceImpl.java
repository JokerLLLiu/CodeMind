package com.software.users.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.software.pojo.PageResult;
import com.software.pojo.Problem;
import com.software.pojo.ProblemQueryParam;
import com.software.users.mapper.ProblemMapper;
import com.software.users.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public PageResult<Problem> getAllProblems(ProblemQueryParam problemQueryParam) {
        PageHelper.startPage(problemQueryParam.getPage(),problemQueryParam.getPageSize());

        List<String> tagList = problemQueryParam.getTagList();
        List<Problem> allProblems = problemMapper.getAllProblems(problemQueryParam.getName(), problemQueryParam.getDifficulty(), tagList);

        Page<Problem> page = (Page<Problem>) allProblems;
        return new PageResult<Problem>(page.getTotal(),page.getResult());
    }
}
