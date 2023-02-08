package com.aqara.common.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.aqara.common.interceptor.MyInterceptor;
@Configuration
public class MyConfigurer implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor MyInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(MyInterceptor);
    }
}
