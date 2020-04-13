package com.lin.config;

import com.lin.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfigurer
 * @Description TODO
 * @Author linjb2
 * @Data 2019/5/22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //增加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new Interceptor())    //指定拦截器类
                .addPathPatterns("/");        //指定url
    }

}
