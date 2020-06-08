package com.hml.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;
    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 机构ID
     */
    private Long deptId;

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
    private String deptName;
    
    @TableField(exist=false)
    private String roleNames;
    
    @TableField(exist=false)
    private List<UserRole> userRoles = new ArrayList<>();

}
