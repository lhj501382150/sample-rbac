package com.hml.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hml.admin.constant.SysConstants;
import com.hml.admin.entity.Role;
import com.hml.admin.entity.RoleMenu;
import com.hml.admin.service.IRoleService;
import com.hml.core.http.HttpResult;
import com.hml.core.page.PageRequest;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author hml
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private IRoleService roleService;
	
	@PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Role record) {
		Role role = roleService.getById(record.getId());
		if(role != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		// 新增角色
		if((record.getId() == null || record.getId() ==0) && !roleService.findByName(record.getName()).isEmpty()) {
			return HttpResult.error("角色名已存在!");
		}
		return HttpResult.ok(roleService.saveOrUpdate(record));
	}

	@PreAuthorize("hasAuthority('sys:role:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody Role record) {
		Role role = roleService.getById(record.getId());
		if(role.getName().equals(SysConstants.ADMIN)){
			return HttpResult.error("超级管理员不能删除!");
		}
		return HttpResult.ok(roleService.removeById(record.getId()));
	}

	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(roleService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findAll")
	public HttpResult findAll() {
		return HttpResult.ok(roleService.list());
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Long roleId) {
		return HttpResult.ok(roleService.findRoleMenus(roleId));
	}
	
	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/saveRoleMenus")
	public HttpResult saveRoleMenus(@RequestBody List<RoleMenu> records) {
		if(records !=null && records.size()>0){
			RoleMenu record = records.get(0);
			Role role = roleService.getById(record.getRoleId());
			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}else{
			return HttpResult.error("请输入正确参数!");
		}
		/*for(RoleMenu record:records) {
			Role role = roleService.getById(record.getRoleId());
			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}*/
		return HttpResult.ok(roleService.saveRoleMenus(records));
	}
}

