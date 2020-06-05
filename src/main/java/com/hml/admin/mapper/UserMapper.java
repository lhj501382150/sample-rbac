package com.hml.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hml.admin.entity.User;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
public interface UserMapper extends BaseMapper<User> {
	List<Map<String,Object>> selectUserRoles(Long id);
	/*SELECT t1.*,t2.name as deptName,t4.name as roleName FROM sys_user t1
	left join sys_dept t2 on t1.dept_id = t2.id
	left join sys_role_dept t3 on t2.id = t3.dept_id
	left join sys_role t4 on t3.role_id = t4.id*/
	@Select("select t1.*,t2.name as deptName,t4.name as roleName from sys_user t1 left join sys_dept t2 on t1.dept_id = t2.id left join sys_role_dept t3 on t2.id = t3.dept_id left join sys_role t4 on t3.role_id = t4.id ${ew.customSqlSegment}")
	Page<User> findPage(Page<User> page,@Param(Constants.WRAPPER)Wrapper wrapper);
	
}
