package com.lin.config.resttemp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author lin
 * @version 1.0
 * @date 2020/4/13 21:26
 */
@Slf4j
public class TrackLogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    public TrackLogClientHttpRequestInterceptor(){}

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        this.logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        this.logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException{
        if(log.isTraceEnabled()){
            log.trace("=================================Request Begin=================================");
            log.trace("URI:          {}", request.getURI());
            log.trace("Method:       {}", request.getMethod());
            log.trace("Headers:      {}", request.getHeaders());
            log.trace("Request Body: {}", new String(body, "UTF-8"));
            log.trace("=================================Request End=================================");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException{
        if(log.isTraceEnabled()){
            String responseBody = getStringFromInputStrem(response.getBody());
            log.trace("=================================Response Begin=================================");
            log.trace("Status code:   {}", response.getStatusCode());
            log.trace("Headers:       {}", response.getHeaders());
            log.trace("Response Body: {}", responseBody.toString());
            log.trace("=================================Response Begin=================================");
        }
    }

    private String getStringFromInputStrem(InputStream body) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(body));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException var){
            var.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException var1){
                    var1.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
