package com.hiekn.knowledge.mining.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;

@Data
@ApiModel
public class ConfigReq {

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

    @ApiModelProperty(value = "参数")
    private Agrs agrs;

    @ApiModelProperty(hidden = true)
    public String getAlgorithm() {
        if (agrs != null) {
            return this.agrs.getAlgorithm();
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    public Integer getSize() {
        if (agrs != null) {
            return this.agrs.getSize();
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    public String getSort() {
        if (agrs != null) {
            return this.agrs.getSort();
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    public String getRuleId() {
        if (agrs != null) {
            return this.agrs.getRuleId();
        }
        return null;
    }

}
