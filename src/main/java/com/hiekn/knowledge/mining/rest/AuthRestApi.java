package com.hiekn.knowledge.mining.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.knowledge.mining.bean.vo.PageQuery;
import com.hiekn.knowledge.mining.rbac.authentication.AuthenticatedUserDetails;
import com.hiekn.knowledge.mining.rbac.model.dao.User;
import com.hiekn.knowledge.mining.rbac.model.dto.UserInfo;
import com.hiekn.knowledge.mining.rbac.service.RoleService;
import com.hiekn.knowledge.mining.rbac.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;


@Component
@Path("auth")
@Api("权限")
//返回给client json类型
@Produces(MediaType.APPLICATION_JSON)
public class AuthRestApi {

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String authorization;

    @Autowired
    private Random random;

    @Autowired
    private UserService userService;

    @ApiOperation("当前用户信息")
    @Path("/me")
    @GET
    @PreAuthorize("isAuthenticated()")
    public RestResp getCurrentUser() {
        AuthenticatedUserDetails userDetails = (AuthenticatedUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserInfo userInfo = new UserInfo();
        User user = userDetails.getUser();
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setUsername(user.getPhone());
        userInfo.setId(user.getId());
        return new RestResp<>(userInfo);
    }

    @ApiOperation("添加用户")
    @Path("/add")
    @POST
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp add(@Validated(UserInfo.Create.class) UserInfo userInfo) throws Exception {
        return new RestResp<>(userService.create(userInfo));
    }

    @ApiOperation("修改用户")
    @Path("/{id}/info")
    @POST
    @PreAuthorize("hasAnyRole('ADMIN','EDIT')")
    public RestResp updateInfo(@PathParam("id") String id,
                               UserInfo userInfo) {
        return new RestResp<>(userService.update(id, userInfo));
    }

    @ApiOperation("修改密码")
    @Path("/{id}/pwd")
    @POST
    @PreAuthorize("hasAnyRole('EDIT')")
    public RestResp updatePwd(
            @PathParam("id") String id,
            @Validated(UserInfo.Update.class) UserInfo userInfo) throws Exception {
        return new RestResp<>(userService.updatePwd(id, userInfo));
    }

    @ApiOperation("重置密码")
    @Path("/reset/{id}")
    @POST
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp reset(
            @PathParam("id") String id) throws Exception {
        int max = 999999;
        int min = 100000;
        int v = random.nextInt(max) % (max - min + 1) + min;
        userService.reset(id, String.valueOf(v));
        RestResp<Integer> resp = new RestResp<>(v);
        resp.setErrorInfo("密码重置成功");
        return resp;
    }

    @ApiOperation("用户列表")
    @Path("/list")
    @POST
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp<RestData> list(PageQuery pageQuery) {
        return new RestResp<>(userService.query(new PageRequest(pageQuery.getPageNo() - 1, pageQuery.getPageSize())));
    }

    @ApiOperation("登陆")
    @POST
    @Path("/login")
    public RestResp login(@Validated(UserInfo.Login.class) UserInfo userInfo) throws Exception {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        return new RestResp<>(userService.login(username, password));
    }

}
