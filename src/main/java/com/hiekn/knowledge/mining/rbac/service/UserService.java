package com.hiekn.knowledge.mining.rbac.service;


import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.model.dao.UserReal;
import com.hiekn.knowledge.mining.rbac.model.dto.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface UserService {
	String login(String username, String password) throws Exception;

	UserInfo create(UserInfo adminInfo)throws Exception;

	User createUser(UserReal source);

	UserInfo update(String id,UserInfo adminInfo);
	UserInfo updatePwd(String id,UserInfo adminInfo) throws Exception;

	void delete(String id);

	void reset(String id, String pwd) throws Exception;

	RestData<UserInfo> query(Pageable pageable);

	Set<GrantedAuthority> mapToGrantedAuthorities(User user);

	User getUserPermissions(User user);
	User findByUserId(String username);

}
