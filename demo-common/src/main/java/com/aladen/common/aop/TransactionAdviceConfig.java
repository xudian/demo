package com.aladen.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @Title: TransactionAdviceConfig
 * @Description: 事物控制
 * @Author xu
 * @Date 2018/8/9 下午3:36
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */

@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.aladen.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice(){
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        DefaultTransactionAttribute txAttr_REQUIRED_READ = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READ.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READ.setReadOnly(true);
        DefaultTransactionAttribute txAttr_REQUIRED_NEW = new DefaultTransactionAttribute();
        txAttr_REQUIRED_NEW.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("get*",txAttr_REQUIRED_READ);
        source.addTransactionalMethod("find*",txAttr_REQUIRED_READ);
        source.addTransactionalMethod("list*",txAttr_REQUIRED_READ);
        source.addTransactionalMethod("query*",txAttr_REQUIRED_READ);
        source.addTransactionalMethod("*",txAttr_REQUIRED);
        return new TransactionInterceptor(transactionManager,source);
    }

    @Bean
    public Advisor txAdviceAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut,txAdvice());
    }

}
