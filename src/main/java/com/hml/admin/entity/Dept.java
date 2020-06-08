package com.hml.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 机构管理
 * </p>
 *
 * @author lhj
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 上级机构ID，一级机构为0
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer orderNum;

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

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Integer delFlag;
    
    @TableField(exist=false)
    private List<Dept> children;
    
    @TableField(exist=false)
    private String parentName;
    
    @TableField(exist=false)
    private Integer level;


}
