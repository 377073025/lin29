package com.lin.config.resttemp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/13 21:18
 */
@Configuration
public class RestTempConfig {

    @Value("${resttemplate.connection.timeout: 60000}")
    private int restTemplateConnectionTimeout;
    @Value("${resttemplate.read.timeout: 5000}")
    private int restTemplateReadTimeout;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        //配置自定义的interceptor拦截器
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeadClientHttpRequestInterceptor());
        interceptors.add(new TrackLogClientHttpRequestInterceptor());
        restTemplate.getInterceptors().add(new TrackLogClientHttpRequestInterceptor());
        //配置自定义的异常处理
        restTemplate.setErrorHandler(new ThrowErrorHandler());
        return restTemplate;
    }


    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(restTemplateReadTimeout);
        factory.setConnectTimeout(restTemplateConnectionTimeout);
        //设置代理
        //factory.setProxy(null);
        return factory;
    }

}
