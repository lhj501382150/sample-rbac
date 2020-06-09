package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Log;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 * 系统操作日志 服务类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
public interface ILogService extends IService<Log> {

	PageResult findPage(PageRequest pageRequest);
	
	int delete(List<Log> records);
	
}
