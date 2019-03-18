package com.hiekn.knowledge.mining.bean.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ApiModel("TokenInterface")
public class TokenInterface {
    @Id
    @ApiModelProperty(value = "id",hidden = true)
    private String id;

    @CreatedDate
    @ApiModelProperty(hidden = true)
    private Long createTime;

    @LastModifiedDate
    @ApiModelProperty(hidden = true)
    private Long updateTime;

    @ApiModelProperty(value = "接口Id")
    @NotBlank(message = "接口Id不能为空")
    private String interfaceId;

    @ApiModelProperty(value = "tokenId")
    @NotBlank(message = "Token Id 不能为空")
    private String tokenId;

    public TokenInterface(){

    }
    public TokenInterface(String interfaceId, String tokenId) {
        this.interfaceId = interfaceId;
        this.tokenId = tokenId;
    }
}
