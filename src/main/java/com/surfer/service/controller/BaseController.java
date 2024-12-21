package com.surfer.service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/21 13:12
 * description 控制层超类，封装控制层方法，根据路径动态调用方法
 */
public class BaseController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 统一标识符路径
        String requestURI = req.getRequestURI();
        logger.info("请求路径: {}", requestURI);
        // 分割URI路径为String数组
        String[] pathURI = requestURI.split("/");
        // 获取执行的方法名
        String methodName = pathURI[pathURI.length - 1];
        logger.info("执行控制层的方法: {}", methodName);
        // 通过反射获取执行的方法
        Class<? extends BaseController> clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }
}
