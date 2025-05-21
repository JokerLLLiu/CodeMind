package com.software.controller.user;

import com.software.dto.ProblemQueryDTO;
import com.software.entity.Problem;
import com.software.result.PageResult;
import com.software.result.Result;
import com.software.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/problems")
@Slf4j
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping
    public Result page(ProblemQueryDTO problemQueryDTO){
        PageResult<Problem> allProblems = problemService.getAllProblems(problemQueryDTO);
        return Result.success(allProblems);
    }
    @GetMapping("/tags")
    public Result getTags(){
        List<Map<Integer,String>> tags = problemService.getTags();
        return Result.success(tags);
    }

    @GetMapping("/{id}")
    public Result getProblemById(@PathVariable Integer id){
        Problem problem = problemService.getProblemById(id);
        return Result.success(problem);
    }
}
