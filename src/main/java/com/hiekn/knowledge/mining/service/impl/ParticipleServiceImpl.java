package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.service.ParticipleService;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipleServiceImpl implements ParticipleService {


    @Override
    public List<String> segment(String input) {
        return null;
    }

    @Override
    public List<PartOfSpeech> pos(String input) {
        return null;
    }

    @Override
    public List<TermBean> ner(String input) {
        return null;
    }

    @Override
    public List<String> extractKeyword(String input) {
        return null;
    }

    @Override
    public List<String> autoSummary(String input) {
        return null;
    }

    @Override
    public String textClassification(String input) {
        return null;
    }
}
