package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.boot.autoconfigure.base.exception.ServiceException;
import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.exception.ErrorCodes;
import com.hiekn.knowledge.mining.filter.LoggingRequestFilter;
import com.hiekn.knowledge.mining.repository.TaskRepository;
import com.hiekn.knowledge.mining.repository.TokenRepository;
import com.hiekn.knowledge.mining.service.TokenCountService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class TokenCountServiceImpl implements TokenCountService {
    private static final Logger log = LoggerFactory.getLogger(TokenCountServiceImpl.class);
    private static String collection = "tokenCount";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void recordToken(String serverId, String token) throws Exception {

        //验证token是否过期
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity == null) {
            throw new Exception("token 不存在");
        }
        Long date = tokenEntity.getExpireDate();
        if (date < System.currentTimeMillis()) {
            throw ServiceException.newInstance(ErrorCodes.TOKEN_FAILURE);
        }
        Document document = new Document();
        document.put("serverId", serverId);
        document.put("token", token);
        mongoTemplate.insert(document, collection);
    }

    @Override
    public List countByServerId(String serverId) {
        List<AggregationOperation> aggregations = new ArrayList<>();
        aggregations.add(match(where("serverId").is(serverId)));
        aggregations.add(group("token").count().as("count"));
        aggregations.add(lookup("token", "_id", "token", "token"));
        aggregations.add(unwind("token"));
        aggregations.add(project()
                .and("count").as("useCount")
                .and("token.name").as("tokenName")
                .and("token.active").as("active")
                .and("token.token").as("token")
                .and("token.remark").as("remark")
                .andExclude("_id")
        );
        Aggregation aggregation = newAggregation(aggregations);
        AggregationResults aggregate = mongoTemplate.aggregate(aggregation, collection, Document.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List countByToken(String token) {
        List<AggregationOperation> aggregations = new ArrayList<>();
        aggregations.add(match(where("token").is(token)));
        aggregations.add(group("serverId").count().as("count"));
        Aggregation aggregation = Aggregation.newAggregation(aggregations);
        AggregationResults<Document> aggregate = mongoTemplate.aggregate(aggregation, collection, Document.class);
        List<Document> mappedResults = aggregate.getMappedResults();
        return mappedResults.stream().map(a -> {
            Task task = taskRepository.findOne((String) a.get("_id"));
            Map<String, Object> map = new HashMap<>();
            map.put("serviceName", task.getName());
            map.put("remark", task.getRemark());
            map.put("serviceId", task.getId());
            map.put("useCount", a.get("count"));
            return map;
        }).collect(Collectors.toList());

    }
}
