package com.surfer.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:20
 * description 新闻-分类-关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsClassifyRelationEntity implements Serializable {

    // 新闻id
    private Integer newsId;

    // 分类id
    private Integer classifyId;

}
