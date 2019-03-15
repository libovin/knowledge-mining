package com.hiekn.knowledge.mining.rbac.service;

import com.hiekn.knowledge.mining.rbac.model.dto.UserCondition;
import com.hiekn.knowledge.mining.rbac.model.dto.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	UserInfo create(UserInfo adminInfo);

	UserInfo update(UserInfo adminInfo);

	void delete(String id);

	UserInfo getInfo(String id);

	Page<UserInfo> query(UserCondition condition, Pageable pageable);

}
