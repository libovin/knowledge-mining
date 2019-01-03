package com.hiekn.knowledge.mining.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatternFind {
    private String text;

    private int start;

    private int end;
}
