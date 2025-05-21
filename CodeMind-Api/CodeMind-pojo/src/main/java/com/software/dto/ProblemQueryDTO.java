package com.software.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemQueryDTO {
    private String title;
    private Integer difficulty;
    private String tags;
    private Integer page;
    private Integer pageSize;

    public List<String> getTagList() {
        return tags != null && !tags.isEmpty() ?
                Arrays.asList(tags.split(",")) :
                new ArrayList<>();
    }
}
