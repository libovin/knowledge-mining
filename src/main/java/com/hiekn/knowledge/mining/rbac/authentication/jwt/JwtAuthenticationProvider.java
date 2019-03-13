package com.hiekn.knowledge.mining.rbac.authentication.jwt;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.hiekn.boot.autoconfigure.jwt.JwtProperties;
import com.hiekn.boot.autoconfigure.jwt.JwtToken;
import com.hiekn.boot.autoconfigure.web.exception.InvalidAuthenticationTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String authenticationToken = (String) authentication.getCredentials();
        JwtToken jwtToken = new JwtToken(jwtProperties);
        String userId;
        try {
            userId = jwtToken.checkToken(authenticationToken).getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            throw new InvalidAuthenticationTokenException("Token验证失败", e);
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId);
        return new JwtAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
