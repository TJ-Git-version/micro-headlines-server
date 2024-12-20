package com.surfer.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:06
 * description 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsUserEntity implements Serializable {

    // 用户唯一标识符
    private Integer id;

    // 用户名，用于登录或唯一标识用户
    private String username;

    // 昵称，用户的展示名称
    private String nickname;

    // 用户密码，存储加密后的值
    private String password;

    // 密码盐值，用于密码加密
    private String passwordSalt;

    // 用户的邮箱地址
    private String email;

    // 用户的手机号码
    private String phoneNumber;

    // 用户头像的URL
    private String avatar;

    // 用户账户状态，例如激活:1, 禁用0
    private Boolean status;

    // 用户注册日期
    private Long registrationDate;

    // 用户最近一次登录的时间
    private Long lastLoginTime;

    // 用户累计登录次数
    private Long loginCount;

    // 用户账户锁定时间
    private Long accountLockTim;

}