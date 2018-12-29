package com.hiekn.knowledge.mining.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.pojo.Item;
import com.hiekn.knowledge.mining.service.RelatedService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class RelatedServiceImpl implements RelatedService {

    private static final Logger logger = LoggerFactory.getLogger(RelatedServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String BD_XUE_SHU_URL = "http://xueshu.baidu.com/";
    private static final String AMINER_URL = "https://api.aminer.cn/api/";

    private String sendGet(String url) {
        String source = "";
        try {
            source = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            logger.error("send request error,url = {}", url);
        }
        return source;
    }

    private String sendPost(String url, MultiValueMap<String, String> body) {
        String source = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
            source = restTemplate.postForObject(BD_XUE_SHU_URL + url, requestEntity, String.class);
        } catch (Exception e) {
            logger.error("send post error,body = {}", body);
        }
        return source;
    }

    private String getReplaceUrl(String source) {
        String script = Jsoup.parse(source).select("script").first().html();
        String url = null;
        Pattern pattern = Pattern.compile("'http:.+'");
        Matcher matcher = pattern.matcher(script);
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                url = matcher.group();
            }
        }
        return Objects.isNull(url) ? url : url.substring(1, url.length() - 1);
    }

    @Override
    public Map<String, Object> extract(String kw) {
        Map<String, Object> map = extractJournal(kw);
        if (map.isEmpty()) {
            map = extractLiterature(kw);
        }
        return map;
    }

    @Override
    public Map<String, Object> extractJournal(String kw) {
        Map<String, Object> rs = Maps.newHashMap();
        String string = sendGet(BD_XUE_SHU_URL + "s?wd=" + kw);
        Document document = Jsoup.parse(string);
        Elements elements = document.select("div.op-scholar-card-info-cont.c-span-last .op-scholar-card-subinfo > div");
        for (Element element : elements) {
            String key = element.select("span:eq(0)").first().ownText();
            String text = element.select("span:eq(1)").first().ownText();
            rs.put(key, text);
        }
        return rs;
    }

    @Override
    public Map<String, Object> extractLiterature(String kw) {
        Map<String, Object> rs = Maps.newHashMap();
        String replaceUrl = getReplaceUrl(sendGet(BD_XUE_SHU_URL + "s?wd=" + kw));
        if (Objects.nonNull(replaceUrl)) {
            Elements elements = Jsoup.parse(sendGet(replaceUrl)).select(".c_content > div");
            for (Element element : elements) {
                rs.put(element.select("p:eq(0)").first().ownText(), element.select("p:eq(1)").first().text());
            }
        }
        return rs;
    }

    @Override
    public Map<String, Object> extractAnalysis(String kw) {
        Map<String, Object> rs = Maps.newHashMap();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("kw_list", kw);
        body.add("cmd", "batch_get_kw_chart");
        body.add("_token", getUUID() + getUUID());
        body.add("_ts", String.valueOf(System.currentTimeMillis() / 1000));
        body.add("_sign", getUUID());
        String source = sendPost("usercenter/data/paperreco", body);
        if (!source.isEmpty()) {
            JSONObject data = JSON.parseObject(source);
            if (data.getInteger("error_no") == 0) {
                data = data.getJSONObject("data").getJSONObject("current_kw_data");
                data.forEach((k, v) -> {
                    rs.put(k, v);
                });
            }
        }
        return rs;
    }

    @Override
    public Map<String, Object> extractAminer(String kw) {
        Map<String, Object> rs = Maps.newHashMap();
        String listData = sendGet(AMINER_URL + "search/person?query=" + kw + "&size=1&sort=relevance");
        JSONArray dataArr = JSON.parseObject(listData).getJSONArray("result");
        if (!dataArr.isEmpty()) {
            JSONObject obj = dataArr.getJSONObject(0);
            String id = obj.getString("id");

            rs.put("statistic", JSON.parse(sendGet(AMINER_URL + "person/indices/" + id)));

            rs.put("interests", JSON.parse(sendGet(AMINER_URL + "person/interests/" + id)));

            rs.put("similar", JSON.parse(sendGet(AMINER_URL + "person/similar/" + id)));

            rs.put("dcore", JSON.parse(sendGet(AMINER_URL + "person/indices/dcore/" + id)));

            rs.put("ego", JSON.parse(sendGet(AMINER_URL + "person/ego/" + id)));

        }

        return rs;
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


    @Override
    public List getProp(){
        List<Item> list =new ArrayList<>();
        list.add(Item.of("baidu","analysis"));
        list.add(Item.of("Aminer","aminer"));
        list.add(Item.of("期刊","journal"));
        list.add(Item.of("文献","literature"));
        return list;
    }
}
