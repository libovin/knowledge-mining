package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.nlplab.conf.Config;
import com.hiekn.nlplab.nlptools.NLPToolService;

import java.util.Map;
import java.util.function.BiFunction;

public enum SegmentEnum implements ArgsStrategy {
    F_SEG() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.FuDan, x, this.name()));
                return map;
            };
        }
    },

    shortest() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    crf() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    nlp() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    dict() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    index() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    standard() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },
    n_short() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, x, this.name()));
                return map;
            };
        }
    },

    L_MME() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.LTP, x, this.name()));
                return map;
            };
        }
    },

    N_SEG() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.NLPir, x, this.name()));
                return map;
            };
        }
    },

    S_SEG() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", nlpToolService.segmentService(Config.STANFORD, x, this.name()));
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
