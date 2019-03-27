package com.aladen.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ApiProperties
 * @Description:
 * @Author xu
 * @Date 2019/3/26 11:35
 * @Version V1.0
 * @Copyright 2019 All Rights Reserved
 */
@Configuration
@ConfigurationProperties(prefix = ApiProperties.PREFIX)
public class ApiProperties {

    public static final String PREFIX = "api";

    private boolean tokenOpen = true;
    private boolean signOpen = true;


    public void setSignOpen(boolean signOpen) {
        this.signOpen = signOpen;
    }

    public boolean isTokenOpen() {
        return tokenOpen;
    }

    public void setTokenOpen(boolean tokenOpen) {
        this.tokenOpen = tokenOpen;
    }

    public boolean isSignOpen() {
        return signOpen;
    }
}
