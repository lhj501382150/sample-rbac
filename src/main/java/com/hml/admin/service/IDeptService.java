package com.hml.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.Dept;
import com.hml.core.page.PageRequest;

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
	List<Dept> findTree(PageRequest pageReuest);
	/**
	 * 删除机构 及 下属
	 * @param records
	 * @return
	 */
	Integer delete(List<Dept> records);
}
