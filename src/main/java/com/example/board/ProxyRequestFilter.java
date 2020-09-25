package com.example.board;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class ProxyRequestFilter extends OncePerRequestFilter {
    private final String requestIdHeaderName;
    // logback-spring.xml에서 http::x-request-id로 가지고 올 수 있다.
    public final String MDC_REQUEST_ID = "http::x-request-id";
    private final String MDC_METHOD = "MDCMethod";
    public ProxyRequestFilter(String requestIdHeaderName) {
        this.requestIdHeaderName = requestIdHeaderName;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String requestId = request.getHeader(requestIdHeaderName);
            if (StringUtils.isEmpty(requestId)) {
                requestId = Base64.encodeBase64String(UUID.randomUUID().toString().getBytes()).concat(".G");
                log.info("RequestId is Empty. generate new RequestId({})", requestId);
            }
            log.info("Put requestId(MDC) : {}", requestId);
            MDC.put(MDC_REQUEST_ID, requestId);
            MDC.put("hoho","HOHO");
            MDC.put(MDC_METHOD,request.getMethod());
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_REQUEST_ID);
            MDC.remove(MDC_METHOD);
        }

    }
}

