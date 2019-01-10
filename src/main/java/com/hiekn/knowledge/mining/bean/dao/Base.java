package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

@Data
public abstract class Base {
    @Id
    @ApiModelProperty(value = "id",hidden = true)
    private String id;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Version
    @ApiModelProperty(hidden = true)
    private Long version;

    @CreatedDate
    @ApiModelProperty(hidden = true)
    private Long createTime;

    @LastModifiedDate
    @ApiModelProperty(hidden = true)
    private Long updateTime;

}
