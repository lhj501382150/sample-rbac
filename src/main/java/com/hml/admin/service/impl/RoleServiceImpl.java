package com.hml.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Menu;
import com.hml.admin.entity.Role;
import com.hml.admin.entity.RoleMenu;
import com.hml.admin.mapper.MenuMapper;
import com.hml.admin.mapper.RoleMapper;
import com.hml.admin.mapper.RoleMenuMapper;
import com.hml.admin.service.IRoleService;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	@Override
	public List<Menu> findRoleMenus(Long roleId) {
		List<Menu> list = menuMapper.findRoleMenus(roleId);
		return list;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveRoleMenus(List<RoleMenu> records) {
		if(records == null || records.isEmpty()) {
			return 1;
		}
		Long roleId = records.get(0).getRoleId(); 
		QueryWrapper<RoleMenu> qw = new QueryWrapper<>();
		qw.eq("roleid", roleId);
		roleMenuMapper.delete(qw);
		for(RoleMenu rm : records){
			roleMenuMapper.insert(rm);
		}
		return 0;
	}

	@Override
	public List<Role> findByName(String name) {
		QueryWrapper<Role> qw = new QueryWrapper<>();
		qw.eq("name", name);
		List<Role> list = roleMapper.selectList(qw);
		return list;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		return MybatisPlusPageHelper.findPage(pageRequest, roleMapper);
	}

}
