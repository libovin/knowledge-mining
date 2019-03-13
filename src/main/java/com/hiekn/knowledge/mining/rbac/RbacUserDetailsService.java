package com.hiekn.knowledge.mining.rbac;

import com.hiekn.knowledge.mining.rbac.authentication.AuthenticatedUserDetails;
import com.hiekn.knowledge.mining.rbac.domain.Permission;
import com.hiekn.knowledge.mining.rbac.domain.Role;
import com.hiekn.knowledge.mining.rbac.domain.User;
import com.hiekn.knowledge.mining.rbac.domain.UserReal;
import com.hiekn.knowledge.mining.rbac.repository.PermissionRepository;
import com.hiekn.knowledge.mining.rbac.repository.RoleRepository;
import com.hiekn.knowledge.mining.rbac.repository.UserRealRepository;
import com.hiekn.knowledge.mining.rbac.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RbacUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRealRepository userRealRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        AuthenticatedUserDetails.Builder builder = new AuthenticatedUserDetails.Builder();
        if (user == null) {
            UserReal userReal = userRealRepository.findOne(username);
            createUser(userReal);
            builder.authorities(mapToGrantedAuthorities(Collections.emptySet()));
            builder.active(userReal.getStatus() == 1);
            builder.username(userReal.getName());
        } else {
            user = getUserPermissions(user);
            builder.authorities(mapToGrantedAuthorities(user.getPermissions()));
            builder.active(true);
            builder.username(user.getName());
        }
        return builder.build();
    }

    private Set<GrantedAuthority> mapToGrantedAuthorities(Set<Permission> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
    }

    private User getUserPermissions(User user) {
        Set<Permission> permissionSet = new HashSet<>();
        for (String ruleId : user.getRoles()) {
            Role role = roleRepository.findOne(ruleId);
            for (String permissionId : role.getPermissions()) {
                Permission permission = permissionRepository.findOne(permissionId);
                permissionSet.add(permission);
            }
        }
        user.setPermissions(permissionSet);
        return user;
    }

    private User createUser(UserReal source) {
        User target = new User();
        BeanUtils.copyProperties(source, target);
        Role role = roleRepository.findByRole("ANONYMOUS");
        if (role == null) {
            role = new Role();
            role.setRole("ANONYMOUS");
            role.setPermissions(Collections.EMPTY_LIST);
            String roleId = roleRepository.insert(role).getId();
            target.setRoles(new LinkedHashSet<String>() {{
                add(roleId);
            }});
        }
        return userRepository.insert(target);
    }

}