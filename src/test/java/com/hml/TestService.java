package com.hml;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hml.admin.service.IUserService;
import com.hml.core.page.PageRequest;
import com.hml.core.page.PageResult;

@SpringBootTest(classes=ManagerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestService {
	
	@Autowired
	private IUserService userService;
	
	@Test
	public void testUserPerms(){
		Set<String> sets = userService.findPermissions("liubei");
		System.out.println(sets);
	}
	
	@Test
	public void testUserAll(){
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNum(1);
		pageRequest.setPageSize(10);
		PageResult pr = userService.findPage(pageRequest);
		pr.getContent().forEach(
				(item)->{
					System.out.println(item);
				}
		);
	}
}
