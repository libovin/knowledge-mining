package com.hiekn.knowledge.mining.rbac.service.impl;

import com.hiekn.knowledge.mining.rbac.domain.RoleUserDo;
import com.hiekn.knowledge.mining.rbac.domain.UserDo;
import com.hiekn.knowledge.mining.rbac.dto.UserCondition;
import com.hiekn.knowledge.mining.rbac.dto.UserInfo;
import com.hiekn.knowledge.mining.rbac.repository.RoleUserRepository;
import com.hiekn.knowledge.mining.rbac.repository.UserRepository;
import com.hiekn.knowledge.mining.rbac.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo create(UserInfo userInfo) {
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userInfo, userDo);
        userDo.setPassword(passwordEncoder.encode("123456"));
        UserDo user = userRepository.save(userDo);
        createRoleUser(userInfo, user);
        return userInfo;
    }

    private void createRoleUser(UserInfo userInfo, UserDo userDo) {
        RoleUserDo ruleUserDo = new RoleUserDo();
        ruleUserDo.setRoleId(userInfo.getRoleId());
        ruleUserDo.setUserId(userDo.getId());
        roleUserRepository.save(ruleUserDo);
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        UserDo userDo = userRepository.findOne(userInfo.getId());
        BeanUtils.copyProperties(userInfo, userDo);
        createRoleUser(userInfo, userDo);
        return userInfo;
    }


    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public UserInfo getInfo(String id) {
        return null;
    }

    @Override
    public Page<UserInfo> query(UserCondition condition, Pageable pageable) {
        return null;
    }


}
