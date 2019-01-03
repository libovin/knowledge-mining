package com.hiekn.knowledge.mining.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.knowledge.mining.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

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
    public RestResp<Map> getConfig() {
        return new RestResp<>(taskService.getProp());
    }

    @GET
    @Path("list")
    @ApiOperation("任务列表")
    public RestResp<RestData> getList() {

        return new RestResp<>();
    }

    @GET
    @Path("{id}")
    @ApiOperation("任务详情")
    public RestResp getTask(@PathParam("id") String id) {

        return new RestResp();
    }


    @POST
    @Path("preprocess")
    @ApiOperation("任务预执行")
    public RestResp preprocess(Map req) {
        return new RestResp(taskService.preprocess(req));
    }

    @POST
    @Path("save")
    @ApiOperation("任务保存/修改")
    public RestResp save() {

        return new RestResp();
    }

    @POST
    @Path("remote/{serverId}")
    @ApiOperation("发布服务调用")
    public RestResp remote(@PathParam("serverId") String serverId,
                           @FormParam("context") @ApiParam(required = true) String context) {

        return new RestResp();
    }

}
