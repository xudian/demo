package com.aladen.common.annotation;

import java.lang.annotation.*;

/**
 * @Title: Stage
 * @Description:
 * @Author xu
 * @Date 2019/3/19 10:22
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD,ElementType.TYPE})
public @interface Stage {
    int stageSource();
}
