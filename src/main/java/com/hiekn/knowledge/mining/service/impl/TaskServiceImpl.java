package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.handler.Handler;
import com.hiekn.knowledge.mining.repository.TaskRepository;
import com.hiekn.knowledge.mining.service.ConfigService;
import com.hiekn.knowledge.mining.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ConfigService configService;


    @Override
    public Map getProp() {
        return configService.getProp();
    }

    @Override
    public Map remote(String serverId, String context) {
        Task task = taskRepository.findOne(serverId);
        List config = task.getConfig();
        Map req = Maps.newHashMap();
        req.put("content", context);
        req.put("config", config);
        return preprocess(req);
    }

    @Override
    public Task save(Task req) {
        return taskRepository.save(req);
    }

    @Override
    public Map preprocess(Map req) {
        List list = (List) req.get("config");
        Handler root = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj instanceof Map) {
                Map map = (Map) obj;
                Handler handler = new Handler(configService.getFunction(map), map);
                if (i == 0) {
                    root = handler;
                }
                if (i + 1 < size) {
                    Map nextMap = (Map) list.get(i + 1);
                    Handler nextHandler = new Handler(configService.getFunction(nextMap), nextMap);
                    handler.setNextHandler(nextHandler);
                } else {
                    handler.setNextHandler(null);
                }
            }
        }
        Map result = root.handle(req, root.getArgs());
        result.put("content", req.get("content"));
        return result;
    }

    @Override
    public Task getTask(String id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
       taskRepository.delete(id);
    }

    @Override
    public RestData<Task> getList() {
        return new RestData<>(taskRepository.findAll(), taskRepository.count());
    }
}
