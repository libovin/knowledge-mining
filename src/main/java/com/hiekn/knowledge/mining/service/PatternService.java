package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.bo.Item;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;

import java.util.List;
import java.util.regex.Pattern;

public interface PatternService {

    Item getProp();

    List<PatternFind> find(String kw, Pattern pattern);

    List<PatternMatches> matches(List list,Pattern pattern);

    PatternMatches matches(String string, Pattern pattern);
}
