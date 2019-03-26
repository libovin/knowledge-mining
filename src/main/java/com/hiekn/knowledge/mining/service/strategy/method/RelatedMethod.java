package com.hiekn.knowledge.mining.service.strategy.method;


import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.RelatedService;
import com.hiekn.knowledge.mining.service.impl.RelatedServiceImpl;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
public enum RelatedMethod implements MethodStrategy {
    BAIDU(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractAnalysis((String)input));
                return map;
            };
        }
    },
    AMINER(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractAminer((String)input));
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
                //用于返回
                Map<String ,Object> mapReturn = new HashMap<>();
                //用于接收
                Map<String , Object> map = (Map)input;
                //获取method类型
                String method =  (String) map.get("method");
                //进行判断
                if ("JOURNAL".equals(method)){
                    //加入四个key，用于在迭代的时候添加归一之后的数据
                    map.put("被引量(归一)",null);
                    map.put("搜索指数(归一)",null);
                    map.put("发文量(归一)",null);
                    map.put("影响因子(归一)",null);
                    //遍历key
                    for (String key : map.keySet()){
                        //进行判断
                        if ("被引量".equals(key)){
                            //对数据归一
                            Double value = (Double.parseDouble(((String) map.get(key)))/1000000);
                            map.put("被引量(归一)",value);
                        } else if ("搜索指数".equals(key)){
                            Double value = (Double.parseDouble(((String) map.get(key)))/100000);
                            map.put("搜索指数(归一)",value);
                        } else if ("发文量".equals(key)) {
                            Double value = (Double.parseDouble(((String) map.get(key)))/100000);
                            map.put("发文量(归一)",value);
                        }
                        else if ("影响因子".equals(key)){
                            Double value = (Double.parseDouble(((String) map.get(key)))/10);
                            map.put("影响因子(归一)",value);
                        }
                    }
//                    double v1 = (double)map.get("被引量(归一)");
//                    double v2 = (double)map.get("搜索指数(归一)");
//                    double v3  = (double)map.get("发文量(归一)");
//                    double v4  = (double)map.get("影响因子(归一)");
                    //判断值是否为null
                    Double v1 = args.getCitedtheamountweight();
                    if (v1==null){
                        v1=1.00;
                    }
                    Double v2 = args.getSearchindexweight();
                    if (v2==null){
                        v2=1.00;
                    }
                    Double v3 = args.getIdentificatedweight();
                    if (v3==null){
                        v3=1.00;
                    }
                    Double v4 = args.getFactorofinfluenceweight();
                    if (v4==null){
                        v4=1.00;
                    }
//                  double num =v1*v5+v2*v6+v3*v7+v4*v8;
//                    double num = (v1*args.getCitedtheamountweight()+
//                            v2*args.getSearchindexweight()+
//                            v3*args.getIdentificatedweight()+
//                            v4*args.getFactorofinfluenceweight())/4;
                    //加权平均
                    double num =  (((double)map.get("被引量(归一)"))*v1+
                            ((double)map.get("搜索指数(归一)"))*v2+
                            ((double)map.get("发文量(归一)"))*v3+
                            ((double)map.get("影响因子(归一)"))*v4)/4;
                    //放入集合
                    map.put("加权平均值",num);
                }else if ("LITERATURE".equals(method)){
                    map.put("被引量(归一)",null);
                    map.put("阅读量(归一)",null);
                    for (String key : map.keySet()){
                        if ("被引量：".equals(key)) {
                            Double value = (Double.parseDouble(((String) map.get(key)))/1000000);
                            map.put("被引量(归一)",value);
                        }else if ("阅读量：".equals(key)){
                            Double value = (Double.parseDouble(((String) map.get(key)))/100000);
                            map.put("阅读量(归一)",value);
                        }
                    }
                    Double v1 = args.getCitedtheamountweight();
                    if (v1==null){
                        v1=1.00;
                    }
                    Double v2 = args.getReadingquantityweight();
                    if (v2==null){
                        v2=1.00;
                    }
                    double num =  (((double)map.get("被引量(归一)"))*v1+
                            ((double)map.get("阅读量(归一)"))*v2)/2;
                    map.put("加权平均值",num);
                }
                mapReturn.put("result", map);
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
