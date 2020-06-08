package com.hml.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hml.admin.entity.Dept;
import com.hml.admin.service.IDeptService;
import com.hml.core.http.HttpResult;

/**
 * <p>
 * 机构管理 前端控制器
 * </p>
 *
 * @author lhj
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

	@Autowired
	private IDeptService deptService;
	
	@PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Dept record) {
		return HttpResult.ok(deptService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody Dept records) {
		return HttpResult.ok(deptService.removeById(records.getId()));
	}

	@PreAuthorize("hasAuthority('sys:dept:view')")
	@GetMapping(value="/findTree")
	public HttpResult findTree() {
		return HttpResult.ok(deptService.findTree());
	}
}

