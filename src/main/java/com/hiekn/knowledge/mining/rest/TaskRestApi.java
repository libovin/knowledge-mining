package com.hiekn.knowledge.mining.rest;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.boot.autoconfigure.base.model.result.RestResp;
import com.hiekn.knowledge.mining.bean.prop.KgProp;
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

    @Autowired
    private KgProp kgProp;

    @GET
    @Path("config")
    @ApiOperation("获取配置项")
    public RestResp getConfig() {
        return new RestResp(kgProp);
    }

    @GET
    @Path("list")
    @ApiOperation("任务列表")
    public RestResp<RestData> getList() {
        return new RestResp<>(taskService.getList());
    }

    @GET
    @Path("{id}")
    @ApiOperation("任务详情")
    public RestResp getTask(@PathParam("id") String id) {

        return new RestResp(taskService.getTask(id));
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
    public RestResp save(Map req) {
        return new RestResp(taskService.save(req));
    }

    @POST
    @Path("remote/{serverId}")
    @ApiOperation("发布服务调用")
    public RestResp remote(@PathParam("serverId") String serverId,
                           @FormParam("context") @ApiParam(required = true) String context) {
        return new RestResp(taskService.remote(serverId, context));
    }

}
