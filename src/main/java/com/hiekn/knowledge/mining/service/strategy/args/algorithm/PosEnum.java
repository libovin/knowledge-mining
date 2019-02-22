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
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.FuDan, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.FuDan, (String) x));
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
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (String) x));
                }
                return map;
            };
        }
    },
    percept() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (String) x));
                }
                return map;
            };
        }
    },

    L_POS() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.LTP, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.LTP, (String) x));
                }
                return map;
            };
        }
    },

    S_POS() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.STANFORD, (List<String>) x, this.name()));
                } else if (x instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.STANFORD, (String) x));
                }
                return map;
            };
        }
    },

    NULL() {
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
