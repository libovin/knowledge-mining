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
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.FuDan, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.FuDan, (String) x));
                }
                return map;
            };
        }
    },

    crf() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (String) x));
                }
                return map;
            };
        }
    },
    hmm() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (String) x));
                }
                return map;
            };
        }
    },

    L_NER() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.LTP, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.LTP, (String) x));
                }
                return map;
            };
        }
    },
    S_NER() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.STANFORD, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.STANFORD, (String) x));
                }
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
