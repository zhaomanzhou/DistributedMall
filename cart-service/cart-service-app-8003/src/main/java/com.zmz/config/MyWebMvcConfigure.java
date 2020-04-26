package com.zmz.config;

import com.zmz.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebMvcConfigure implements WebMvcConfigurer
{
    @Resource
    private LoginInterceptor loginInterceptor;

//    @Value("${project.imageslocation}")
//    private String imagesLocation;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry)
//    {
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations(imagesLocation);
//    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/unlogin");

    }
}
