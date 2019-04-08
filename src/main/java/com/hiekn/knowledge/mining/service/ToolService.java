package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.bo.Counter;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;

import java.util.List;


public interface ToolService {

    List<Counter> count(List<String> req , ArgsReq argsReq);

    List<PatternFind> find(String req, ArgsReq argsReq);

    PatternMatches matches(String req, ArgsReq argsReq);

    List<PatternMatches> matches (List req,ArgsReq argsReq);
}
