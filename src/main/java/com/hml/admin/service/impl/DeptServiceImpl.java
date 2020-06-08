package com.hml.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Dept;
import com.hml.admin.mapper.DeptMapper;
import com.hml.admin.service.IDeptService;
import com.hml.core.page.MybatisPlusPageHelper;
import com.hml.core.page.PageRequest;
import com.hml.utils.StringUtils;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author lhj
 * @since 2020-06-08
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
	
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	@Transactional
	public List<Dept> findTree(PageRequest pageReuest) {
		boolean flag = false;
		if(pageReuest.getParams()!=null){
			Object obj = pageReuest.getParams().get("name");
			flag = obj !=null &&  !StringUtils.isBlank(obj.toString());
		}
		List<Dept> sysDepts = new ArrayList<>();
		List<Dept> depts = deptMapper.selectList(null);
		if(flag){
			QueryWrapper<Dept> qw = (QueryWrapper<Dept>) MybatisPlusPageHelper.getQueryWrapper(pageReuest);
			List<Dept> parent = deptMapper.selectList(qw);
			for(Dept dept:parent){
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}else{
			for (Dept dept : depts) {
				if (dept.getParentId() == null || dept.getParentId() == 0) {
					dept.setLevel(0);
					sysDepts.add(dept);
				}
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}
	
	
	private void findChildren(List<Dept> sysDepts, List<Dept> depts) {
		for (Dept sysDept : sysDepts) {
			List<Dept> children = new ArrayList<>();
			for (Dept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(sysDept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}
	
	@Override
	@Transactional
	public Integer delete(List<Dept> records) {
		 for(Dept dept: records){
			 int ret = deptMapper.deleteById(dept.getId());
		 }
		return 1;
	}
}
