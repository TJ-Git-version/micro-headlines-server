package com.surfer.service.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:38
 * description 新闻分类修改-实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsClassifyUpdateReq {

    // 修改分类id
    private Integer id;

    // 名称或标题
    private String name;

    // 备注信息
    private String remark;

}
