package com.hiekn.knowledge.mining.rbac.authentication.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.boot.autoconfigure.web.exception.InvalidAuthenticationTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        HttpStatus status;
        RestResp restResp = new RestResp();

        if (authException instanceof InvalidAuthenticationTokenException) {
            status = HttpStatus.UNAUTHORIZED;
            restResp.setActionStatus(status.getReasonPhrase());
            restResp.setErrorInfo(authException.getMessage());
            restResp.setData(authException.getCause().getMessage());
        } else {
            status = HttpStatus.FORBIDDEN;
            restResp.setActionStatus(status.getReasonPhrase());
            restResp.setErrorInfo(authException.getMessage());
        }

        restResp.setErrorCode(status.value());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), restResp);
    }
}