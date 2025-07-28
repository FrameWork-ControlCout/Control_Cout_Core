/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Config;

 
import com.FrameWork.ControlCout.web.errors.IllegalBusinessLogiqueException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import org.slf4j.*;
import org.springframework.http.*;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        ClientHttpResponse response = execution.execute(request, body);
//
//        log.debug("request method: {}, request URI: {},request Query: {}, request headers: {},  response status code: {}, response headers: {}",
//                request.getMethod(),
//                request.getURI(),
//                request.getURI().getRawQuery(),
//                request.getHeaders(),
//                response.getStatusCode(),
//                response.getHeaders()
//        );
//
//        if (response != null) {
//                String responseBody = traceResponseWhenError(response);
//                shouldThrowIllegalBusinessException(response, responseBody);
//            } else {
//                log.error("{}", e);
//            }
//        return response;
//    }
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        ClientHttpResponse response = null;
        try {

            if (RequestContextHolder.getRequestAttributes() != null) {
                String token = RequestContextHolder.getRequestAttributes().getSessionId();
                String lang = LocaleContextHolder.getLocale().getLanguage();
                if (request.getHeaders().get("Authorization") == null) {
                    request.getHeaders().add("Authorization", "Bearer " + token);
                }
                // request.getHeaders().set("x-auth-token", "989410f6-eb7b-45da-b75b-544d86f489f2");
                if (request.getHeaders().get("Accept-language") == null) {
                    request.getHeaders().add("Accept-language", lang);
                }

            }
            traceRequest(request, body);
            response = execution.execute(request, body);
            traceResponse(response);
            int statusCodeValue = response.getStatusCode().value();

            if (statusCodeValue != 200 && statusCodeValue != 201) {
            
                throw new Exception();
            }

            return response;
        } catch (Exception e) {
            log.error("-----------------------------Begin Communication Trace Error------------------------------------");
            traceRequestWhenError(request, body);

            if (response != null) {
                String responseBody = traceResponseWhenError(response);
                shouldThrowIllegalBusinessException(response, responseBody);
            } else {
                log.error("{}", e);
            }
            log.error("-----------------------------End Communication Trace Error------------------------------------");
        }
        System.out.println("xxxxxxxxxxxxxxxxxxx");
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("===========================request begin================================================");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", request.getHeaders());
        log.debug("Request body: {}", new String(body, "UTF-8"));
        log.debug("==========================request end================================================");
    }

    private void traceRequestWhenError(HttpRequest request, byte[] body) throws IOException {
        log.error("===========================request begin================================================");
        log.error("URI         : {}", request.getURI());
        log.error("Method      : {}", request.getMethod());
        log.error("Headers     : {}", request.getHeaders());
        log.error("Request body: {}", new String(body, "UTF-8"));
        log.error("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.debug("============================response begin==========================================");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text      : {}", response.getStatusText());
        log.debug("Headers     : {}", response.getHeaders());

        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.debug("Response body: {}", inputStringBuilder.toString());
        log.debug("=======================response end=================================================");

    }

    private String traceResponseWhenError(ClientHttpResponse response) throws IOException {
        log.error("============================response begin==========================================");
        log.error("Status code  : {}", response.getStatusCode());
        log.error("Status text      : {}", response.getStatusText());
        log.error("Headers     : {}", response.getHeaders());

        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.error("Response body: {}", inputStringBuilder.toString());
        log.error("=======================response end=================================================");
        return inputStringBuilder.toString();
    }

    private void shouldThrowIllegalBusinessException(ClientHttpResponse response, String responseBody) throws IOException {
           int statusCodeValue = response.getStatusCode().value();
        if (statusCodeValue == 409) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode error = mapper.readTree(responseBody);
            String description = error.get("description").asText();
            throw new IllegalBusinessLogiqueException(description);
        }
        if (statusCodeValue == 403) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode error = mapper.readTree(responseBody);
            String description = error.get("message").asText();
            throw new IllegalBusinessLogiqueException(description);
        }
        if (statusCodeValue == 401) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode error = mapper.readTree(responseBody);
            String description = error.get("message").asText();
            throw new IllegalBusinessLogiqueException(description);
        }

    }
}
