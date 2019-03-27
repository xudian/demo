package com.aladen.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Title: SignUtil
 * @Description:
 * @Author xu
 * @Date 2019/3/26 15:06
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    public static boolean checkSign (HttpServletRequest request,String sign,String privateKey) {
        TreeMap<String,Object> treeMap = new TreeMap<>();
        boolean result = false;
        Map<String,String[]> maps = request.getParameterMap();
        StringBuffer sb = new StringBuffer();
        if (!maps.isEmpty()) {
            for (String key : maps.keySet()) {
                if (StringUtils.equals(key,"sign")) {
                    continue;
                }
                String[] values = maps.get(key);
                String value = null;
                if (values == null || values.length == 0) {
                    continue;
                }
                for (int i= 0;i < values.length;i++) {
                    if (value == null) {
                        value = values[i];
                    } else {
                        value = value + "," + values[i];
                    }
                }
                treeMap.put(key,value);
            }
        }
        if (!treeMap.isEmpty()) {
            for (String key : treeMap.keySet()) {
                sb.append(treeMap.get(key));
            }
        }
        logger.info("签名前的数据:{}",sb);
        String signature = DigestUtils.md5Hex(sb.append(privateKey).toString());
        logger.info("签名后的数据:{},sign:{}",signature,sign);
        if (StringUtils.equals(signature,sign)) {
            result = true;
        }
        return result;
    }
}
