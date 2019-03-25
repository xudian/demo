package com.aladen.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Generator
 * @Description:
 * @Author xu
 * @Date 2019/3/25 17:27
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class Generator {

    public void generatorCode(String baseDir,
                              String jdbcUrl,
                              String jdbcDriver,
                              String username,
                              String passwrod,
                              String packageName,
                              String[] tableNames) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(baseDir + "/src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName(jdbcDriver);
        dsc.setUrl(jdbcUrl);
        dsc.setUsername(username);
        dsc.setPassword(passwrod);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        mpg.setStrategy(strategy);
        strategy.setInclude(tableNames);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.aladen");
        pc.setEntity("entity."+ packageName );
        pc.setService("service." + packageName);
        pc.setServiceImpl("service." + packageName + ".impl");
        pc.setMapper("mapper." + packageName);
        mpg.setPackageInfo(pc);

        /**
         * 注入自定义配置
         */
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig abc = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDir + "/src/main/resources/mapper/" + packageName + "/" + tableInfo.getEntityName() + ".xml";
            }
        });
        abc.setFileOutConfigList(fileOutList);
        mpg.setCfg(abc);

        /**
         * 模板配置
         */
        mpg.setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null).setController(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );

        // 执行生成
        mpg.execute();
    }
}
