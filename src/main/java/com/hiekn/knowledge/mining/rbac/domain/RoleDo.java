package com.hiekn.knowledge.mining.rbac.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class RoleDo {

    @Id
    private String id;

    private String name;

}
