package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.nlplab.conf.Config;
import com.hiekn.nlplab.nlptools.NLPToolService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum SegmentEnum implements ArgsStrategy {
    F_SEG() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.FuDan, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },

    shortest() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },
    crf() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },
    nlp() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },
    dict() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },
    indeinput() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },
    standard() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },
    n_short() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },

    L_MME() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.LTP, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },

    N_SEG() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.NLPir, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    },

    S_SEG() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.STANFORD, input, this.name(),args.getLanguage()));
                return map;
            };
        }
    };

    SegmentEnum() {

    }

    protected NLPToolService nlpToolService = new NLPToolService();

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq args) {
        for (SegmentEnum argsEnum : SegmentEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(args.getAlgorithm())) {
                return argsEnum;
            }
        }
        return ArgsNullEnum.NULL;
    }
}
