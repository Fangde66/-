package com.itfd.interceptor;

import com.itfd.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    public static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 3.获取请求头中的token（接口文档中规定，每次请求请求头中携带token）
        String token = request.getHeader("token");

        // 4.判断token不为空和null
        if(token == null || "".equals(token)){
            log.info("请求头中token为空");
            response.setStatus(401);
            return false;
        }

        // 5.校验token是否正确
        try {
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("token解析错误");
            response.setStatus(401);
            return false;
        }

        // 6. 校验通过，放行
        log.info("token校验通过");
        return true;
    }
}
