package com.hiekn.knowledge.mining.bean;

import lombok.Data;

import java.util.Map;

@Data
public class NlpConfig  extends Config{

    /**
     * nlp工具
     */
    private String tool;

    /**
     * 使用的方法
     */
    private String method;

    /**
     * 下个步骤的配置
     */
    private Map next;
}
