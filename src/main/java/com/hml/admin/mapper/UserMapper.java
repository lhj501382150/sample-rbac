package com.hml.admin.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hml.admin.entity.User;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
public interface UserMapper extends BaseMapper<User> {
	List<Map<String,Object>> selectUserRoles(Long id);
}
