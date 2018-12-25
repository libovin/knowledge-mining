package com.hiekn.knowledge.mining.bean;

import lombok.Data;

@Data
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
