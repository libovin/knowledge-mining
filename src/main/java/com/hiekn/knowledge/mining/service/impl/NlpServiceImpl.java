package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.bo.Counter;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import com.hiekn.nlp.tool.support.FdNLPService;
import com.hiekn.nlp.tool.support.HanLpService;
import com.hiekn.nlp.tool.support.LTPService;
import com.hiekn.nlp.tool.support.NLPIRService;
import com.hiekn.nlp.tool.support.StanfordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NlpServiceImpl implements NlpService {

    @Autowired
    private FdNLPService fdNLPService;

    @Autowired
    private HanLpService hanLpService;

    @Autowired
    private LTPService ltpService;

    @Autowired
    private NLPIRService nlpirService;

    @Autowired
    private StanfordService stanfordService;


    @Override
    public List<String> segment(Map req, Map config) {
        String input = (String) config.get("input");
        Object s = config.get("algorithm");
        String algorithm = null;
        if (s != null) {
            algorithm = (String) s;
        }

        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            if (algorithm == null) {
                return hanLpService.segment(content);
            } else {
                return hanLpService.segment(content, algorithm);
            }
        } else if (o instanceof List) {
            List list = (List) o;
            List<String> wordList = getList(list);
//            if (algorithm == null) {
//                return hanLpService.segment(wordList);
//            } else {
//                return hanLpService.segment(wordList, algorithm);
//            }
        }
        return null;
    }

    private List<String> getList(List list) {
        List<String> wordList = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof String) {
                String word = (String) obj;
                wordList.add(word);
            } else if (obj instanceof PartOfSpeech) {
                PartOfSpeech word = (PartOfSpeech) obj;
                wordList.add(word.getDescription());
            } else if (obj instanceof TermBean) {
                TermBean word = (TermBean) obj;
                wordList.add(word.getTerm());
            } else if (obj instanceof PatternFind) {
                PatternFind word = (PatternFind) obj;
                wordList.add(word.getText());
            } else if (obj instanceof PatternMatches) {
                PatternMatches word = (PatternMatches) obj;
                wordList.add(word.getText());
            } else if (obj instanceof Counter) {
                Counter word = (Counter) obj;
                wordList.add(word.getWord());
            }
        }
        return wordList;
    }


    @Override
    public List<PartOfSpeech> pos(Map req, Map config) {
        String content = (String) req.get("content");
        return hanLpService.pos(content);
    }

    @Override
    public List<TermBean> ner(Map req, Map config) {
        String content = (String) req.get("content");
        return hanLpService.ner(content);
    }

    @Override
    public List<String> keyword(Map req, Map config) {
        String content = (String) req.get("content");
        return hanLpService.extractKeyword(content);
    }

    @Override
    public List<String> summary(Map req, Map config) {
        String content = (String) req.get("content");
        return hanLpService.autoSummary(content);
    }

    @Override
    public String classifier(Map req, Map config) {
        String content = (String) req.get("content");
        return hanLpService.textClassification(content);
    }
}
