package com.aladen.service.proxy.impl;

import com.aladen.service.base.BaseService;
import com.aladen.service.proxy.SealPhone;

/**
 * @Title: SealIphone
 * @Description:
 * @Author xu
 * @Date 2019/3/19 15:57
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class SealIphone extends BaseService implements SealPhone {


    @Override
    public void seal() {
        logger.info("seal iphoneXSMax");
    }
}
