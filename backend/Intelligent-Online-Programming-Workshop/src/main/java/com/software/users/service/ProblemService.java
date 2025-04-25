package com.software.users.service;

import com.software.pojo.PageResult;
import com.software.pojo.Problem;
import com.software.pojo.ProblemQueryParam;
import com.software.users.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProblemService {

    PageResult<Problem> getAllProblems(ProblemQueryParam problemQueryParam);

}
