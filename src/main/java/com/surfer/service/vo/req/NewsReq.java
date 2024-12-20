package com.surfer.service.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:35
 * description 新闻查询条件-实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsReq {

    // 名称或标题
    private String name;

    // 内容详情
    private String content;

    // 内容摘要
    private String summary;

    // 状态，默认草稿:0 审核中:1 已发布:2
    private Integer status;

    // 浏览排序:升序 true | 降序:false
    private Boolean viewsSort;

    // 点赞数量:升序 true | 降序:false
    private Integer likesSort;

    // 评论数量:升序 true | 降序:false
    private Integer commentsSort;

    // 是否为原创内容 默认不是原创:false, true:原创
    private Boolean isOriginal;

}
