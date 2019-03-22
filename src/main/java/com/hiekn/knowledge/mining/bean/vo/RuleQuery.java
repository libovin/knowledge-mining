package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class RuleQuery {

    @ApiModelProperty(example = "数字规则", value = "规则名")
    private String name;

}
