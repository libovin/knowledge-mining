package com.hiekn.knowledge.mining.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.bean.vo.PreProcess;
import com.hiekn.knowledge.mining.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("task")
@Api("知识挖掘任务")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class TaskRestApi {

    @Autowired
    private TaskService taskService;

    @GET
    @Path("config")
    @ApiOperation("获取配置项")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp getConfig() {
        return new RestResp<>(taskService.getProp());
    }

    @GET
    @Path("list")
    @ApiOperation("任务列表")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp<RestData<Task>> getList() {
        return new RestResp<>(taskService.getList());
    }

    @GET
    @Path("{id}")
    @ApiOperation("任务详情")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp<Task> getTask(@PathParam("id") String id) {
        return new RestResp<>(taskService.getTask(id));
    }


    @POST
    @Path("preprocess")
    @ApiOperation("任务预执行")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp preprocess(@Valid PreProcess req) {
        return new RestResp<>(taskService.preprocess(req));
    }

    @POST
    @Path("save")
    @ApiOperation("任务保存/修改")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp<Task> save(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization,
            @Valid Task req) {
        return new RestResp<>(taskService.save(req));
    }

    @DELETE
    @Path("{id}")
    @ApiOperation("任务删除")
    @PreAuthorize("hasRole('ADMIN')")
    public RestResp delete(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization,
            @PathParam("id") String id) {
        taskService.delete(id);
        return new RestResp<>("删除成功");
    }

    @POST
    @Path("remote/{serverId}")
    @ApiOperation("发布服务调用")
    public RestResp remote(@PathParam("serverId") String serverId,
                           @FormParam("context") @ApiParam(required = true) String context,
                           @ApiParam(required =true,value = "token值") @FormParam("token") String token) {
        return new RestResp<>(taskService.remote(serverId, context));
    }

}
