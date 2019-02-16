package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.bean.vo.PreProcess;
import com.hiekn.knowledge.mining.handler.Handler;
import com.hiekn.knowledge.mining.repository.TaskRepository;
import com.hiekn.knowledge.mining.service.ConfigService;
import com.hiekn.knowledge.mining.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
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
        List<ConfigReq> config = task.getConfig();
        return preprocess(new PreProcess(context, config));
    }

    @Override
    public Task save(Task req) {
        if (req.getId() != null) {
            Task task = taskRepository.findOne(req.getId());
            task.setName(req.getName());
            task.setConfig(req.getConfig());
            return taskRepository.save(task);
        }
        return taskRepository.save(req);
    }

    @Override
    public Map preprocess(PreProcess req) {
        List<ConfigReq> list = req.getConfig();
        Handler root = buildHandler(list, 0);
        Map result = root.handle(BeanMap.create(req), root.getArgs());
        result.put("content", req.getContent());
        return result;
    }

    private Handler buildHandler(List<ConfigReq> list, int i) {
        Handler handler = null;
        if (i < list.size()) {
            ConfigReq configReq = list.get(i);
            handler = new Handler(configService.getFun(configReq), configReq.getArgs());
            handler.setNextHandler(buildHandler(list, i + 1));
        }
        return handler;
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
