package com.hiekn.knowledge.mining.service.strategy.method;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.NerEnum;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.PosEnum;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.SegmentEnum;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.nlplab.bean.TermBean;
import com.hiekn.nlplab.conf.Config;
import com.hiekn.nlplab.nlptools.NLPToolService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum NlpMethod implements MethodStrategy {
    SEGMENT(SegmentEnum.crf) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.segmentService(Config.HANLP, (String) input, "",y.getLanguage()));
                return map;
            };
        }
    },
    POS(PosEnum.crf) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                map.put("result", nlpToolService.posTaggerService(Config.HANLP, (String)input,y.getLanguage()));
                return map;
            };
        }
    },
    NER(NerEnum.crf) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                List<TermBean> list = new ArrayList<>();
                if (input instanceof String) {
                    list = nlpToolService.nerService(Config.HANLP, (String) input,y.getLanguage());
                } else if (input instanceof List) {
                    list = nlpToolService.nerService(Config.HANLP, (List<String>) input, Config.CRF,y.getLanguage());
                }
                map.put("result", list);
                return map;
            };
        }
    },
    KEYWORD(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                List<String> list = new ArrayList<>();
                if (input instanceof String) {
                    list = nlpToolService.keywordsService(Config.HANLP, (String) input, y.getSize(),y.getLanguage());
                } else if (input instanceof List) {
                    list = nlpToolService.keywordsService(Config.HANLP, (List<String>) input, y.getSize(),y.getLanguage());
                }
                map.put("result", list);
                return map;
            };
        }
    },
    SUMMARY(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                List<String> list = nlpToolService.summaryService(Config.HANLP, (String) input, y.getSize(),y.getLanguage());
                map.put("result", list);
                return map;
            };
        }
    },
    CLASSIFIER(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                String str = nlpToolService.classifyService(Config.HANLP, (String) input,y.getLanguage());
                map.put("result", str);
                return map;
            };
        }
    },
    DENPENDENCY(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq y) -> {
                Map map = new HashMap();
                String str = nlpToolService.denpendencyService(Config.HANLP, (String) input,y.getLanguage());
                map.put("result", str);
                return map;
            };
        }
    };

    protected NLPToolService nlpToolService = new NLPToolService();

    private ArgsStrategy args;

    public ArgsStrategy getArgs() {
        return args;
    }

    NlpMethod(ArgsStrategy kind) {
        this.args = kind;
    }


    public MethodStrategy getMethodStrategy(ConfigReq configReq) {
        for (NlpMethod methodEnum : NlpMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NullMethod.NULL;
    }
}
