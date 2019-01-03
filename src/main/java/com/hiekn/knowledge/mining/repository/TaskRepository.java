package com.hiekn.knowledge.mining.repository;

import com.hiekn.knowledge.mining.bean.dao.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task,String> {
}
