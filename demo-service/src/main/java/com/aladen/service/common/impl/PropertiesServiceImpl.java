package com.aladen.service.common.impl;

import com.aladen.service.common.PropertiesService;
import com.aladen.service.sys.ISysFieldInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: PropertiesServiceImpl
 * @Description:
 * @Author xu
 * @Date 2019/3/29 09:56
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Autowired
    private ISysFieldInfoService fieldInfoService;

    @Override
    public String getProperty(String key) {
        Map<String,String> map = fieldInfoService.getPropertys();
        if (map.isEmpty()) {
            return null;
        } else {
            return map.get(key);
        }
    }
}
