package com.hiekn.knowledge.mining.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.ConfigService;

import com.hiekn.knowledge.mining.service.strategy.ModelEnum;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.knowledge.mining.service.strategy.method.MethodStrategy;
import com.hiekn.knowledge.mining.service.strategy.method.NullMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.function.BiFunction;


@Service
public class ConfigServiceImpl implements ConfigService {

    @Override
    public Map getProp() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String fileName = "json/config.json";
        Enumeration<URL> resources;
        JSONObject jsonObject = new JSONObject();
        try {
            resources = classLoader.getResources(fileName);
        } catch (IOException e) {
            return jsonObject;
        }
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                String json = Resources.toString(url, Charsets.UTF_8);
                jsonObject.putAll(JSON.parseObject(json));
            } catch (IOException e) {

            }
        }
        return jsonObject;
    }

    public BiFunction<? extends Object, ArgsReq, Map> getFun(ConfigReq r) {
        ModelEnum modelEnum = ModelEnum.NULL.getModelStrategy(r);
        if (modelEnum != ModelEnum.NULL) {
            MethodStrategy methods = modelEnum.getMethods().getMethodStrategy(r);
            if (methods != NullMethod.NULL) {
                ArgsStrategy argsStrategy = methods.getArgs();
                if (argsStrategy != ArgsNullEnum.NULL) {
                    return argsStrategy.getArgsStrategy(r.getArgs()).getFun();
                } else {
                    return methods.getFun();
                }
            }
        }
        return (x, y) -> {
            Map map = Maps.newHashMap();
            map.put("result", x);
            return map;
        };
    }

}
