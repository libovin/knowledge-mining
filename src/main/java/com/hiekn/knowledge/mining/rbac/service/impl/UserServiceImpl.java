package com.hiekn.knowledge.mining.rbac.service.impl;

import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.model.dto.UserCondition;
import com.hiekn.knowledge.mining.rbac.model.dto.UserInfo;
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
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo create(UserInfo userInfo) {
        User userDo = new User();
        BeanUtils.copyProperties(userInfo, userDo);
        User user = userRepository.save(userDo);
        createRoleUser(userInfo, user);
        return userInfo;
    }

    private void createRoleUser(UserInfo userInfo, User userDo) {

    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        User userDo = userRepository.findOne(userInfo.getId());
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
