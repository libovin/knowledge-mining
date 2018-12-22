package com.hiekn.knowledge.mining.bean.nlp;

import com.hiekn.knowledge.mining.bean.Config;
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
