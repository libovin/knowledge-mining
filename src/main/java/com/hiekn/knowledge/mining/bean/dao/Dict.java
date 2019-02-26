package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;

@Data
@Document
@ApiModel
public class Dict extends Base {

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "字典内容")
    private LinkedHashSet<String> text = new LinkedHashSet<>();
}
