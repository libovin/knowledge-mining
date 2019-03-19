package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.boot.autoconfigure.base.exception.ServiceException;
import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Task;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.exception.ErrorCodes;
import com.hiekn.knowledge.mining.filter.LoggingRequestFilter;
import com.hiekn.knowledge.mining.service.TokenCountService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class TokenCountServiceImpl implements TokenCountService {
    private static final Logger log = LoggerFactory.getLogger(TokenCountServiceImpl.class);
    private static String collection = "tokenCount";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void recordToken(String serverId, String token) {
        try {
            //验证token是否过期
            List<Token> tokenList = mongoTemplate.find(new Query(Criteria.where("content").is(token)),Token.class);
            if (tokenList != null && tokenList.size() > 0){
                Token  t = tokenList.get(0);
                Date date = t.getDate();
                if (date.getTime() < (new Date()).getTime()){
                    throw ServiceException.newInstance(ErrorCodes.TOKEN_FAILURE);
                }
            }
            Document document = new Document();
            document.put("serverId",serverId);
            document.put("token",token);
            mongoTemplate.insert(document,collection);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Override
    public RestData countByServerId() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("serverId").count().as("count"));
        AggregationResults<Document> aggregate = mongoTemplate.aggregate(aggregation, collection, Document.class);
        Iterator<Document> iterator = aggregate.iterator();
        List<Map<String,String>> mapList = Lists.newArrayList();
        while (iterator.hasNext()){
            Map<String,String> map = Maps.newHashMap();
            Document next = iterator.next();
            String serverId = (String)next.get("_id");
            Task task = mongoTemplate.findOne(new Query(Criteria.where("_id").is(serverId)), Task.class);
            map.put("name",task.getName());
            map.put("serverId",serverId);
            map.put("count",String.valueOf(next.get("count")));
            mapList.add(map);
        }
        return new RestData(mapList,mapList.size());
    }
}
