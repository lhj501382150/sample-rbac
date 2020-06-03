package com.hml;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hml.admin.service.IUserService;

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
}
