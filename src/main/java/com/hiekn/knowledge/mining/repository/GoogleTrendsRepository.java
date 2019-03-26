package com.hiekn.knowledge.mining.repository;


import com.hiekn.knowledge.mining.bean.vo.GoogleTrends;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoogleTrendsRepository extends MongoRepository<GoogleTrends,String> {
}
