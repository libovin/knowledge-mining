package com.hiekn.knowledge.mining.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Permission {

    @Id
    private String id;

    private String permission;
    //授权链接
    private String url;

    private String method;

    private String type;
    //权限描述
    private String remark;

}
