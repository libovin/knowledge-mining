package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.nlplab.conf.Config;
import com.hiekn.nlplab.nlptools.NLPToolService;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum NerEnum implements ArgsStrategy {
    F_NER() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.nerService(Config.FuDan, x, this.name()));
                return map;
            };
        }
    },

    crf() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.nerService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    hmm() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.nerService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },

    L_NER() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.nerService(Config.LTP, x, this.name()));
                return map;
            };
        }
    },
    S_NER() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.nerService(Config.STANFORD, x, this.name()));
                return map;
            };
        }
    };

    protected NLPToolService nlpToolService = new NLPToolService();

    NerEnum() {
    }

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        for (NerEnum argsEnum : NerEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(agrs.getAlgorithm())) {
                return argsEnum;
            }
        }
        return ArgsNullEnum.NULL;
    }
}
