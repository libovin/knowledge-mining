package com.hiekn.knowledge.mining.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.knowledge.mining.rbac.authentication.AuthenticatedUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Component
@Path("auth")
@Api("权限")
@Produces(MediaType.APPLICATION_JSON)

public class AuthRestApi {

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String authorization;

    @ApiOperation("当前用户信息")
    @Path("me")
    @GET
    @PreAuthorize("isAuthenticated()")
    public RestResp<AuthenticatedUserDetails> getCurrentUser() {
        AuthenticatedUserDetails userDetails = (AuthenticatedUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return new RestResp<>(userDetails);
    }

    @ApiOperation("添加用户")
    @Path("add")
    @GET
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp add(){

        return new RestResp();
    }
}
