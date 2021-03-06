package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.bo.Counter;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.ToolService;
import com.hiekn.knowledge.mining.util.RuleUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ToolServiceImpl implements ToolService {

    @Override
    public List<Counter> count(List<String> req, ArgsReq argsReq) {
        List<Counter> counters = new ArrayList<>();
        Map<String, BigDecimal> map = new HashMap();
        BigDecimal size = new BigDecimal(req.size());
        for (String item : req) {
            map.compute(item, (k, v) -> v == null ? BigDecimal.ONE : v.add(BigDecimal.ONE));
        }
        for (Map.Entry<String, BigDecimal> l : map.entrySet()) {
            BigDecimal v = l.getValue();
            BigDecimal f = v.divide(size, 5, BigDecimal.ROUND_HALF_EVEN);
            counters.add(new Counter(l.getKey(), l.getValue().intValue(), f.doubleValue()));
        }
        Comparator<Counter> c = Comparator.comparing(Counter::getCount);
        if (StringUtils.hasText(argsReq.getSort()) && "desc".equalsIgnoreCase(argsReq.getSort())) {
            c = c.reversed();
        }
        counters.sort(c);
        return counters;
    }

    @Override
    public List<PatternFind> find(String req, ArgsReq argsReq) {
        List<PatternFind> arr = new ArrayList<>();
        List<String> split = argsReq.getRuleId();
        for (String ss : split) {
            Pattern pattern = RuleUtils.instance.getRulePattern(ss);
            Matcher matcher = pattern.matcher(req);
            while (matcher.find()) {
                int s = matcher.start();
                int e = matcher.end();
                String w = req.substring(s, e);
                arr.add(new PatternFind(w, s, e));
            }
        }

        return arr;
    }

    @Override
    public PatternMatches matches(String req, ArgsReq argsReq) {
        List<String> split = argsReq.getRuleId();
        boolean flag = false;
        for (String s : split) {
            Pattern pattern = RuleUtils.instance.getRulePattern(s);
            Matcher matcher = pattern.matcher(req);
            flag = flag | matcher.matches();
        }
        return new PatternMatches(req, flag);
    }

    @Override
    public List<PatternMatches> matches(List req, ArgsReq argsReq) {
        List<PatternMatches> patternList = new ArrayList<>();
        for (Object item : req) {
            if (item instanceof String) {
                patternList.add(matches((String) item, argsReq));
            } else if (item instanceof PatternMatches) {
                PatternMatches p = (PatternMatches) item;
                patternList.add(matches(p.getText(), argsReq));
            }
        }
        return patternList;
    }
}
