package com.aladen.service.test.impl;

import com.aladen.service.base.BaseService;
import com.aladen.service.test.TestRollBackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: TestRollBackServiceImpl
 * @Description: TODO
 * @Author xu
 * @Date 2018/9/12 10:16
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Service
public class TestRollBackServiceImpl extends BaseService implements TestRollBackService {


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int saveUser(String userName) {
        int result = 0;
        try {
            result = save(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int save(String userName) {
        return 0;
    }
}
