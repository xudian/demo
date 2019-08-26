package com.aladen.common.constants;

import com.aladen.common.util.EnvironmentUtil;

public class Constant {

    public static final String KAFKA_SERVER = EnvironmentUtil.getProperty("kafka.broker-server");

    public static final String BATCH_POLL_SIZE = "20";
}
