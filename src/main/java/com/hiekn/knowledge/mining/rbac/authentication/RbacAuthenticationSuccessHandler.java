package com.hiekn.knowledge.mining.rbac.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component("rbacAuthenticationSuccessHandler")
public class RbacAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


}
