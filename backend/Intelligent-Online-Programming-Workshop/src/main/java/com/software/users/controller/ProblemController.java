package com.software.users.controller;

import com.software.pojo.Problem;
import com.software.pojo.Result;
import com.software.users.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problems")
    public Result page(){
        List<Problem> allProblems = problemService.getAllProblems();
        return Result.success(allProblems);
    }

}
