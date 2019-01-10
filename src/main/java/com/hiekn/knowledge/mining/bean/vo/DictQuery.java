package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class DictQuery {

    @ApiModelProperty(example = "会议名称字典", value = "表类型")
    private String name;
}
