package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;

@Data
@Document
@ApiModel
public class Config {

    @ApiModelProperty(value = "模型")
    @Pattern(regexp = "(nlp|counter|pattern|related)", message = "模型必须为nlp|counter|pattern|related")
    @NotBlank
    private String model;

    @ApiModelProperty(value = "输入")
    @Pattern(regexp = "(content|result)", message = "模型必须为content|result")
    @NotBlank
    private String input;

    @ApiModelProperty(value = "方法")
    @NotBlank
    private String method;

    @ApiModelProperty(value = "算法")
    private String algorithm;

    @ApiModelProperty(value = "数量")
    private Integer size;

    @ApiModelProperty(value = "模式匹配Id")
    private String pattern;
}
