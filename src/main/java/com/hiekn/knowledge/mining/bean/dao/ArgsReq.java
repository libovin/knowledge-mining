package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
@ApiModel
public class ArgsReq {

    @ApiModelProperty(value = "算法", example = "crf")
    @Pattern(regexp = "(F_SEG|shortest|crf|nlp|dict|index|standard|n_short|L_MME|N_SEG|S_SEG|F_POS|percept|L_POS|S_POS|F_NER|hmm|L_NER|S_NER)",
            message = "算法必须为 F_SEG|shortest|crf|nlp|dict|index|standard|n_short|L_MME|N_SEG|S_SEG|F_POS|percept|L_POS|S_POS|F_NER|hmm|L_NER|S_NER")
    private String algorithm;

    @ApiModelProperty(value = "数量")
    @Min(1)
    private Integer size;

    @ApiModelProperty(value = "规则Id")
    private String ruleId;

    @ApiModelProperty(value = "排序", example = "desc")
    @Pattern(regexp = "(asc|desc)", message = "排序必须为 asc|desc")
    private String sort;

    @ApiModelProperty(value = "词典Id")
    private String dictId;
}
