package com.lin.aspectj;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author lin
 * @version 1.0
 * @date 2020/3/30 22:40
 */
@Aspect
@Component
@Slf4j
public class LoggerConfiguration {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "||@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object interception(ProceedingJoinPoint joinPoint) throws Throwable {

        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 设置请求开始时间
        Long startTime = System.currentTimeMillis();
        // 提取全部参数 paramJson
        Enumeration<String> paramNames = request.getParameterNames();
        JSONObject paramJson = new JSONObject();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            paramJson.put(paramName, request.getParameter(paramName));
        }
        //controller中接收参数的实体类
        StringBuilder param = new StringBuilder();
        if (joinPoint.getArgs() != null) {
            Arrays.asList(joinPoint.getArgs()).forEach(obj -> param.append(",").append(obj));
        }
        //相应参数类和抛出异常处理
        Object result = null;
        Throwable exception = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            exception = throwable;
            throw throwable;
        } finally {
            log.info(request.getMethod() + " "
                    + request.getRequestURL() + " 参数：" + paramJson.toJSONString() + param + " ," + (System.currentTimeMillis() - startTime) + "ms," + " 响应结果：" + result, exception);
        }
        return result;
    }
}
