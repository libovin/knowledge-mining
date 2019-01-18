package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.bo.Counter;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.nlplab.bean.TermBean;
import com.hiekn.nlplab.nlptools.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NlpServiceImpl implements NlpService {

    @Autowired
    private ToolService toolService;

    @Override
    public List<String> segment(Map req, ConfigReq config) {
        String input = config.getInput();
        String algorithm = config.getAlgorithm();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.segmentService("hanlp", content, algorithm);
        }
        return null;
    }

    private List<String> getList(List list) {
        List<String> wordList = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof String) {
                String word = (String) obj;
                wordList.add(word);
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
    public List<TermBean> pos(Map req, ConfigReq config) {
        String input = config.getInput();
        String algorithm = config.getAlgorithm();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.posTaggerService("hanlp", content);
        } else if (o instanceof List) {
            List list = (List) o;
            return toolService.posTaggerService("hanlp", getList(list), algorithm);
        }
        return null;
    }

    @Override
    public List<TermBean> ner(Map req, ConfigReq config) {
        String input = config.getInput();
        String algorithm = config.getAlgorithm();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.nerService("hanlp", content);
        } else if (o instanceof List) {
            List list = (List) o;
            return toolService.nerService("hanlp", getList(list), algorithm);
        }
        return null;
    }

    @Override
    public List<String> keyword(Map req, ConfigReq config) {
        String input = config.getInput();
        Integer size = config.getSize();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.keywordsService("hanlp", content, size);
        } else if (o instanceof List) {
            List list = (List) o;
            return toolService.keywordsService("hanlp", getList(list), size);
        }
        return null;
    }

    @Override
    public List<String> summary(Map req, ConfigReq config) {
        String input = config.getInput();
        Integer size = config.getSize();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.summaryService("hanlp", content, size);
        }
        return null;
    }

    @Override
    public String classifier(Map req, ConfigReq config) {
        String input = config.getInput();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.classifyService("hanlp", content);
        }
        return null;
    }

    @Override
    public String denpendency(Map req, ConfigReq config) {
        String input = config.getInput();
        Object o = req.get(input);
        if (o instanceof String) {
            String content = (String) o;
            return toolService.denpendencyService("hanlp", content);
        }
        return null;
    }
}
