package com.hml.admin.service.impl;

import com.hml.admin.entity.Config;
import com.hml.admin.mapper.ConfigMapper;
import com.hml.admin.service.IConfigService;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

	@Autowired
	private ConfigMapper configMapper;
	
	@Override
	@Transactional
	public int delete(List<Config> records) {
		for(Config config : records){
			configMapper.deleteById(config.getId());
		}
		return 0;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		return MybatisPlusPageHelper.findPage(pageRequest, configMapper);
	}

	@Override
	public List<Config> findByLable(String lable) {
		
		QueryWrapper<Config> qw = new QueryWrapper<>();
		qw.like("label", lable);
		List<Config> list = configMapper.selectList(qw);
		return list;
	}

	
}
