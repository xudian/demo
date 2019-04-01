package com.aladen.common.annotation;

import java.lang.annotation.*;

/**
 * @Title: CheckParams
 * @Description:
 * @Author xu
 * @Date 2019/4/1 10:32
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD })
@Documented
@Inherited
public @interface CheckParams {

    String[] value();
}
