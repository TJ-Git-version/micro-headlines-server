package com.surfer.service.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:38
 * description 新闻分类新增-实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsClassifyInsertReq {

    // 名称或标题
    private String name;

    // 备注信息
    private String remark;

}
