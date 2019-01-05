package com.hiekn.knowledge.mining.bean.prop.common;

public class ComProp {
    private Args args = new Args();
    private Next allowedNext =new Next();

    public Args getArgs() {
        return args;
    }

    public void setArgs(Args args) {
        this.args = args;
    }

    public Next getAllowedNext() {
        return allowedNext;
    }

    public void setAllowedNext(Next allowedNext) {
        this.allowedNext = allowedNext;
    }
}
