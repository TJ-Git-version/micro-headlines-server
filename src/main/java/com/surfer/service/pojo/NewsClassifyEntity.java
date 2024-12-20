package com.surfer.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:18
 * description 新闻分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsClassifyEntity implements Serializable {

    // 唯一标识符ID
    private Integer id;

    // 名称或标题
    private String name;

    // 备注信息
    private String remark;

    // 创建时间
    private Long createTime;

}
