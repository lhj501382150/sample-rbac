package com.hml.admin.service;

import com.hml.admin.entity.Config;
import com.hml.admin.entity.Dict;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
public interface IDictService extends IService<Dict> {

	int delete(List<Dict> records);
	
	PageResult findPage(PageRequest pageRequest);
	
	List<Dict> findByLable(String lable);
}
