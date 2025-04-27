package com.software.users.controller;

import com.software.pojo.PageResult;
import com.software.pojo.Problem;
import com.software.pojo.ProblemQueryParam;
import com.software.pojo.Result;
import com.software.users.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problems")
    public Result page(ProblemQueryParam problemQueryParam){
        PageResult<Problem> allProblems = problemService.getAllProblems(problemQueryParam);
        return Result.success(allProblems);
    }
    @GetMapping("/problems/tags")
    public Result getTags(){
        List<Map<Integer,String>> tags = problemService.getTags();
        return Result.success(tags);
    }
}
