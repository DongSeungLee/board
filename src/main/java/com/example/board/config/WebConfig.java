package com.example.board.config;

import com.example.board.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;
    // HandlerMethodArgumentResolver를 추가한다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> agrumentResolverList){
        agrumentResolverList.add(loginUserArgumentResolver);
    }
}
