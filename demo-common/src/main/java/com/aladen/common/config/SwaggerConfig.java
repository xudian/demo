package com.aladen.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: SwaggerConfig
 * @Description: swagger 配置
 * @Author xu
 * @Date 2018/8/10 10:14
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */

@Configuration
@EnableSwagger2
@Profile(value = {"dev","test"})
public class SwaggerConfig {

    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-demo")
                .description("基础服务")
                .termsOfServiceUrl("http://www.google.com.hk")
                .contact(new Contact("程序猿","http://google.com.hk","505112396@qq.com"))
                .version("1.0.0")
                .build();
    }
}
