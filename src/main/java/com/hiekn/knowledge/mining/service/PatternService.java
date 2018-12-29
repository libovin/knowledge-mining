package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.pojo.Item;

import java.util.List;
import java.util.regex.Pattern;

public interface PatternService {

    Item getProp();

    Object find(String kw, Pattern pattern);

    Object matches(List list,Pattern pattern);

    Object matches(String string, Pattern pattern);
}
