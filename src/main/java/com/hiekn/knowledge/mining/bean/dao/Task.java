package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Task {

    @Id
    private String id;

    private List config;

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
