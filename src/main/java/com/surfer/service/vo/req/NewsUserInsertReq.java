package com.surfer.service.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:26
 * description 用户新增-实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUserInsertReq {

    // 用户名，用于登录或唯一标识用户
    private String username;

    // 昵称，用户的展示名称
    private String nickname;

    // 用户密码，存储加密后的值
    private String password;

    // 用户的邮箱地址
    private String email;

    // 用户的手机号码
    private String phoneNumber;

    // 用户头像的URL
    private String avatar;

}
