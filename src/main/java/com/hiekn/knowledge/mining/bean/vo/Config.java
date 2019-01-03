package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public abstract class Config {

    private String model;

    private String source;

    private String method;
}
