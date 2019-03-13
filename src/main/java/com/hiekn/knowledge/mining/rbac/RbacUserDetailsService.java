package com.hiekn.knowledge.mining.rbac;

import com.hiekn.knowledge.mining.rbac.domain.UserDo;
import com.hiekn.knowledge.mining.rbac.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RbacUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("表单登录用户名:" + username);
        UserDo userDo = userRepository.findByUsername(username);
        if(userDo == null) {

        }
        return userDo;
    }

}