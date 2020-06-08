package com.hml.admin.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseModel {
	 /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    private LocalDateTime lastUpdateTime;

}
