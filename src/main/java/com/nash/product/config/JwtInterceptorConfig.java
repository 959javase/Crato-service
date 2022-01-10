package com.nash.product.config;

import com.nash.product.filter.JwtAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description：安全认证配置
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/19 9:17 下午
 */
@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {
    /**
     * 注册JWT拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //默认拦截所有路径
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/error");
    }

    /**
     * JWT拦截器实例化
     * @return
     */
    @Bean
    public JwtAuthenticationInterceptor authenticationInterceptor() {
        return new JwtAuthenticationInterceptor();
    }
}
