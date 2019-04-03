package com.hiekn.knowledge.mining.service.strategy.method;


import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.RelatedService;
import com.hiekn.knowledge.mining.service.impl.RelatedServiceImpl;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum RelatedMethod implements MethodStrategy {
    BAIDU(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractAnalysis((String) input));
                return map;
            };
        }
    },
    AMINER(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractAminer((String) input));
                return map;
            };
        }
    },
    JOURNAL(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractJournal((String) input));
                return map;
            };
        }
    },
    LITERATURE(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractLiterature((String) input));
                return map;
            };
        }
    },
    //重要性评价
    IMPORTANT(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                System.out.println(input);
                //用于接收
                Map<String, Object> map = (Map) input;
                //用于计算
                Map<String, BigDecimal> mapBigDecimal = new HashMap<>();
                //用于返回
                Map<String, Object> mapReturn = new HashMap<>();
                //获取method类型
                String method = (String) map.get("method");
                //取小数点后面8位，四舍五入
                MathContext mc = new MathContext(8, RoundingMode.HALF_DOWN);
                //进行判断
                if ("JOURNAL".equals(method)) {
                    if (map.containsKey("被引量")) {
                        BigDecimal bigDecimal = (new BigDecimal((String) map.get("被引量")));
                        //这里的值是随便定的
                        BigDecimal value = bigDecimal.divide(BigDecimal.valueOf(1297080), mc);
                        mapBigDecimal.put("被引量(归一)", value);
                    } else {
                        mapBigDecimal.put("被引量(归一)", BigDecimal.valueOf(0));
                    }
                    if (map.containsKey("搜索指数")) {
                        BigDecimal bigDecimal = (new BigDecimal((String) map.get("搜索指数")));
                        BigDecimal value = bigDecimal.divide(BigDecimal.valueOf(180901), mc);
                        mapBigDecimal.put("搜索指数(归一)", value);
                    } else {
                        mapBigDecimal.put("搜索指数(归一)", BigDecimal.valueOf(0));
                    }
                    if (map.containsKey("发文量")) {
                        BigDecimal bigDecimal = (new BigDecimal((String) map.get("发文量")));
                        BigDecimal value = bigDecimal.divide(BigDecimal.valueOf(108091), mc);
                        mapBigDecimal.put("发文量(归一)", value);
                    } else {
                        mapBigDecimal.put("发文量(归一)", BigDecimal.valueOf(0));
                    }
                    if (map.containsKey("影响因子")) {
                        mapBigDecimal.put("影响因子", new BigDecimal((String) map.get("影响因子")));
                    } else {
                        mapBigDecimal.put("影响因子", BigDecimal.valueOf(0));
                    }
                    //判断值是否为null
                    BigDecimal v1 = args.getCitedAmountWeight();
                    if (v1 == null) {
                        v1 = BigDecimal.valueOf(1);
                    }
                    BigDecimal v2 = args.getSearchIndexWeight();
                    if (v2 == null) {
                        v2 = BigDecimal.valueOf(1);
                    }
                    BigDecimal v3 = args.getPublishedWeight();
                    if (v3 == null) {
                        v3 = BigDecimal.valueOf(1);
                    }
                    BigDecimal v4 = args.getFactorInfluenceWeight();
                    if (v4 == null) {
                        v4 = BigDecimal.valueOf(1);
                    }
                    //加权平均
                    BigDecimal num = ((
                            mapBigDecimal.get("被引量(归一)")).multiply(v1).add((
                            mapBigDecimal.get("搜索指数(归一)")).multiply(v2)).add((
                            mapBigDecimal.get("发文量(归一)")).multiply(v3)).add((
                            mapBigDecimal.get("影响因子")).multiply(v4))).divide((
                            BigDecimal.valueOf(4)), mc);
                    //放入集合
                    mapBigDecimal.put("加权平均值", num);
                } else if ("LITERATURE".equals(method)) {
                    if (map.containsKey("被引量：")) {
                        BigDecimal bigDecimal = (new BigDecimal((String) map.get("被引量：")));
                        BigDecimal value = bigDecimal.divide(BigDecimal.valueOf(107041), mc);
                        mapBigDecimal.put("被引量(归一)", value);
                    } else {
                        mapBigDecimal.put("被引量(归一)", BigDecimal.valueOf(0));
                    }
                    if (map.containsKey("阅读量：")) {
                        BigDecimal bigDecimal = (new BigDecimal((String) map.get("阅读量：")));
                        BigDecimal value = bigDecimal.divide(BigDecimal.valueOf(106241), mc);
                        mapBigDecimal.put("阅读量(归一)", value);
                    } else {
                        mapBigDecimal.put("阅读量(归一)", BigDecimal.valueOf(0));

                    }
                    BigDecimal v1 = args.getCitedAmountWeight();
                    if (v1 == null) {
                        v1 = BigDecimal.valueOf(1);
                    }
                    BigDecimal v2 = args.getReadingWeight();
                    if (v2 == null) {
                        v2 = BigDecimal.valueOf(1);
                    }
                    //加权平均
                    BigDecimal num = ((
                            mapBigDecimal.get("被引量(归一)")).multiply(v1).add((
                            mapBigDecimal.get("阅读量(归一)")).multiply(v2))
                            .divide((
                                    BigDecimal.valueOf(2)), mc));
                    mapBigDecimal.put("加权平均值", num);
                }
                mapReturn.put("result", mapBigDecimal);
                return mapReturn;
            };
        }
    };

    protected RelatedService relatedService = new RelatedServiceImpl(new RestTemplate());

    private ArgsStrategy args;

    public ArgsStrategy getArgs() {
        return args;
    }

    RelatedMethod(ArgsStrategy kind) {
        this.args = kind;
    }

    public MethodStrategy getMethodStrategy(ConfigReq configReq) {
        for (RelatedMethod methodEnum : RelatedMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NullMethod.NULL;
    }

}
