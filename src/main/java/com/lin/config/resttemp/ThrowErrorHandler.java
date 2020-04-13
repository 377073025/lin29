package com.lin.config.resttemp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/13 21:52
 */
@Slf4j
public class ThrowErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        if(statusCode.is3xxRedirection()){
            return true;
        }
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // 这里面可以实现你自己遇到了Error进行合理的处理
        HttpStatus statusCode = response.getStatusCode();
        if(statusCode.is3xxRedirection()){
            log.info("########30X错误，需要重定向！##########");
            return;
        }
        super.handleError(response);
    }

}
