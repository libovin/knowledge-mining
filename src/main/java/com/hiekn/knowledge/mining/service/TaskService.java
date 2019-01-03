package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.dao.Task;

import java.util.List;
import java.util.Map;

/**
 * 任务
 */
public interface TaskService {

    Map getProp();

    Map remote(String serverId, String content);

    Task save(Map req);

    Map preprocess(Map req);

    Task getTask(String id);

    List<Task> getList();
}
