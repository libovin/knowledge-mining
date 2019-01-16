package com.hiekn.knowledge.mining.bean.dao;

import com.hiekn.knowledge.mining.bean.vo.ConfigReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document
@ApiModel
public class Task extends Base{

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "配置列表")
    @Size(min = 1, message = "配置数量必须大于1")
    @NotNull(message = "配置不能为空")
    private List<ConfigReq> config;

}
