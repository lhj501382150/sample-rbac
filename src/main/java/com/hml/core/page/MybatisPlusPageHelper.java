package com.hml.core.page;

import java.util.Map;
import java.util.Map.Entry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hml.utils.ReflectionUtils;
import com.hml.utils.StringUtils;

/**
 * MyBatis 分页查询助手
 * @author hml
 * @date Jan 12, 2019
 */
public class MybatisPlusPageHelper {

	public static final String findPage = "selectPage";
	
	/**
	 * 分页查询, 约定查询方法名为 “findPage” 
	 * @param pageRequest 分页请求
	 * @param mapper Dao对象，MyBatis的 Mapper	
	 * @param args 方法参数
	 * @return
	 */
	public static PageResult findPage(PageRequest pageRequest, Object mapper) {
		return findPage(pageRequest, mapper, findPage);
	}
	
	/**
	 * 调用分页插件进行分页查询
	 * @param pageRequest 分页请求
	 * @param mapper Dao对象，MyBatis的 Mapper	
	 * @param queryMethodName 要分页的查询方法名
	 * @param args 方法参数
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName) {
		// 设置分页参数
		Page<?> page = getPage(pageRequest);
		QueryWrapper<?> qw = getQueryWrapper(pageRequest);
		Object[] args = new Object[]{page,qw};
		// 利用反射调用查询方法
		IPage result = (IPage)ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, result);
	}

	/**
	 * 将分页信息封装到统一的接口
	 * @param pageRequest 
	 * @param page
	 * @return
	 */
	private static PageResult getPageResult(PageRequest pageRequest, IPage<?> pageInfo) {
		PageResult pageResult = new PageResult();
        pageResult.setPageNum(formatInt(pageInfo.getCurrent()));
        pageResult.setPageSize(formatInt(pageInfo.getSize()));
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(formatInt(pageInfo.getPages()));
        pageResult.setContent(pageInfo.getRecords());
		return pageResult;
	}
	
	private static int formatInt(Object val){
		return Integer.parseInt(String.valueOf(val));
	}
	
	public static Page<?> getPage(PageRequest pageRequest){
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		
		Page<?> page = new Page<>(pageNum,pageSize);
		return page;
	}
	
	public static QueryWrapper<?> getQueryWrapper(PageRequest pageRequest){
		QueryWrapper<?> qw = new QueryWrapper<>();
		Map<String, Object> params = pageRequest.getParams();
		for(Entry entry:params.entrySet()){
			if (entry == null) continue;
			String key = entry.getKey().toString();
			if(StringUtils.isBlank(key)) continue;
			Object val = entry.getValue();
			if(val==null || StringUtils.isBlank(val.toString())) continue;
			if(key.contains("@")){
				String[] paras = key.split("@");
				String temp = paras[1].toString().toUpperCase();
				if("EQ".equals(temp)){
					qw.eq(paras[0], val);
				}else if("GE".equals(temp)){//>=
					qw.ge(paras[0], val);
				}else if("LE".equals(temp)){//<=
					qw.le(paras[0], val);
				}else if("LIKE".equals(temp)){
					qw.like(paras[0], val);
				}else if("ASC".equals(temp)){
					qw.orderByAsc(paras[0]);
				}else if("DESC".equals(temp)){
					qw.orderByDesc(paras[0]);
				}if("EQ".equals(temp)){
					qw.eq(paras[0], val);
				}
			}else{
				qw.eq(key, val);
			}
		}
		
		return qw;
	}

}
