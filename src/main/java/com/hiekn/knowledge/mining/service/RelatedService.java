package com.hiekn.knowledge.mining.service;

import java.util.List;
import java.util.Map;

/**
 * 关联分析
 */
public interface RelatedService {
    Map<String,Object> extract(String kw);
    Map<String,Object> extractJournal(String kw);
    Map<String,Object> extractLiterature(String kw);
    Map<String,Object> extractAnalysis(String kw);
    Map<String,Object> extractAminer(String kw);
    List getProp();
}
