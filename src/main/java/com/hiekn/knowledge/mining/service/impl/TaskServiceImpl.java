package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.bean.vo.CounterConfig;
import com.hiekn.knowledge.mining.bean.vo.NlpConfig;
import com.hiekn.knowledge.mining.bean.vo.PatternConfig;
import com.hiekn.knowledge.mining.bean.vo.RelatedConfig;
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
        List config = task.getConfig();
        Map req = Maps.newHashMap();
        req.put("content", context);
        req.put("config", config);
        return preprocess(req);
    }

    @Override
    public Task save(Map req) {
        List list = (List) req.get("config");
        Task task = new Task();
        task.setConfig(list);
        return taskRepository.save(task);
    }

    @Override
    public Map preprocess(Map req) {
        List list = (List) req.get("config");
        Map result = Maps.newHashMap();
        for (Object obj : list) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                String model = (String) map.get("model");
                if ("nlp".equals(model)) {
                    BeanMap beanMap = BeanMap.create(new NlpConfig());
                    beanMap.putAll(map);
                    NlpConfig config = (NlpConfig) beanMap.getBean();
                    result = configService.handler(result, config);
                } else if ("pattern".equals(model)) {
                    BeanMap beanMap = BeanMap.create(new PatternConfig());
                    beanMap.putAll(map);
                    PatternConfig config = (PatternConfig) beanMap.getBean();
                    result = configService.handler(result, config);
                } else if ("related".equals(model)) {
                    BeanMap beanMap = BeanMap.create(new RelatedConfig());
                    beanMap.putAll(map);
                    RelatedConfig config = (RelatedConfig) beanMap.getBean();
                    result = configService.handler(result, config);
                } else if ("counter".equals(model)) {
                    BeanMap beanMap = BeanMap.create(new CounterConfig());
                    beanMap.putAll(map);
                    CounterConfig config = (CounterConfig) beanMap.getBean();
                    result = configService.handler(result, config);
                }
            }
        }
        return result;
    }

    @Override
    public Task getTask(String id) {
        return taskRepository.findOne(id);
    }

    @Override
    public List getList() {
        return taskRepository.findAll();
    }
}
