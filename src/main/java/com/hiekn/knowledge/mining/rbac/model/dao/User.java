package com.hiekn.knowledge.mining.rbac.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Document(collection = "sys_user")
public class User {
    @Id
    private String id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private LinkedHashSet<String> roles;

    @Transient
    private Set<Role> roleSet;

    @Transient
    private Set<Permission> permissionSet;



}
