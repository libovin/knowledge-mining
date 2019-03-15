package com.hiekn.knowledge.mining.rbac.service;

import com.hiekn.knowledge.mining.rbac.model.dto.RoleInfo;

import java.util.List;

public interface RoleService {

	RoleInfo create(RoleInfo roleInfo);

	RoleInfo update(RoleInfo roleInfo);

	void delete(String id);

	RoleInfo getInfo(String id);

	List<RoleInfo> findAll();

	String[] getRoleResources(String id);

	void setRoleResources(String id, String ids);

}
