package com.hiekn.knowledge.mining.service;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Task;

import java.util.List;
import java.util.Map;

/**
 * 任务
 */
public interface TaskService {

    Map getProp();

    Map remote(String serverId, String content);

    Task save(Task req);

    Map preprocess(Map req);

    void delete(String id);

    Task getTask(String id);

    RestData<List<Task>> getList();
}
