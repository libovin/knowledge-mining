package com.hiekn.knowledge.mining.service;

import java.util.List;
import java.util.Map;

/**
 * 任务
 */
public interface TaskService {

    Map getProp();

    Map remote(String serverId, String context);

    Map save();

    Map preprocess(Map req);

    Map getTask(String id);

    List getList();
}
