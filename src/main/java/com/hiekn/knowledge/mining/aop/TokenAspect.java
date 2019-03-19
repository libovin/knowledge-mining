package com.hiekn.knowledge.mining.aop;

import com.hiekn.knowledge.mining.service.TokenCountService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class TokenAspect {

    @Pointcut("execution(* com.hiekn.knowledge.mining.rest.TaskRestApi.remote(..))")
    public void recordToken(){}


    @Autowired
    private TokenCountService tokenCountService;


    @Around("recordToken()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {

        Object o = null;
        try {
            o = joinPoint.proceed();
            Object[] args = joinPoint.getArgs();
            System.out.println("args size : " + args.length);
            if (args != null && args.length == 3){
                String serverId = (String) args[0];
                String token = (String) args[2];
                //验证token是否过期
                tokenCountService.recordToken(serverId,token);
            }

            Arrays.stream(args).forEach(System.out::println);
            System.out.println(o.toString());
        }catch (Throwable e){
            throw e;
        }

        return o;
    }
}
