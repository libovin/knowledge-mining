package com.hiekn.knowledge.mining.rest;


import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;

import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.bean.dao.TokenInterface;
import com.hiekn.knowledge.mining.bean.vo.TokenQuery;
import com.hiekn.knowledge.mining.service.TokenCountService;
import com.hiekn.knowledge.mining.service.TokenInterfaceService;
import com.hiekn.knowledge.mining.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("token")
@Api("Token管理")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TokenRestApi {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenInterfaceService tokenInterfaceService;

    @Autowired
    private TokenCountService tokenCountService;

    @ApiOperation("Token列表")
    @POST
    @Path("/get/list")
    public RestResp<RestData<Token>> findAll(@Valid TokenQuery tokenQuery) {
        return new RestResp<>(tokenService.findAll(tokenQuery));
    }

    @ApiOperation("根据接口ID获取token列表")
    @POST
    @Path("/list/{serverId}")
    public RestResp<RestData<Token>> findByInterfaceId(@PathParam("serverId") String interfaceId) {
        return new RestResp<>(tokenInterfaceService.findByInterfaceId(interfaceId));
    }

    @ApiOperation("Token详情")
    @GET
    @Path("/get/{id}")
    public RestResp<Token> findOne(@PathParam("id") String id) {
        return new RestResp<>(tokenService.findOne(id));
    }

    @ApiOperation("删除Token")
    @DELETE
    @Path("/delete/{id}")
    public RestResp delete(@PathParam("id") String id) throws Exception {
        tokenService.delete(id);
        return new RestResp<>();
    }

    @ApiOperation("修改Token")
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResp<Token> modify(@PathParam("id") String id, @Valid Token token) {
        return new RestResp<>(tokenService.modify(id, token));
    }

    @ApiOperation("新增Token")
    @POST
    @Path("/add")
    public RestResp<Token> add(@Valid Token token) {
        return new RestResp<>(tokenService.add(token));
    }

    @ApiOperation("接口分配Token")
    @POST
    @Path("/interface/{id}")
    public RestResp tokenInterfaceAdd(@ApiParam(required = true, value = "接口Id") @PathParam("id") String id,
                                      @ApiParam(required = true, value = "tokenIds") @FormParam(value = "ids") String ids) {
        return new RestResp<>(tokenInterfaceService.addToken(id, ids));
    }

    @ApiOperation("接口解绑Token")
    @POST
    @Path("/interface/delete/{id}")
    public RestResp tokenInterfaceDelete(@ApiParam(required = true, value = "接口Id") @PathParam("id") String id,
                                         @ApiParam(required = true, value = "tokenIds") @FormParam(value = "ids") String ids) {
        tokenInterfaceService.deleteTokenInterfaces(id, ids);
        return new RestResp();
    }

    @ApiOperation("统计接口使用Token次数")
    @POST
    @Path("/count/service/{serverId}")
    public RestResp<List> tokenCount(@PathParam("serverId") String serverId) {
        return new RestResp<>(tokenCountService.countByServerId(serverId));
    }

    @ApiOperation("统计接口使用Token次数")
    @POST
    @Path("/count/token/{token}")
    public RestResp<List> serviceCount(@PathParam("token") String token) {
        return new RestResp<>(tokenCountService.countByToken(token));
    }

}
