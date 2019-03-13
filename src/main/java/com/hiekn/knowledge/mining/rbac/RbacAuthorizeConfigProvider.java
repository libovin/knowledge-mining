/**
 * 
 */
package com.hiekn.knowledge.mining.rbac;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order
public class RbacAuthorizeConfigProvider {

	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config
			.antMatchers()
				.access("@rbacService.hasPermission(request, authentication)");
		return true;
	}

}
