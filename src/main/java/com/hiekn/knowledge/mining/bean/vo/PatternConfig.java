package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class PatternConfig extends Config {

    /**
     * 正则表达式
     */
    private String pattern;

}
