package com.hiekn.knowledge.mining.rbac.service;

import com.hiekn.knowledge.mining.rbac.model.dao.Role;

public interface RoleService {
    Role findRoleBy(String authority);

    Role findOne(String id);

}
