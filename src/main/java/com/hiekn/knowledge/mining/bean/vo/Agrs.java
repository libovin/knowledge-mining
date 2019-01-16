package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Agrs {

    @ApiModelProperty(value = "算法")
    private String algorithm;

    @ApiModelProperty(value = "数量")
    private Integer size;

    @ApiModelProperty(value = "模式匹配Id")
    private String ruleId;

    @ApiModelProperty(value = "排序")
    private String sort;
}
