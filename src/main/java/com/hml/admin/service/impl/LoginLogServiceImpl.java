package com.hml.admin.service.impl;

import com.hml.admin.entity.LoginLog;
import com.hml.admin.mapper.LoginLogMapper;
import com.hml.admin.service.ILoginLogService;
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
 * 系统登录日志 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

	@Autowired
	private LoginLogMapper loginLogMapper;
	
	@Override
	@Transactional
	public int delete(List<LoginLog> records) {
		for(LoginLog loginLog :records){
			loginLogMapper.deleteById(loginLog.getId());
		}
		return 1;
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		return MybatisPlusPageHelper.findPage(pageRequest, loginLogMapper);
	}
}
