package com.hml.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Dict;
import com.hml.admin.mapper.DictMapper;
import com.hml.admin.service.IDictService;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	@Transactional
	public int delete(List<Dict> records) {
		for(Dict dict : records){
			dictMapper.deleteById(dict.getId());
		}
		return 0;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		return MybatisPlusPageHelper.findPage(pageRequest, dictMapper);
	}

	@Override
	public List<Dict> findByLable(String lable) {
		
		QueryWrapper<Dict> qw = new QueryWrapper<>();
		qw.like("label", lable);
		List<Dict> list = dictMapper.selectList(qw);
		return list;
	}
}
