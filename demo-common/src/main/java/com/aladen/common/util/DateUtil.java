package com.aladen.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Title: DateUtil
 * @Description:
 * @Author xu
 * @Date 2019/4/4 15:25
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
public class DateUtil {

    public static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /** 标准日期格式 */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /** 标准时间格式 */
    public final static String NORM_TIME_PATTERN = "HH:mm:ss";
    /** 标准日期时间格式 */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**  查询日期格式 */
    public final static String QUERY_DATE_PARTENT = "yyyyMMdd";


    public static String format(LocalDateTime date,String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public static String getNow() {
        return format(LocalDateTime.now(),NORM_DATE_PATTERN);
    }

    public static String getQueryNow() {
        return format(LocalDateTime.now(),QUERY_DATE_PARTENT);
    }

    public static LocalDateTime parse(String dateStr,String format) {
        return LocalDateTime.parse(dateStr,DateTimeFormatter.ofPattern(format));
    }
}
