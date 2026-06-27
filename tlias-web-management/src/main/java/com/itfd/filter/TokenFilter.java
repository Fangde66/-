package com.itfd.filter;

import com.itfd.utils.CurrentHolder;
import com.itfd.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class TokenFilter implements Filter {
    public static final Logger log = LoggerFactory.getLogger(TokenFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1. 获取请求路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath(); // 去掉项目名、去掉参数之后，真正匹配到你后端代码的那段路径

        // 2. 判断请求路径是否为登录请求（/login）
        // 若是登录请求，则放行
        if(path.contains("/login") || path.contains("/doc.html")
                || path.contains("/webjars")
                || path.contains("/swagger-resources")
                || path.contains("/v2/api-docs")
                || path.contains("/v3/api-docs")){
            log.info("登录请求：{}", path);
            filterChain.doFilter(request, response);
            return;
        }

        // 3.获取请求头中的token（接口文档中规定，每次请求请求头中携带token）
        String token = request.getHeader("token");

        // 4. 判断token不为空和null
        if(token == null || "".equals(token)){
            log.info("请求头中token为空");
            response.setStatus(401); // 设置响应状态码为未授权
            return;
        }

        // 5.校验token是否正确
        try {
            Claims claims = JwtUtils.parseJwt(token);
            Integer empId = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(empId); // 存入当前登录用户ID
            log.info("当前登录用户ID：{}，存入到当前TreadLocal中", empId);
        }catch (Exception e){
            log.info("token解析错误");
            response.setStatus(401);
            return;
        }

        // 6. 校验通过，放行
        log.info("token校验通过");
        filterChain.doFilter(request, response);

        // 7.删除threadlocal中的数据
        CurrentHolder.remove();
    }
}
