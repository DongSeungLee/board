package com.example.board;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private Interceptor interceptor;

    public WebMvcConfig(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")

                .excludePathPatterns("/test/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hihi").setViewName("forward:/Favorite/bookmark");
    }

    // log에 http.x-request-id를 찍고 싶은데 어떻게 하는 지 아직 모르겠다......
    // 근데 쉽게 함 ^_^
    @Bean
    public ProxyRequestFilter requestIdFilter(@Value(value = "${proxy.server.request-id-header-name}") String requestIdHeaderName) {
        ProxyRequestFilter orderedProxyRequestIdFilter = new ProxyRequestFilter(requestIdHeaderName);

        return orderedProxyRequestIdFilter;
    }

}

