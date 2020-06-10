package com.hml.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hml.admin.entity.Log;
import com.hml.admin.service.ILogService;
import com.hml.core.http.HttpResult;
import com.hml.core.page.PageRequest;

/**
 * <p>
 * 系统操作日志 前端控制器
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

	@Autowired
	private ILogService logService;

	@PreAuthorize("hasAuthority('sys:log:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		pageRequest.getParams().put("id@desc", "A");
		return HttpResult.ok(logService.findPage(pageRequest));
	}
	
	@PreAuthorize("hasAuthority('sys:log:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Log> records) {
		return HttpResult.ok(logService.delete(records));
	}
}

