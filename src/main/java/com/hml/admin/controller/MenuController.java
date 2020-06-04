package com.hml.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hml.admin.entity.Menu;
import com.hml.admin.service.IMenuService;
import com.hml.core.http.HttpResult;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private IMenuService menuService;
	
	@PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Menu record) {
		return HttpResult.ok(menuService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:menu:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody Menu record) {
		return HttpResult.ok(menuService.removeById(record.getId()));
	}

	@PreAuthorize("hasAuthority('sys:menu:view')")
	@GetMapping(value="/findNavTree")
	public HttpResult findNavTree(@RequestParam String userName) {
		return HttpResult.ok(menuService.findTree(userName, 1));
	}
	
	@PreAuthorize("hasAuthority('sys:menu:view')")
	@GetMapping(value="/findMenuTree")
	public HttpResult findMenuTree() {
		return HttpResult.ok(menuService.findTree(null, 0));
	}
}

