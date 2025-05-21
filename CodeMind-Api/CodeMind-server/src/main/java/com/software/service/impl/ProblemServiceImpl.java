package com.software.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.software.dto.ProblemQueryDTO;
import com.software.entity.Problem;
import com.software.result.PageResult;
import com.software.mapper.ProblemMapper;
import com.software.service.ProblemService;
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
        return problemMapper.selectById(id);
    }

    @Override
    public PageResult<Problem> getAllProblems(ProblemQueryDTO problemQueryDTO) {
        List<String> tagList = problemQueryDTO.getTagList();  // 获得全部tagId

        if(tagList == null){
            log.info("tagList is null");
        }

        PageHelper.startPage(problemQueryDTO.getPage(),problemQueryDTO.getPageSize());


        List<Problem> allProblems = problemMapper.getAllProblems(problemQueryDTO.getTitle(), problemQueryDTO.getDifficulty(), tagList);

        Page<Problem> page = (Page<Problem>) allProblems;
        return new PageResult<Problem>(page.getTotal(),page.getResult());
    }
}
