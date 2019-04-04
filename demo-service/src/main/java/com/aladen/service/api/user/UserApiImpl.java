package com.aladen.service.api.user;

import com.aladen.entity.user.SysUserInfo;
import com.aladen.service.api.base.BaseApiService;
import com.aladen.service.user.ISysUserInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Title: UserApiImpl
 * @Description:
 * @Author xu
 * @Date 2019/3/26 11:04
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service("userRegister")
public class UserApiImpl extends BaseApiService {

    @Autowired
    private ISysUserInfoService userInfoService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public JSONObject execute(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setUserName("test032701");
        userInfo.setTrueName("测试事物1232");
        userInfo.setPassword("123456");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setUserStatus("0");
        boolean saveFlag = userInfoService.save(userInfo);
        logger.info("保存数据成功;saveFlag:{}",saveFlag);

        mongoTemplate.save(userInfo,"sysUserInfo");
        Query query = new Query(Criteria.where("userName").is(userInfo.getUserName()));

        List<SysUserInfo> list = mongoTemplate.find(query,SysUserInfo.class);
        if (!list.isEmpty()) {
            for (SysUserInfo user : list) {
                logger.info("---------------userId:{},date:{}",user.getUserId(), user.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }
        return result;
    }
}
