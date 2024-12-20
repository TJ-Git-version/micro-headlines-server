package com.surfer.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:12
 * description 新闻信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsEntity implements Serializable {

    // 唯一标识符ID
    private Integer id;

    // 名称或标题
    private String name;

    // 内容详情
    private String content;

    // 内容摘要
    private String summary;

    // 图片URL集合，通常以逗号分隔
    private String images;

    // 浏览次数
    private Integer views;

    // 点赞数量
    private Integer likes;

    // 评论数量
    private Integer comments;

    // 状态，默认草稿:0 审核中:1 已发布:2
    private Integer status;

    // 是否置顶，标记内容是否固定在顶端 默认 false 不置顶,true 置顶
    private Boolean isPinned;

    // 是否为原创内容 默认不是原创:false, true:原创
    private Boolean isOriginal;

    // 编辑人员的ID
    private Integer editorID;

    // 审核时间
    private Long reviewTime;

    // 发布时间
    private Long publishTime;

    // 最近更新时间
    private Long updateTime;

    // 是否被删除，标记内容是否逻辑删除, 默认为 true 不删除, false 已删除
    private Boolean isDeleted;

}
