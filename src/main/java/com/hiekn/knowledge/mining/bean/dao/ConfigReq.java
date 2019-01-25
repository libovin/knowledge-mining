package com.hiekn.knowledge.mining.bean.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConfigReq {

    @ApiModelProperty(value = "模型", example = "nlp")
    @Pattern(regexp = "(nlp|tool|related)",
            message = "模型必须为 nlp|counter|pattern|related")
    @NotBlank
    private String model;

    @ApiModelProperty(value = "输入", example = "content")
    @Pattern(regexp = "(content|result)",
            message = "输入必须为 content|result")
    @NotBlank
    private String input;

    @ApiModelProperty(value = "方法", example = "segment")
    @Pattern(regexp = "(segment|pos|ner|keyword|summary|classifier|denpendency|count|matches|find|baidu|aminer|journal|literature)",
            message = "方法必须为 segment|pos|ner|keyword|summary|classifier|denpendency|count|matches|find|baidu|aminer|journal|literature")
    @NotBlank
    private String method;

    @ApiModelProperty(value = "参数")
    @Valid
    private ArgsReq agrs;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public String getAlgorithm() {
        if (agrs != null) {
            return this.agrs.getAlgorithm();
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public Integer getSize() {
        if (agrs != null) {
            return this.agrs.getSize();
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public String getSort() {
        if (agrs != null) {
            return this.agrs.getSort();
        }
        return null;
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public String getRuleId() {
        if (agrs != null) {
            return this.agrs.getRuleId();
        }
        return null;
    }

}
