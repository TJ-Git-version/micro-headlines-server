package com.surfer.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surfer.service.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/18 23:19
 * description WEBUtil工具类:请求和响应JSON转换工具
 */
public class WebUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 设置json和object转换时的时间日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 从请求中获取json串转为object
    public static <T> T readJSON(HttpServletRequest request, Class<T> clazz) {
        if (request == null) return null;
        try {
            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return objectMapper.readValue(buffer.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException("[readJSON] 解析请求体json为对象异常");
        }
    }

    // 将object转为json串并放到请求体中
    public static void writeJSON(HttpServletResponse response, Result<?> result) {
        if (result == null) return;
        try {
            String resultJson = objectMapper.writeValueAsString(response);
            response.getWriter().write(resultJson);
        } catch (Exception e) {
            throw new RuntimeException("[writeJSON] 实体类转为响应体json异常");
        }
    }
}
