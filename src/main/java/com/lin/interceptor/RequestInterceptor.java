package com.lin.interceptor;

import com.lin.base.AjaxResult;
import com.lin.exception.BaseException;
import com.lin.util.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/1 22:36
 */
@Slf4j
@Aspect
@Component
public class RequestInterceptor {

    @Before("execution(public * com.lin.controller.*.*(..))")
    public void doAround(JoinPoint joinPoint){
        log.info("Method singature {} args {}", joinPoint.getSignature(), joinPoint.getArgs());
        Object[] args = joinPoint.getArgs();
        for(Object o : args){
            if(o == null){
                log.error("----------parameter not be null");
                throw new BaseException("parameter not be null");
            }
            AjaxResult validate = ValidatorUtils.validate(o);
            if(validate != null){
                throw new BaseException(validate.get(AjaxResult.CODE_TAG).toString()
                        , validate.get(AjaxResult.MSG_TAG).toString());
            }
        }
    }

}
