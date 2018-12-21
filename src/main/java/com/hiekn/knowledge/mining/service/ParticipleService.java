package com.hiekn.knowledge.mining.service;

import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import com.hiekn.nlp.tool.NLPService;

import java.util.List;

/**
 * 分词
 */
public interface ParticipleService {


    /**
     * 分词
     *
     * @param input
     * @return
     */
    List<String> segment(String input);

    /**
     * 词性
     *
     * @param input
     * @return
     */
    List<PartOfSpeech> pos(String input);

    /**
     * 实体识别
     *
     * @param input
     * @return
     */
    List<TermBean> ner(String input);

    /**
     * 关键词提取
     *
     * @param input
     * @return
     */
    List<String> extractKeyword(String input);


    /**
     * 自动摘要
     *
     * @param input
     * @return
     */
    List<String> autoSummary(String input);


    /**
     * 文本分类
     *
     * @param input
     * @return
     */
    String textClassification(String input);
}
