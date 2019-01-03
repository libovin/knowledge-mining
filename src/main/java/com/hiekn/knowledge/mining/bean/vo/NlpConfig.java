package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class NlpConfig  extends Config {

    private String algorithm;

    private Integer size;

}
