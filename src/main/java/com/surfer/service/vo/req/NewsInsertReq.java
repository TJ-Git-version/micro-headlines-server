package com.surfer.service.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:30
 * description 新闻新增-实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsInsertReq {

    // 名称或标题
    private String name;

    // 内容详情
    private String content;

    // 内容摘要
    private String summary;

    // 图片URL集合，通常以逗号分隔
    private String images;

    // 是否为原创内容 默认不是原创:false, true:原创
    private Boolean isOriginal;

}
