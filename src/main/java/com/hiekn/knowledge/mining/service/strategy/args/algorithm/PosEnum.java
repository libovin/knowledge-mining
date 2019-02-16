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

public enum PosEnum implements ArgsStrategy {
    F_POS() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.posTaggerService(Config.FuDan, x, this.name()));
                return map;
            };
        }
    },

    crf() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.posTaggerService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    percept() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.posTaggerService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },

    L_POS() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.posTaggerService(Config.LTP, x, this.name()));
                return map;
            };
        }
    },

    S_POS () {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.posTaggerService(Config.STANFORD, x, this.name()));
                return map;
            };
        }
    },

    NULL () {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", x);
                return map;
            };
        }
    };

    PosEnum() {
    }

    protected NLPToolService nlpToolService = new NLPToolService();


    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq args) {
        for (PosEnum argsEnum : PosEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(args.getAlgorithm())) {
                return argsEnum;
            }
        }
        return ArgsNullEnum.NULL;
    }
}
