package com.hiekn.knowledge.mining.rest;


import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.knowledge.mining.bean.dao.Rule;
import com.hiekn.knowledge.mining.bean.vo.CheckRule;
import com.hiekn.knowledge.mining.bean.vo.RuleQuery;
import com.hiekn.knowledge.mining.service.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;


@Component
@Path("rule")
@Api("规则")
@Produces(MediaType.APPLICATION_JSON)
public class RuleRestApi {

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String authorization;

    @Autowired
    private RuleService ruleService;

    @ApiOperation("规则列表")
    @POST
    @Path("list")
    public RestResp<RestData<Rule>> findAll(@Valid RuleQuery query) {
        return new RestResp<>(ruleService.findAll(query));
    }

    @ApiOperation("规则详情")
    @GET
    @Path("{id}")
    public RestResp<Rule> findOne(@PathParam("id") String id) {
        return new RestResp<>(ruleService.findOne(id));
    }

    @ApiOperation("删除规则")
    @DELETE
    @Path("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp delete(@PathParam("id") String id) {
        ruleService.delete(id);
        return new RestResp<>();
    }

    @ApiOperation("修改规则")
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAnyRole('EDIT','ADMIN')")
    public RestResp<Rule> modify(@PathParam("id") String id, @Valid Rule rule) {
        return new RestResp<>(ruleService.modify(id, rule));
    }

    @ApiOperation("新增规则")
    @POST
    @Path("add")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp<Rule> add(@Valid Rule rule) {
        return new RestResp<>(ruleService.add(rule));
    }

    @ApiOperation("规则校验")
    @POST
    @Path("check")
    public RestResp<Map> check(@Valid CheckRule checkRule){
        return new RestResp<>(ruleService.check(checkRule));
    }

}
