package com.hml.admin.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hml.admin.entity.User;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
public interface IUserService extends IService<User> {
	User findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 生成用户信息Excel文件
	 * @param pageRequest 要导出的分页查询参数
	 * @return
	 */
	File createUserExcelFile(PageRequest pageRequest);
	
	PageResult findPage(PageRequest pageRequest);
	
	Object delete(List<User> list);
	
	List<Map<String,Object>> findUserRoles(Long userId);
}
