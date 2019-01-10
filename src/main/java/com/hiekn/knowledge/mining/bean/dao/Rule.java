package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 规则
 */
@Data
@Document
@XmlRootElement
public class Rule extends Base {

    @ApiModelProperty(value = "规则名")
    private String name;

    @ApiModelProperty(value = "表达式列表")
    private List<RuleModel> rules;
}
