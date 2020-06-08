package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Dept;

/**
 * <p>
 * 机构管理 服务类
 * </p>
 *
 * @author lhj
 * @since 2020-06-08
 */
public interface IDeptService extends IService<Dept> {
	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<Dept> findTree();
}
