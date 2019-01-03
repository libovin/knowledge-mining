package com.hiekn.knowledge.mining.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Counter {
    /**
     * 词
     */
    private String word;
    /**
     * 频次
     */
    private Integer count;

    /**
     * 频率
     */
    private double frequency;

}
