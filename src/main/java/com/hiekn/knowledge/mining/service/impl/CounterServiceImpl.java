package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.bo.Counter;
import com.hiekn.knowledge.mining.service.CounterService;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class CounterServiceImpl implements CounterService {

    @Override
    public List<Counter> count(List list) {
        Map<String, BigDecimal> map = Maps.newHashMap();
        BigDecimal size = new BigDecimal(list.size());
        for (Object item : list) {
            if (item instanceof String) {
                String key = (String) item;
                map.compute(key, (k, v) -> v == null ? BigDecimal.ONE : v.add(BigDecimal.ONE));
            } else if (item instanceof PartOfSpeech) {
                PartOfSpeech partOfSpeech = (PartOfSpeech) item;
                map.compute(partOfSpeech.getDescription(), (k, v) -> v == null ? BigDecimal.ONE : v.add(BigDecimal.ONE));
            } else if (item instanceof TermBean) {
                TermBean termBean = (TermBean) item;
                map.compute(termBean.getTerm(), (k, v) -> v == null ? BigDecimal.ONE : v.add(BigDecimal.ONE));
            } else {

            }
        }
        List<Counter> counters = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> l : map.entrySet()) {
            BigDecimal v = l.getValue();
            BigDecimal f = v.divide(size, 5, BigDecimal.ROUND_HALF_EVEN);
            counters.add(new Counter(l.getKey(), l.getValue().intValue(), f.doubleValue()));
        }
        Collections.sort(counters, new Comparator<Counter>() {
            @Override
            public int compare(Counter o1, Counter o2) {
                int s = o1.getCount() - o2.getCount();
                return s;
            }
        });
        return counters;
    }

    @Override
    public Map getProp() {
        return null;
    }
}