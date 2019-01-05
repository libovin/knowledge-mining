package com.hiekn.knowledge.mining.service;

import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;

import java.util.List;
import java.util.Map;

/**
 * 分词
 */
public interface NlpService {


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
    List<String> keyword(String input);


    /**
     * 自动摘要
     *
     * @param input
     * @return
     */
    List<String> summary(String input);


    /**
     * 文本分类
     *
     * @param input
     * @return
     */
    String classifier(String input);

    Map getProp();
}
