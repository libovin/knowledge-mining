package com.hiekn.knowledge.mining.rbac.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sys_permission")
public class Permission {

    @Id
    private String id;
    /**
     * 授权
     */
    private String permission;
    /**
     * 授权链接
     */
    private String url;
    /**
     *
     */
    private String method;
    /**
     *
     */
    private String type;
    /**
     * 授权描述
     */
    private String remark;

}
