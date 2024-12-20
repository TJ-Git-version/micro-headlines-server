package com.surfer.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surfer.service.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger log = LoggerFactory.getLogger(WebUtil.class);

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 设置json和object转换时的时间日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 从请求中获取json串转为object
    public static <T> T readJSON(HttpServletRequest request, Class<T> clazz) {
        log.info("转换对象: {}", clazz.getName());
        if (request == null) return null;
        try {
            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String line = null;
            log.info("读取的行内容：");
            while ((line = reader.readLine()) != null) {
                log.info(line);
                buffer.append(line);
            }
            log.info("转换完成，对象内容：{}", buffer);
            return objectMapper.readValue(buffer.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException("[readJSON] 解析请求体json为对象异常");
        }
    }

    // 将object转为json串并放到请求体中
    public static void writeJSON(HttpServletResponse response, Result<?> result) {
        log.info("响应对象：{}", result);
        if (result == null) return;
        try {
            log.info("响应，开始转换");
            String resultJson = objectMapper.writeValueAsString(result);
            response.getWriter().write(resultJson);
            log.info("响应，转换完成：{}", resultJson);
        } catch (Exception e) {
            throw new RuntimeException("[writeJSON] 实体类转为响应体json异常");
        }
    }
}
