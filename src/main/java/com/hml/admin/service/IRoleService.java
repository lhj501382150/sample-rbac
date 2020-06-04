package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Menu;
import com.hml.admin.entity.Role;
import com.hml.admin.entity.RoleMenu;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author hml
 * @since 2020-06-04
 */
public interface IRoleService extends IService<Role> {

	PageResult findPage(PageRequest pageRequest);
	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<Menu> findRoleMenus(Long roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<RoleMenu> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<Role> findByName(String name);
}
