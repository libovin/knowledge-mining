package com.hiekn.knowledge.mining.service;


import com.hiekn.knowledge.mining.bean.vo.ConfigReq;
import com.hiekn.nlplab.bean.TermBean;

import java.util.List;
import java.util.Map;

/**
 * 分词
 */
public interface NlpService {


    /**
     * 分词
     *
     * @param req
     * @return
     */
    List<String> segment(Map req, ConfigReq config);

    /**
     * 词性
     *
     * @param req
     * @return
     */
    List<TermBean> pos(Map req, ConfigReq config);

    /**
     * 实体识别
     *
     * @param req
     * @return
     */
    List<TermBean> ner(Map req, ConfigReq config);

    /**
     * 关键词提取
     *
     * @param req
     * @return
     */
    List<String> keyword(Map req, ConfigReq config);


    /**
     * 自动摘要
     *
     * @param req
     * @return
     */
    List<String> summary(Map req, ConfigReq config);


    /**
     * 文本分类
     *
     * @param req
     * @return
     */
    String classifier(Map req, ConfigReq config);


    String denpendency(Map req, ConfigReq config);
}
