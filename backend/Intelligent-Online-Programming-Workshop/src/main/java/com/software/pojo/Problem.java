package com.software.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    private Integer id;
    private String title;
    private String description;
    private String inputExample;
    private String outputExample;
    private int timeLimit;
    private int memoryLimit;
    private int difficulty;
    private String tags;
    private double passRate;
    private int status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
