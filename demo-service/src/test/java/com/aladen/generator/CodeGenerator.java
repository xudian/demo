package com.aladen.generator;

/**
 * @Title: CodeGenerator
 * @Description:
 * @Author xu
 * @Date 2019/3/25 16:06
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class CodeGenerator {

    public static void main(String[] args) {

        String baseDir = "/Users/xu/Project/demo/demo-service";

        String jdbcUrl = "jdbc:mysql://www.xujingya.xyz:3306/demo?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "root";
        String packageName = "sys";
        String[]  tableNames = {"sys_field_info"};

        new Generator().generatorCode(baseDir,jdbcUrl,driverName,username,password,packageName,tableNames);
    }
}
