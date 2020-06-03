package com.hml.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hml.admin.entity.Menu;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
public interface MenuMapper extends BaseMapper<Menu> {
	List<Menu> selectMenuByUser(String userName);
}
