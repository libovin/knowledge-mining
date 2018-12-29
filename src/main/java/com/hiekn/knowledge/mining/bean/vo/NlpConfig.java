package com.hiekn.knowledge.mining.bean.vo;

import lombok.Data;

@Data
public class NlpConfig  extends Config {

    /**
     * nlp工具
     */
    private String tool;

    /**
     * 使用的方法
     */
    private String method;

    /**
     * 算法
     */
    private String algorithm;

}
