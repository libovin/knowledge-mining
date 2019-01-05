package com.hiekn.knowledge.mining.bean.prop.common;

import java.util.ArrayList;
import java.util.List;

public class Next {

    private List nlp = new ArrayList();
    private List counter =new ArrayList();
    private List pattern =new ArrayList();

    public List getNlp() {
        return nlp;
    }

    public void setNlp(List nlp) {
        this.nlp = nlp;
    }

    public List getCounter() {
        return counter;
    }

    public void setCounter(List counter) {
        this.counter = counter;
    }

    public List getPattern() {
        return pattern;
    }

    public void setPattern(List pattern) {
        this.pattern = pattern;
    }
}
