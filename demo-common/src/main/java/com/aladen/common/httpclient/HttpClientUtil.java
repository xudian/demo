package com.aladen.common.httpclient;

import com.aladen.common.constants.HttpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Title: HttpClientUtil
 * @Description:
 * @Author xu
 * @Date 2018/8/23 17:40
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * @Description: 调用接口,传入的参数不是以键值对的方式获取
     * @params: [url, content]
     * @return: java.lang.String
     * @date: 2018/8/23 18:22
     */
    public static String httpClientPost(String url, String content){
        logger.info("调用接口start url：{} content:{}",url,content);
        String result = "";
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Connection","close");
        HttpEntity<String> requestEntity = new HttpEntity<>(content,headers);
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity,String.class);
            logger.info("调用接口:{};传入参数:{},返回状态码:{}",url,content,responseEntity.getStatusCodeValue());
            if (responseEntity.getStatusCodeValue() == org.apache.http.HttpStatus.SC_OK){
                result = responseEntity.getBody();
            }
        } catch (Exception e) {
            logger.info("调用接口异常;e:{}",e);
        }
        logger.info("调用接口end;url:{},content:{},result:{}",url,content,result);
        return result;
    }

    /**
     * @Description: 调用接口,传入的参数以key-value的格式传入
     * @params: [url, paramsMap]
     * @return: java.lang.String
     * @date: 2019/2/21 11:26
     */
    public static String httpClientPost(String url, Map<String,Object> paramsMap){
        logger.info("调用接口start url：{} content:{}",url,paramsMap);
        String result = "";
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        if (!paramsMap.isEmpty()) {
            for (Map.Entry<String,Object> entry : paramsMap.entrySet()) {
                map.add(entry.getKey(),String.valueOf(entry.getValue()));
            }
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class);
            logger.info("调用接口:{};传入参数:{},返回状态码:{}",url,map,response.getStatusCodeValue());
            if (response.getStatusCodeValue() == org.apache.http.HttpStatus.SC_OK){
                result = response.getBody();
            }
        } catch (Exception e) {
            logger.info("调用接口异常;e:{}",e);
        }
        logger.info("调用接口end;url:{},content:{},result:{}",url,paramsMap,result);
        return result;
    }

    /**
     * @Description: get提交数据，可以以键值对的方式获取信息
     * @params: [url, content]
     * @return: java.lang.String
     * @date: 2018/8/30 14:40
     */
    public static String httpGet(String url, String content){
        logger.info("调用接口start url：{} content:{}",url,content);
        String result = "";
        RestTemplate restTemplate = getRestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "?" + content, String.class);
            if (responseEntity.getStatusCodeValue() == org.apache.http.HttpStatus.SC_OK) {
                result = responseEntity.getBody();
            }
        } catch (Exception e) {
            logger.info("调用接口异常;e:{}",e);
        }
        logger.info("调用接口end;url:{},content:{},result:{}",url,content,result);
        return result;
    }



    private static RestTemplate getRestTemplate(){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        // 设置超时时间，请求时间、连接时间、读取时间
        requestFactory.setConnectionRequestTimeout(HttpConstants.I_1000);
        requestFactory.setConnectTimeout(HttpConstants.I_5000);
        requestFactory.setReadTimeout(HttpConstants.I_5000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
