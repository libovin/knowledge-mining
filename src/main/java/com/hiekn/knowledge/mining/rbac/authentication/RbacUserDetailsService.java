package com.hiekn.knowledge.mining.rbac.authentication;

import com.hiekn.knowledge.mining.rbac.model.dao.Permission;
import com.hiekn.knowledge.mining.rbac.model.dao.Role;
import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.model.dao.UserReal;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RbacUserDetailsService implements UserDetailsService {

    private String rolePrefix = "ROLE_";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRealRepository userRealRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username);
        AuthenticatedUserDetails.Builder builder = new AuthenticatedUserDetails.Builder();
        if (user == null) {
            UserReal userReal = userRealRepository.findOne(username);
            User userTemp = createUser(userReal);
            builder.authorities(mapToGrantedAuthorities(userTemp));
            builder.active(userReal.getStatus() == 1);
            builder.user(userTemp);
        } else {
            user = getUserPermissions(user);
            builder.authorities(mapToGrantedAuthorities(user));
            builder.active(true);
            builder.user(user);
        }
        return builder.build();
    }

    private Set<GrantedAuthority> mapToGrantedAuthorities(User user) {
        Set<GrantedAuthority> role = user
                .getRoleSet()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toSet());
        Set<GrantedAuthority> permission = user
                .getPermissionSet()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission())).collect(Collectors.toSet());
        role.addAll(permission);
        return role;
    }

    private User getUserPermissions(User user) {
        Set<Permission> permissionSet = new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        for (String ruleId : user.getRoles()) {
            Role role = roleRepository.findOne(ruleId);
            roleSet.add(role);
            for (String permissionId : role.getPermissions()) {
                Permission permission = permissionRepository.findOne(permissionId);
                permissionSet.add(permission);
            }
        }
        user.setRoleSet(roleSet);
        user.setPermissionSet(permissionSet);
        return user;
    }

    private User createUser(UserReal source) {
        User target = new User();
        BeanUtils.copyProperties(source, target);
        String password = target.getPassword();

        Role role = roleRepository.findByRole(Authority.ADMIN.name());
        if (role == null) {
            role = new Role();
            role.setRole(rolePrefix + Authority.ADMIN.name());
            role.setPermissions(Collections.emptyList());
            role = roleRepository.insert(role);
        }
        String roleId = role.getId();
        target.setRoles(new LinkedHashSet<String>() {{
            add(roleId);
        }});
        User user = userRepository.insert(target);
        return getUserPermissions(user);
    }

}