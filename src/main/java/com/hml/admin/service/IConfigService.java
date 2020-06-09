package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Config;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author hml
 * @since 2020-06-09
 */
public interface IConfigService extends IService<Config> {
	int delete(List<Config> records);
	
	PageResult findPage(PageRequest pageRequest);
	
	List<Config> findByLable(String lable);
	
	
}
