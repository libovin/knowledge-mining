package com.hiekn.knowledge.mining.rbac.service;

import com.hiekn.knowledge.mining.rbac.model.dao.Role;
import com.hiekn.knowledge.mining.rbac.model.dto.RoleInfo;

import java.util.List;

public interface RoleService {
    Role findRoleBy(String authority);

    Role findOne(String id);

}
