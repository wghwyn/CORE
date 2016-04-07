package com.wgh.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  进入和退出交易时记录日志
 */
@Aspect
@Component
public class TradeLog {
    private final static Logger log = LoggerFactory.getLogger(TradeLog.class);

    @Pointcut("execution(public void com.wgh.core.trade.BaseTrade.run())")
    public void trade(){}

    @Before("trade()")
    public void before(){
        log.info("进入交易");
    }

    @After("trade()")
    public void after(){
        log.info("退出交易");
    }


    @Around("trade()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("开始交易");
        Object object = joinPoint.proceed();
        log.info("结束交易");
        return object;
    }
}
