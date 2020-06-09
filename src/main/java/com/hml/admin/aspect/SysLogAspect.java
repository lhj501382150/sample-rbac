package com.hml.admin.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hml.admin.entity.Log;
import com.hml.admin.service.ILogService;
import com.hml.admin.util.HttpUtils;
import com.hml.admin.util.IPUtils;
import com.hml.admin.util.SecurityUtils;

/*
 * 操作日志处理
 */
@Aspect
@Component
public class SysLogAspect {

	@Autowired
	private ILogService logService;
	
	@Pointcut("execution(* com.hml.admin.controller.*.*(..))")
	private void logPointCut(){
		
	}
	
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable{
		long beginTime = System.currentTimeMillis();
//		执行方法
		Object result = point.proceed();
		
		long time = System.currentTimeMillis() - beginTime;
		
		saveSysLog(point, time);
		
		return result;
	}
	
	private void saveSysLog(ProceedingJoinPoint joinPoint,long time){
		
		if(joinPoint.getTarget() instanceof ILogService){
			return;
		}
		String userName = SecurityUtils.getUsername();
		MethodSignature method = (MethodSignature)joinPoint.getSignature();
		Log log = new Log();
		
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = method.getName();
		
		log.setMethod(className + "."+methodName +"()"); 
		Object[] args = joinPoint.getArgs();
		try {
			String param = JSONObject.toJSONString(args);
			if(param.length()>200){
				param = param.substring(0,200)+"...";
			}
			log.setParams(param);
		} catch (Exception e) {
			
		}
		// 获取request
		HttpServletRequest request = HttpUtils.getHttpServletRequest();
		// 设置IP地址
		log.setIp(IPUtils.getIpAddr(request));

		// 用户名
		log.setUserName(userName);
		
		// 执行时长(毫秒)
		log.setTime(time);
		
		// 保存系统日志
		logService.save(log);
	}
}
