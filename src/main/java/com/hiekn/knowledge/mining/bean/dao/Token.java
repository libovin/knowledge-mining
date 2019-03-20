package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Document
@ApiModel("Token")
public class Token extends Base {

    @ApiModelProperty(value = "token名称")
    @NotBlank(message = "token名称不能为空")
    private String name;

    @ApiModelProperty(value = "token过期时间,长整型时间戳")
    @NotNull(message = "过期时间不能为空")
    private Long expireDate;

    @ApiModelProperty(value = "token状态,0-close | 1-open")
    @Min(0)
    @Max(1)
    private Integer active;

    @ApiModelProperty(hidden = true)
    private String token;

    @Transient
    @ApiModelProperty(value = "分配时间")
    private Long tsCreateTime;

}
