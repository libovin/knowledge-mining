package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@ApiModel("Token")
public class Token  extends Base {

    @ApiModelProperty(value = "token名称")
    private String name;

    @ApiModelProperty(value = "token失效日期")
    private Date date;

    @ApiModelProperty(value = "token状态,0-close | 1-open")
    private Integer active;

    @ApiModelProperty(value = "token值",hidden = true)
    private String content;

}
