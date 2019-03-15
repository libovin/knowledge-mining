package com.hiekn.knowledge.mining.rbac.model.dao;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "sys_role")
public class Role {

    @Id
    private String id;

    private String role;

    private List<String> permissions;
}
