package com.aladen.service.test;

/**
 * @Title: TestTransactionService
 * @Description: TODO
 * @Author xu
 * @Date 2018/9/9 11:29
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public interface TestTransactionService {

    int testTrans(String userName, long roleId);

    int listUser(String[] userNames);

}
