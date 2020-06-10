package com.hml.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.constant.SysConstants;
import com.hml.admin.entity.Menu;
import com.hml.admin.mapper.MenuMapper;
import com.hml.admin.service.IMenuService;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Object delete(List<Menu> records) {
		List<Long> ids = new ArrayList<>();
		for(Menu menu:records){
			ids.add(menu.getId());
		}
		return menuMapper.deleteBatchIds(ids);
	}
	
	@Override
	public List<Menu> findTree(String userName, int menuType) {
		List<Menu> sysMenus = new ArrayList<>();
		List<Menu> menus = findByUser(userName);
		for (Menu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType);
		return sysMenus;
	}
	
	@Override
	public List<Menu> findByUser(String userName) {
		if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
			return menuMapper.selectList(null);
		}
		return menuMapper.selectMenuByUser(userName);
	}

	
	private boolean exists(List<Menu> menus, Menu sysMenu) {
		boolean exist = false;
		for(Menu menu:menus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
	private void findChildren(List<Menu> sysMenus, List<Menu> menus, int menuType) {
		for (Menu sysMenu : sysMenus) {
			List<Menu> children = new ArrayList<>();
			for (Menu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (sysMenu.getId() != null && sysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(sysMenu.getName());
					menu.setLevel(sysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			sysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}
}
