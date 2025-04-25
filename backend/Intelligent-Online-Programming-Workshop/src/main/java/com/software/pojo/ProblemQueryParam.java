package com.software.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemQueryParam {
    private String title;
    private Integer difficulty;
    private String tags;
    private Integer page = 1;
    private Integer pageSize = 10;

    public List<String> getTagList() {
        return tags != null && !tags.isEmpty() ?
                Arrays.asList(tags.split(",")) :
                Collections.emptyList();
    }
}
