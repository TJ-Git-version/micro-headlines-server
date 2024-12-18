package com.surfer.service.util;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/18 22:37
 * description JwtHelper工具类：生成token
 */
public class JwtHelper {

    // 设置token有效时间
    public static final Integer tokenExpiration = 60 * 60 * 24 * 24;
    // 声明token签名
    private static final String tokenSignKey = "3n7%Z!sP@d^F#yQ1wR&L8u$Xb*H9jG5aK0MzT@WqVx";

    /**
     * 创建token
     *
     * @param uid 用户id
     * @return token
     */
    public static String createToken(Integer uid) {
        return Jwts.builder()
                .setSubject("micro-headlines-server")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("uid", uid)
                .signWith(SignatureAlgorithm.HS256, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 获取用户id
     *
     * @param token 需要解析的token
     * @return 返回uid
     */
    public static Integer getUserId(String token) {
        if (StringUtils.isEmpty(token)) return null;
        return Jwts.parser()
                .setSigningKey(tokenSignKey)
                .parseClaimsJwt(token)
                .getBody()
                .get("uid", Integer.class);
    }

    /**
     * 校验token是否有效
     * @param token 校验的token
     * @return true:无效, false:有效
     */
    public static boolean checkTokenExpiration(String token) {
        if (StringUtils.isEmpty(token)) return false;
        try {
            return Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJwt(token)
                    .getBody()
                    .getExpiration().before(new Date());
        } catch (Exception e) {
            //过期出现异常，返回true
            e.printStackTrace();
            return true;
        }
    }


}
