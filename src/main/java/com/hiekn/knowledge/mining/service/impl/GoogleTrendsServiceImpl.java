package com.hiekn.knowledge.mining.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.boot.autoconfigure.base.exception.ServiceException;

import com.hiekn.knowledge.mining.bean.vo.GoogleTrends;
import com.hiekn.knowledge.mining.exception.ErrorCodes;
import com.hiekn.knowledge.mining.repository.GoogleTrendsRepository;
import com.hiekn.knowledge.mining.service.GoogleTrendsService;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class GoogleTrendsServiceImpl implements GoogleTrendsService {
    private static final Logger logger = LoggerFactory.getLogger(GoogleTrendsServiceImpl.class);

    private static final String GOOGLE_URL = "https://trends.google.com/trends/api/";
    private static final String EXPLORE = "explore";//获取token
    private static final String WIDGETDATA_MULTILINE = "widgetdata/multiline";//热度随时间变化的趋势
    private static final String WIDGETDATA_COMPAREDGEO = "widgetdata/comparedgeo";////搜索热度（按子区域）
    private static final String WIDGETDATA_RELATEDSEARCHES = "widgetdata/relatedsearches";//相关主题\相关查询 参数不同

    private static final String PROXY_HOST_NAME = "127.0.0.1";
    private static final Integer PROXY_PORT = 1080;

    private static final String TIMESERIES = "TIMESERIES";//热度随时间变化的趋势
    private static final String GEO_MAP = "GEO_MAP";//搜索热度（按子区域）
    private static final String RELATED_TOPICS = "RELATED_TOPICS";//相关主题
    private static final String RELATED_QUERIES = "RELATED_QUERIES";//相关查询

    @Autowired
    private GoogleTrendsRepository googleTrendsRepository;

    public static void main(String[] args) {
        GoogleTrendsServiceImpl googleTrendsService = new GoogleTrendsServiceImpl();
        String keyWord = "篮球";
        GoogleTrends googleTrends = googleTrendsService.getGoogleTrends(keyWord);
        System.out.println(googleTrends);
    }

    private String decodeJson(String jsonStr) {
        String source;
        try {
            source = JSONObject.parseObject(jsonStr).toJSONString();
            return source;
        } catch (Exception e) {
            throw ServiceException.newInstance(ErrorCodes.JSON_PARSE_ERROR);
        }

    }

    private GoogleTrends getGoogleTrends(String keyWord) {
        GoogleTrends googleTrends = new GoogleTrends();
        Map<String, String> tokenMap = getToken(keyWord);
        for (Map.Entry<String, String> entry : tokenMap.entrySet()) {
            if (TIMESERIES.equals(entry.getKey())) {
                String url = GOOGLE_URL + WIDGETDATA_MULTILINE + "?";
                String token = entry.getValue();
                String sourceJson = getSourceJson(url, keyWord, 2, token);

                googleTrends.setTimeSeries(decodeJson(sourceJson));
            } else if (GEO_MAP.equals(entry.getKey())) {
                String url = GOOGLE_URL + WIDGETDATA_COMPAREDGEO + "?";
                String token = entry.getValue();
                String sourceJson = getSourceJson(url, keyWord, 3, token);

                googleTrends.setGeoMap(decodeJson(sourceJson));
            } else if (RELATED_TOPICS.equals(entry.getKey())) {
                String url = GOOGLE_URL + WIDGETDATA_RELATEDSEARCHES + "?";
                String token = entry.getValue();
                String sourceJson = getSourceJson(url, keyWord, 4, token);

                googleTrends.setRelatedTopics(decodeJson(sourceJson));
            } else if (RELATED_QUERIES.equals(entry.getKey())) {
                String url = GOOGLE_URL + WIDGETDATA_RELATEDSEARCHES + "?";
                String token = entry.getValue();
                String sourceJson = getSourceJson(url, keyWord, 5, token);

                googleTrends.setRelatedQueries(decodeJson(sourceJson));
            }
        }

        return googleTrends;
    }

    private String getSourceJson(String url, String keyWord, Integer type, String token) {
        //String url = GOOGLE_URL + EXPLORE + "?";
        System.out.println("url : " + url);
        logger.debug("url : " + url);
        HttpClient httpClient = getHttpClient(PROXY_HOST_NAME, PROXY_PORT, true);
        //1-获取token, 2-TIMESERIES ,3-GEO_MAP ,4-RELATED_TOPICS ,5-RELATED_QUERIES
        List<NameValuePair> nameValueList = getNameValueList(keyWord, type, token);
        URI uri = getUri(url, nameValueList);
        HttpGet httpGet = getHttpGet(uri);
        try {
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
            System.out.println("响应状态 : " + response.getStatusLine());
            if (200 != response.getStatusLine().getStatusCode()) {
                throw ServiceException.newInstance(ErrorCodes.COLLECT_SOURCE_UNAVAILABLE);
            }
            String source = null;
            try {
                source = inputStreamTOString(response.getEntity().getContent(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String jsonStr = source.substring(5).trim();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> getToken(String keyWord) {
        Map<String, String> map = Maps.newHashMap();
        String url = GOOGLE_URL + EXPLORE + "?";
        System.out.println("url : " + url);
        logger.debug("url : " + url);
        HttpClient httpClient = getHttpClient(PROXY_HOST_NAME, PROXY_PORT, true);
        //1-获取token, 2-TIMESERIES ,3-GEO_MAP ,4-RELATED_TOPICS ,5-RELATED_QUERIES
        List<NameValuePair> nameValueList = getNameValueList(keyWord, 1, "");
        URI uri = getUri(url, nameValueList);
        HttpGet httpGet = getHttpGet(uri);
        try {
            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
            System.out.println("响应状态 : " + response.getStatusLine());
            if (200 != response.getStatusLine().getStatusCode()) {
                throw ServiceException.newInstance(ErrorCodes.COLLECT_SOURCE_UNAVAILABLE);
            }
            String source = null;
            try {
                source = inputStreamTOString(response.getEntity().getContent(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String jsonStr = source.substring(4).trim();
            JSONObject jsonObject;
            try {
                jsonObject = JSONObject.parseObject(jsonStr);
                JSONArray widgetsArray = jsonObject.getJSONArray("widgets");
                for (int i = 0; i < widgetsArray.size(); i++) {
                    JSONObject json = (JSONObject) widgetsArray.get(i);
                    String id = json.getString("id");
                    String token = json.getString("token");
                    System.out.println("id :" + id);
                    System.out.println("token : " + token);
                    map.put(id, token);
                }
            } catch (Exception e) {
                throw ServiceException.newInstance(ErrorCodes.JSON_PARSE_ERROR);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    private List<NameValuePair> getNameValueList(String keyWord, Integer type, String token) {
        List<NameValuePair> list = Lists.newArrayList();
        list.add(new BasicNameValuePair("hl", "zh-CN"));
        list.add(new BasicNameValuePair("tz", "-480"));
        if (type == 1) {
            list.add(new BasicNameValuePair("req", "{\"comparisonItem\":[{\"keyword\":\"" +
                    keyWord +
                    "\",\"geo\":\"US\",\"time\":\"today 12-m\"}],\"category\":0,\"property\":\"\"}"));
            list.add(new BasicNameValuePair("tz", "-480"));
        } else if (type == 2) {
            list.add(new BasicNameValuePair("req", "{\"time\":\"2018-03-25 2019-03-25\",\"resolution\":\"WEEK\"," +
                    "\"locale\":\"zh-CN\",\"comparisonItem\":[{\"geo\":{\"country\":\"US\"}," +
                    "\"complexKeywordsRestriction\":{\"keyword\":[{\"type\":\"BROAD\",\"value\":\"" +
                    keyWord +
                    "\"}]}}]," +
                    "\"requestOptions\":{\"property\":\"\",\"backend\":\"IZG\",\"category\":0}}"));

            list.add(new BasicNameValuePair("token", token));
            list.add(new BasicNameValuePair("tz", "-480"));
        } else if (type == 3) {
            list.add(new BasicNameValuePair("req", "{\"geo\":{\"country\":\"US\"}," +
                    "\"comparisonItem\":[{\"time\":\"2018-03-25 2019-03-25\"," +
                    "\"complexKeywordsRestriction\":{\"keyword\":[{\"type\":\"BROAD\",\"value\":\"" +
                    keyWord +
                    "\"}]}}],\"resolution\":\"REGION\",\"locale\":\"zh-CN\",\"requestOptions\":{\"property\":\"\"," +
                    "\"backend\":\"IZG\",\"category\":0}}"));

            list.add(new BasicNameValuePair("token", token));
        } else if (type == 4) {
            list.add(new BasicNameValuePair("req", "{\"restriction\":{\"geo\":{\"country\":\"US\"}," +
                    "\"time\":\"2018-03-25 2019-03-25\",\"originalTimeRangeForExploreUrl\":\"today 12-m\"," +
                    "\"complexKeywordsRestriction\":{\"keyword\":[{\"type\":\"BROAD\",\"value\":\"" +
                    keyWord +
                    "\"}]}},\"keywordType\":\"ENTITY\",\"metric\":[\"TOP\",\"RISING\"]," +
                    "\"trendinessSettings\":{\"compareTime\":\"2017-03-24 2018-03-24\"}," +
                    "\"requestOptions\":{\"property\":\"\",\"backend\":\"IZG\",\"category\":0},\"language\":\"zh\"}"));

            list.add(new BasicNameValuePair("token", token));
        } else if (type == 5) {
            list.add(new BasicNameValuePair("req", "{\"restriction\":{\"geo\":{\"country\":\"US\"}," +
                    "\"time\":\"2018-03-25 2019-03-25\",\"originalTimeRangeForExploreUrl\":\"today 12-m\"," +
                    "\"complexKeywordsRestriction\":{\"keyword\":[{\"type\":\"BROAD\",\"value\":\"" +
                    keyWord +
                    "\"}]}},\"keywordType\":\"QUERY\",\"metric\":[\"TOP\",\"RISING\"]," +
                    "\"trendinessSettings\":{\"compareTime\":\"2017-03-24 2018-03-24\"}," +
                    "\"requestOptions\":{\"property\":\"\",\"backend\":\"IZG\",\"category\":0},\"language\":\"zh\"}"));

            list.add(new BasicNameValuePair("token", token));
        }

        return list;
    }

    private HttpClient getHttpClient(String proxyHostName, int proxyPort, boolean useProxy) {
//        HttpHost proxy = new HttpHost("127.0.0.1",1080);
        RequestConfig globalConfig;
        if (useProxy) {
            HttpHost proxy = new HttpHost(proxyHostName, proxyPort);
            globalConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .setConnectionRequestTimeout(6000)
                    .setConnectTimeout(6000)
                    .build();
        } else {
            globalConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .setConnectionRequestTimeout(6000)
                    .setConnectTimeout(6000)
                    .build();
        }
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        return httpClient;
    }

    private URI getUri(String url, List<NameValuePair> valuePairList) {
        URI uri;
        try {
            uri = new URIBuilder(url).addParameters(valuePairList).build();
            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;

    }

    private HttpGet getHttpGet(URI uri) {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        httpGet.addHeader("Cookie", "__utma=10102256.612709185.1553483021.1553483021.1553483021.1; " +
                "__utmc=10102256; __utmz=10102256.1553483021.1.1.utmcsr=google|utmccn=(organic)" +
                "|utmcmd=organic|utmctr=(not%20provided); __utmt=1; __utmb=10102256.11.9.1553483121932; NID=179=nXUyeNd75l1-y4vCU-eUxjJV3Qpr1b5cpHRvIlMEkzrSyDHNykmpvgevHlKkubPhJRTtKDbdYhNm70VG3QG9FIxs4ohxCOnXtBDTS7Ems9o0xAjAZWy7iyIyy8jnKJ-cSkGd4TzDQBcwczT8GGL7qzMPiNA6VyBPEucrV00bSWM; ANID=AHWqTUnAwLDpTQRJwYj5HcTxeT1DQb-BgaHnFef7z0fvdZcSc5WGs9hj-MTTIwuk; 1P_JAR=2019-3-25-3");

        //httpGet.addHeader("Cookie",cookieValue);
        return httpGet;
    }

    @Override
    public GoogleTrends collectToMongo(String keyWord) {

        return googleTrendsRepository.insert(getGoogleTrends(keyWord));
    }

    private final static int BUFFER_SIZE = 4096 * 4;

    private String inputStreamTOString(InputStream in, String encoding) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), encoding);
    }
}
