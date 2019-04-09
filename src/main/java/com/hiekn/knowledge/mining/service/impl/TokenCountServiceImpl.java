package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.repository.TaskRepository;
import com.hiekn.knowledge.mining.repository.TokenRepository;
import com.hiekn.knowledge.mining.service.TokenCountService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.query.Criteria.where;

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
    public Boolean recordToken(String serverId, String token) {

        //验证token是否过期
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity == null) {
            return false;
        }
        if(0 == tokenEntity.getActive()){
            return false;
        }
        Long date = tokenEntity.getExpireDate();
        Long x=System.currentTimeMillis();
        if (date < x) {
            return false;
        }
        Document document = new Document();
        document.put("serverId", serverId);
        document.put("token", token);
        mongoTemplate.insert(document, collection);
        return true;
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
