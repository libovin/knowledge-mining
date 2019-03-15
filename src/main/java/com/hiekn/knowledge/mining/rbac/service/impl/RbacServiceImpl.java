package com.hiekn.knowledge.mining.rbac.service.impl;

import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof User) {
			//如果用户名是admin，就永远返回true
//			if (StringUtils.equals(((User) principal).getUsername(), "admin")) {
//				hasPermission = true;
//			} else {
				// 读取用户所拥有权限的所有URL
//				Set<String> urls = ((User) principal).getUrls();
//				for (String url : urls) {
//					if (antPathMatcher.match(url, request.getRequestURI())) {
//						hasPermission = true;
//						break;
//					}
//				}
//			}
		}
		return hasPermission;
	}
}
