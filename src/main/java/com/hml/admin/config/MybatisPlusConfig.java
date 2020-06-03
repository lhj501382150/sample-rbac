package com.hml.admin.config;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

/**
 * Mybatis配置
 * @author hml
 * @date Jan 11, 2019
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.hml.admin.mapper.*")    // 扫描DAO
public class MybatisPlusConfig {
  
	@Bean
	public PaginationInterceptor getPaginationInterceptor(){
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		/*
		 * 限制设置最大页操作后 处置
		 * paginationInterceptor.setOverflow(false);
		 * 
		 * 设置单页最大条数
			paginationInterceptor.setLimit(500);
		
		*/
		paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
		
		return paginationInterceptor;
	}
}