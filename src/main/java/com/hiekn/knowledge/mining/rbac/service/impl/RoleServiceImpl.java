package com.hiekn.knowledge.mining.rbac.service.impl;


import com.hiekn.knowledge.mining.rbac.dto.RoleInfo;
import com.hiekn.knowledge.mining.rbac.repository.LinkRepository;
import com.hiekn.knowledge.mining.rbac.repository.RoleLinkRepository;
import com.hiekn.knowledge.mining.rbac.repository.RoleRepository;
import com.hiekn.knowledge.mining.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author zhailiang
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private RoleLinkRepository roleLinkRepository;

	@Override
	public RoleInfo create(RoleInfo roleInfo) {
		return null;
	}

	@Override
	public RoleInfo update(RoleInfo roleInfo) {
		return null;
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public RoleInfo getInfo(String id) {
		return null;
	}

	@Override
	public List<RoleInfo> findAll() {
		return null;
	}

	@Override
	public String[] getRoleResources(String id) {
		return new String[0];
	}

	@Override
	public void setRoleResources(String id, String ids) {

	}
}
