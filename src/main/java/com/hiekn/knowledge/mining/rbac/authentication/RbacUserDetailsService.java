package com.hiekn.knowledge.mining.rbac.authentication;

import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.model.dao.UserReal;
import com.hiekn.knowledge.mining.rbac.repository.UserRealRepository;
import com.hiekn.knowledge.mining.rbac.service.UserService;
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
    private UserService userService;

    @Autowired
    private UserRealRepository userRealRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserId(username);
        AuthenticatedUserDetails.Builder builder = new AuthenticatedUserDetails.Builder();
        if (user == null) {
            UserReal userReal = userRealRepository.findOne(username);
            User userTemp = userService.createUser(userReal);
            builder.authorities(userService.mapToGrantedAuthorities(userTemp));
            builder.active(userReal.getStatus() == 1);
            builder.user(userTemp);
        } else {
            user = userService.getUserPermissions(user);
            builder.authorities(userService.mapToGrantedAuthorities(user));
            builder.active(true);
            builder.user(user);
        }
        return builder.build();
    }

}