package com.surfer.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/17 23:36
 * description TODO
 */
public class MD5Util {

    public static void main(String[] args) {
        System.out.println(encrypt("hello")); // 5d41402abc4b2a76b9719d911017c592
    }

    public static String encrypt(String input) {
        try {
            // 获取 MD5 摘要算法的实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes()); // 将字符串转换为字节数组并更新到摘要中

            // 完成哈希计算并返回字节数组
            byte[] digest = md.digest();

            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff)); // 保证结果是两位十六进制
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 加密出错", e);
        }
    }

}
