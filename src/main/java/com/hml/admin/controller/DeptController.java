package com.hml.admin.controller;


import java.time.LocalDateTime;
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
import com.hml.admin.util.SecurityUtils;
import com.hml.core.http.HttpResult;
import com.hml.core.page.PageRequest;

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
		if(record.getId()==null || record.getId() == 0){
			record.setCreateBy(SecurityUtils.getUsername());
			record.setCreateTime(LocalDateTime.now());
		}else{
			record.setLastUpdateBy(SecurityUtils.getUsername());
			record.setLastUpdateTime(LocalDateTime.now());
		}
		return HttpResult.ok(deptService.saveOrUpdate(record));
	}

	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Dept> records) {
		return HttpResult.ok(deptService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:dept:view')")
	@PostMapping(value="/findTree")
	public HttpResult findTree(@RequestBody PageRequest pageReuest) {
		return HttpResult.ok(deptService.findTree(pageReuest));
	}
}

