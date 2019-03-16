package com.hiekn.knowledge.mining.rbac.service.impl;


import com.hiekn.knowledge.mining.rbac.authentication.Authority;
import com.hiekn.knowledge.mining.rbac.model.dao.Role;
import com.hiekn.knowledge.mining.rbac.model.dto.RoleInfo;
import com.hiekn.knowledge.mining.rbac.repository.PermissionRepository;
import com.hiekn.knowledge.mining.rbac.repository.RoleRepository;
import com.hiekn.knowledge.mining.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	private String rolePrefix = "ROLE_";

	public Role findRoleBy(String authority){
		Role role = roleRepository.findByRole(authority);
		if (role == null) {
			role = new Role();
			role.setRole(rolePrefix + authority);
			role.setPermissions(Collections.emptyList());
			role = roleRepository.insert(role);
		}
		return role;
	}

	@Override
	public Role findOne(String id) {
		return roleRepository.findOne(id);
	}
}
