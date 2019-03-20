package com.hiekn.knowledge.mining.rbac.service.impl;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import com.hiekn.knowledge.mining.rbac.authentication.Authority;
import com.hiekn.knowledge.mining.rbac.model.dao.Permission;
import com.hiekn.knowledge.mining.rbac.model.dao.Role;
import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.model.dao.UserReal;
import com.hiekn.knowledge.mining.rbac.model.dto.UserInfo;
import com.hiekn.knowledge.mining.rbac.repository.PermissionRepository;
import com.hiekn.knowledge.mining.rbac.repository.UserRepository;
import com.hiekn.knowledge.mining.rbac.service.RoleService;
import com.hiekn.knowledge.mining.rbac.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public UserInfo create(UserInfo userInfo) throws Exception {
        User user = new User();
        User userTemp = userRepository.findByPhone(userInfo.getUsername());
        if (userTemp != null) {
            throw new Exception("用户已存在");
        }
        user.setEmail(userInfo.getEmail());
        user.setName(userInfo.getName());
        user.setPhone(userInfo.getUsername());
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        Role role = roleService.findRoleBy(Authority.ROLE_EDIT.name());
        String roleId = role.getId();
        user.setRoles(new LinkedHashSet<String>() {{
            add(roleId);
        }});
        userRepository.insert(user);
        return userInfo;
    }

    @Override
    public UserInfo update(String id, UserInfo userInfo) {
        User user = userRepository.findOne(id);
        user.setName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        userRepository.save(user);
        return userInfo;
    }

    @Override
    public UserInfo updatePwd(String id, UserInfo userInfo) throws Exception {
        User user = userRepository.findOne(id);
        if (!passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) {
            throw new Exception("密码不匹配");
        }
        user.setPassword(passwordEncoder.encode(userInfo.getNewPwd()));
        userRepository.save(user);
        return userInfo;
    }


    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public void reset(String id, String pwd) throws Exception {
        User user = userRepository.findOne(id);
        user = getUserPermissions(user);
        Set<GrantedAuthority> grantedAuthorities = mapToGrantedAuthorities(user);
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            if (grantedAuthority.getAuthority().equals(Authority.ROLE_ADMIN.name())) {
                throw new Exception("关联账户不能修改密码");
            }
        }
        user.setPassword(passwordEncoder.encode(pwd));
        userRepository.save(user);
    }

    @Override
    public RestData<UserInfo> query(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserInfo> collect = users.getContent().stream().map(user -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(user.getPhone());
            userInfo.setEmail(user.getEmail());
            userInfo.setName(user.getName());
            userInfo.setId(user.getId());
            return userInfo;
        }).collect(Collectors.toList());
        return new RestData<>(collect, users.getTotalElements());
    }


    public Set<GrantedAuthority> mapToGrantedAuthorities(User user) {
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

    public User getUserPermissions(User user) {
        Set<Permission> permissionSet = new HashSet<>();
        Set<Role> roleSet = new HashSet<>();
        for (String ruleId : user.getRoles()) {
            Role role = roleService.findOne(ruleId);
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

    @Override
    public User findByUserId(String username) {
        return userRepository.findByUserId(username);
    }

    public User createUser(UserReal source) {
        User target = new User();
        BeanUtils.copyProperties(source, target);
        target.setPassword(source.getPwd());
        String password = target.getPassword();
        target.setPassword(passwordEncoder.encode(password));
        Role role = roleService.findRoleBy(Authority.ROLE_ADMIN.name());
        String roleId = role.getId();
        target.setRoles(new LinkedHashSet<String>() {{
            add(roleId);
        }});
        User user = userRepository.insert(target);
        return getUserPermissions(user);
    }

    public String login(String username, String password) throws Exception {
        User user = userRepository.findByPhone(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("用户名密码不匹配");
        }
        return jwtToken.createToken(user.getUserId());
    }

}
