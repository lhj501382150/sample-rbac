package com.hml.admin.service.impl;

import com.hml.admin.entity.Log;
import com.hml.admin.mapper.LogMapper;
import com.hml.admin.service.ILogService;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
	
	@Autowired
	private LogMapper logMapper;
	
	@Override
	@Transactional
	public int delete(List<Log> records) {
		for(Log log:records){
			logMapper.deleteById(log.getId());
		}
		return 1;
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		 
		return MybatisPlusPageHelper.findPage(pageRequest, logMapper);
	}
	
}
