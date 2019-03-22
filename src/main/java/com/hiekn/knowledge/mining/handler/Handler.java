package com.hiekn.knowledge.mining.handler;

import com.hiekn.knowledge.mining.bean.bo.Counter;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.NerEnum;
import com.hiekn.nlplab.bean.TermBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
public class Handler {
    private Handler nextHandler;
    private BiFunction doHandle;
    private ArgsReq args;

    public ArgsReq getArgs() {
        return args;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler(
            BiFunction doHandle,
            ArgsReq args) {
        this.doHandle = doHandle;
        this.args = args;
    }


    public Map handle(Map request, ArgsReq args ,List list) {
        Map apply;
        if (request.containsKey("result")) {
            Object result = request.get("result");
            if (result instanceof List) {
                apply = (Map) doHandle.apply(getList((List) result), args);
            } else {
                apply = (Map) doHandle.apply(result, args);
            }
        } else {
            apply = (Map) doHandle.apply(request.get("content"), args);
        }
        list.add(apply.get("result"));
        if (nextHandler == null) {
            return apply;
        } else {
            return nextHandler.handle(apply, nextHandler.getArgs(), list);
        }
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

}
