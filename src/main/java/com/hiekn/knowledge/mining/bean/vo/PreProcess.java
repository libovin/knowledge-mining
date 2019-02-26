package com.hiekn.knowledge.mining.bean.vo;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PreProcess {

    @ApiModelProperty(value = "待处理文本")
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty(value = "配置列表")
    @Size(min = 1, message = "配置数量必须大于1")
    @NotNull(message = "配置不能为空")
    private List<ConfigReq> config;

}
