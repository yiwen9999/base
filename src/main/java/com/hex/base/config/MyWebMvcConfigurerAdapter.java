package com.hex.base.config;

import com.hex.base.aspect.MyInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器
 * User: hexuan
 * Date: 2017/8/15
 * Time: 下午1:25
 */
//@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/index", "/toLogin", "/login", "/welcome", "/register", "/test/*", "/mobile_*", "/saveDemand", "/findEngineerAndProjectNum");
        super.addInterceptors(registry);
    }
}
