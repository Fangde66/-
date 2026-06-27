package com.itfd.config;

import com.itfd.interceptor.TokenInterceptor;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMVC 配置类
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    public static final Logger log = LoggerFactory.getLogger(WebMVCConfig.class);

    @Autowired
    TokenInterceptor tokenInterceptor;

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/doc.html",
                        "/webjars/**",
                        "/swagger**/**",
                        "/v2/**",
                        "/v3/**",
                        "/configuration/**",
                        "/favicon.ico"
                );
    }

    /**
     * Knife4j 4.x 使用 SpringDoc OpenAPI 规范，通过 OpenAPI Bean 配置接口文档信息
     * 静态资源（/doc.html, /webjars/**）由 Knife4j 自动配置处理，无需手动映射
     */
    @Bean
    public OpenAPI openAPI() {
        log.info("准备生成接口文档");

        return new OpenAPI()
                .info(new Info()
                        .title("员工与部门管理接口文档")
                        .description("员工与部门管理接口文档")
                        .version("1.0"));
    }
}
