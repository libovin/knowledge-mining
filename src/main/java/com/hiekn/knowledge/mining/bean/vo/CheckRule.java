package com.hiekn.knowledge.mining.bean.vo;


import com.hiekn.knowledge.mining.bean.dao.RuleModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CheckRule {

    @ApiModelProperty(value = "待校验文本")
    @NotBlank(message = "校验文本不能为空")
    private String content;

    @Valid
    @ApiModelProperty(value = "规则")
    @Size(min = 1, message = "规则定义没填")
    @NotNull(message = "规则定义没填")
    private List<RuleModel> rules;
}
