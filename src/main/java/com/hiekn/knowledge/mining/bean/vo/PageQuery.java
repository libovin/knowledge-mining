package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;

@Data
public class PageQuery {

    @DefaultValue("10")
    @ApiModelProperty(example = "10")
    @Min(1)
    private Integer pageSize = 10;

    @DefaultValue("1")
    @ApiModelProperty(example = "1")
    @Min(1)
    private Integer pageNo = 1;

}
