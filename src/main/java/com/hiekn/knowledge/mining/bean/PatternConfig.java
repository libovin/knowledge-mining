package com.hiekn.knowledge.mining.bean;

import lombok.Data;

@Data
public class PatternConfig extends Config {

    /**
     * 正则表达式
     */
    private String pattern;

    /**
     * 匹配模式（全匹配/部分匹配）
     */
    private Boolean type;
}
