package com.aladen.api;

import com.aladen.api.base.BaseApiService;
import com.aladen.base.BaseController;
import com.aladen.common.helper.SpringContextHolder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: ApiController
 * @Description:
 * @Author xu
 * @Date 2019/3/26 09:40
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@RestController
@RequestMapping("api")
public class ApiController extends BaseController {

    @RequestMapping(value ="{ifn}")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public JSONObject execute(@PathVariable String ifn, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        logger.info("==============调用接口:{} start============================",ifn);
        printParams(request);
        try {
            BaseApiService baseApiService = SpringContextHolder.getBean(ifn);
            result = baseApiService.execute(request);
        } catch (Exception e) {
            logger.error("调用接口异常;ifn:{};e:{}",ifn,e);
        }
        logger.info("调用接口:{},返回结果:{}",ifn,result);
        logger.info("==============调用接口:{} end============================",ifn);
        return result;
    }
}
