package com.hiekn.knowledge.mining.rbac.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LinkDo {

    @Id
    private String id;

    private String name;

    private String link;

    private String icon;

    private LinkType type;

    private String parentId;

    private int sort;

}
