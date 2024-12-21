package com.surfer.service.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/21 13:37
 * description 服务端解决跨域请求,实现filter
 */
@WebFilter("/*")
public class CrosFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 允许的请求来源  *（允许所有来源）
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求方式
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        // 允许携带的请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 是否允许携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 预检请求的缓存时间（秒）
        response.setHeader("Access-Control-Max-Age", "3600");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
