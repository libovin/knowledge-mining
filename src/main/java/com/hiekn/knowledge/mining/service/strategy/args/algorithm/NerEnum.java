package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.nlplab.conf.Config;
import com.hiekn.nlplab.nlptools.NLPToolService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum NerEnum implements ArgsStrategy {
    F_NER() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.FuDan, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.FuDan, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },

    crf() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },
    hmm() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.HANLP, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },

    L_NER() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.LTP, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.LTP, (String) input,args.getLanguage()));
                }
                System.out.println(map);
                return map;
            };
        }
    },
    S_NER() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.nerService(Config.STANFORD, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.nerService(Config.STANFORD, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    };

    protected NLPToolService nlpToolService = new NLPToolService();

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
