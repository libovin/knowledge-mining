package com.hiekn.knowledge.mining.rbac.authentication.jwt;

import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        JwtToken jwtToken= (JwtToken) authenticationToken.getPrincipal();

        if(StringUtils.isEmpty(jwtToken.getToken())){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        String userId = jwtToken.getUserIdAsString();
        System.out.println(userId);
        // Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authenticationToken.getProviderId(), providerUserIds);

//        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
//            throw new InternalAuthenticationServiceException("无法获取用户信息");
//        }

        // String userId = userIds.iterator().next();

        UserDetails user = null;// = userDetailsService.loadUserByUserId(userId);

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        JwtAuthenticationToken authenticationResult = new JwtAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
