package com.hiekn.knowledge.mining.rbac.authentication.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String authenticationToken;
    private UserDetails userDetails;

    public JwtAuthenticationToken(String authenticationToken) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.authenticationToken = authenticationToken;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetails userDetails,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted. Use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return authenticationToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public Object getDetails() {
        return this.userDetails;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.authenticationToken = null;
    }
}
