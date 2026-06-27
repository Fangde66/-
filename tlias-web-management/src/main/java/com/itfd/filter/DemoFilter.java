package com.itfd.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// @WebFilter(urlPatterns = "/*") // 拦截所有请求
public class DemoFilter implements Filter {
    public static final Logger log = LoggerFactory.getLogger(DemoFilter.class);

    // 初始化方法，web服务器启动时运行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        log.info("初始化...");
    }

    // 拦截到请求后执行，可执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到请求...");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 销毁方法，web服务器关闭时运行，只执行一次
    @Override
    public void destroy() {
//        Filter.super.destroy();
        log.info("销毁...");
    }
}
