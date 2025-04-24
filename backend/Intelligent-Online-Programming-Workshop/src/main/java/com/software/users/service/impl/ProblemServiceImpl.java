package com.software.users.service.impl;

import com.software.pojo.Problem;
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
    public List<Problem> getAllProblems() {
        return problemMapper.getAllProblems();
    }
}
