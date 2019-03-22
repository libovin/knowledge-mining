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

public enum PosEnum implements ArgsStrategy {
    F_POS() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.FuDan, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    String str = (String) input;
                    if (str.endsWith("\n")) {
                        str = str.replace("\n", "");
                    }
                    map.put("result", nlpToolService.posTaggerService(Config.FuDan, str,args.getLanguage()));
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
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },
    percept() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.HANLP, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },

    L_POS() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.LTP, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.LTP, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },

    S_POS() {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof List) {
                    map.put("result", nlpToolService.posTaggerService(Config.STANFORD, (List<String>) input, this.name(),args.getLanguage()));
                } else if (input instanceof String) {
                    map.put("result", nlpToolService.posTaggerService(Config.STANFORD, (String) input,args.getLanguage()));
                }
                return map;
            };
        }
    },

    NULL() {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", input);
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
