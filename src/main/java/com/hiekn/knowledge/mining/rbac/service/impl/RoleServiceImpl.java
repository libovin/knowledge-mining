package com.hiekn.knowledge.mining.rbac.service.impl;


import com.hiekn.knowledge.mining.rbac.model.dao.Role;
import com.hiekn.knowledge.mining.rbac.repository.RoleRepository;
import com.hiekn.knowledge.mining.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role findRoleBy(String authority){
		Role role = roleRepository.findByRole(authority);
		if (role == null) {
			role = new Role();
			role.setRole(authority);
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
