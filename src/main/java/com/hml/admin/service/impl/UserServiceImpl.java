package com.hml.admin.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.constant.SysConstants;
import com.hml.admin.entity.Menu;
import com.hml.admin.entity.Role;
import com.hml.admin.entity.User;
import com.hml.admin.entity.UserRole;
import com.hml.admin.mapper.MenuMapper;
import com.hml.admin.mapper.RoleMapper;
import com.hml.admin.mapper.UserMapper;
import com.hml.admin.mapper.UserRoleMapper;
import com.hml.admin.service.IUserService;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;
import com.hml.utils.StringUtils;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	 @Override
	 @Transactional
	public boolean saveOrUpdate(User user) {
		 boolean flag = true;
		 flag = super.saveOrUpdate(user);
//		 处理角色信息
		 if(flag && user.getId()!=null){
			 List<UserRole> userRoles = user.getUserRoles();
			 QueryWrapper<UserRole> qw = new QueryWrapper<>();
			 qw.eq("user_id",user.getId());
			 userRoleMapper.delete(qw);
			 for(UserRole userRole:userRoles){
				 userRoleMapper.insert(userRole);
			 }
		 }
		 return flag;
	}
	
	@Override
	public User findByName(String username) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("name", username);
		User user = userMapper.selectOne(qw);
		return user;
	}

	@Override
	public Set<String> findPermissions(String userName) {
		 Set<String> perms = new HashSet<>();
		 List<Menu> list = null ;
		 if(SysConstants.ADMIN.equals(userName)){
			 list = menuMapper.selectList(null);
		 }else{
			 list = menuMapper.selectMenuByUser(userName);
		 }
		 for(Menu menu:list){
			 String perm = menu.getPerms();
			 if(!StringUtils.isBlank(perm)){
				 perms.add(perm);
			 }
		 }
		return perms;
	}

	@Override
	public File createUserExcelFile(PageRequest pageRequest) {
		 
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		PageResult pr =  MybatisPlusPageHelper.findPage(pageRequest, userMapper,"findPage");
//		加载用户角色信息
		findUserRoles(pr);
		return pr;
	}
	
	/**
	 * 加载用户角色
	 * @param pageResult
	 */
	private void findUserRoles(PageResult pageResult) {
		List<User> content = (List<User>)pageResult.getContent();
		for(User user:content) {
			List<UserRole> userRoles = findUserRoles(user.getId());
			user.setUserRoles(userRoles);
			user.setRoleNames(getRoleNames(userRoles));
		}
	}

	private String getRoleNames(List<UserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<UserRole> iter=userRoles.iterator(); iter.hasNext();) {
			UserRole userRole = iter.next();
			Role role = roleMapper.selectById(userRole.getRoleId());
			if(role == null) {
				continue ;
			}
			sb.append(role.getRemark());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Object delete(List<User> list) {
		List<Long> idList = new ArrayList<>();
		for(User user:list){
			idList.add(user.getId());
		}
		int num = userMapper.deleteBatchIds(idList);
		return num;
	}

	@Override
	public List<UserRole> findUserRoles(Long userId) {
		QueryWrapper<UserRole> qw = new QueryWrapper<>();
		qw.eq("user_id", userId);
		List<UserRole> list = userRoleMapper.selectList(qw);
		return list;
	}
	
}
