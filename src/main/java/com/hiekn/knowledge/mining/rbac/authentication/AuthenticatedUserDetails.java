package com.hiekn.knowledge.mining.rbac.authentication;

import com.hiekn.knowledge.mining.rbac.model.dao.User;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public final class AuthenticatedUserDetails implements UserDetails, CredentialsContainer {

    private final User user;
    private String password;
    private final Set<GrantedAuthority> authorities;
    private final boolean active;
    private final boolean expired;
    private final boolean locked;
    private final boolean credentials;

    private AuthenticatedUserDetails(User user, String password, Set<GrantedAuthority> authorities, boolean active) {
        this.user = user;
        this.password = password;
        this.authorities = Collections.unmodifiableSet(authorities);
        this.active = active;
        this.expired = true;
        this.locked = true;
        this.credentials = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentials;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public User getUser() {
        return this.user;
    }

    public static class Builder {

        private User user;
        private String password;
        private Set<GrantedAuthority> authorities;
        private boolean active;

        public Builder user(User user) {
            user.setPermissionSet(null);
            user.setRoles(null);
            user.setRoleSet(null);
            this.user = user;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder authorities(Set<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public AuthenticatedUserDetails build() {
            return new AuthenticatedUserDetails(user, password, authorities, active);
        }
    }
}
