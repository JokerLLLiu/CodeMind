package com.software.users.controller;

import com.software.pojo.Problem;
import com.software.pojo.ProblemQueryParam;
import com.software.pojo.Result;
import com.software.users.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problems")
    public Result page(ProblemQueryParam problemQueryParam){
        log.info("problemQueryParam的值为：{}", problemQueryParam);
        List<Problem> allProblems = problemService.getAllProblems();
        return Result.success(allProblems);
    }
}
