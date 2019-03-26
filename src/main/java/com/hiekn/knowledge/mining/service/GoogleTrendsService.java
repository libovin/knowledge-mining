package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.vo.GoogleTrends;

public interface GoogleTrendsService {

    GoogleTrends collectToMongo(String keyWord);
}
