package com.software.users.service;

import com.software.pojo.Problem;
import com.software.users.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProblemService {

    List<Problem> getAllProblems();

}
