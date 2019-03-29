package com.aladen.service;

import com.aladen.DemoApplicationTests;
import com.aladen.service.common.PropertiesService;
import com.aladen.service.sys.ISysFieldInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Title: FieldInfoServiceTest
 * @Description:
 * @Author xu
 * @Date 2019/3/28 16:13
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class FieldInfoServiceTest extends DemoApplicationTests {

    @Autowired
    private ISysFieldInfoService service;

    @Autowired
    private PropertiesService propertiesService;

    @Test
    public void testCache() {
        /*logger.info("----------------第一次查询，没有缓存");
        Map<String,String> map = service.getPropertys();
        logger.info("----------map:{}",map);
        logger.info("----------------第二次查询，有缓存");
        Map<String,String> map1 = service.getPropertys();
        logger.info("----------map:{}",map);*/
        logger.info("----------------引用缓存查询");
        String value = propertiesService.getProperty("order.status");
        logger.info("----------------value:{}",value);
        logger.info("----------------再次引用缓存查询");
        String value1 = propertiesService.getProperty("order.status");
        logger.info("----------------value1:{}",value1);
    }
}
